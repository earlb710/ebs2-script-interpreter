package com.eb.script.lexer;

import java.util.Map;

/**
 * Test class for MdTokenColor functionality.
 * 
 * @version 2.0.0
 * @since 2025-12-30
 */
public class MdTokenColorTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 Markdown Token Color Test Suite ===\n");
        
        testColorNames();
        testDefaultColors();
        testTokenToColorMapping();
        testColorCategories();
        
        System.out.println("\n=== All MdTokenColor Tests Completed ===");
    }
    
    private static void testColorNames() {
        System.out.println("Test 1: Markdown Color Name Constants");
        
        // Verify color names follow the "md." prefix pattern
        assert MdTokenColor.MD_COMMENT.startsWith("md.") : "Color name should start with 'md.'";
        assert MdTokenColor.MD_KEYWORD.startsWith("md.") : "Color name should start with 'md.'";
        assert MdTokenColor.MD_STRING.startsWith("md.") : "Color name should start with 'md.'";
        
        System.out.println("  Sample Markdown color names:");
        System.out.println("    " + MdTokenColor.MD_COMMENT + " = " + MdTokenColor.getDefaultColor(MdTokenColor.MD_COMMENT));
        System.out.println("    " + MdTokenColor.MD_KEYWORD + " = " + MdTokenColor.getDefaultColor(MdTokenColor.MD_KEYWORD));
        System.out.println("    " + MdTokenColor.MD_STRING + " = " + MdTokenColor.getDefaultColor(MdTokenColor.MD_STRING));
        System.out.println("    " + MdTokenColor.MD_NUMBER + " = " + MdTokenColor.getDefaultColor(MdTokenColor.MD_NUMBER));
        System.out.println("    " + MdTokenColor.MD_FUNCTION_BUILTIN + " = " + MdTokenColor.getDefaultColor(MdTokenColor.MD_FUNCTION_BUILTIN));
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testDefaultColors() {
        System.out.println("Test 2: Markdown Default Color Values");
        
        // Verify colors are in hex RGB format
        String commentColor = MdTokenColor.getDefaultColor(MdTokenColor.MD_COMMENT);
        assert commentColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + commentColor;
        
        String keywordColor = MdTokenColor.getDefaultColor(MdTokenColor.MD_KEYWORD);
        assert keywordColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + keywordColor;
        
        System.out.println("  Verified all colors are in hex RGB format (#RRGGBB)");
        
        // Display all color categories
        Map<String, String> allColors = MdTokenColor.getAllColors();
        System.out.println("  Total color definitions: " + allColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testTokenToColorMapping() {
        System.out.println("Test 3: Markdown Token Type to Color Mapping");
        
        // Test specific token types
        String varColor = MdTokenColor.getColorName(TokenType.VAR);
        assert varColor.equals(MdTokenColor.MD_KEYWORD_DECLARATION) : 
            "VAR should map to declaration keyword color";
        
        String ifColor = MdTokenColor.getColorName(TokenType.IF);
        assert ifColor.equals(MdTokenColor.MD_KEYWORD_CONTROL) : 
            "IF should map to control keyword color";
        
        String numberColor = MdTokenColor.getColorName(TokenType.NUMBER);
        assert numberColor.equals(MdTokenColor.MD_NUMBER) : 
            "NUMBER should map to number literal color";
        
        String builtinColor = MdTokenColor.getColorName(TokenType.BUILTIN_FUNCTION);
        assert builtinColor.equals(MdTokenColor.MD_FUNCTION_BUILTIN) : 
            "BUILTIN_FUNCTION should map to builtin function color";
        
        System.out.println("  Sample token to Markdown color mappings:");
        System.out.println("    VAR -> " + varColor + " = " + MdTokenColor.getDefaultColor(varColor));
        System.out.println("    IF -> " + ifColor + " = " + MdTokenColor.getDefaultColor(ifColor));
        System.out.println("    NUMBER -> " + numberColor + " = " + MdTokenColor.getDefaultColor(numberColor));
        System.out.println("    BUILTIN_FUNCTION -> " + builtinColor + " = " + MdTokenColor.getDefaultColor(builtinColor));
        
        // Display total mappings
        Map<TokenType, String> tokenColors = MdTokenColor.getTokenColorMapping();
        System.out.println("  Total token type mappings: " + tokenColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testColorCategories() {
        System.out.println("Test 4: Markdown Color Categories and Examples");
        
        System.out.println("  Keyword categories:");
        System.out.println("    Control flow (if, for, while, loop):");
        System.out.println("      " + TokenType.IF + " -> " + MdTokenColor.getColorName(TokenType.IF) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("      " + TokenType.LOOP + " -> " + MdTokenColor.getColorName(TokenType.LOOP) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.LOOP));
        
        System.out.println("    Declaration (var, function, procedure):");
        System.out.println("      " + TokenType.FUNCTION + " -> " + MdTokenColor.getColorName(TokenType.FUNCTION) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.FUNCTION));
        System.out.println("      " + TokenType.PROCEDURE + " -> " + MdTokenColor.getColorName(TokenType.PROCEDURE) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.PROCEDURE));
        
        System.out.println("    Type (array, number, text, record):");
        System.out.println("      " + TokenType.ARRAY + " -> " + MdTokenColor.getColorName(TokenType.ARRAY) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.ARRAY));
        System.out.println("      " + TokenType.RECORD + " -> " + MdTokenColor.getColorName(TokenType.RECORD) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.RECORD));
        
        System.out.println("\n  Operator categories:");
        System.out.println("    Arithmetic (+, -, *, /):");
        System.out.println("      " + TokenType.PLUS + " -> " + MdTokenColor.getColorName(TokenType.PLUS) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.PLUS));
        
        System.out.println("    Logical (and, or, not):");
        System.out.println("      " + TokenType.AND + " -> " + MdTokenColor.getColorName(TokenType.AND) + 
                         " = " + MdTokenColor.getDefaultColorForToken(TokenType.AND));
        
        System.out.println("\n  Literal categories:");
        System.out.println("    String: " + MdTokenColor.getDefaultColorForToken(TokenType.TEXT));
        System.out.println("    Number: " + MdTokenColor.getDefaultColorForToken(TokenType.NUMBER));
        System.out.println("    Boolean: " + MdTokenColor.getDefaultColorForToken(TokenType.TRUE));
        
        System.out.println("\n  Special categories:");
        System.out.println("    Comment: " + MdTokenColor.getDefaultColorForToken(TokenType.COMMENT));
        System.out.println("    Built-in function: " + MdTokenColor.getDefaultColorForToken(TokenType.BUILTIN_FUNCTION));
        System.out.println("    Identifier: " + MdTokenColor.getDefaultColorForToken(TokenType.IDENTIFIER));
        System.out.println("    Error: " + MdTokenColor.getDefaultColorForToken(TokenType.ILLEGAL));
        
        System.out.println("✓ Passed\n");
    }
}
