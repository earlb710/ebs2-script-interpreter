package com.eb.cli;

import com.eb.script.console.Console;
import com.eb.script.console.Handler;
import com.eb.script.console.ScriptArea;
import com.eb.script.console.ConsoleConfig;
import com.eb.script.ast.Statement;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import com.eb.script.parser.Parser;
import com.eb.script.runtime.Interpreter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * JavaFX Console application for interactive EBS2 script execution.
 * 
 * This is the main executable for running the EBS2 interactive console.
 * It provides a JavaFX-based IDE with syntax highlighting, command history,
 * and interactive script execution.
 * 
 * Usage:
 *   mvn javafx:run
 *   java -jar ebs2-interpreter-javafx.jar
 * 
 * @version 2.0.0
 * @since 2026-01-02
 */
public class ConsoleApp extends Application {
    
    private static final String APP_TITLE = "EBS2 Interactive Console";
    private static final String VERSION = "2.0.0";
    
    private Console console;
    private ConsoleConfig config;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load console configuration
            config = new ConsoleConfig();
            
            // Create console handler
            Handler handler = new EBS2ConsoleHandler();
            
            // Create console
            console = new Console(handler);
            
            // Create main layout
            TabPane tabPane = new TabPane();
            tabPane.getTabs().add(console.getConsoleTab());
            
            BorderPane root = new BorderPane();
            root.setCenter(tabPane);
            
            // Create scene with loaded configuration
            Scene scene = new Scene(root, 900, 700);
            
            // Apply console stylesheet if available
            try {
                String stylesheet = getClass().getResource("/console.css").toExternalForm();
                if (stylesheet != null) {
                    scene.getStylesheets().add(stylesheet);
                }
            } catch (Exception e) {
                // CSS file not found - continue without it
            }
            
            // Configure stage
            primaryStage.setTitle(APP_TITLE + " v" + VERSION);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Request focus on console
            Platform.runLater(() -> console.requestFocus());
            
            // Print welcome message
            printWelcomeMessage();
            
        } catch (Exception e) {
            System.err.println("Error starting console application: " + e.getMessage());
            e.printStackTrace();
            Platform.exit();
        }
    }
    
    @Override
    public void stop() {
        // No configuration to save
    }
    
    /**
     * Prints a welcome message to the console.
     */
    private void printWelcomeMessage() {
        console.getOutputArea().printStyled("=== " + APP_TITLE + " v" + VERSION + " ===\n", "info");
        console.getOutputArea().printStyled("Type EBS2 code in the input area below and press Ctrl+Enter to execute.\n", "info");
        console.getOutputArea().printStyled("Press Ctrl+L to clear output, Up/Down arrows for command history.\n", "info");
        console.getOutputArea().printStyled("\n", "info");
    }
    
    /**
     * Handler implementation for EBS2 console commands.
     */
    private static class EBS2ConsoleHandler implements Handler {
        
        private ScriptArea outputArea;
        
        @Override
        public void submit(String... lines) throws Exception {
            String code = String.join("\n", lines);
            
            if (code.trim().isEmpty()) {
                return;
            }
            
            // Capture output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            PrintStream originalErr = System.err;
            
            try {
                PrintStream ps = new PrintStream(baos, true, "UTF-8");
                System.setOut(ps);
                System.setErr(ps);
                
                // Lex the source code
                Lexer lexer = new Lexer(code);
                List<Token> tokens = lexer.scanTokens();
                
                // Parse the tokens
                Parser parser = new Parser(tokens);
                List<Statement> statements = parser.parse();
                
                // Check for parse errors
                if (parser.hadError()) {
                    for (com.eb.script.parser.ParseError error : parser.getErrors()) {
                        if (outputArea != null) {
                            outputArea.printStyled(error.toString() + "\n", "error");
                        }
                    }
                    return;
                }
                
                // Execute the statements
                Interpreter interpreter = new Interpreter();
                interpreter.execute(statements);
                
                // Display output
                String output = baos.toString("UTF-8");
                if (!output.isEmpty() && outputArea != null) {
                    outputArea.printStyled(output, "info");
                    if (!output.endsWith("\n")) {
                        outputArea.printStyled("\n", "info");
                    }
                }
                
            } catch (Exception e) {
                if (outputArea != null) {
                    outputArea.printStyled("Error: " + e.getMessage() + "\n", "error");
                }
            } finally {
                System.setOut(originalOut);
                System.setErr(originalErr);
            }
        }
        
        @Override
        public void submitErrors(String... lines) {
            if (outputArea != null) {
                for (String line : lines) {
                    outputArea.printStyled(line + "\n", "error");
                }
            }
        }
        
        @Override
        public Object callBuiltin(String builtin, Object... args) throws Exception {
            // Builtin functions can be implemented here
            throw new UnsupportedOperationException("Builtin not implemented: " + builtin);
        }
        
        @Override
        public void setUI_outputArea(ScriptArea outputArea) {
            this.outputArea = outputArea;
        }
    }
    
    /**
     * Main entry point for the console application.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
