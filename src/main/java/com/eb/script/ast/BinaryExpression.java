package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * BinaryExpression represents a binary operation (e.g., a + b, x > y).
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class BinaryExpression implements Expression {
    private final Expression left;
    private final Token operator;
    private final Expression right;
    
    public BinaryExpression(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    
    public Expression getLeft() {
        return left;
    }
    
    public Token getOperator() {
        return operator;
    }
    
    public Expression getRight() {
        return right;
    }
    
    @Override
    public Token getStartToken() {
        return left.getStartToken();
    }
    
    @Override
    public Token getEndToken() {
        return right.getEndToken();
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitBinaryExpression(this, context);
    }
    
    @Override
    public String toString() {
        return "(" + left + " " + operator.getLexeme() + " " + right + ")";
    }
}
