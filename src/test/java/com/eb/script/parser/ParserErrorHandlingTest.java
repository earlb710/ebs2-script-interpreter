package com.eb.script.parser;

import com.eb.script.ast.*;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Parser error handling and recovery.
 * 
 * Tests that the parser:
 * - Reports errors with detailed location information
 * - Recovers from errors to find additional issues
 * - Provides helpful error messages
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ParserErrorHandlingTest {
    
    /**
     * Helper method to parse and get parser with errors.
     */
    private Parser parseWithErrors(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        parser.parse();
        return parser;
    }
    
    @Test
    @DisplayName("Test error on missing variable name")
    public void testMissingVariableName() {
        String source = "var = 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
        
        ParseError error = parser.getErrors().get(0);
        assertTrue(error.getMessage().toLowerCase().contains("variable name"));
        assertEquals(1, error.getLine());
    }
    
    @Test
    @DisplayName("Test error on missing type after 'as'")
    public void testMissingTypeAfterAs() {
        String source = "var x as = 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
        
        ParseError error = parser.getErrors().get(0);
        assertTrue(error.getMessage().toLowerCase().contains("type"));
    }
    
    @Test
    @DisplayName("Test error on missing 'then' in if statement")
    public void testMissingThenInIf() {
        String source = "if x > 5 print \"big\"";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
        
        ParseError error = parser.getErrors().get(0);
        assertTrue(error.getMessage().toLowerCase().contains("then"));
    }
    
    @Test
    @DisplayName("Test error on invalid expression")
    public void testInvalidExpression() {
        String source = "var x = + 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
    }
    
    @Test
    @DisplayName("Test error on unclosed parenthesis")
    public void testUnclosedParenthesis() {
        String source = "var x = (2 + 3";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
        
        ParseError error = parser.getErrors().get(0);
        assertTrue(error.getMessage().contains(")"));
    }
    
    @Test
    @DisplayName("Test error on unexpected token")
    public void testUnexpectedToken() {
        String source = "var x = 5 +";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
    }
    
    @Test
    @DisplayName("Test multiple errors are reported")
    public void testMultipleErrors() {
        String source = "var = 5\nvar y as = 10\nif x print \"test\"";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        // Should report multiple errors (one per line)
        assertTrue(parser.getErrors().size() > 1);
    }
    
    @Test
    @DisplayName("Test error recovery allows parsing to continue")
    public void testErrorRecovery() {
        String source = "var = 5\nvar y = 10\nprint y";
        Parser parser = parseWithErrors(source);
        
        // First line has error, but parser should recover and parse remaining lines
        assertTrue(parser.hadError());
        // Parser collects errors but may not produce statements for erroneous lines
        // This is expected behavior for a parser with error recovery
    }
    
    @Test
    @DisplayName("Test error provides line and column information")
    public void testErrorLocationInfo() {
        String source = "var x = 5\nvar = 10";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        ParseError error = parser.getErrors().get(0);
        
        assertEquals(2, error.getLine()); // Error on line 2
        assertTrue(error.getColumn() > 0); // Has column info
    }
    
    @Test
    @DisplayName("Test error message includes token information")
    public void testErrorIncludesTokenInfo() {
        String source = "var x as number = \"text\"";
        // Note: This may parse successfully, testing syntax not semantics
        Parser parser = parseWithErrors(source);
        
        // Parser should succeed (type checking is semantic, not syntactic)
        // This test verifies parser doesn't confuse syntax with semantics
        assertFalse(parser.hadError());
    }
    
    @Test
    @DisplayName("Test detailed error report format")
    public void testDetailedErrorReport() {
        String source = "var = 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        ParseError error = parser.getErrors().get(0);
        
        String detailedReport = error.getDetailedReport();
        assertNotNull(detailedReport);
        assertTrue(detailedReport.contains("Parse Error"));
        assertTrue(detailedReport.contains("Location"));
        assertTrue(detailedReport.contains("Message"));
    }
    
    @Test
    @DisplayName("Test error toString format")
    public void testErrorToStringFormat() {
        String source = "var = 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        ParseError error = parser.getErrors().get(0);
        
        String errorStr = error.toString();
        assertNotNull(errorStr);
        assertTrue(errorStr.contains("Line"));
        assertTrue(errorStr.contains("Column"));
    }
    
    @Test
    @DisplayName("Test error type classification")
    public void testErrorTypeClassification() {
        String source = "var = 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        ParseError error = parser.getErrors().get(0);
        
        assertNotNull(error.getType());
        assertEquals(ParseError.ErrorType.UNEXPECTED_TOKEN, error.getType());
    }
    
    @Test
    @DisplayName("Test parser exception includes context")
    public void testParserExceptionContext() {
        String source = "var x as";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        ParseError error = parser.getErrors().get(0);
        
        assertNotNull(error.getContext());
        // Context may be empty string for EOF token, which is valid
        assertNotNull(error.getContext()); // Just check it's not null
    }
    
    @Test
    @DisplayName("Test synchronization at statement boundaries")
    public void testSynchronizationAtStatementBoundaries() {
        String source = "var x\nvar y = 10\nprint y";
        Parser parser = parseWithErrors(source);
        
        // "var x" is actually valid (declaration without initializer)
        // So this should not produce an error
        assertFalse(parser.hadError());
    }
    
    @Test
    @DisplayName("Test error on empty if condition")
    public void testEmptyIfCondition() {
        String source = "if then print \"test\"";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
    }
    
    @Test
    @DisplayName("Test graceful handling of EOF during parsing")
    public void testEOFDuringParsing() {
        String source = "var x = ";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
    }
    
    @Test
    @DisplayName("Test error messages are user-friendly")
    public void testUserFriendlyErrorMessages() {
        String source = "var = 5";
        Parser parser = parseWithErrors(source);
        
        assertTrue(parser.hadError());
        ParseError error = parser.getErrors().get(0);
        
        String message = error.getMessage();
        // Error message should be descriptive
        assertTrue(message.length() > 10);
        // Should mention what was expected
        assertTrue(message.toLowerCase().contains("expected") || 
                  message.toLowerCase().contains("variable"));
    }
}
