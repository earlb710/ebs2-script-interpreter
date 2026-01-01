package com.eb.script.parser;

import com.eb.script.ast.*;
import com.eb.script.lexer.Lexer;
import com.eb.script.lexer.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for import statements and parse caching.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class ParserImportTest {
    
    @BeforeEach
    public void setUp() {
        // Clear cache before each test
        Parser.clearCache();
    }
    
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
     * Helper method to parse with cache key.
     */
    private List<Statement> parseWithCache(String source, String cacheKey) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        return parser.parse(cacheKey);
    }
    
    @Test
    @DisplayName("Test parsing simple import statement")
    public void testSimpleImportStatement() {
        String source = "import \"myfile.ebs\"";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof ImportStatement);
        
        ImportStatement importStmt = (ImportStatement) statements.get(0);
        assertEquals("myfile.ebs", importStmt.getFilenameValue());
    }
    
    @Test
    @DisplayName("Test parsing multiple import statements")
    public void testMultipleImportStatements() {
        String source = "import \"file1.ebs\"\nimport \"file2.ebs\"\nimport \"file3.ebs\"";
        List<Statement> statements = parse(source);
        
        assertEquals(3, statements.size());
        assertTrue(statements.get(0) instanceof ImportStatement);
        assertTrue(statements.get(1) instanceof ImportStatement);
        assertTrue(statements.get(2) instanceof ImportStatement);
        
        ImportStatement import1 = (ImportStatement) statements.get(0);
        ImportStatement import2 = (ImportStatement) statements.get(1);
        ImportStatement import3 = (ImportStatement) statements.get(2);
        
        assertEquals("file1.ebs", import1.getFilenameValue());
        assertEquals("file2.ebs", import2.getFilenameValue());
        assertEquals("file3.ebs", import3.getFilenameValue());
    }
    
    @Test
    @DisplayName("Test imports are placed before other statements")
    public void testImportsPlacedFirst() {
        String source = "var x = 5\nimport \"file1.ebs\"\nprint x\nimport \"file2.ebs\"";
        List<Statement> statements = parse(source);
        
        assertEquals(4, statements.size());
        
        // First two should be imports (reordered)
        assertTrue(statements.get(0) instanceof ImportStatement);
        assertTrue(statements.get(1) instanceof ImportStatement);
        
        // Then var and print
        assertTrue(statements.get(2) instanceof VarStatement);
        assertTrue(statements.get(3) instanceof PrintStatement);
        
        ImportStatement import1 = (ImportStatement) statements.get(0);
        ImportStatement import2 = (ImportStatement) statements.get(1);
        
        assertEquals("file1.ebs", import1.getFilenameValue());
        assertEquals("file2.ebs", import2.getFilenameValue());
    }
    
    @Test
    @DisplayName("Test error when import comes after non-import statement")
    public void testImportAfterNonImportError() {
        String source = "var x = 5\nimport \"file.ebs\"";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        parser.parse();
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
        
        ParseError error = parser.getErrors().get(0);
        assertTrue(error.getMessage().toLowerCase().contains("import"));
        assertTrue(error.getMessage().toLowerCase().contains("before"));
    }
    
    @Test
    @DisplayName("Test import with single quotes")
    public void testImportWithSingleQuotes() {
        String source = "import 'myfile.ebs'";
        List<Statement> statements = parse(source);
        
        assertEquals(1, statements.size());
        assertTrue(statements.get(0) instanceof ImportStatement);
        
        ImportStatement importStmt = (ImportStatement) statements.get(0);
        assertEquals("myfile.ebs", importStmt.getFilenameValue());
    }
    
    @Test
    @DisplayName("Test error on import without string literal")
    public void testImportWithoutStringLiteral() {
        String source = "import myfile";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        parser.parse();
        
        assertTrue(parser.hadError());
        assertFalse(parser.getErrors().isEmpty());
    }
    
    @Test
    @DisplayName("Test parse caching works")
    public void testParseCaching() {
        String source = "var x = 5\nprint x";
        String cacheKey = "test_cache_key";
        
        // First parse - should cache
        List<Statement> statements1 = parseWithCache(source, cacheKey);
        assertEquals(2, statements1.size());
        
        // Check cache size
        assertEquals(1, Parser.getCacheSize());
        
        // Second parse - should use cache
        List<Statement> statements2 = parseWithCache(source, cacheKey);
        assertEquals(2, statements2.size());
        
        // Verify cache was used (both results should be equal)
        assertEquals(statements1.size(), statements2.size());
    }
    
    @Test
    @DisplayName("Test cache clearing")
    public void testCacheClearing() {
        String source = "var x = 5";
        String cacheKey = "test_key";
        
        // Parse and cache
        parseWithCache(source, cacheKey);
        assertEquals(1, Parser.getCacheSize());
        
        // Clear cache
        Parser.clearCache();
        assertEquals(0, Parser.getCacheSize());
    }
    
    @Test
    @DisplayName("Test cache removal")
    public void testCacheRemoval() {
        String source1 = "var x = 5";
        String source2 = "var y = 10";
        String key1 = "key1";
        String key2 = "key2";
        
        // Parse and cache both
        parseWithCache(source1, key1);
        parseWithCache(source2, key2);
        assertEquals(2, Parser.getCacheSize());
        
        // Remove one entry
        Parser.removeCacheEntry(key1);
        assertEquals(1, Parser.getCacheSize());
    }
    
    @Test
    @DisplayName("Test parsing without cache key doesn't cache")
    public void testParseWithoutCaching() {
        String source = "var x = 5";
        
        // Parse without cache key
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        parser.parse();
        
        // Cache should be empty
        assertEquals(0, Parser.getCacheSize());
    }
    
    @Test
    @DisplayName("Test import statement toString")
    public void testImportToString() {
        String source = "import \"test.ebs\"";
        List<Statement> statements = parse(source);
        
        ImportStatement importStmt = (ImportStatement) statements.get(0);
        String str = importStmt.toString();
        
        assertTrue(str.contains("Import"));
        assertTrue(str.contains("test.ebs"));
    }
    
    @Test
    @DisplayName("Test mixed imports and statements are properly ordered")
    public void testMixedStatementOrdering() {
        String source = """
            print "start"
            import "lib1.ebs"
            var x = 5
            import "lib2.ebs"
            print x
            """;
        
        List<Statement> statements = parse(source);
        
        assertEquals(5, statements.size());
        
        // First two should be imports
        assertTrue(statements.get(0) instanceof ImportStatement);
        assertTrue(statements.get(1) instanceof ImportStatement);
        
        // Then the other statements in their original order
        assertTrue(statements.get(2) instanceof PrintStatement);
        assertTrue(statements.get(3) instanceof VarStatement);
        assertTrue(statements.get(4) instanceof PrintStatement);
    }
}
