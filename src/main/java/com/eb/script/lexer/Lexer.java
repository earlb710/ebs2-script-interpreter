package com.eb.script.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lexer (Lexical Analyzer) for the EBS2 scripting language.
 * 
 * The lexer scans source code and converts it into a sequence of tokens.
 * 
 * Features:
 * - Case-insensitive keywords and identifiers (as per EBS2 spec)
 * - Case-sensitive string literals
 * - Single-line comments (//)
 * - Number literals (integers and decimals)
 * - String literals with escape sequences
 * - Multi-character operators (++, --, ==, !=, <=, >=, .., =>)
 * - Natural language operators ("is equal to", etc.)
 * 
 * @version 2.0.0
 * @since 2025-12-29
 */
public class Lexer {
    private final String source;
    private final List<Token> tokens;
    private final List<LexerException> errors;
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private int column = 1;
    private int tokenStartColumn = 1;
    private boolean hadError = false;
    
    // Keywords map (case-insensitive)
    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();
    
    static {
        // Basic keywords (beginner level)
        KEYWORDS.put("program", TokenType.PROGRAM);
        KEYWORDS.put("end", TokenType.END);
        KEYWORDS.put("var", TokenType.VAR);
        KEYWORDS.put("variable", TokenType.VARIABLE);
        KEYWORDS.put("as", TokenType.AS);
        KEYWORDS.put("is", TokenType.IS);
        KEYWORDS.put("if", TokenType.IF);
        KEYWORDS.put("then", TokenType.THEN);
        KEYWORDS.put("else", TokenType.ELSE);
        KEYWORDS.put("repeat", TokenType.REPEAT);
        KEYWORDS.put("times", TokenType.TIMES);
        KEYWORDS.put("for", TokenType.FOR);
        KEYWORDS.put("each", TokenType.EACH);
        KEYWORDS.put("in", TokenType.IN);
        KEYWORDS.put("to", TokenType.TO);
        KEYWORDS.put("loop", TokenType.LOOP);
        KEYWORDS.put("call", TokenType.CALL);
        KEYWORDS.put("with", TokenType.WITH);
        KEYWORDS.put("and", TokenType.AND);
        KEYWORDS.put("return", TokenType.RETURN);
        KEYWORDS.put("print", TokenType.PRINT);
        KEYWORDS.put("log", TokenType.LOG);
        KEYWORDS.put("hide", TokenType.HIDE);
        KEYWORDS.put("ask", TokenType.ASK);
        KEYWORDS.put("screen", TokenType.SCREEN);
        KEYWORDS.put("button", TokenType.BUTTON);
        KEYWORDS.put("label", TokenType.LABEL);
        KEYWORDS.put("textbox", TokenType.TEXTBOX);
        KEYWORDS.put("array", TokenType.ARRAY);
        KEYWORDS.put("text", TokenType.TEXT_TYPE);
        KEYWORDS.put("number", TokenType.NUMBER_TYPE);
        KEYWORDS.put("flag", TokenType.FLAG);
        KEYWORDS.put("true", TokenType.TRUE);
        KEYWORDS.put("false", TokenType.FALSE);
        KEYWORDS.put("const", TokenType.CONST);
        KEYWORDS.put("constant", TokenType.CONSTANT);
        KEYWORDS.put("indicator", TokenType.INDICATOR);
        KEYWORDS.put("record", TokenType.RECORD);
        KEYWORDS.put("type", TokenType.TYPE);
        KEYWORDS.put("function", TokenType.FUNCTION);
        KEYWORDS.put("procedure", TokenType.PROCEDURE);
        
        // Advanced keywords
        KEYWORDS.put("try", TokenType.TRY);
        KEYWORDS.put("catch", TokenType.CATCH);
        KEYWORDS.put("throw", TokenType.THROW);
        KEYWORDS.put("import", TokenType.IMPORT);
        KEYWORDS.put("from", TokenType.FROM);
        KEYWORDS.put("export", TokenType.EXPORT);
        KEYWORDS.put("map", TokenType.MAP);
        KEYWORDS.put("json", TokenType.JSON);
        KEYWORDS.put("while", TokenType.WHILE);
        KEYWORDS.put("until", TokenType.UNTIL);
        KEYWORDS.put("break", TokenType.BREAK);
        KEYWORDS.put("continue", TokenType.CONTINUE);
        KEYWORDS.put("switch", TokenType.SWITCH);
        KEYWORDS.put("case", TokenType.CASE);
        KEYWORDS.put("default", TokenType.DEFAULT);
        KEYWORDS.put("let", TokenType.LET);
        KEYWORDS.put("async", TokenType.ASYNC);
        KEYWORDS.put("await", TokenType.AWAIT);
        KEYWORDS.put("class", TokenType.CLASS);
        KEYWORDS.put("extends", TokenType.EXTENDS);
        KEYWORDS.put("new", TokenType.NEW);
        KEYWORDS.put("do", TokenType.DO);
        KEYWORDS.put("down", TokenType.DOWN);
        KEYWORDS.put("step", TokenType.STEP);
        KEYWORDS.put("by", TokenType.BY);
        KEYWORDS.put("typeof", TokenType.TYPEOF);
        KEYWORDS.put("date", TokenType.DATE);
        KEYWORDS.put("or", TokenType.OR);
        KEYWORDS.put("not", TokenType.NOT);
        KEYWORDS.put("mod", TokenType.MOD);
    }
    
    // Built-in functions (case-insensitive, for syntax highlighting)
    private static final String[] BUILTIN_FUNCTIONS = {
        // Type conversion
        "toText", "toNumber", "toInt", "toFlag", "toDate",
        
        // Text operations
        "toUpper", "toLower", "toUpperCase", "toLowerCase",
        "trim", "trimLeft", "trimRight", "trimStart", "trimEnd",
        "substring", "substr", "head", "tail", "lead",
        "split", "join", "replace", "replaceFirst", "replaceAll", "replaceLast",
        "find", "findFirst", "findLast", "contains", "indexOf", "lastIndexOf",
        "startsWith", "endsWith", "charAt",
        "padLeft", "padRight", "padCenter",
        "reverse",
        
        // Array operations
        "append", "add", "addFirst", "remove", "removeAt",
        "sort", "reverseSort", "reverse",
        "copy", "slice", "fill", "expand", "shrink",
        "first", "last", "isEmpty",
        "findFirst", "findLast", "contains",
        
        // Record operations
        "fields", "hasField", "getField", "setField",
        "addField", "removeField", "merge", "copy",
        "toJSON", "fromJSON",
        
        // Math functions
        "abs", "ceil", "floor", "round", "sqrt", "pow",
        "min", "max", "random",
        "sin", "cos", "tan", "asin", "acos", "atan", "atan2",
        "exp", "log", "log10",
        
        // Date operations
        "today", "now", "year", "month", "day", 
        "hour", "minute", "second",
        "addDays", "addMonths", "addYears",
        "diffDays", "diffMonths", "diffYears",
        
        // Console/UI
        "print", "println", "log", "clear",
        "ask", "askNumber", "askYesNo", "askChoice"
    };
    
    /**
     * Constructs a new Lexer with the given source code.
     * 
     * @param source The EBS2 source code to tokenize
     */
    public Lexer(String source) {
        this.source = source;
        this.tokens = new ArrayList<>();
        this.errors = new ArrayList<>();
    }
    
    /**
     * Scans the entire source code and returns a list of tokens.
     * Continues scanning even after errors to find all issues.
     * 
     * @return List of tokens
     */
    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            tokenStartColumn = column;
            try {
                scanToken();
            } catch (Exception e) {
                // Error recovery: log error and skip to next character
                reportError("Unexpected error: " + e.getMessage());
                advance(); // Skip problematic character
            }
        }
        
        tokens.add(new Token(TokenType.EOF, "", line, column, current, current));
        return tokens;
    }
    
    /**
     * Gets all lexer errors encountered during scanning.
     * 
     * @return List of lexer errors
     */
    public List<LexerException> getErrors() {
        return errors;
    }
    
    /**
     * Checks if any errors were encountered during scanning.
     * 
     * @return true if errors were found
     */
    public boolean hadError() {
        return hadError;
    }
    
    /**
     * Scans a single token from the current position.
     */
    private void scanToken() {
        char c = advance();
        
        switch (c) {
            case '(': addToken(TokenType.LPAREN); break;
            case ')': addToken(TokenType.RPAREN); break;
            case '{': addToken(TokenType.LBRACE); break;
            case '}': addToken(TokenType.RBRACE); break;
            case '[': addToken(TokenType.LBRACKET); break;
            case ']': addToken(TokenType.RBRACKET); break;
            case ',': addToken(TokenType.COMMA); break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case ':': addToken(TokenType.COLON); break;
            case '?': addToken(TokenType.QUESTION); break;
            
            // Dot or range (..)
            case '.':
                if (match('.')) {
                    addToken(TokenType.RANGE);
                } else {
                    addToken(TokenType.DOT);
                }
                break;
            
            // Plus or increment (++)
            case '+':
                if (match('+')) {
                    addToken(TokenType.INCREMENT);
                } else {
                    addToken(TokenType.PLUS);
                }
                break;
            
            // Minus or decrement (--)
            case '-':
                if (match('-')) {
                    addToken(TokenType.DECREMENT);
                } else {
                    addToken(TokenType.MINUS);
                }
                break;
            
            // Multiply
            case '*':
                addToken(TokenType.MULTIPLY);
                break;
            
            // Divide or comment
            case '/':
                if (match('/')) {
                    // Single-line comment - consume until end of line
                    while (peek() != '\n' && !isAtEnd()) {
                        advance();
                    }
                } else {
                    addToken(TokenType.DIVIDE);
                }
                break;
            
            // Equal or arrow (=, ==, =>)
            case '=':
                if (match('=')) {
                    addToken(TokenType.EQUAL);
                } else if (match('>')) {
                    addToken(TokenType.ARROW);
                } else {
                    addToken(TokenType.ASSIGN);
                }
                break;
            
            // Not equal (!=) or not (!)
            case '!':
                if (match('=')) {
                    addToken(TokenType.NOT_EQUAL);
                } else {
                    addToken(TokenType.NOT);
                }
                break;
            
            // Less than or less equal (< or <=) or not equal (<>)
            case '<':
                if (match('=')) {
                    addToken(TokenType.LESS_EQUAL);
                } else if (match('>')) {
                    addToken(TokenType.NOT_EQUAL);
                } else {
                    addToken(TokenType.LESS_THAN);
                }
                break;
            
            // Greater than or greater equal (> or >=)
            case '>':
                if (match('=')) {
                    addToken(TokenType.GREATER_EQUAL);
                } else {
                    addToken(TokenType.GREATER_THAN);
                }
                break;
            
            // OR operator (||)
            case '|':
                if (match('|')) {
                    addToken(TokenType.OR);
                } else {
                    addToken(TokenType.OR); // Single | is also OR
                }
                break;
            
            // AND operator (&&)
            case '&':
                if (match('&')) {
                    addToken(TokenType.AND);
                } else {
                    addToken(TokenType.AND); // Single & is also AND
                }
                break;
            
            // Whitespace
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace
                break;
            
            // Newline
            case '\n':
                line++;
                column = 0; // Will be incremented to 1
                break;
            
            // String literal
            case '"':
                scanString();
                break;
            
            // Single quote strings (alternative syntax)
            case '\'':
                scanString();
                break;
            
            default:
                if (isDigit(c)) {
                    scanNumber();
                } else if (isAlpha(c)) {
                    scanIdentifier();
                } else {
                    // Error recovery: report but continue
                    reportError("Unexpected character: '" + c + "'");
                    addToken(TokenType.ILLEGAL);
                }
                break;
        }
    }
    
    /**
     * Scans a string literal.
     */
    private void scanString() {
        char quote = source.charAt(current - 1);
        StringBuilder value = new StringBuilder();
        
        while (peek() != quote && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
                column = 0;
            }
            
            // Handle escape sequences
            if (peek() == '\\') {
                advance();
                if (!isAtEnd()) {
                    char escaped = advance();
                    switch (escaped) {
                        case 'n': value.append('\n'); break;
                        case 't': value.append('\t'); break;
                        case 'r': value.append('\r'); break;
                        case '\\': value.append('\\'); break;
                        case '"': value.append('"'); break;
                        case '\'': value.append('\''); break;
                        default: value.append(escaped); break;
                    }
                }
            } else {
                value.append(advance());
            }
        }
        
        if (isAtEnd()) {
            // Unterminated string - report error but create token anyway for error recovery
            reportError("Unterminated string");
            addToken(TokenType.TEXT, value.toString());
            return;
        }
        
        // Consume closing quote
        advance();
        
        addToken(TokenType.TEXT, value.toString());
    }
    
    /**
     * Scans a number literal (integer or decimal).
     */
    private void scanNumber() {
        while (isDigit(peek())) {
            advance();
        }
        
        // Look for decimal part
        if (peek() == '.' && isDigit(peekNext())) {
            advance(); // Consume the '.'
            
            while (isDigit(peek())) {
                advance();
            }
        }
        
        String text = source.substring(start, current);
        try {
            if (text.contains(".")) {
                addToken(TokenType.NUMBER, Double.parseDouble(text));
            } else {
                addToken(TokenType.NUMBER, Integer.parseInt(text));
            }
        } catch (NumberFormatException e) {
            reportError("Invalid number format: " + text);
            addToken(TokenType.ILLEGAL);
        }
    }
    
    /**
     * Scans an identifier or keyword.
     */
    private void scanIdentifier() {
        while (isAlphaNumeric(peek())) {
            advance();
        }
        
        String text = source.substring(start, current);
        
        // Check for multi-word natural language operators
        if (text.equalsIgnoreCase("is")) {
            // Look ahead for natural language comparison operators
            if (tryMatchPhrase("equal to")) {
                addToken(TokenType.IS_EQUAL_TO);
                return;
            } else if (tryMatchPhrase("not equal to")) {
                addToken(TokenType.IS_NOT_EQUAL_TO);
                return;
            } else if (tryMatchPhrase("greater than or equal to")) {
                addToken(TokenType.IS_GREATER_EQUAL);
                return;
            } else if (tryMatchPhrase("less than or equal to")) {
                addToken(TokenType.IS_LESS_EQUAL);
                return;
            } else if (tryMatchPhrase("greater than")) {
                addToken(TokenType.IS_GREATER_THAN);
                return;
            } else if (tryMatchPhrase("less than")) {
                addToken(TokenType.IS_LESS_THAN);
                return;
            }
        }
        
        // Check if it's a keyword (case-insensitive)
        TokenType type = KEYWORDS.get(text.toLowerCase());
        if (type == null) {
            // Check if it's a built-in function (case-insensitive)
            if (isBuiltinFunction(text)) {
                type = TokenType.BUILTIN_FUNCTION;
            } else {
                type = TokenType.IDENTIFIER;
            }
        }
        
        // For TRUE and FALSE keywords, add boolean literal value
        if (type == TokenType.TRUE) {
            addToken(type, true);
        } else if (type == TokenType.FALSE) {
            addToken(type, false);
        } else {
            addToken(type);
        }
    }
    
    /**
     * Checks if the given identifier is a built-in function name.
     * 
     * @param name The identifier to check (case-insensitive)
     * @return true if it's a built-in function
     */
    private boolean isBuiltinFunction(String name) {
        for (String func : BUILTIN_FUNCTIONS) {
            if (func.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tries to match a phrase after the current position.
     * 
     * @param phrase The phrase to match
     * @return true if the phrase was matched and consumed
     */
    private boolean tryMatchPhrase(String phrase) {
        int savedCurrent = current;
        int savedColumn = column;
        
        // Skip whitespace
        while (peek() == ' ' || peek() == '\t') {
            advance();
        }
        
        String[] words = phrase.split(" ");
        for (String word : words) {
            // Skip whitespace between words
            while (peek() == ' ' || peek() == '\t') {
                advance();
            }
            
            // Try to match the word
            int wordStart = current;
            while (isAlpha(peek())) {
                advance();
            }
            
            String matched = source.substring(wordStart, current);
            if (!matched.equalsIgnoreCase(word)) {
                // Revert to saved position
                current = savedCurrent;
                column = savedColumn;
                return false;
            }
        }
        
        return true;
    }
    
    // Helper methods
    
    private boolean isAtEnd() {
        return current >= source.length();
    }
    
    private char advance() {
        char c = source.charAt(current);
        current++;
        column++;
        return c;
    }
    
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;
        
        current++;
        column++;
        return true;
    }
    
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }
    
    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }
    
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
               c == '_';
    }
    
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }
    
    private void addToken(TokenType type) {
        addToken(type, null);
    }
    
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line, tokenStartColumn, start, current));
    }
    
    /**
     * Reports a lexical error and stores it for later retrieval.
     * The lexer continues scanning to find more errors.
     * 
     * @param message The error message
     */
    private void reportError(String message) {
        hadError = true;
        LexerException error = new LexerException(message, line, tokenStartColumn);
        errors.add(error);
        // Optionally print to stderr for immediate feedback
        System.err.println(error.getMessage());
    }
    
    /**
     * Gets the list of tokens (after scanning).
     * 
     * @return The list of tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }
}
