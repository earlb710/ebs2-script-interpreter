package com.eb.script.lexer;

import java.util.List;

/**
 * Generic lexer interface for all language lexers.
 * Provides a common contract for lexical analyzers across different languages (EBS2, HTML, XML, etc.).
 */
public interface ILexer {
    /**
     * Scan the source code and return a list of tokens.
     * @return List of tokens representing the source code
     */
    List<? extends IToken> scanTokens();
    
    /**
     * Get all errors encountered during lexical analysis.
     * @return List of lexer exceptions
     */
    List<LexerException> getErrors();
    
    /**
     * Check if any errors were encountered during lexical analysis.
     * @return true if errors were found, false otherwise
     */
    boolean hadError();
}
