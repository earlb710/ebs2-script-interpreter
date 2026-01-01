package com.eb.script.ast;

import com.eb.script.lexer.Token;
import java.util.List;

/**
 * BlockStatement represents a sequence of statements grouped together.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class BlockStatement implements Statement {
    private final List<Statement> statements;
    private final Token startToken;
    private final Token endToken;
    
    public BlockStatement(List<Statement> statements, Token startToken, Token endToken) {
        this.statements = statements;
        this.startToken = startToken;
        this.endToken = endToken;
    }
    
    public List<Statement> getStatements() {
        return statements;
    }
    
    @Override
    public Token getStartToken() {
        return startToken;
    }
    
    @Override
    public Token getEndToken() {
        return endToken;
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitBlockStatement(this, context);
    }
    
    @Override
    public String toString() {
        return "Block(" + statements.size() + " statements)";
    }
}
