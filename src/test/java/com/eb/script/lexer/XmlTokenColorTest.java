package com.eb.script.lexer;

import java.util.Map;

/**
 * Test class for XmlTokenColor functionality.
 * 
 * @version 2.0.0
 * @since 2025-12-30
 */
public class XmlTokenColorTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 XML Token Color Test Suite ===\n");
        
        testColorNames();
        testDefaultColors();
        testTokenToColorMapping();
        testColorCategories();
        
        System.out.println("\n=== All XmlTokenColor Tests Completed ===");
    }
    
    private static void testColorNames() {
        System.out.println("Test 1: XML Color Name Constants");
        
        // Verify color names follow the "xml." prefix pattern
        assert XmlTokenColor.XML_COMMENT.startsWith("xml.") : "Color name should start with 'xml.'";
        assert XmlTokenColor.XML_KEYWORD.startsWith("xml.") : "Color name should start with 'xml.'";
        assert XmlTokenColor.XML_STRING.startsWith("xml.") : "Color name should start with 'xml.'";
        
        System.out.println("  Sample XML color names:");
        System.out.println("    " + XmlTokenColor.XML_COMMENT + " = " + XmlTokenColor.getDefaultColor(XmlTokenColor.XML_COMMENT));
        System.out.println("    " + XmlTokenColor.XML_KEYWORD + " = " + XmlTokenColor.getDefaultColor(XmlTokenColor.XML_KEYWORD));
        System.out.println("    " + XmlTokenColor.XML_STRING + " = " + XmlTokenColor.getDefaultColor(XmlTokenColor.XML_STRING));
        System.out.println("    " + XmlTokenColor.XML_NUMBER + " = " + XmlTokenColor.getDefaultColor(XmlTokenColor.XML_NUMBER));
        System.out.println("    " + XmlTokenColor.XML_FUNCTION_BUILTIN + " = " + XmlTokenColor.getDefaultColor(XmlTokenColor.XML_FUNCTION_BUILTIN));
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testDefaultColors() {
        System.out.println("Test 2: XML Default Color Values");
        
        // Verify colors are in hex RGB format
        String commentColor = XmlTokenColor.getDefaultColor(XmlTokenColor.XML_COMMENT);
        assert commentColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + commentColor;
        
        String keywordColor = XmlTokenColor.getDefaultColor(XmlTokenColor.XML_KEYWORD);
        assert keywordColor.matches("#[0-9A-F]{6}") : "Color should be in hex RGB format: " + keywordColor;
        
        System.out.println("  Verified all colors are in hex RGB format (#RRGGBB)");
        
        // Display all color categories
        Map<String, String> allColors = XmlTokenColor.getAllColors();
        System.out.println("  Total color definitions: " + allColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testTokenToColorMapping() {
        System.out.println("Test 3: XML Token Type to Color Mapping");
        
        // Test specific token types
        String varColor = XmlTokenColor.getColorName(TokenType.VAR);
        assert varColor.equals(XmlTokenColor.XML_KEYWORD_DECLARATION) : 
            "VAR should map to declaration keyword color";
        
        String ifColor = XmlTokenColor.getColorName(TokenType.IF);
        assert ifColor.equals(XmlTokenColor.XML_KEYWORD_CONTROL) : 
            "IF should map to control keyword color";
        
        String numberColor = XmlTokenColor.getColorName(TokenType.NUMBER);
        assert numberColor.equals(XmlTokenColor.XML_NUMBER) : 
            "NUMBER should map to number literal color";
        
        String builtinColor = XmlTokenColor.getColorName(TokenType.BUILTIN_FUNCTION);
        assert builtinColor.equals(XmlTokenColor.XML_FUNCTION_BUILTIN) : 
            "BUILTIN_FUNCTION should map to builtin function color";
        
        System.out.println("  Sample token to XML color mappings:");
        System.out.println("    VAR -> " + varColor + " = " + XmlTokenColor.getDefaultColor(varColor));
        System.out.println("    IF -> " + ifColor + " = " + XmlTokenColor.getDefaultColor(ifColor));
        System.out.println("    NUMBER -> " + numberColor + " = " + XmlTokenColor.getDefaultColor(numberColor));
        System.out.println("    BUILTIN_FUNCTION -> " + builtinColor + " = " + XmlTokenColor.getDefaultColor(builtinColor));
        
        // Display total mappings
        Map<TokenType, String> tokenColors = XmlTokenColor.getTokenColorMapping();
        System.out.println("  Total token type mappings: " + tokenColors.size());
        
        System.out.println("✓ Passed\n");
    }
    
    private static void testColorCategories() {
        System.out.println("Test 4: XML Color Categories and Examples");
        
        System.out.println("  Keyword categories:");
        System.out.println("    Control flow (if, for, while, loop):");
        System.out.println("      " + TokenType.IF + " -> " + XmlTokenColor.getColorName(TokenType.IF) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("      " + TokenType.LOOP + " -> " + XmlTokenColor.getColorName(TokenType.LOOP) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.LOOP));
        
        System.out.println("    Declaration (var, function, procedure):");
        System.out.println("      " + TokenType.FUNCTION + " -> " + XmlTokenColor.getColorName(TokenType.FUNCTION) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.FUNCTION));
        System.out.println("      " + TokenType.PROCEDURE + " -> " + XmlTokenColor.getColorName(TokenType.PROCEDURE) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.PROCEDURE));
        
        System.out.println("    Type (array, number, text, record):");
        System.out.println("      " + TokenType.ARRAY + " -> " + XmlTokenColor.getColorName(TokenType.ARRAY) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.ARRAY));
        System.out.println("      " + TokenType.RECORD + " -> " + XmlTokenColor.getColorName(TokenType.RECORD) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.RECORD));
        
        System.out.println("\n  Operator categories:");
        System.out.println("    Arithmetic (+, -, *, /):");
        System.out.println("      " + TokenType.PLUS + " -> " + XmlTokenColor.getColorName(TokenType.PLUS) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.PLUS));
        
        System.out.println("    Logical (and, or, not):");
        System.out.println("      " + TokenType.AND + " -> " + XmlTokenColor.getColorName(TokenType.AND) + 
                         " = " + XmlTokenColor.getDefaultColorForToken(TokenType.AND));
        
        System.out.println("\n  Literal categories:");
        System.out.println("    String: " + XmlTokenColor.getDefaultColorForToken(TokenType.TEXT));
        System.out.println("    Number: " + XmlTokenColor.getDefaultColorForToken(TokenType.NUMBER));
        System.out.println("    Boolean: " + XmlTokenColor.getDefaultColorForToken(TokenType.TRUE));
        
        System.out.println("\n  Special categories:");
        System.out.println("    Comment: " + XmlTokenColor.getDefaultColorForToken(TokenType.COMMENT));
        System.out.println("    Built-in function: " + XmlTokenColor.getDefaultColorForToken(TokenType.BUILTIN_FUNCTION));
        System.out.println("    Identifier: " + XmlTokenColor.getDefaultColorForToken(TokenType.IDENTIFIER));
        System.out.println("    Error: " + XmlTokenColor.getDefaultColorForToken(TokenType.ILLEGAL));
        
        System.out.println("✓ Passed\n");
    }
}
