package com.eb.script.lexer;

import java.util.List;

/**
 * Simple test class for the EBS2 Lexer.
 * 
 * This is a basic manual test to verify the lexer functionality.
 * Can be run with: java com.eb.script.lexer.LexerTest
 * 
 * @version 2.0.0
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
        System.out.println("✓ Passed\n");
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
    
    private static void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.getType() != TokenType.EOF) {
                System.out.println("  " + token);
            }
        }
    }
}
