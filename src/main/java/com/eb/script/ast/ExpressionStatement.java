package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * ExpressionStatement represents a standalone expression used as a statement.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ExpressionStatement implements Statement {
    private final Expression expression;
    
    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
    
    public Expression getExpression() {
        return expression;
    }
    
    @Override
    public Token getStartToken() {
        return expression.getStartToken();
    }
    
    @Override
    public Token getEndToken() {
        return expression.getEndToken();
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitExpressionStatement(this, context);
    }
    
    @Override
    public String toString() {
        return "ExprStmt(" + expression + ")";
    }
}
