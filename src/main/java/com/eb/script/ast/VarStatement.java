package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * VarStatement represents a variable declaration in the EBS2 language.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class VarStatement implements Statement {
    private final Token keyword;
    private final Token name;
    private final Token type;
    private final Expression initializer;
    
    public VarStatement(Token keyword, Token name, Token type, Expression initializer) {
        this.keyword = keyword;
        this.name = name;
        this.type = type;
        this.initializer = initializer;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    public Token getName() {
        return name;
    }
    
    public Token getType() {
        return type;
    }
    
    public Expression getInitializer() {
        return initializer;
    }
    
    @Override
    public Token getStartToken() {
        return keyword;
    }
    
    @Override
    public Token getEndToken() {
        if (initializer != null) {
            return initializer.getEndToken();
        }
        if (type != null) {
            return type;
        }
        return name;
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitVarStatement(this, context);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Var(");
        sb.append(name.getLexeme());
        if (type != null) {
            sb.append(" as ").append(type.getLexeme());
        }
        if (initializer != null) {
            sb.append(" = ").append(initializer);
        }
        sb.append(")");
        return sb.toString();
    }
}
