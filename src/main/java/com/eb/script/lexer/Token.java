package com.eb.script.lexer;

/**
 * Token represents a lexical token in the EBS2 language.
 * 
 * A token consists of:
 * - type: The type of token (keyword, identifier, operator, etc.)
 * - lexeme: The actual text from the source code
 * - line: The line number where the token appears
 * - column: The column position where the token starts
 * 
 * @version 2.0.0
 * @since 2025-12-29
 */
public class Token {
    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    private final int line;
    private final int column;
    
    /**
     * Constructs a new Token.
     * 
     * @param type The type of the token
     * @param lexeme The text representation of the token
     * @param literal The literal value (for numbers, strings, booleans)
     * @param line The line number (1-based)
     * @param column The column position (1-based)
     */
    public Token(TokenType type, String lexeme, Object literal, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
        this.column = column;
    }
    
    /**
     * Constructs a new Token without a literal value.
     * 
     * @param type The type of the token
     * @param lexeme The text representation of the token
     * @param line The line number (1-based)
     * @param column The column position (1-based)
     */
    public Token(TokenType type, String lexeme, int line, int column) {
        this(type, lexeme, null, line, column);
    }
    
    // Getters
    
    public TokenType getType() {
        return type;
    }
    
    public String getLexeme() {
        return lexeme;
    }
    
    public Object getLiteral() {
        return literal;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
    
    /**
     * Checks if this token is of the specified type.
     * 
     * @param type The token type to check
     * @return true if the token matches the type
     */
    public boolean is(TokenType type) {
        return this.type == type;
    }
    
    /**
     * Checks if this token is any of the specified types.
     * 
     * @param types Variable number of token types to check
     * @return true if the token matches any of the types
     */
    public boolean isAny(TokenType... types) {
        for (TokenType t : types) {
            if (this.type == t) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if this token is a keyword.
     * 
     * @return true if the token is a keyword
     */
    public boolean isKeyword() {
        return type != TokenType.IDENTIFIER && 
               type != TokenType.NUMBER && 
               type != TokenType.TEXT &&
               type != TokenType.EOF &&
               type != TokenType.ILLEGAL &&
               type != TokenType.WHITESPACE &&
               type != TokenType.NEWLINE &&
               type != TokenType.COMMENT;
    }
    
    @Override
    public String toString() {
        if (literal != null) {
            return String.format("Token(%s, '%s', %s, %d:%d)", 
                type, lexeme, literal, line, column);
        }
        return String.format("Token(%s, '%s', %d:%d)", 
            type, lexeme, line, column);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Token token = (Token) obj;
        return line == token.line &&
               column == token.column &&
               type == token.type &&
               lexeme.equals(token.lexeme);
    }
    
    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + lexeme.hashCode();
        result = 31 * result + line;
        result = 31 * result + column;
        return result;
    }
}
