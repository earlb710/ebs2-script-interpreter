package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * RepeatStatement represents a repeat-times loop in the EBS2 language.
 * 
 * Syntax: repeat EXPRESSION times STATEMENTS end repeat
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class RepeatStatement implements Statement {
    private final Token keyword;
    private final Expression times;
    private final Statement body;
    private final Token endToken;
    
    public RepeatStatement(Token keyword, Expression times, Statement body, Token endToken) {
        this.keyword = keyword;
        this.times = times;
        this.body = body;
        this.endToken = endToken;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    public Expression getTimes() {
        return times;
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
        return visitor.visitRepeatStatement(this, context);
    }
    
    @Override
    public String toString() {
        return "Repeat(" + times + " times, " + body + ")";
    }
}
