package com.eb.script.parser;

import com.eb.script.lexer.Token;

/**
 * ParseError represents a parsing error with detailed location and context information.
 * 
 * Unlike ParserException which is thrown, ParseError is used to collect errors
 * during parsing to report multiple errors at once.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ParseError {
    private final String message;
    private final Token token;
    private final int line;
    private final int column;
    private final String context;
    private final ErrorType type;
    
    /**
     * Error types to classify different kinds of parsing errors.
     */
    public enum ErrorType {
        UNEXPECTED_TOKEN,
        MISSING_TOKEN,
        INVALID_SYNTAX,
        INVALID_EXPRESSION,
        INVALID_STATEMENT,
        UNEXPECTED_EOF,
        OTHER
    }
    
    /**
     * Constructs a ParseError with full details.
     * 
     * @param message The error message
     * @param token The token where the error occurred
     * @param type The type of error
     * @param context Additional context information
     */
    public ParseError(String message, Token token, ErrorType type, String context) {
        this.message = message;
        this.token = token;
        this.line = token.getLine();
        this.column = token.getColumn();
        this.type = type;
        this.context = context;
    }
    
    /**
     * Constructs a ParseError with message and token.
     * 
     * @param message The error message
     * @param token The token where the error occurred
     * @param type The type of error
     */
    public ParseError(String message, Token token, ErrorType type) {
        this(message, token, type, token.getLexeme());
    }
    
    /**
     * Gets the error message.
     * 
     * @return The error message
     */
    public String getMessage() {
        return message;
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
     * Gets the error type.
     * 
     * @return The error type
     */
    public ErrorType getType() {
        return type;
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
     * Returns a formatted string representation of the error.
     * 
     * @return Formatted error message
     */
    @Override
    public String toString() {
        return String.format("[Line %d, Column %d] %s: %s at '%s'", 
            line, column, type, message, context);
    }
    
    /**
     * Returns a detailed error report.
     * 
     * @return Detailed error report
     */
    public String getDetailedReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parse Error: ").append(type).append("\n");
        sb.append("  Location: Line ").append(line).append(", Column ").append(column).append("\n");
        sb.append("  Message: ").append(message).append("\n");
        sb.append("  Context: ").append(context).append("\n");
        sb.append("  Token: ").append(token.getTokenType()).append(" ('").append(token.getLexeme()).append("')\n");
        return sb.toString();
    }
}
