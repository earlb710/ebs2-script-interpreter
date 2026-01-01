package com.eb.script.ast;

import com.eb.script.lexer.Token;

/**
 * ImportStatement represents an import statement in the EBS2 language.
 * 
 * Grammar: import "filename"
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ImportStatement implements Statement {
    private final Token keyword;
    private final Token filename;
    
    public ImportStatement(Token keyword, Token filename) {
        this.keyword = keyword;
        this.filename = filename;
    }
    
    public Token getKeyword() {
        return keyword;
    }
    
    public Token getFilename() {
        return filename;
    }
    
    public String getFilenameValue() {
        return (String) filename.getLiteral();
    }
    
    @Override
    public Token getStartToken() {
        return keyword;
    }
    
    @Override
    public Token getEndToken() {
        return filename;
    }
    
    @Override
    public <R, C> R accept(ASTVisitor<R, C> visitor, C context) {
        return visitor.visitImportStatement(this, context);
    }
    
    @Override
    public String toString() {
        return "Import(\"" + getFilenameValue() + "\")";
    }
}
