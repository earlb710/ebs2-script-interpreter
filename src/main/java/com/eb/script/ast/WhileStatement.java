package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * WhileStatement represents a while loop in the EBS2 language.
 * 
 * Syntax: while CONDITION loop STATEMENTS end while
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class WhileStatement implements Statement {
    private final Token keyword;
    private final Expression condition;
    private final Statement body;
    private final Token endToken;
    
    public WhileStatement(Token keyword, Expression condition, Statement body, Token endToken) {
        this.keyword = keyword;
        this.condition = condition;
        this.body = body;
        this.endToken = endToken;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    public Expression getCondition() {
        return condition;
    }
    
    public Statement getBody() {
        return body;
    }
    
    @Override
    public Token getStartToken() {
        return keyword;
    }
    
    @Override
    public Token getEndToken() {
        return endToken != null ? endToken : body.getEndToken();
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitWhileStatement(this, context);
    }
    
    @Override
    public String toString() {
        return "While(" + condition + ", " + body + ")";
    }
}
