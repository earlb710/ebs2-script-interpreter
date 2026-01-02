package com.eb.script.runtime;

import com.eb.script.ast.Statement;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import com.eb.script.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Demo program to run the interpreter on an example EBS2 script.
 * 
 * This demonstrates how to use the lexer, parser, and interpreter together
 * to execute EBS2 code.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class InterpreterDemo {
    
    public static void main(String[] args) {
        String filename = "doc/examples/12_interpreter_demo.ebs";
        
        if (args.length > 0) {
            filename = args[0];
        }
        
        try {
            // Read the source file
            String source = Files.readString(Paths.get(filename));
            
            // Lex the source code
            Lexer lexer = new Lexer(source);
            List<Token> tokens = lexer.scanTokens();
            
            // Parse the tokens
            Parser parser = new Parser(tokens);
            List<Statement> statements = parser.parse();
            
            // Check for parse errors
            if (parser.hadError()) {
                System.err.println("Parse errors:");
                parser.getErrors().forEach(error -> System.err.println("  " + error));
                System.exit(1);
            }
            
            // Execute the statements
            Interpreter interpreter = new Interpreter();
            interpreter.execute(statements);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Runtime error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
