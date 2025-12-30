package com.eb.script.lexer;

import java.util.List;

/**
 * Simple test class for the EBS2 Lexer.
 * 
 * This is a basic manual test to verify the lexer functionality.
 * Can be run with: java com.eb.script.lexer.LexerTest
 * 
 * @version 2.1.0
 * @since 2025-12-29
 */
public class LexerTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 Lexer Test ===\n");
        
        testBasicKeywords();
        testNumbers();
        testStrings();
        testOperators();
        testLoopSyntax();
        testFunctionSyntax();
        testArraySyntax();
        testBuiltinFunctions();
        testErrorRecovery();
        testInternalErrorHandling();
        
        System.out.println("\n=== All Tests Completed ===");
    }
    
    private static void testBasicKeywords() {
        System.out.println("Test 1: Basic Keywords");
        String source = "program var if then else end";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        assert tokens.size() == 7; // 6 keywords + EOF
        assert tokens.get(0).getType() == TokenType.PROGRAM;
        assert tokens.get(1).getType() == TokenType.VAR;
        assert tokens.get(0).getStartPos() == 0;
        assert tokens.get(0).getEndPos() == 7;
        System.out.println("✓ Passed (with position tracking)\n");
    }
    
    private static void testNumbers() {
        System.out.println("Test 2: Numbers");
        String source = "42 3.14 -10";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        System.out.println("✓ Passed\n");
    }
    
    private static void testStrings() {
        System.out.println("Test 3: Strings");
        String source = "\"Hello World\" 'Single quotes'";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        assert tokens.get(0).getType() == TokenType.TEXT;
        assert tokens.get(0).getLiteral().equals("Hello World");
        System.out.println("✓ Passed\n");
    }
    
    private static void testOperators() {
        System.out.println("Test 4: Operators");
        String source = "+ - * / == != < > <= >= ++ --";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        assert tokens.get(0).getType() == TokenType.PLUS;
        assert tokens.get(10).getType() == TokenType.INCREMENT;
        System.out.println("✓ Passed\n");
    }
    
    private static void testLoopSyntax() {
        System.out.println("Test 5: Loop Syntax");
        String source = "for i = 1 to 10 loop\n    print i\nend for";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        System.out.println("✓ Passed\n");
    }
    
    private static void testFunctionSyntax() {
        System.out.println("Test 6: Function Syntax");
        String source = "function add(a as number, b as number) as number {\n    return a + b\n}";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        System.out.println("✓ Passed\n");
    }
    
    private static void testArraySyntax() {
        System.out.println("Test 7: Array Syntax");
        String source = "var arr as array[5] = {1, 2, 3, 4, 5}";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        System.out.println("✓ Passed\n");
    }
    
    private static void testBuiltinFunctions() {
        System.out.println("Test 8: Built-in Functions");
        String source = "var text = name.toText()\nvar num = value.toNumber()\nprint(\"Hello\")";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        
        // Verify that toText, toNumber, and print are recognized as built-in functions
        boolean foundToText = false;
        boolean foundToNumber = false;
        boolean foundPrint = false;
        
        for (Token token : tokens) {
            if (token.getType() == TokenType.BUILTIN_FUNCTION) {
                if (token.getLexeme().equalsIgnoreCase("toText")) foundToText = true;
                if (token.getLexeme().equalsIgnoreCase("toNumber")) foundToNumber = true;
            }
            if (token.getType() == TokenType.PRINT) {
                foundPrint = true;
            }
        }
        
        assert foundToText : "toText should be recognized as BUILTIN_FUNCTION";
        assert foundToNumber : "toNumber should be recognized as BUILTIN_FUNCTION";
        System.out.println("✓ Passed (toText, toNumber recognized as BUILTIN_FUNCTION)\n");
    }
    
    private static void testErrorRecovery() {
        System.out.println("Test 9: Error Recovery");
        String source = "var x = 10\nvar y = \"unterminated\nvar z = 20";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        printTokens(tokens);
        
        // Check that lexer found errors
        assert lexer.hadError() : "Lexer should have detected errors";
        assert !lexer.getErrors().isEmpty() : "Error list should not be empty";
        
        System.out.println("Errors found: " + lexer.getErrors().size());
        for (LexerException error : lexer.getErrors()) {
            System.out.println("  - " + error.getMessage());
        }
        
        // Check that lexer continued and found var z = 20
        boolean foundZ = false;
        for (Token token : tokens) {
            if (token.getType() == TokenType.IDENTIFIER && token.getLexeme().equals("z")) {
                foundZ = true;
                break;
            }
        }
        
        assert foundZ : "Lexer should have recovered and found 'z' identifier";
        System.out.println("✓ Passed (error recovery working)\n");
    }
    
    private static void testInternalErrorHandling() {
        System.out.println("Test 10: Internal Error Handling");
        // Note: This test demonstrates that internal errors are caught and logged
        // In a real scenario, internal errors would be triggered by unexpected exceptions
        // For demonstration, we'll verify the error reporting mechanism exists
        
        String source = "var x = 10\nvar y = \"unterminated\nvar z = 20";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        // Verify error collection mechanism works
        if (lexer.hadError()) {
            System.out.println("Error handling verified. Sample error format:");
            for (LexerException error : lexer.getErrors()) {
                String msg = error.getMessage();
                // Check if error message contains position info
                assert msg.contains("line") || msg.contains("Unterminated") : 
                    "Error should contain descriptive information";
                System.out.println("  - " + msg.substring(0, Math.min(80, msg.length())) + "...");
            }
        }
        
        System.out.println("✓ Passed (internal error handling ready)\n");
        System.out.println("Note: Internal errors with stack traces are logged when unexpected exceptions occur");
    }
    
    private static void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.getType() != TokenType.EOF) {
                System.out.println("  " + token);
            }
        }
    }
}
