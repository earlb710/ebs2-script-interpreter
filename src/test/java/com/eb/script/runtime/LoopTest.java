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
 * Test class for loop statements in the EBS2 Interpreter.
 * 
 * Tests all loop types:
 * - For loops (with and without step)
 * - Repeat loops
 * 
 * Note: While loops require assignment operators which are not yet implemented.
 * These tests focus on loops that don't require variable reassignment.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class LoopTest {
    
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
    
    // ===== For Loop Tests =====
    
    @Test
    @DisplayName("Test basic for loop")
    public void testBasicForLoop() {
        String source = """
            for i = 1 to 5 loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("1\n2\n3\n4\n5\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with step")
    public void testForLoopWithStep() {
        String source = """
            for i = 0 to 10 step 2 loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("0\n2\n4\n6\n8\n10\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with negative step")
    public void testForLoopNegativeStep() {
        String source = """
            for i = 5 to 1 step -1 loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("5\n4\n3\n2\n1\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with single iteration")
    public void testForLoopSingleIteration() {
        String source = """
            for i = 1 to 1 loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("1\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with zero iterations")
    public void testForLoopZeroIterations() {
        String source = """
            for i = 10 to 5 loop
                print i
            end for
            print "done"
            """;
        execute(source);
        
        assertEquals("done\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop variable scope")
    public void testForLoopVariableScope() {
        String source = """
            for i = 1 to 3 loop
                print i
            end for
            """;
        RuntimeContext context = execute(source);
        
        // Loop variable should not be accessible outside the loop
        assertFalse(context.isDefined("i"));
    }
    
    @Test
    @DisplayName("Test for loop with expression in range")
    public void testForLoopExpressionRange() {
        String source = """
            var startVal = 2
            var endVal = 4
            for i = startVal to endVal loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("2\n3\n4\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with negative range")
    public void testForLoopNegativeRange() {
        String source = """
            for i = -2 to 2 loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("-2\n-1\n0\n1\n2\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with step expression")
    public void testForLoopStepExpression() {
        String source = """
            var stepSize = 3
            for i = 0 to 10 step stepSize loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("0\n3\n6\n9\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop with zero step throws error")
    public void testForLoopZeroStep() {
        String source = """
            for i = 1 to 5 step 0 loop
                print i
            end for
            """;
        
        assertThrows(RuntimeException.class, () -> execute(source));
    }
    
    @Test
    @DisplayName("Test for loop with large step")
    public void testForLoopLargeStep() {
        String source = """
            for i = 0 to 100 step 25 loop
                print i
            end for
            """;
        execute(source);
        
        assertEquals("0\n25\n50\n75\n100\n", getOutput());
    }
    
    // ===== Repeat Loop Tests =====
    
    @Test
    @DisplayName("Test basic repeat loop")
    public void testBasicRepeatLoop() {
        String source = """
            repeat 3 times
                print "hello"
            end repeat
            """;
        execute(source);
        
        assertEquals("hello\nhello\nhello\n", getOutput());
    }
    
    @Test
    @DisplayName("Test repeat loop with zero times")
    public void testRepeatLoopZeroTimes() {
        String source = """
            repeat 0 times
                print "hello"
            end repeat
            print "done"
            """;
        execute(source);
        
        assertEquals("done\n", getOutput());
    }
    
    @Test
    @DisplayName("Test repeat loop with expression")
    public void testRepeatLoopWithExpression() {
        String source = """
            var count = 2 + 2
            repeat count times
                print "x"
            end repeat
            """;
        execute(source);
        
        assertEquals("x\nx\nx\nx\n", getOutput());
    }
    
    @Test
    @DisplayName("Test repeat loop with single iteration")
    public void testRepeatLoopSingleIteration() {
        String source = """
            repeat 1 times
                print "once"
            end repeat
            """;
        execute(source);
        
        assertEquals("once\n", getOutput());
    }
    
    @Test
    @DisplayName("Test repeat loop with negative times throws error")
    public void testRepeatLoopNegativeTimes() {
        String source = """
            repeat -5 times
                print "hello"
            end repeat
            """;
        
        assertThrows(RuntimeException.class, () -> execute(source));
    }
    
    @Test
    @DisplayName("Test repeat loop with large number")
    public void testRepeatLoopLargeNumber() {
        String source = """
            var counter = 0
            repeat 10 times
                var counter = 1
            end repeat
            """;
        execute(source);
        
        // Should execute 10 times without output
        assertEquals("", getOutput());
    }
    
    // ===== Nested Loop Tests =====
    
    @Test
    @DisplayName("Test nested for loops")
    public void testNestedForLoops() {
        String source = """
            for i = 1 to 2 loop
                for j = 1 to 2 loop
                    print i
                    print j
                end for
            end for
            """;
        execute(source);
        
        assertEquals("1\n1\n1\n2\n2\n1\n2\n2\n", getOutput());
    }
    
    @Test
    @DisplayName("Test repeat loop inside for loop")
    public void testRepeatInsideFor() {
        String source = """
            for i = 1 to 2 loop
                repeat 2 times
                    print i
                end repeat
            end for
            """;
        execute(source);
        
        assertEquals("1\n1\n2\n2\n", getOutput());
    }
    
    @Test
    @DisplayName("Test for loop inside repeat")
    public void testForInsideRepeat() {
        String source = """
            repeat 2 times
                for i = 1 to 2 loop
                    print i
                end for
            end repeat
            """;
        execute(source);
        
        assertEquals("1\n2\n1\n2\n", getOutput());
    }
    
    // ===== Complex Loop Tests =====
    
    @Test
    @DisplayName("Test loop with conditionals")
    public void testLoopWithConditionals() {
        String source = """
            for i = 1 to 5 loop
                if i > 3 then print i
            end for
            """;
        execute(source);
        
        assertEquals("4\n5\n", getOutput());
    }
    
    @Test
    @DisplayName("Test loop with complex conditions")
    public void testLoopWithComplexConditions() {
        String source = """
            for i = 1 to 10 loop
                if i > 3 and i < 7 then print i
            end for
            """;
        execute(source);
        
        assertEquals("4\n5\n6\n", getOutput());
    }
    
    @Test
    @DisplayName("Test loop with multiple operations")
    public void testLoopMultipleOperations() {
        String source = """
            for i = 1 to 3 loop
                print i
                print i + 10
            end for
            """;
        execute(source);
        
        assertEquals("1\n11\n2\n12\n3\n13\n", getOutput());
    }
    
    @Test
    @DisplayName("Test loop using variable in calculations")
    public void testLoopVariableCalculations() {
        String source = """
            for i = 1 to 5 loop
                var doubled = i * 2
                print doubled
            end for
            """;
        execute(source);
        
        assertEquals("2\n4\n6\n8\n10\n", getOutput());
    }
}
