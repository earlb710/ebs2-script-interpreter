package com.eb.script.parser;

import com.eb.script.ast.*;
import com.eb.script.lexer.Token;
import com.eb.script.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parser for the EBS2 scripting language.
 * 
 * Converts a stream of tokens from the lexer into an Abstract Syntax Tree (AST).
 * Provides detailed error handling and recovery to report multiple errors at once.
 * 
 * Features:
 * - Recursive descent parsing
 * - Detailed error messages with line/column information
 * - Error recovery at synchronization points
 * - Support for basic statements (var, print, if, import)
 * - Expression parsing with operator precedence
 * - Parse caching for performance
 * - Import statement support with enforcement that imports come first
 * 
 * @version 1.1.0
 * @since 2026-01-01
 */
public class Parser {
    private final List<Token> tokens;
    private final List<ParseError> errors;
    private int current = 0;
    private boolean hasSeenNonImportStatement = false;
    
    // Static cache for parsed results (key: source code hash or identifier)
    private static final Map<String, ParseResult> parseCache = new ConcurrentHashMap<>();
    
    /**
     * Represents a cached parse result.
     */
    public static class ParseResult {
        private final List<Statement> statements;
        private final List<ParseError> errors;
        private final long timestamp;
        
        public ParseResult(List<Statement> statements, List<ParseError> errors) {
            this.statements = new ArrayList<>(statements);
            this.errors = new ArrayList<>(errors);
            this.timestamp = System.currentTimeMillis();
        }
        
        public List<Statement> getStatements() {
            return new ArrayList<>(statements);
        }
        
        public List<ParseError> getErrors() {
            return new ArrayList<>(errors);
        }
        
        public long getTimestamp() {
            return timestamp;
        }
    }
    
