package com.eb.script.lexer;

/**
 * Generic token interface for all language lexers.
 * Provides a common contract for tokens across different languages (EBS2, HTML, XML, etc.).
 */
public interface IToken {
    /**
     * Get the token type as a string representation.
     * @return Token type name
     */
    String getType();
    
    /**
     * Get the original text (lexeme) from the source code.
     * @return The original text
     */
    String getLexeme();
    
    /**
     * Get the literal value of the token (if applicable).
     * For example, the numeric value of a number token or the parsed string value.
     * @return The literal value, or null if not applicable
     */
    Object getLiteral();
    
    /**
     * Get the line number where this token appears.
     * @return Line number (1-based)
     */
    int getLine();
    
    /**
     * Get the column number where this token starts.
     * @return Column number (1-based)
     */
    int getColumn();
    
    /**
     * Get the absolute start position in the source file.
     * @return Start position (0-based)
     */
    int getStartPos();
    
    /**
     * Get the absolute end position in the source file.
     * @return End position (0-based, exclusive)
     */
    int getEndPos();
    
    /**
     * Get the length of the token.
     * @return Token length in characters
     */
    int getLength();
}
