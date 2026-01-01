package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * GroupingExpression represents a parenthesized expression.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class GroupingExpression implements Expression {
    private final Expression expression;
    private final Token leftParen;
    private final Token rightParen;
    
    public GroupingExpression(Token leftParen, Expression expression, Token rightParen) {
        this.leftParen = leftParen;
        this.expression = expression;
        this.rightParen = rightParen;
    }
    
    public Expression getExpression() {
        return expression;
    }
    
    public Token getLeftParen() {
        return leftParen;
    }
    
    public Token getRightParen() {
        return rightParen;
    }
    
    @Override
    public Token getStartToken() {
        return leftParen;
    }
    
    @Override
    public Token getEndToken() {
        return rightParen;
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitGroupingExpression(this, context);
    }
    
    @Override
    public String toString() {
        return "(" + expression + ")";
    }
}