    /**
     * Constructs a new Parser with the given tokens.
     * 
     * @param tokens The list of tokens from the lexer
     */
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.errors = new ArrayList<>();
        this.hasSeenNonImportStatement = false;
    }
    
    /**
     * Parses the tokens into a list of statements with caching support.
     * 
     * @param cacheKey Optional cache key for caching the parse result (null to disable caching)
     * @return List of parsed statements
     */
    public List<Statement> parse(String cacheKey) {
        // Check cache if key provided
        if (cacheKey != null) {
            ParseResult cached = parseCache.get(cacheKey);
            if (cached != null) {
                // Return cached result
                this.errors.addAll(cached.getErrors());
                return cached.getStatements();
            }
        }
        
        // Parse the statements
        List<Statement> statements = parse();
        
        // Cache the result if key provided
        if (cacheKey != null) {
            parseCache.put(cacheKey, new ParseResult(statements, errors));
        }
        
        return statements;
    }
    
    /**
     * Parses the tokens into a list of statements.
     * 
     * @return List of parsed statements
     */
    public List<Statement> parse() {
        List<Statement> statements = new ArrayList<>();
        List<Statement> importStatements = new ArrayList<>();
        List<Statement> otherStatements = new ArrayList<>();
        
        while (!isAtEnd()) {
            try {
                Statement stmt = parseStatement();
                if (stmt != null) {
                    // Separate imports from other statements
                    if (stmt instanceof ImportStatement) {
                        importStatements.add(stmt);
                    } else {
                        otherStatements.add(stmt);
                    }
                }
            } catch (ParserException e) {
                // Error has been recorded, synchronize and continue
                synchronize();
            }
        }
        
        // Place imports first, then other statements
        statements.addAll(importStatements);
        statements.addAll(otherStatements);
        
        return statements;
    }
    
    /**
     * Clears the parse cache.
     */
    public static void clearCache() {
        parseCache.clear();
    }
    
    /**
     * Removes a specific entry from the parse cache.
     * 
     * @param cacheKey The cache key to remove
     */
    public static void removeCacheEntry(String cacheKey) {
        if (cacheKey != null) {
            parseCache.remove(cacheKey);
        }
    }
    
    /**
     * Gets the size of the parse cache.
     * 
     * @return The number of cached entries
     */
    public static int getCacheSize() {
        return parseCache.size();
    }
    
    /**
     * Gets all parse errors encountered during parsing.
     * 
     * @return List of parse errors
     */
    public List<ParseError> getErrors() {
        return errors;
    }
    
    /**
     * Checks if any errors were encountered during parsing.
     * 
     * @return true if errors were found
     */
    public boolean hadError() {
        return !errors.isEmpty();
    }
    
    // ===== Statement Parsing =====
    
    /**
     * Parses a single statement.
     * 
     * @return The parsed statement
     * @throws ParserException if a syntax error is encountered
     */
    private Statement parseStatement() throws ParserException {
        try {
            // Import statement - must come before other statements
            if (match(TokenType.IMPORT)) {
                if (hasSeenNonImportStatement) {
                    error(previous(), "Import statements must appear before all other statements");
                }
                return parseImportStatement();
            }
            
            // Mark that we've seen a non-import statement
            hasSeenNonImportStatement = true;
            
            // Variable declaration
            if (match(TokenType.VAR, TokenType.VARIABLE)) {
                return parseVarStatement();
            }
            
            // Print statement
            if (match(TokenType.PRINT)) {
                return parsePrintStatement();
            }
            
            // If statement
            if (match(TokenType.IF)) {
                return parseIfStatement();
            }
            
            // Expression statement
            return parseExpressionStatement();
            
        } catch (ParserException e) {
            recordError(e);
            throw e;
        }
    }
    
    /**
     * Parses an import statement.
     * 
     * Grammar: import "filename"
     * 
     * @return The parsed ImportStatement
     * @throws ParserException if a syntax error is encountered
     */
    private Statement parseImportStatement() throws ParserException {
        Token keyword = previous();
        
        // Expect string literal for filename
        if (!check(TokenType.TEXT)) {
            throw error(peek(), "Expected string literal for import filename");
        }
        
        Token filename = advance();
        
        // Validate that the literal is actually a string
        if (!(filename.getLiteral() instanceof String)) {
            throw error(filename, "Import filename must be a string literal");
        }
        
        return new ImportStatement(keyword, filename);
    }
    
    /**
     * Parses a variable declaration statement.
     * 
     * Grammar: var IDENTIFIER [as TYPE] [= EXPRESSION]
     * 
     * @return The parsed VarStatement
     * @throws ParserException if a syntax error is encountered
     */
    private Statement parseVarStatement() throws ParserException {
        Token keyword = previous();
        
        // Expect identifier
        Token name = consume(TokenType.IDENTIFIER, 
            "Expected variable name after '" + keyword.getLexeme() + "'");
        
        Token type = null;
        // Optional type annotation
        if (match(TokenType.AS)) {
            type = consumeType("Expected type name after 'as'");
        }
        
        Expression initializer = null;
        // Optional initializer
        if (match(TokenType.ASSIGN)) {
            try {
                initializer = parseExpression();
            } catch (ParserException e) {
                error(peek(), "Invalid expression in variable initializer");
                throw e;
            }
        }
        
        return new VarStatement(keyword, name, type, initializer);
    }
    
    /**
     * Parses a print statement.
     * 
     * Grammar: print EXPRESSION
     * 
     * @return The parsed PrintStatement
     * @throws ParserException if a syntax error is encountered
     */
    private Statement parsePrintStatement() throws ParserException {
        Token keyword = previous();
        
        try {
            Expression expr = parseExpression();
            return new PrintStatement(keyword, expr);
        } catch (ParserException e) {
            error(peek(), "Invalid expression in print statement");
            throw e;
        }
    }
    
    /**
     * Parses an if statement.
     * 
     * Grammar: if EXPRESSION then STATEMENT [else STATEMENT] [end if]
     * 
     * @return The parsed IfStatement
     * @throws ParserException if a syntax error is encountered
     */
    private Statement parseIfStatement() throws ParserException {
        Token keyword = previous();
        
        // Parse condition
        Expression condition;
        try {
            condition = parseExpression();
        } catch (ParserException e) {
            error(peek(), "Invalid condition in if statement");
            throw e;
        }
        
        // Expect 'then' keyword
        consume(TokenType.THEN, "Expected 'then' after if condition");
        
        // Parse then branch
        Statement thenBranch;
        try {
            thenBranch = parseStatement();
        } catch (ParserException e) {
            error(peek(), "Invalid statement in 'then' branch");
            throw e;
        }
        
        // Optional else branch
        Statement elseBranch = null;
        if (match(TokenType.ELSE)) {
            try {
                elseBranch = parseStatement();
            } catch (ParserException e) {
                error(peek(), "Invalid statement in 'else' branch");
                throw e;
            }
        }
        
        // Optional 'end if' for multi-line form
        Token endToken = null;
        if (check(TokenType.END)) {
            advance();
            if (match(TokenType.IF)) {
                endToken = previous();
            } else {
                // Back up - this END is for something else
                current--;
            }
        }
        
        return new IfStatement(keyword, condition, thenBranch, elseBranch, endToken);
    }
    
    /**
     * Parses an expression statement.
     * 
     * @return The parsed ExpressionStatement
     * @throws ParserException if a syntax error is encountered
     */
    private Statement parseExpressionStatement() throws ParserException {
        Expression expr = parseExpression();
        return new ExpressionStatement(expr);
    }
    
    // ===== Expression Parsing =====
    
    /**
     * Parses an expression.
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseExpression() throws ParserException {
        return parseOr();
    }
    
    /**
     * Parses a logical OR expression.
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseOr() throws ParserException {
        Expression expr = parseAnd();
        
        while (match(TokenType.OR)) {
            Token operator = previous();
            Expression right = parseAnd();
            expr = new BinaryExpression(expr, operator, right);
        }
        
        return expr;
    }
    
    /**
     * Parses a logical AND expression.
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseAnd() throws ParserException {
        Expression expr = parseEquality();
        
        while (match(TokenType.AND)) {
            Token operator = previous();
            Expression right = parseEquality();
            expr = new BinaryExpression(expr, operator, right);
        }
        
        return expr;
    }
    
    /**
     * Parses an equality expression (==, !=).
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseEquality() throws ParserException {
        Expression expr = parseComparison();
        
        while (match(TokenType.EQUAL, TokenType.NOT_EQUAL, 
                    TokenType.IS_EQUAL_TO, TokenType.IS_NOT_EQUAL_TO)) {
            Token operator = previous();
            Expression right = parseComparison();
            expr = new BinaryExpression(expr, operator, right);
        }
        
        return expr;
    }
    
    /**
     * Parses a comparison expression (<, >, <=, >=).
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseComparison() throws ParserException {
        Expression expr = parseTerm();
        
        while (match(TokenType.LESS_THAN, TokenType.GREATER_THAN,
                    TokenType.LESS_EQUAL, TokenType.GREATER_EQUAL,
                    TokenType.IS_LESS_THAN, TokenType.IS_GREATER_THAN,
                    TokenType.IS_LESS_EQUAL, TokenType.IS_GREATER_EQUAL)) {
            Token operator = previous();
            Expression right = parseTerm();
            expr = new BinaryExpression(expr, operator, right);
        }
        
        return expr;
    }
    
    /**
     * Parses an additive expression (+, -).
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseTerm() throws ParserException {
        Expression expr = parseFactor();
        
        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            Expression right = parseFactor();
            expr = new BinaryExpression(expr, operator, right);
        }
        
        return expr;
    }
    
    /**
     * Parses a multiplicative expression (*, /, mod).
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseFactor() throws ParserException {
        Expression expr = parseUnary();
        
        while (match(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.MOD)) {
            Token operator = previous();
            Expression right = parseUnary();
            expr = new BinaryExpression(expr, operator, right);
        }
        
        return expr;
    }
    
    /**
     * Parses a unary expression (-, not).
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parseUnary() throws ParserException {
        if (match(TokenType.MINUS, TokenType.NOT)) {
            Token operator = previous();
            Expression operand = parseUnary();
            return new UnaryExpression(operator, operand);
        }
        
        return parsePrimary();
    }
    
    /**
     * Parses a primary expression (literals, identifiers, grouping).
     * 
     * @return The parsed expression
     * @throws ParserException if a syntax error is encountered
     */
    private Expression parsePrimary() throws ParserException {
        // Number literal
        if (match(TokenType.NUMBER)) {
            Token token = previous();
            return new LiteralExpression(token, token.getLiteral());
        }
        
        // Text literal
        if (match(TokenType.TEXT)) {
            Token token = previous();
            return new LiteralExpression(token, token.getLiteral());
        }
        
        // Boolean literals
        if (match(TokenType.TRUE)) {
            return new LiteralExpression(previous(), true);
        }
        
        if (match(TokenType.FALSE)) {
            return new LiteralExpression(previous(), false);
        }
        
        // Variable reference
        if (match(TokenType.IDENTIFIER)) {
            return new VariableExpression(previous());
        }
        
        // Grouping expression
        if (match(TokenType.LPAREN)) {
            Token leftParen = previous();
            Expression expr = parseExpression();
            Token rightParen = consume(TokenType.RPAREN, 
                "Expected ')' after expression");
            return new GroupingExpression(leftParen, expr, rightParen);
        }
        
        // Unexpected token
        throw error(peek(), "Expected expression");
    }
    
    // ===== Helper Methods =====
    
    /**
     * Checks if the current token matches any of the given types.
     * If so, consumes it and returns true.
     * 
     * @param types The token types to match
     * @return true if a match was found
     */
    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the current token is of the given type.
     * 
     * @param type The token type to check
     * @return true if the current token matches
     */
    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().getTokenType() == type;
    }
    
    /**
     * Consumes the current token and returns it.
     * 
     * @return The consumed token
     */
    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }
    
    /**
     * Checks if we're at the end of the token stream.
     * 
     * @return true if at EOF
     */
    private boolean isAtEnd() {
        return peek().getTokenType() == TokenType.EOF;
    }
    
    /**
     * Returns the current token without consuming it.
     * 
     * @return The current token
     */
    private Token peek() {
        return tokens.get(current);
    }
    
    /**
     * Returns the previous token.
     * 
     * @return The previous token
     */
    private Token previous() {
        return tokens.get(current - 1);
    }
    
    /**
     * Consumes the current token if it matches the expected type.
     * Otherwise, throws a parser error.
     * 
     * @param type The expected token type
     * @param message The error message if not found
     * @return The consumed token
     * @throws ParserException if the token doesn't match
     */
    private Token consume(TokenType type, String message) throws ParserException {
        if (check(type)) return advance();
        throw error(peek(), message);
    }
    
    /**
     * Consumes a type token (type keywords or identifier).
     * 
     * @param message The error message if not found
     * @return The consumed type token
     * @throws ParserException if not a valid type
     */
    private Token consumeType(String message) throws ParserException {
        if (match(TokenType.TEXT_TYPE, TokenType.NUMBER_TYPE, TokenType.FLAG, 
                 TokenType.DATE, TokenType.ARRAY, TokenType.RECORD, TokenType.JSON)) {
            return previous();
        }
        
        // Also allow custom type names (identifiers)
        if (match(TokenType.IDENTIFIER)) {
            return previous();
        }
        
        throw error(peek(), message);
    }
    
    /**
     * Creates and records a parse error.
     * 
     * @param token The token where the error occurred
     * @param message The error message
     * @return The parser exception
     */
    private ParserException error(Token token, String message) {
        ParseError parseError = new ParseError(
            message, 
            token, 
            ParseError.ErrorType.UNEXPECTED_TOKEN
        );
        errors.add(parseError);
        
        // Print error immediately for feedback
        System.err.println(parseError.toString());
        
        return new ParserException(message, token);
    }
    
    /**
     * Records a parser exception as a parse error.
     * 
     * @param exception The parser exception
     */
    private void recordError(ParserException exception) {
        // Error already recorded in error() method
        // This is a no-op but kept for clarity
    }
    
    /**
     * Synchronizes the parser after an error.
     * 
     * Advances until we find a statement boundary (semicolon, newline, or keyword).
     */
    private void synchronize() {
        advance();
        
        while (!isAtEnd()) {
            // Semicolon marks end of statement
            if (previous().getTokenType() == TokenType.SEMICOLON) {
                return;
            }
            
            // These keywords typically start new statements
            switch (peek().getTokenType()) {
                case PROGRAM:
                case IMPORT:
                case VAR:
                case VARIABLE:
                case IF:
                case WHILE:
                case FOR:
                case REPEAT:
                case FUNCTION:
                case PROCEDURE:
                case RETURN:
                case PRINT:
                case END:
                    return;
            }
            
            advance();
        }
    }
}
