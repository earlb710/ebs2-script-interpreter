package com.eb.script.parser;

import com.eb.script.lexer.Token;

/**
 * ParserException is thrown when the parser encounters a syntax error.
 * 
 * This exception provides detailed information about the error location,
 * including line number, column, and the problematic token.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ParserException extends Exception {
    private final Token token;
    private final int line;
    private final int column;
    private final String context;
    
    /**
     * Constructs a ParserException with a message and token.
     * 
     * @param message The error message
     * @param token The token where the error occurred
     */
    public ParserException(String message, Token token) {
        super(formatMessage(message, token));
        this.token = token;
        this.line = token.getLine();
        this.column = token.getColumn();
        this.context = token.getLexeme();
    }
    
    /**
     * Constructs a ParserException with a message, token, and context.
     * 
     * @param message The error message
     * @param token The token where the error occurred
     * @param context Additional context information
     */
    public ParserException(String message, Token token, String context) {
        super(formatMessage(message, token, context));
        this.token = token;
        this.line = token.getLine();
        this.column = token.getColumn();
        this.context = context;
    }
    
    /**
     * Gets the token where the error occurred.
     * 
     * @return The error token
     */
    public Token getToken() {
        return token;
    }
    
    /**
     * Gets the line number where the error occurred.
     * 
     * @return The line number (1-based)
     */
    public int getLine() {
        return line;
    }
    
    /**
     * Gets the column where the error occurred.
     * 
     * @return The column (1-based)
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Gets the context information.
     * 
     * @return The context string
     */
    public String getContext() {
        return context;
    }
    
    /**
     * Formats an error message with token information.
     * 
     * @param message The base error message
     * @param token The token where the error occurred
     * @return The formatted error message
     */
    private static String formatMessage(String message, Token token) {
        return String.format("[Line %d, Column %d] %s at '%s'", 
            token.getLine(), 
            token.getColumn(), 
            message, 
            token.getLexeme());
    }
    
    /**
     * Formats an error message with token and context information.
     * 
     * @param message The base error message
     * @param token The token where the error occurred
     * @param context Additional context
     * @return The formatted error message
     */
    private static String formatMessage(String message, Token token, String context) {
        return String.format("[Line %d, Column %d] %s at '%s' (Context: %s)", 
            token.getLine(), 
            token.getColumn(), 
            message, 
            token.getLexeme(),
            context);
    }
}
