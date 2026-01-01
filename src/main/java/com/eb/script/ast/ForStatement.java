package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * ForStatement represents a for loop in the EBS2 language.
 * 
 * Syntax: for VARIABLE = START to END [step STEP] loop STATEMENTS end for
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ForStatement implements Statement {
    private final Token keyword;
    private final Token variable;
    private final Expression start;
    private final Expression end;
    private final Expression step;  // Optional, defaults to 1
    private final Statement body;
    private final Token endToken;
    
    public ForStatement(Token keyword, Token variable, Expression start, Expression end, 
                       Expression step, Statement body, Token endToken) {
        this.keyword = keyword;
        this.variable = variable;
        this.start = start;
        this.end = end;
        this.step = step;
        this.body = body;
        this.endToken = endToken;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    public Token getVariable() {
        return variable;
    }
    
    public Expression getStart() {
        return start;
    }
    
    public Expression getEnd() {
        return end;
    }
    
    public Expression getStep() {
        return step;
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
        return visitor.visitForStatement(this, context);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("For(");
        sb.append(variable.getLexeme()).append(" = ");
        sb.append(start).append(" to ").append(end);
        if (step != null) {
            sb.append(" step ").append(step);
        }
        sb.append(", ").append(body).append(")");
        return sb.toString();
    }
}
