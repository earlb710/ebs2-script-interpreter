package com.eb.script.ast;

/**
 * ASTVisitor provides the visitor pattern for traversing the AST.
 * 
 * Implement this interface to perform operations on AST nodes
 * such as evaluation, code generation, or analysis.
 * 
 * @param <R> The return type of visitor methods
 * @param <C> The context type passed through the visitor
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public interface ASTVisitor<R, C> {
    
    // Statement visitors
    R visitPrintStatement(PrintStatement stmt, C context);
    R visitVarStatement(VarStatement stmt, C context);
    R visitIfStatement(IfStatement stmt, C context);
    R visitBlockStatement(BlockStatement stmt, C context);
    R visitExpressionStatement(ExpressionStatement stmt, C context);
    R visitImportStatement(ImportStatement stmt, C context);
    R visitWhileStatement(WhileStatement stmt, C context);
    R visitForStatement(ForStatement stmt, C context);
    R visitRepeatStatement(RepeatStatement stmt, C context);
    
    // Expression visitors
    R visitLiteralExpression(LiteralExpression expr, C context);
    R visitVariableExpression(VariableExpression expr, C context);
    R visitBinaryExpression(BinaryExpression expr, C context);
    R visitUnaryExpression(UnaryExpression expr, C context);
    R visitGroupingExpression(GroupingExpression expr, C context);
}
