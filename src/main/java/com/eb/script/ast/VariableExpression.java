package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * VariableExpression represents a reference to a variable.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class VariableExpression implements Expression {
    private final Token name;
    
    public VariableExpression(Token name) {
        this.name = name;
    }
    
    public Token getName() {
        return name;
    }
    
    @Override
    public Token getStartToken() {
        return name;
    }
    
    @Override
    public Token getEndToken() {
        return name;
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitVariableExpression(this, context);
    }
    
    @Override
    public String toString() {
        return name.getLexeme();
    }
}
