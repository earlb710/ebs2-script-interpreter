package com.eb.script.runtime;

import com.eb.script.ast.Statement;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import com.eb.script.parser.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the EBS2 Interpreter.
 * 
 * Tests statement execution including:
 * - Variable declarations and initialization
 * - Print statements
 * - If statements and conditionals
 * - Expression evaluation (arithmetic, comparison, logical)
 * - Runtime error handling
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class InterpreterTest {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private Interpreter interpreter;
    
    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        interpreter = new Interpreter(printStream);
    }
    
    /**
     * Helper method to parse and execute source code.
     */
    private RuntimeContext execute(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        List<Statement> statements = parser.parse();
        return interpreter.execute(statements);
    }
    
    /**
     * Helper method to get output from print statements.
     */
    private String getOutput() {
        return outputStream.toString();
    }
    
    // ===== Variable Declaration Tests =====
    
    @Test
    @DisplayName("Test variable declaration without initialization")
    public void testVarDeclarationWithoutInit() {
        String source = "var x";
        RuntimeContext context = execute(source);
        
        assertTrue(context.isDefined("x"));
        assertNull(context.get("x"));
    }
    
    @Test
    @DisplayName("Test variable declaration with number initialization")
    public void testVarDeclarationWithNumber() {
        String source = "var x = 42";
        RuntimeContext context = execute(source);
        
        assertTrue(context.isDefined("x"));
        assertEquals(42, context.get("x"));
    }
    
    @Test
    @DisplayName("Test variable declaration with string initialization")
    public void testVarDeclarationWithString() {
        String source = "var name = \"Alice\"";
        RuntimeContext context = execute(source);
        
        assertTrue(context.isDefined("name"));
        assertEquals("Alice", context.get("name"));
    }
    
    @Test
    @DisplayName("Test variable declaration with boolean initialization")
    public void testVarDeclarationWithBoolean() {
        String source = "var isActive = true";
        RuntimeContext context = execute(source);
        
        assertTrue(context.isDefined("isActive"));
        assertEquals(Boolean.TRUE, context.get("isActive"));
    }
    
    @Test
    @DisplayName("Test multiple variable declarations")
    public void testMultipleVarDeclarations() {
        String source = """
            var x = 10
            var y = 20
            var z = 30
            """;
        RuntimeContext context = execute(source);
        
        assertEquals(10, context.get("x"));
        assertEquals(20, context.get("y"));
        assertEquals(30, context.get("z"));
    }
    
    @Test
    @DisplayName("Test accessing undefined variable throws exception")
    public void testUndefinedVariableThrowsException() {
        String source = "var x = y";
        
        assertThrows(RuntimeException.class, () -> execute(source));
    }
    
    // ===== Print Statement Tests =====
    
    @Test
    @DisplayName("Test print number")
    public void testPrintNumber() {
        String source = "print 42";
        execute(source);
        
        assertEquals("42\n", getOutput());
    }
    
    @Test
    @DisplayName("Test print string")
    public void testPrintString() {
        String source = "print \"Hello, World!\"";
        execute(source);
        
        assertEquals("Hello, World!\n", getOutput());
    }
    
    @Test
    @DisplayName("Test print boolean")
    public void testPrintBoolean() {
        String source = "print true";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test print variable")
    public void testPrintVariable() {
        String source = """
            var x = 100
            print x
            """;
        execute(source);
        
        assertEquals("100\n", getOutput());
    }
    
    @Test
    @DisplayName("Test print expression")
    public void testPrintExpression() {
        String source = "print 10 + 5";
        execute(source);
        
        assertEquals("15\n", getOutput());
    }
    
    @Test
    @DisplayName("Test multiple print statements")
    public void testMultiplePrintStatements() {
        String source = """
            print 1
            print 2
            print 3
            """;
        execute(source);
        
        assertEquals("1\n2\n3\n", getOutput());
    }
    
    // ===== Arithmetic Expression Tests =====
    
    @Test
    @DisplayName("Test addition")
    public void testAddition() {
        String source = "print 10 + 5";
        execute(source);
        
        assertEquals("15\n", getOutput());
    }
    
    @Test
    @DisplayName("Test subtraction")
    public void testSubtraction() {
        String source = "print 10 - 5";
        execute(source);
        
        assertEquals("5\n", getOutput());
    }
    
    @Test
    @DisplayName("Test multiplication")
    public void testMultiplication() {
        String source = "print 10 * 5";
        execute(source);
        
        assertEquals("50\n", getOutput());
    }
    
    @Test
    @DisplayName("Test division")
    public void testDivision() {
        String source = "print 10 / 5";
        execute(source);
        
        assertEquals("2\n", getOutput());
    }
    
    @Test
    @DisplayName("Test modulo")
    public void testModulo() {
        String source = "print 10 mod 3";
        execute(source);
        
        assertEquals("1\n", getOutput());
    }
    
    @Test
    @DisplayName("Test division by zero throws exception")
    public void testDivisionByZero() {
        String source = "print 10 / 0";
        
        assertThrows(RuntimeException.class, () -> execute(source));
    }
    
    @Test
    @DisplayName("Test string concatenation with plus")
    public void testStringConcatenation() {
        String source = "print \"Hello\" + \" \" + \"World\"";
        execute(source);
        
        assertEquals("Hello World\n", getOutput());
    }
    
    @Test
    @DisplayName("Test complex arithmetic expression")
    public void testComplexArithmetic() {
        String source = "print 2 + 3 * 4";
        execute(source);
        
        assertEquals("14\n", getOutput());
    }
    
    @Test
    @DisplayName("Test arithmetic with variables")
    public void testArithmeticWithVariables() {
        String source = """
            var x = 10
            var y = 5
            print x + y
            """;
        execute(source);
        
        assertEquals("15\n", getOutput());
    }
    
    // ===== Comparison Expression Tests =====
    
    @Test
    @DisplayName("Test greater than")
    public void testGreaterThan() {
        String source = "print 10 > 5";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test less than")
    public void testLessThan() {
        String source = "print 5 < 10";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test greater than or equal")
    public void testGreaterEqual() {
        String source = "print 10 >= 10";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test less than or equal")
    public void testLessEqual() {
        String source = "print 5 <= 10";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test equality")
    public void testEquality() {
        String source = "print 5 == 5";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test inequality")
    public void testInequality() {
        String source = "print 5 != 10";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    // ===== Logical Expression Tests =====
    
    @Test
    @DisplayName("Test logical AND with true values")
    public void testLogicalAndTrue() {
        String source = "print true and true";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test logical AND with false values")
    public void testLogicalAndFalse() {
        String source = "print true and false";
        execute(source);
        
        assertEquals("false\n", getOutput());
    }
    
    @Test
    @DisplayName("Test logical OR with true values")
    public void testLogicalOrTrue() {
        String source = "print false or true";
        execute(source);
        
        assertEquals("true\n", getOutput());
    }
    
    @Test
    @DisplayName("Test logical OR with false values")
    public void testLogicalOrFalse() {
        String source = "print false or false";
        execute(source);
        
        assertEquals("false\n", getOutput());
    }
    
    @Test
    @DisplayName("Test logical NOT")
    public void testLogicalNot() {
        String source = "print not true";
        execute(source);
        
        assertEquals("false\n", getOutput());
    }
    
    // ===== Unary Expression Tests =====
    
    @Test
    @DisplayName("Test unary minus")
    public void testUnaryMinus() {
        String source = "print -42";
        execute(source);
        
        assertEquals("-42\n", getOutput());
    }
    
    // ===== Grouping Expression Tests =====
    
    @Test
    @DisplayName("Test grouping with parentheses")
    public void testGrouping() {
        String source = "print (2 + 3) * 4";
        execute(source);
        
        assertEquals("20\n", getOutput());
    }
    
    @Test
    @DisplayName("Test nested grouping")
    public void testNestedGrouping() {
        String source = "print ((2 + 3) * 4) / 2";
        execute(source);
        
        assertEquals("10\n", getOutput());
    }
    
    // ===== If Statement Tests =====
    
    @Test
    @DisplayName("Test if statement with true condition")
    public void testIfStatementTrue() {
        String source = """
            if true then print "yes"
            """;
        execute(source);
        
        assertEquals("yes\n", getOutput());
    }
    
    @Test
    @DisplayName("Test if statement with false condition")
    public void testIfStatementFalse() {
        String source = """
            if false then print "yes"
            """;
        execute(source);
        
        assertEquals("", getOutput());
    }
    
    @Test
    @DisplayName("Test if-else statement with true condition")
    public void testIfElseTrue() {
        String source = """
            if true then print "yes" else print "no"
            """;
        execute(source);
        
        assertEquals("yes\n", getOutput());
    }
    
    @Test
    @DisplayName("Test if-else statement with false condition")
    public void testIfElseFalse() {
        String source = """
            if false then print "yes" else print "no"
            """;
        execute(source);
        
        assertEquals("no\n", getOutput());
    }
    
    @Test
    @DisplayName("Test if statement with comparison")
    public void testIfWithComparison() {
        String source = """
            var x = 10
            if x > 5 then print "greater"
            """;
        execute(source);
        
        assertEquals("greater\n", getOutput());
    }
    
    // ===== Complex Integration Tests =====
    
    @Test
    @DisplayName("Test complex program with variables and conditionals")
    public void testComplexProgram() {
        String source = """
            var x = 10
            var y = 20
            var sum = x + y
            print sum
            if sum > 25 then print "large" else print "small"
            """;
        execute(source);
        
        assertEquals("30\nlarge\n", getOutput());
    }
    
    @Test
    @DisplayName("Test nested conditionals")
    public void testNestedConditionals() {
        String source = """
            var x = 15
            if x > 10 then if x < 20 then print "between"
            """;
        execute(source);
        
        assertEquals("between\n", getOutput());
    }
    
    @Test
    @DisplayName("Test using variable in expression initialization")
    public void testVariableInExpression() {
        String source = """
            var x = 5
            var y = x + 10
            print y
            """;
        execute(source);
        
        assertEquals("15\n", getOutput());
    }
    
    @Test
    @DisplayName("Test expression statement")
    public void testExpressionStatement() {
        String source = """
            var x = 10
            x + 5
            print x
            """;
        execute(source);
        
        // Expression statement doesn't modify x
        assertEquals("10\n", getOutput());
    }
}
