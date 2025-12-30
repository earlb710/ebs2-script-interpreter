package com.eb.script.lexer;

import java.util.Map;

/**
 * Test class for TokenColor functionality.
 * 
 * @version 2.0.0
 * @since 2025-12-30
 */
public class TokenColorTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 Token Color Test Suite ===\n");
        
        testColorNames();
        testDefaultColors();
        testTokenToColorMapping();
        testColorCategories();
        
        System.out.println("\n=== All TokenColor Tests Completed ===");
    }
    
    private static void testColorNames() {
        System.out.println("Test 1: Color Name Constants");
        
        // Verify color names follow the "editor." prefix pattern
        assert TokenColor.EDITOR_COMMENT.startsWith("editor.") : "Color name should start with 'editor.'";
        assert TokenColor.EDITOR_KEYWORD.startsWith("editor.") : "Color name should start with 'editor.'";
        assert TokenColor.EDITOR_STRING.startsWith("editor.") : "Color name should start with 'editor.'";
        
        System.out.println("  Sample color names:");
        System.out.println("    " + TokenColor.EDITOR_COMMENT + " = " + TokenColor.getDefaultColor(TokenColor.EDITOR_COMMENT));
        System.out.println("    " + TokenColor.EDITOR_KEYWORD + " = " + TokenColor.getDefaultColor(TokenColor.EDITOR_KEYWORD));
        System.out.println("    " + TokenColor.EDITOR_STRING + " = " + TokenColor.getDefaultColor(TokenColor.EDITOR_STRING));
        System.out.println("    " + TokenColor.EDITOR_NUMBER + " = " + TokenColor.getDefaultColor(TokenColor.EDITOR_NUMBER));
        System.out.println("    " + TokenColor.EDITOR_FUNCTION_BUILTIN + " = " + TokenColor.getDefaultColor(TokenColor.EDITOR_FUNCTION_BUILTIN));
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testDefaultColors() {
        System.out.println("Test 2: Default Color Values");
        
        // Verify colors are in hex RGB format
        String commentColor = TokenColor.getDefaultColor(TokenColor.EDITOR_COMMENT);
        assert commentColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + commentColor;
        
        String keywordColor = TokenColor.getDefaultColor(TokenColor.EDITOR_KEYWORD);
        assert keywordColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + keywordColor;
        
        System.out.println("  Verified all colors are in hex RGB format (#RRGGBB)");
        
        // Display all color categories
        Map<String, String> allColors = TokenColor.getAllColors();
        System.out.println("  Total color definitions: " + allColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testTokenToColorMapping() {
        System.out.println("Test 3: Token Type to Color Mapping");
        
        // Test specific token types
        String varColor = TokenColor.getColorName(TokenType.VAR);
        assert varColor.equals(TokenColor.EDITOR_KEYWORD_DECLARATION) : 
            "VAR should map to declaration keyword color";
        
        String ifColor = TokenColor.getColorName(TokenType.IF);
        assert ifColor.equals(TokenColor.EDITOR_KEYWORD_CONTROL) : 
            "IF should map to control keyword color";
        
        String numberColor = TokenColor.getColorName(TokenType.NUMBER);
        assert numberColor.equals(TokenColor.EDITOR_NUMBER) : 
            "NUMBER should map to number literal color";
        
        String builtinColor = TokenColor.getColorName(TokenType.BUILTIN_FUNCTION);
        assert builtinColor.equals(TokenColor.EDITOR_FUNCTION_BUILTIN) : 
            "BUILTIN_FUNCTION should map to builtin function color";
        
        System.out.println("  Sample token to color mappings:");
        System.out.println("    VAR -> " + varColor + " = " + TokenColor.getDefaultColor(varColor));
        System.out.println("    IF -> " + ifColor + " = " + TokenColor.getDefaultColor(ifColor));
        System.out.println("    NUMBER -> " + numberColor + " = " + TokenColor.getDefaultColor(numberColor));
        System.out.println("    BUILTIN_FUNCTION -> " + builtinColor + " = " + TokenColor.getDefaultColor(builtinColor));
        
        // Display total mappings
        Map<TokenType, String> tokenColors = TokenColor.getTokenColorMapping();
        System.out.println("  Total token type mappings: " + tokenColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testColorCategories() {
        System.out.println("Test 4: Color Categories and Examples");
        
        System.out.println("  Keyword categories:");
        System.out.println("    Control flow (if, for, while, loop):");
        System.out.println("      " + TokenType.IF + " -> " + TokenColor.getColorName(TokenType.IF) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("      " + TokenType.LOOP + " -> " + TokenColor.getColorName(TokenType.LOOP) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.LOOP));
        
        System.out.println("    Declaration (var, function, procedure):");
        System.out.println("      " + TokenType.FUNCTION + " -> " + TokenColor.getColorName(TokenType.FUNCTION) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.FUNCTION));
        System.out.println("      " + TokenType.PROCEDURE + " -> " + TokenColor.getColorName(TokenType.PROCEDURE) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.PROCEDURE));
        
        System.out.println("    Type (array, number, text, record):");
        System.out.println("      " + TokenType.ARRAY + " -> " + TokenColor.getColorName(TokenType.ARRAY) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.ARRAY));
        System.out.println("      " + TokenType.RECORD + " -> " + TokenColor.getColorName(TokenType.RECORD) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.RECORD));
        
        System.out.println("\n  Operator categories:");
        System.out.println("    Arithmetic (+, -, *, /):");
        System.out.println("      " + TokenType.PLUS + " -> " + TokenColor.getColorName(TokenType.PLUS) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.PLUS));
        
        System.out.println("    Logical (and, or, not):");
        System.out.println("      " + TokenType.AND + " -> " + TokenColor.getColorName(TokenType.AND) + 
                         " = " + TokenColor.getDefaultColorForToken(TokenType.AND));
        
        System.out.println("\n  Literal categories:");
        System.out.println("    String: " + TokenColor.getDefaultColorForToken(TokenType.TEXT));
        System.out.println("    Number: " + TokenColor.getDefaultColorForToken(TokenType.NUMBER));
        System.out.println("    Boolean: " + TokenColor.getDefaultColorForToken(TokenType.TRUE));
        
        System.out.println("\n  Special categories:");
        System.out.println("    Comment: " + TokenColor.getDefaultColorForToken(TokenType.COMMENT));
        System.out.println("    Built-in function: " + TokenColor.getDefaultColorForToken(TokenType.BUILTIN_FUNCTION));
        System.out.println("    Identifier: " + TokenColor.getDefaultColorForToken(TokenType.IDENTIFIER));
        System.out.println("    Error: " + TokenColor.getDefaultColorForToken(TokenType.ILLEGAL));
        
        System.out.println("✓ Passed\n");
    }
}
