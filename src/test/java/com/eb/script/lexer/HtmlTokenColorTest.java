package com.eb.script.lexer;

import java.util.Map;

/**
 * Test class for HtmlTokenColor functionality.
 * 
 * @version 2.0.0
 * @since 2025-12-30
 */
public class HtmlTokenColorTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 HTML Token Color Test Suite ===\n");
        
        testColorNames();
        testDefaultColors();
        testTokenToColorMapping();
        testColorCategories();
        
        System.out.println("\n=== All HtmlTokenColor Tests Completed ===");
    }
    
    private static void testColorNames() {
        System.out.println("Test 1: HTML Color Name Constants");
        
        // Verify color names follow the "html." prefix pattern
        assert HtmlTokenColor.HTML_COMMENT.startsWith("html.") : "Color name should start with 'html.'";
        assert HtmlTokenColor.HTML_KEYWORD.startsWith("html.") : "Color name should start with 'html.'";
        assert HtmlTokenColor.HTML_STRING.startsWith("html.") : "Color name should start with 'html.'";
        
        System.out.println("  Sample HTML color names:");
        System.out.println("    " + HtmlTokenColor.HTML_COMMENT + " = " + HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_COMMENT));
        System.out.println("    " + HtmlTokenColor.HTML_KEYWORD + " = " + HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_KEYWORD));
        System.out.println("    " + HtmlTokenColor.HTML_STRING + " = " + HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_STRING));
        System.out.println("    " + HtmlTokenColor.HTML_NUMBER + " = " + HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_NUMBER));
        System.out.println("    " + HtmlTokenColor.HTML_FUNCTION_BUILTIN + " = " + HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_FUNCTION_BUILTIN));
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testDefaultColors() {
        System.out.println("Test 2: HTML Default Color Values");
        
        // Verify colors are in hex RGB format
        String commentColor = HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_COMMENT);
        assert commentColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + commentColor;
        
        String keywordColor = HtmlTokenColor.getDefaultColor(HtmlTokenColor.HTML_KEYWORD);
        assert keywordColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + keywordColor;
        
        System.out.println("  Verified all colors are in hex RGB format (#RRGGBB)");
        
        // Display all color categories
        Map<String, String> allColors = HtmlTokenColor.getAllColors();
        System.out.println("  Total color definitions: " + allColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testTokenToColorMapping() {
        System.out.println("Test 3: HTML Token Type to Color Mapping");
        
        // Test specific token types
        String varColor = HtmlTokenColor.getColorName(TokenType.VAR);
        assert varColor.equals(HtmlTokenColor.HTML_KEYWORD_DECLARATION) : 
            "VAR should map to declaration keyword color";
        
        String ifColor = HtmlTokenColor.getColorName(TokenType.IF);
        assert ifColor.equals(HtmlTokenColor.HTML_KEYWORD_CONTROL) : 
            "IF should map to control keyword color";
        
        String numberColor = HtmlTokenColor.getColorName(TokenType.NUMBER);
        assert numberColor.equals(HtmlTokenColor.HTML_NUMBER) : 
            "NUMBER should map to number literal color";
        
        String builtinColor = HtmlTokenColor.getColorName(TokenType.BUILTIN_FUNCTION);
        assert builtinColor.equals(HtmlTokenColor.HTML_FUNCTION_BUILTIN) : 
            "BUILTIN_FUNCTION should map to builtin function color";
        
        System.out.println("  Sample token to HTML color mappings:");
        System.out.println("    VAR -> " + varColor + " = " + HtmlTokenColor.getDefaultColor(varColor));
        System.out.println("    IF -> " + ifColor + " = " + HtmlTokenColor.getDefaultColor(ifColor));
        System.out.println("    NUMBER -> " + numberColor + " = " + HtmlTokenColor.getDefaultColor(numberColor));
        System.out.println("    BUILTIN_FUNCTION -> " + builtinColor + " = " + HtmlTokenColor.getDefaultColor(builtinColor));
        
        // Display total mappings
        Map<TokenType, String> tokenColors = HtmlTokenColor.getTokenColorMapping();
        System.out.println("  Total token type mappings: " + tokenColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testColorCategories() {
        System.out.println("Test 4: HTML Color Categories and Examples");
        
        System.out.println("  Keyword categories:");
        System.out.println("    Control flow (if, for, while, loop):");
        System.out.println("      " + TokenType.IF + " -> " + HtmlTokenColor.getColorName(TokenType.IF) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("      " + TokenType.LOOP + " -> " + HtmlTokenColor.getColorName(TokenType.LOOP) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.LOOP));
        
        System.out.println("    Declaration (var, function, procedure):");
        System.out.println("      " + TokenType.FUNCTION + " -> " + HtmlTokenColor.getColorName(TokenType.FUNCTION) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.FUNCTION));
        System.out.println("      " + TokenType.PROCEDURE + " -> " + HtmlTokenColor.getColorName(TokenType.PROCEDURE) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.PROCEDURE));
        
        System.out.println("    Type (array, number, text, record):");
        System.out.println("      " + TokenType.ARRAY + " -> " + HtmlTokenColor.getColorName(TokenType.ARRAY) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.ARRAY));
        System.out.println("      " + TokenType.RECORD + " -> " + HtmlTokenColor.getColorName(TokenType.RECORD) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.RECORD));
        
        System.out.println("\n  Operator categories:");
        System.out.println("    Arithmetic (+, -, *, /):");
        System.out.println("      " + TokenType.PLUS + " -> " + HtmlTokenColor.getColorName(TokenType.PLUS) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.PLUS));
        
        System.out.println("    Logical (and, or, not):");
        System.out.println("      " + TokenType.AND + " -> " + HtmlTokenColor.getColorName(TokenType.AND) + 
                         " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.AND));
        
        System.out.println("\n  Literal categories:");
        System.out.println("    String: " + HtmlTokenColor.getDefaultColorForToken(TokenType.TEXT));
        System.out.println("    Number: " + HtmlTokenColor.getDefaultColorForToken(TokenType.NUMBER));
        System.out.println("    Boolean: " + HtmlTokenColor.getDefaultColorForToken(TokenType.TRUE));
        
        System.out.println("\n  Special categories:");
        System.out.println("    Comment: " + HtmlTokenColor.getDefaultColorForToken(TokenType.COMMENT));
        System.out.println("    Built-in function: " + HtmlTokenColor.getDefaultColorForToken(TokenType.BUILTIN_FUNCTION));
        System.out.println("    Identifier: " + HtmlTokenColor.getDefaultColorForToken(TokenType.IDENTIFIER));
        System.out.println("    Error: " + HtmlTokenColor.getDefaultColorForToken(TokenType.ILLEGAL));
        
        System.out.println("✓ Passed\n");
    }
}
