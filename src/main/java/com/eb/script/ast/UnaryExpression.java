package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * UnaryExpression represents a unary operation (e.g., -x, not flag).
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class UnaryExpression implements Expression {
    private final Token operator;
    private final Expression operand;
    
    public UnaryExpression(Token operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }
    
    public Token getOperator() {
        return operator;
    }
    
    public Expression getOperand() {
        return operand;
    }
    
    @Override
    public Token getStartToken() {
        return operator;
    }
    
    @Override
    public Token getEndToken() {
        return operand.getEndToken();
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitUnaryExpression(this, context);
    }
    
    @Override
    public String toString() {
        return "(" + operator.getLexeme() + operand + ")";
    }
}
