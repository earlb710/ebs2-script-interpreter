package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * IfStatement represents an if-then-else conditional statement.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class IfStatement implements Statement {
    private final Token keyword;
    private final Expression condition;
    private final Statement thenBranch;
    private final Statement elseBranch;
    private final Token endToken;
    
    public IfStatement(Token keyword, Expression condition, Statement thenBranch, 
                      Statement elseBranch, Token endToken) {
        this.keyword = keyword;
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
        this.endToken = endToken;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    public Expression getCondition() {
        return condition;
    }
    
    public Statement getThenBranch() {
        return thenBranch;
    }
    
    public Statement getElseBranch() {
        return elseBranch;
    }
    
    @Override
    public Token getStartToken() {
        return keyword;
    }
    
    @Override
    public Token getEndToken() {
        return endToken != null ? endToken : 
               (elseBranch != null ? elseBranch.getEndToken() : thenBranch.getEndToken());
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitIfStatement(this, context);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("If(");
        sb.append(condition);
        sb.append(", then: ").append(thenBranch);
        if (elseBranch != null) {
            sb.append(", else: ").append(elseBranch);
        }
        sb.append(")");
        return sb.toString();
    }
}
