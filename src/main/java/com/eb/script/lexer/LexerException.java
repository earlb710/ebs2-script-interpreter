package com.eb.script.lexer;

/**
 * LexerException is thrown when the lexer encounters an error
 * during tokenization of EBS2 source code.
 * 
 * @version 2.0.0
 * @since 2025-12-29
 */
public class LexerException extends RuntimeException {
    private final int line;
    private final int column;
    
    /**
     * Constructs a new LexerException.
     * 
     * @param message The error message
     * @param line The line number where the error occurred
     * @param column The column position where the error occurred
     */
    public LexerException(String message, int line, int column) {
        super(String.format("[Line %d, Column %d] %s", line, column, message));
        this.line = line;
        this.column = column;
    }
    
    /**
     * Constructs a new LexerException without position information.
     * 
     * @param message The error message
     */
    public LexerException(String message) {
        super(message);
        this.line = -1;
        this.column = -1;
    }
    
    /**
     * Gets the line number where the error occurred.
     * 
     * @return The line number, or -1 if not available
     */
    public int getLine() {
        return line;
    }
    
    /**
     * Gets the column position where the error occurred.
     * 
     * @return The column position, or -1 if not available
     */
    public int getColumn() {
        return column;
    }
}
