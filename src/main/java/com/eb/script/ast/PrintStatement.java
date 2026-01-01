package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * PrintStatement represents a print statement in the EBS2 language.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class PrintStatement implements Statement {
    private final Expression expression;
    private final Token keyword;
    
    public PrintStatement(Token keyword, Expression expression) {
        this.keyword = keyword;
        this.expression = expression;
    }
    
    public Expression getExpression() {
        return expression;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    @Override
    public Token getStartToken() {
        return keyword;
    }
    
    @Override
    public Token getEndToken() {
        return expression.getEndToken();
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitPrintStatement(this, context);
    }
    
    @Override
    public String toString() {
        return "Print(" + expression + ")";
    }
}
