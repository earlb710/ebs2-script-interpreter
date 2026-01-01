package com.eb.script.parser;

import com.eb.script.ast.*;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the EBS2 Parser.
 * 
 * Tests basic statement parsing including:
 * - Variable declarations
 * - Print statements
 * - If statements
 * - Expressions
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ParserTest {
    
    /**
     * Helper method to parse source code.
     */
    private List<Statement> parse(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        return parser.parse();
    }
    
    /**
     * Helper method to parse and get parser errors.
     */
    private Parser parseWithParser(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        parser.parse();
        return parser;
    }
    
    @Test
    @DisplayName("Test parsing simple variable declaration")
    public void testSimpleVarDeclaration() {
        String source = "var x = 5";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof VarStatement);
        
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertEquals("x", varStmt.getName().getLexeme());
        assertNotNull(varStmt.getInitializer());
        assertTrue(varStmt.getInitializer() instanceof LiteralExpression);
    }
    
    @Test
    @DisplayName("Test parsing variable declaration with type")
    public void testVarDeclarationWithType() {
        String source = "var count as number = 10";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof VarStatement);
        
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertEquals("count", varStmt.getName().getLexeme());
        assertNotNull(varStmt.getType());
        assertEquals("number", varStmt.getType().getLexeme());
        assertNotNull(varStmt.getInitializer());
    }
    
    @Test
    @DisplayName("Test parsing variable declaration without initializer")
    public void testVarDeclarationNoInitializer() {
        String source = "var name as text";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof VarStatement);
        
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertEquals("name", varStmt.getName().getLexeme());
        assertNotNull(varStmt.getType());
        assertNull(varStmt.getInitializer());
    }
    
    @Test
    @DisplayName("Test parsing print statement with literal")
    public void testPrintStatementLiteral() {
        String source = "print \"Hello World\"";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof PrintStatement);
        
        PrintStatement printStmt = (PrintStatement) statements.get(0);
        assertNotNull(printStmt.getExpression());
        assertTrue(printStmt.getExpression() instanceof LiteralExpression);
        
        LiteralExpression literal = (LiteralExpression) printStmt.getExpression();
        assertEquals("Hello World", literal.getValue());
    }
    
    @Test
    @DisplayName("Test parsing print statement with variable")
    public void testPrintStatementVariable() {
        String source = "print myVar";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof PrintStatement);
        
        PrintStatement printStmt = (PrintStatement) statements.get(0);
        assertTrue(printStmt.getExpression() instanceof VariableExpression);
        
        VariableExpression varExpr = (VariableExpression) printStmt.getExpression();
        assertEquals("myVar", varExpr.getName().getLexeme());
    }
    
    @Test
    @DisplayName("Test parsing print statement with expression")
    public void testPrintStatementExpression() {
        String source = "print 2 + 3";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof PrintStatement);
        
        PrintStatement printStmt = (PrintStatement) statements.get(0);
        assertTrue(printStmt.getExpression() instanceof BinaryExpression);
    }
    
    @Test
    @DisplayName("Test parsing simple if statement")
    public void testSimpleIfStatement() {
        String source = "if x > 5 then print \"big\"";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof IfStatement);
        
        IfStatement ifStmt = (IfStatement) statements.get(0);
        assertNotNull(ifStmt.getCondition());
        assertTrue(ifStmt.getCondition() instanceof BinaryExpression);
        assertNotNull(ifStmt.getThenBranch());
        assertTrue(ifStmt.getThenBranch() instanceof PrintStatement);
        assertNull(ifStmt.getElseBranch());
    }
    
    @Test
    @DisplayName("Test parsing if-else statement")
    public void testIfElseStatement() {
        String source = "if age < 13 then print \"child\" else print \"teen\"";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof IfStatement);
        
        IfStatement ifStmt = (IfStatement) statements.get(0);
        assertNotNull(ifStmt.getCondition());
        assertNotNull(ifStmt.getThenBranch());
        assertNotNull(ifStmt.getElseBranch());
        assertTrue(ifStmt.getElseBranch() instanceof PrintStatement);
    }
    
    @Test
    @DisplayName("Test parsing binary expressions")
    public void testBinaryExpressions() {
        String source = "var result = 10 + 20 * 3";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof VarStatement);
        
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertNotNull(varStmt.getInitializer());
        assertTrue(varStmt.getInitializer() instanceof BinaryExpression);
        
        // Check operator precedence: should be (10 + (20 * 3))
        BinaryExpression expr = (BinaryExpression) varStmt.getInitializer();
        assertEquals("+", expr.getOperator().getLexeme());
        assertTrue(expr.getRight() instanceof BinaryExpression);
        
        BinaryExpression rightExpr = (BinaryExpression) expr.getRight();
        assertEquals("*", rightExpr.getOperator().getLexeme());
    }
    
    @Test
    @DisplayName("Test parsing comparison expressions")
    public void testComparisonExpressions() {
        String source = "var isGreater = x > 10";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertTrue(varStmt.getInitializer() instanceof BinaryExpression);
        
        BinaryExpression expr = (BinaryExpression) varStmt.getInitializer();
        assertEquals(">", expr.getOperator().getLexeme());
    }
    
    @Test
    @DisplayName("Test parsing logical expressions")
    public void testLogicalExpressions() {
        String source = "var valid = x > 5 and y < 10";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertTrue(varStmt.getInitializer() instanceof BinaryExpression);
        
        BinaryExpression expr = (BinaryExpression) varStmt.getInitializer();
        assertEquals("and", expr.getOperator().getLexeme());
        assertTrue(expr.getLeft() instanceof BinaryExpression);
        assertTrue(expr.getRight() instanceof BinaryExpression);
    }
    
    @Test
    @DisplayName("Test parsing unary expressions")
    public void testUnaryExpressions() {
        String source = "var negated = -x";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertTrue(varStmt.getInitializer() instanceof UnaryExpression);
        
        UnaryExpression expr = (UnaryExpression) varStmt.getInitializer();
        assertEquals("-", expr.getOperator().getLexeme());
        assertTrue(expr.getOperand() instanceof VariableExpression);
    }
    
    @Test
    @DisplayName("Test parsing grouped expressions")
    public void testGroupedExpressions() {
        String source = "var result = (2 + 3) * 4";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertTrue(varStmt.getInitializer() instanceof BinaryExpression);
        
        BinaryExpression expr = (BinaryExpression) varStmt.getInitializer();
        assertEquals("*", expr.getOperator().getLexeme());
        assertTrue(expr.getLeft() instanceof GroupingExpression);
    }
    
    @Test
    @DisplayName("Test parsing multiple statements")
    public void testMultipleStatements() {
        String source = "var x = 5\nvar y = 10\nprint x + y";
        List<Statement> statements = parse(source);
        
        assertEquals(3, statements.size());
        assertTrue(statements.get(0) instanceof VarStatement);
        assertTrue(statements.get(1) instanceof VarStatement);
        assertTrue(statements.get(2) instanceof PrintStatement);
    }
    
    @Test
    @DisplayName("Test parsing boolean literals")
    public void testBooleanLiterals() {
        String source = "var flag1 = true\nvar flag2 = false";
        List<Statement> statements = parse(source);
        
        assertEquals(2, statements.size());
        
        VarStatement var1 = (VarStatement) statements.get(0);
        assertTrue(var1.getInitializer() instanceof LiteralExpression);
        assertEquals(true, ((LiteralExpression) var1.getInitializer()).getValue());
        
        VarStatement var2 = (VarStatement) statements.get(1);
        assertTrue(var2.getInitializer() instanceof LiteralExpression);
        assertEquals(false, ((LiteralExpression) var2.getInitializer()).getValue());
    }
    
    @Test
    @DisplayName("Test parsing natural language operators")
    public void testNaturalLanguageOperators() {
        String source = "var result = x is greater than 5";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        VarStatement varStmt = (VarStatement) statements.get(0);
        assertTrue(varStmt.getInitializer() instanceof BinaryExpression);
        
        BinaryExpression expr = (BinaryExpression) varStmt.getInitializer();
        // The lexer should have converted "is greater than" to a single token
        assertTrue(expr.getOperator().getLexeme().contains("greater"));
    }
    
    @Test
    @DisplayName("Test toString methods for debugging")
    public void testToStringMethods() {
        String source = "var x = 5";
        List<Statement> statements = parse(source);
        
        assertNotNull(statements.get(0).toString());
        assertTrue(statements.get(0).toString().contains("Var"));
    }
}
