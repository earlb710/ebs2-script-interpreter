package com.eb.script.runtime;

import com.eb.script.ast.*;
import com.eb.script.lexer.Token;
import com.eb.script.lexer.TokenType;

import java.io.PrintStream;
import java.util.List;

/**
 * Interpreter executes EBS2 statements within a runtime context.
 * 
 * Implements the visitor pattern to traverse the AST and execute statements.
 * Supports variable declarations, expressions, print statements, and control flow.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class Interpreter implements ASTVisitor<Object, RuntimeContext> {
    private final PrintStream output;
    
    /**
     * Creates a new interpreter that prints to System.out.
     */
    public Interpreter() {
        this.output = System.out;
    }
    
    /**
     * Creates a new interpreter with a custom output stream.
     * 
     * @param output The output stream for print statements
     */
    public Interpreter(PrintStream output) {
        this.output = output;
    }
    
    /**
     * Executes a list of statements in a new runtime context.
     * 
     * @param statements The statements to execute
     * @return The runtime context after execution
     */
    public RuntimeContext execute(List<Statement> statements) {
        RuntimeContext context = new RuntimeContext();
        return execute(statements, context);
    }
    
    /**
     * Executes a list of statements in the given runtime context.
     * 
     * @param statements The statements to execute
     * @param context The runtime context to use
     * @return The runtime context after execution
     */
    public RuntimeContext execute(List<Statement> statements, RuntimeContext context) {
        for (Statement statement : statements) {
            statement.accept(this, context);
        }
        return context;
    }
    
    // ===== Statement Visitors =====
    
    @Override
    public Object visitPrintStatement(PrintStatement stmt, RuntimeContext context) {
        Object value = stmt.getExpression().accept(this, context);
        output.println(stringify(value));
        return null;
    }
    
    @Override
    public Object visitVarStatement(VarStatement stmt, RuntimeContext context) {
        Object value = null;
        
        // Evaluate initializer if present
        if (stmt.getInitializer() != null) {
            value = stmt.getInitializer().accept(this, context);
        }
        
        // Define the variable in the current context
        context.define(stmt.getName().getLexeme(), value);
        return null;
    }
    
    @Override
    public Object visitIfStatement(IfStatement stmt, RuntimeContext context) {
        Object condition = stmt.getCondition().accept(this, context);
        
        if (isTruthy(condition)) {
            stmt.getThenBranch().accept(this, context);
        } else if (stmt.getElseBranch() != null) {
            stmt.getElseBranch().accept(this, context);
        }
        
        return null;
    }
    
    @Override
    public Object visitBlockStatement(BlockStatement stmt, RuntimeContext context) {
        // Create a new scope for the block
        RuntimeContext blockContext = context.createChild();
        
        // Execute statements in the block
        for (Statement statement : stmt.getStatements()) {
            statement.accept(this, blockContext);
        }
        
        return null;
    }
    
    @Override
    public Object visitExpressionStatement(ExpressionStatement stmt, RuntimeContext context) {
        // Evaluate the expression and discard the result
        stmt.getExpression().accept(this, context);
        return null;
    }
    
    @Override
    public Object visitImportStatement(ImportStatement stmt, RuntimeContext context) {
        // TODO: Import statement implementation
        // For now, this is a no-op
        return null;
    }
    
    // ===== Expression Visitors =====
    
    @Override
    public Object visitLiteralExpression(LiteralExpression expr, RuntimeContext context) {
        return expr.getValue();
    }
    
    @Override
    public Object visitVariableExpression(VariableExpression expr, RuntimeContext context) {
        return context.get(expr.getName().getLexeme());
    }
    
    @Override
    public Object visitBinaryExpression(BinaryExpression expr, RuntimeContext context) {
        Object left = expr.getLeft().accept(this, context);
        Object right = expr.getRight().accept(this, context);
        TokenType operator = expr.getOperator().getTokenType();
        
        switch (operator) {
            // Arithmetic operators
            case PLUS:
                if (isNumber(left) && isNumber(right)) {
                    return toDouble(left) + toDouble(right);
                }
                if (left instanceof String || right instanceof String) {
                    return stringify(left) + stringify(right);
                }
                throw new RuntimeException("Operands must be two numbers or strings");
                
            case MINUS:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) - toDouble(right);
                
            case MULTIPLY:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) * toDouble(right);
                
            case DIVIDE:
                checkNumberOperands(expr.getOperator(), left, right);
                if (toDouble(right) == 0.0) {
                    throw new RuntimeException("Division by zero");
                }
                return toDouble(left) / toDouble(right);
                
            case MOD:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) % toDouble(right);
                
            // Comparison operators
            case GREATER_THAN:
            case IS_GREATER_THAN:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) > toDouble(right);
                
            case GREATER_EQUAL:
            case IS_GREATER_EQUAL:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) >= toDouble(right);
                
            case LESS_THAN:
            case IS_LESS_THAN:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) < toDouble(right);
                
            case LESS_EQUAL:
            case IS_LESS_EQUAL:
                checkNumberOperands(expr.getOperator(), left, right);
                return toDouble(left) <= toDouble(right);
                
            // Equality operators
            case EQUAL:
            case IS_EQUAL_TO:
                return isEqual(left, right);
                
            case NOT_EQUAL:
            case IS_NOT_EQUAL_TO:
                return !isEqual(left, right);
                
            // Logical operators
            case AND:
                return isTruthy(left) && isTruthy(right);
                
            case OR:
                return isTruthy(left) || isTruthy(right);
                
            default:
                throw new RuntimeException("Unknown binary operator: " + operator);
        }
    }
    
    @Override
    public Object visitUnaryExpression(UnaryExpression expr, RuntimeContext context) {
        Object operand = expr.getOperand().accept(this, context);
        TokenType operator = expr.getOperator().getTokenType();
        
        switch (operator) {
            case MINUS:
                checkNumberOperand(expr.getOperator(), operand);
                return -toDouble(operand);
                
            case NOT:
                return !isTruthy(operand);
                
            default:
                throw new RuntimeException("Unknown unary operator: " + operator);
        }
    }
    
    @Override
    public Object visitGroupingExpression(GroupingExpression expr, RuntimeContext context) {
        return expr.getExpression().accept(this, context);
    }
    
    // ===== Helper Methods =====
    
    /**
     * Checks if a value is a number (Integer or Double).
     * 
     * @param value The value to check
     * @return true if the value is a number
     */
    private boolean isNumber(Object value) {
        return value instanceof Integer || value instanceof Double;
    }
    
    /**
     * Converts a number value to double.
     * 
     * @param value The value to convert
     * @return The double value
     */
    private double toDouble(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        throw new RuntimeException("Value is not a number");
    }
    
    /**
     * Converts a value to a boolean for conditional statements.
     * 
     * @param value The value to test
     * @return true if the value is truthy
     */
    private boolean isTruthy(Object value) {
        if (value == null) return false;
        if (value instanceof Boolean) return (Boolean) value;
        return true;
    }
    
    /**
     * Checks if two values are equal.
     * 
     * @param left The left value
     * @param right The right value
     * @return true if the values are equal
     */
    private boolean isEqual(Object left, Object right) {
        if (left == null && right == null) return true;
        if (left == null) return false;
        return left.equals(right);
    }
    
    /**
     * Converts a value to a string for output.
     * 
     * @param value The value to stringify
     * @return The string representation
     */
    private String stringify(Object value) {
        if (value == null) return "null";
        
        if (value instanceof Double) {
            String text = value.toString();
            // Remove trailing .0 for whole numbers
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }
        
        return value.toString();
    }
    
    /**
     * Checks that an operand is a number.
     * 
     * @param operator The operator token for error messages
     * @param operand The operand to check
     * @throws RuntimeException if the operand is not a number
     */
    private void checkNumberOperand(Token operator, Object operand) {
        if (isNumber(operand)) return;
        throw new RuntimeException("Operand must be a number");
    }
    
    /**
     * Checks that both operands are numbers.
     * 
     * @param operator The operator token for error messages
     * @param left The left operand to check
     * @param right The right operand to check
     * @throws RuntimeException if either operand is not a number
     */
    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (isNumber(left) && isNumber(right)) return;
        throw new RuntimeException("Operands must be numbers");
    }
}
