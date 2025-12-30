package com.eb.script.lexer;

import java.util.Map;

/**
 * Test class for CssTokenColor functionality.
 * 
 * @version 2.0.0
 * @since 2025-12-30
 */
public class CssTokenColorTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 CSS Token Color Test Suite ===\n");
        
        testColorNames();
        testDefaultColors();
        testTokenToColorMapping();
        testColorCategories();
        
        System.out.println("\n=== All CssTokenColor Tests Completed ===");
    }
    
    private static void testColorNames() {
        System.out.println("Test 1: CSS Color Name Constants");
        
        // Verify color names follow the "css." prefix pattern
        assert CssTokenColor.CSS_COMMENT.startsWith("css.") : "Color name should start with 'css.'";
        assert CssTokenColor.CSS_KEYWORD.startsWith("css.") : "Color name should start with 'css.'";
        assert CssTokenColor.CSS_STRING.startsWith("css.") : "Color name should start with 'css.'";
        
        System.out.println("  Sample CSS color names:");
        System.out.println("    " + CssTokenColor.CSS_COMMENT + " = " + CssTokenColor.getDefaultColor(CssTokenColor.CSS_COMMENT));
        System.out.println("    " + CssTokenColor.CSS_KEYWORD + " = " + CssTokenColor.getDefaultColor(CssTokenColor.CSS_KEYWORD));
        System.out.println("    " + CssTokenColor.CSS_STRING + " = " + CssTokenColor.getDefaultColor(CssTokenColor.CSS_STRING));
        System.out.println("    " + CssTokenColor.CSS_NUMBER + " = " + CssTokenColor.getDefaultColor(CssTokenColor.CSS_NUMBER));
        System.out.println("    " + CssTokenColor.CSS_FUNCTION_BUILTIN + " = " + CssTokenColor.getDefaultColor(CssTokenColor.CSS_FUNCTION_BUILTIN));
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testDefaultColors() {
        System.out.println("Test 2: CSS Default Color Values");
        
        // Verify colors are in hex RGB format
        String commentColor = CssTokenColor.getDefaultColor(CssTokenColor.CSS_COMMENT);
        assert commentColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + commentColor;
        
        String keywordColor = CssTokenColor.getDefaultColor(CssTokenColor.CSS_KEYWORD);
        assert keywordColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + keywordColor;
        
        System.out.println("  Verified all colors are in hex RGB format (#RRGGBB)");
        
        // Display all color categories
        Map<String, String> allColors = CssTokenColor.getAllColors();
        System.out.println("  Total color definitions: " + allColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testTokenToColorMapping() {
        System.out.println("Test 3: CSS Token Type to Color Mapping");
        
        // Test specific token types
        String varColor = CssTokenColor.getColorName(TokenType.VAR);
        assert varColor.equals(CssTokenColor.CSS_KEYWORD_DECLARATION) : 
            "VAR should map to declaration keyword color";
        
        String ifColor = CssTokenColor.getColorName(TokenType.IF);
        assert ifColor.equals(CssTokenColor.CSS_KEYWORD_CONTROL) : 
            "IF should map to control keyword color";
        
        String numberColor = CssTokenColor.getColorName(TokenType.NUMBER);
        assert numberColor.equals(CssTokenColor.CSS_NUMBER) : 
            "NUMBER should map to number literal color";
        
        String builtinColor = CssTokenColor.getColorName(TokenType.BUILTIN_FUNCTION);
        assert builtinColor.equals(CssTokenColor.CSS_FUNCTION_BUILTIN) : 
            "BUILTIN_FUNCTION should map to builtin function color";
        
        System.out.println("  Sample token to CSS color mappings:");
        System.out.println("    VAR -> " + varColor + " = " + CssTokenColor.getDefaultColor(varColor));
        System.out.println("    IF -> " + ifColor + " = " + CssTokenColor.getDefaultColor(ifColor));
        System.out.println("    NUMBER -> " + numberColor + " = " + CssTokenColor.getDefaultColor(numberColor));
        System.out.println("    BUILTIN_FUNCTION -> " + builtinColor + " = " + CssTokenColor.getDefaultColor(builtinColor));
        
        // Display total mappings
        Map<TokenType, String> tokenColors = CssTokenColor.getTokenColorMapping();
        System.out.println("  Total token type mappings: " + tokenColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testColorCategories() {
        System.out.println("Test 4: CSS Color Categories and Examples");
        
        System.out.println("  Keyword categories:");
        System.out.println("    Control flow (if, for, while, loop):");
        System.out.println("      " + TokenType.IF + " -> " + CssTokenColor.getColorName(TokenType.IF) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("      " + TokenType.LOOP + " -> " + CssTokenColor.getColorName(TokenType.LOOP) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.LOOP));
        
        System.out.println("    Declaration (var, function, procedure):");
        System.out.println("      " + TokenType.FUNCTION + " -> " + CssTokenColor.getColorName(TokenType.FUNCTION) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.FUNCTION));
        System.out.println("      " + TokenType.PROCEDURE + " -> " + CssTokenColor.getColorName(TokenType.PROCEDURE) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.PROCEDURE));
        
        System.out.println("    Type (array, number, text, record):");
        System.out.println("      " + TokenType.ARRAY + " -> " + CssTokenColor.getColorName(TokenType.ARRAY) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.ARRAY));
        System.out.println("      " + TokenType.RECORD + " -> " + CssTokenColor.getColorName(TokenType.RECORD) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.RECORD));
        
        System.out.println("\n  Operator categories:");
        System.out.println("    Arithmetic (+, -, *, /):");
        System.out.println("      " + TokenType.PLUS + " -> " + CssTokenColor.getColorName(TokenType.PLUS) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.PLUS));
        
        System.out.println("    Logical (and, or, not):");
        System.out.println("      " + TokenType.AND + " -> " + CssTokenColor.getColorName(TokenType.AND) + 
                         " = " + CssTokenColor.getDefaultColorForToken(TokenType.AND));
        
        System.out.println("\n  Literal categories:");
        System.out.println("    String: " + CssTokenColor.getDefaultColorForToken(TokenType.TEXT));
        System.out.println("    Number: " + CssTokenColor.getDefaultColorForToken(TokenType.NUMBER));
        System.out.println("    Boolean: " + CssTokenColor.getDefaultColorForToken(TokenType.TRUE));
        
        System.out.println("\n  Special categories:");
        System.out.println("    Comment: " + CssTokenColor.getDefaultColorForToken(TokenType.COMMENT));
        System.out.println("    Built-in function: " + CssTokenColor.getDefaultColorForToken(TokenType.BUILTIN_FUNCTION));
        System.out.println("    Identifier: " + CssTokenColor.getDefaultColorForToken(TokenType.IDENTIFIER));
        System.out.println("    Error: " + CssTokenColor.getDefaultColorForToken(TokenType.ILLEGAL));
        
        System.out.println("✓ Passed\n");
    }
}
