package com.eb.cli;

import com.eb.script.ast.Statement;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import com.eb.script.parser.Parser;
import com.eb.script.runtime.Interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command-line application for executing EBS2 scripts.
 * 
 * This is the main executable for running EBS2 scripts from the command line.
 * It accepts a script filename as an argument, parses it, and executes it.
 * 
 * Usage:
 *   java -jar ebs2-interpreter.jar [options] <script.ebs>
 *   java -cp ... com.eb.cli.CommandLineApp [options] <script.ebs>
 * 
 * Options:
 *   -h, --help     Show help message
 *   -v, --version  Show version information
 * 
 * @version 2.0.0
 * @since 2026-01-02
 */
public class CommandLineApp {
    
    private static final String VERSION = "2.0.0";
    private static final String APP_NAME = "EBS2 Script Interpreter";
    
    public static void main(String[] args) {
        // Check for help or version flags
        if (args.length == 0 || hasFlag(args, "-h", "--help")) {
            printHelp();
            System.exit(0);
        }
        
        if (hasFlag(args, "-v", "--version")) {
            printVersion();
            System.exit(0);
        }
        
        // Get the script filename (last non-flag argument)
        String filename = getScriptFilename(args);
        if (filename == null) {
            System.err.println("Error: No script file specified");
            System.err.println("Use --help for usage information");
            System.exit(1);
        }
        
        // Execute the script
        try {
            executeScript(filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Runtime error: " + e.getMessage());
            if (System.getenv("EBS2_DEBUG") != null) {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }
    
    /**
     * Executes an EBS2 script from a file.
     * 
     * @param filename the script file to execute
     * @throws IOException if there's an error reading the file
     */
    private static void executeScript(String filename) throws IOException {
        Path path = Paths.get(filename);
        
        // Check if file exists
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filename);
        }
        
        // Read the source file
        String source = Files.readString(path);
        
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
    }
    
    /**
     * Checks if any of the given flags are present in the arguments.
     * 
     * @param args command-line arguments
     * @param flags flags to check for
     * @return true if any flag is present
     */
    private static boolean hasFlag(String[] args, String... flags) {
        for (String arg : args) {
            for (String flag : flags) {
                if (flag.equals(arg)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Gets the script filename from command-line arguments.
     * Returns the last non-flag argument.
     * 
     * @param args command-line arguments
     * @return script filename or null if not found
     */
    private static String getScriptFilename(String[] args) {
        for (int i = args.length - 1; i >= 0; i--) {
            if (!args[i].startsWith("-")) {
                return args[i];
            }
        }
        return null;
    }
    
    /**
     * Prints help information.
     */
    private static void printHelp() {
        System.out.println(APP_NAME + " v" + VERSION);
        System.out.println();
        System.out.println("Usage: ebs2 [options] <script.ebs>");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  -h, --help     Show this help message");
        System.out.println("  -v, --version  Show version information");
        System.out.println();
        System.out.println("Environment Variables:");
        System.out.println("  EBS2_DEBUG     Set to any value to enable debug output");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  ebs2 script.ebs");
        System.out.println("  ebs2 examples/hello.ebs");
        System.out.println("  java -jar ebs2-interpreter.jar script.ebs");
        System.out.println();
        System.out.println("For more information, visit:");
        System.out.println("  https://github.com/earlb710/ebs2-script-interpreter");
    }
    
    /**
     * Prints version information.
     */
    private static void printVersion() {
        System.out.println(APP_NAME + " v" + VERSION);
        System.out.println("Copyright (c) 2026 Earl Bosch");
        System.out.println("License: MIT");
    }
}
