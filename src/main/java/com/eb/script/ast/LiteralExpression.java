package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * LiteralExpression represents a literal value (number, text, boolean).
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class LiteralExpression implements Expression {
    private final Token token;
    private final Object value;
    
    public LiteralExpression(Token token, Object value) {
        this.token = token;
        this.value = value;
    }
    
    public Token getToken() {
        return token;
    }
    
    public Object getValue() {
        return value;
    }
    
    @Override
    public Token getStartToken() {
        return token;
    }
    
    @Override
    public Token getEndToken() {
        return token;
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitLiteralExpression(this, context);
    }
    
    @Override
    public String toString() {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }
}
