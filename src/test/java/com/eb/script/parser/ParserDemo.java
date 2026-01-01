package com.eb.script.parser;

import com.eb.script.ast.*;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;

import java.util.List;

/**
 * Demonstration of the EBS2 Parser.
 * 
 * This program shows how to use the lexer and parser together to
 * parse EBS2 source code into an Abstract Syntax Tree (AST).
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ParserDemo {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 Parser Demonstration ===\n");
        
        demonstrateBasicParsing();
        demonstrateErrorHandling();
        demonstrateComplexExpression();
        
        System.out.println("\n=== Demonstration Complete ===");
    }
    
    /**
     * Demonstrates basic parsing of variable declarations and print statements.
     */
    private static void demonstrateBasicParsing() {
        System.out.println("--- Basic Parsing ---");
        String source = """
            var x = 5
            var name as text = "Alice"
            print name
            """;
        
        System.out.println("Source code:");
        System.out.println(source);
        
        // Tokenize
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        // Parse
        Parser parser = new Parser(tokens);
        List<Statement> statements = parser.parse();
        
        System.out.println("\nParsed " + statements.size() + " statements:");
        for (Statement stmt : statements) {
            System.out.println("  " + stmt);
        }
        
        if (parser.hadError()) {
            System.out.println("\nErrors found:");
            for (ParseError error : parser.getErrors()) {
                System.out.println("  " + error);
            }
        } else {
            System.out.println("\n✓ Parsing successful!");
        }
        System.out.println();
    }
    
    /**
     * Demonstrates error handling with detailed error messages.
     */
    private static void demonstrateErrorHandling() {
        System.out.println("--- Error Handling ---");
        String source = """
            var = 5
            var y = 10
            print y
            """;
        
        System.out.println("Source code (with error on first line):");
        System.out.println(source);
        
        // Tokenize
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        // Parse
        Parser parser = new Parser(tokens);
        List<Statement> statements = parser.parse();
        
        System.out.println("\nParsing results:");
        System.out.println("  Valid statements parsed: " + statements.size());
        
        if (parser.hadError()) {
            System.out.println("  Errors detected: " + parser.getErrors().size());
            System.out.println("\nDetailed error reports:");
            for (ParseError error : parser.getErrors()) {
                System.out.println(error.getDetailedReport());
            }
        }
        System.out.println();
    }
    
    /**
     * Demonstrates parsing of complex expressions with operator precedence.
     */
    private static void demonstrateComplexExpression() {
        System.out.println("--- Complex Expression Parsing ---");
        String source = """
            var result = (10 + 20) * 3
            var valid = x > 5 and y < 10
            if age > 18 then print "Adult" else print "Minor"
            """;
        
        System.out.println("Source code:");
        System.out.println(source);
        
        // Tokenize
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        
        // Parse
        Parser parser = new Parser(tokens);
        List<Statement> statements = parser.parse();
        
        System.out.println("\nParsed " + statements.size() + " statements:");
        for (int i = 0; i < statements.size(); i++) {
            Statement stmt = statements.get(i);
            System.out.println("\nStatement " + (i + 1) + ": " + stmt.getClass().getSimpleName());
            System.out.println("  AST: " + stmt);
            
            // Show details for variable declarations
            if (stmt instanceof VarStatement) {
                VarStatement varStmt = (VarStatement) stmt;
                System.out.println("  Variable name: " + varStmt.getName().getLexeme());
                if (varStmt.getInitializer() != null) {
                    System.out.println("  Initializer type: " + varStmt.getInitializer().getClass().getSimpleName());
                }
            }
            
            // Show details for if statements
            if (stmt instanceof IfStatement) {
                IfStatement ifStmt = (IfStatement) stmt;
                System.out.println("  Condition type: " + ifStmt.getCondition().getClass().getSimpleName());
                System.out.println("  Then branch: " + ifStmt.getThenBranch().getClass().getSimpleName());
                if (ifStmt.getElseBranch() != null) {
                    System.out.println("  Else branch: " + ifStmt.getElseBranch().getClass().getSimpleName());
                }
            }
        }
        
        if (!parser.hadError()) {
            System.out.println("\n✓ All statements parsed successfully!");
        }
        System.out.println();
    }
}
