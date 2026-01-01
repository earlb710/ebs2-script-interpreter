package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * ASTNode is the base interface for all nodes in the Abstract Syntax Tree.
 * 
 * Each node represents a syntactic construct in the EBS2 language
 * and provides access to its location in the source code.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public interface ASTNode {
    
    /**
     * Gets the starting token of this node.
     * 
     * @return The first token of this syntactic construct
     */
    Token getStartToken();
    
    /**
     * Gets the ending token of this node.
     * 
     * @return The last token of this syntactic construct
     */
    Token getEndToken();
    
    /**
     * Accepts a visitor for traversing the AST.
     * 
     * @param visitor The visitor to accept
     * @param <R> The return type of the visitor
     * @param <C> The context type
     * @return The result of the visit
     */
    <R, C> R accept(ASTVisitor<R, C> visitor, C context);
    
    /**
     * Returns a string representation of this AST node.
     * 
     * @return A descriptive string
     */
    String toString();
}
