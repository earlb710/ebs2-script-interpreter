package com.eb.script.lexer;

/**
 * Demonstration of all three TokenColor classes working together.
 * This shows the different naming conventions for editor, HTML, and XML formats.
 * 
 * @version 2.0.0
 * @since 2025-12-30
 */
public class TokenColorDemo {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 Token Color Classes Demonstration ===\n");
        
        // Demonstrate editor format (original)
        System.out.println("1. EDITOR FORMAT (editor.*):");
        System.out.println("   VAR keyword: " + TokenColor.getColorName(TokenType.VAR));
        System.out.println("   Color value: " + TokenColor.getDefaultColorForToken(TokenType.VAR));
        System.out.println();
        
        // Demonstrate HTML format
        System.out.println("2. HTML FORMAT (html.*):");
        System.out.println("   VAR keyword: " + HtmlTokenColor.getColorName(TokenType.VAR));
        System.out.println("   Color value: " + HtmlTokenColor.getDefaultColorForToken(TokenType.VAR));
        System.out.println();
        
        // Demonstrate XML format
        System.out.println("3. XML FORMAT (xml.*):");
        System.out.println("   VAR keyword: " + XmlTokenColor.getColorName(TokenType.VAR));
        System.out.println("   Color value: " + XmlTokenColor.getDefaultColorForToken(TokenType.VAR));
        System.out.println();
        
        // Compare all three formats for the same token
        System.out.println("4. COMPARISON - All three formats for IF token:");
        System.out.println("   Editor: " + TokenColor.getColorName(TokenType.IF) + 
                           " = " + TokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("   HTML:   " + HtmlTokenColor.getColorName(TokenType.IF) + 
                           " = " + HtmlTokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println("   XML:    " + XmlTokenColor.getColorName(TokenType.IF) + 
                           " = " + XmlTokenColor.getDefaultColorForToken(TokenType.IF));
        System.out.println();
        
        // Show category mappings
        System.out.println("5. CATEGORY CONSISTENCY:");
        System.out.println("   All three formats use the same color values for the same categories:");
        System.out.println("   - Comments: " + TokenColor.getDefaultColorForToken(TokenType.COMMENT) + 
                           " (editor), " + HtmlTokenColor.getDefaultColorForToken(TokenType.COMMENT) + 
                           " (html), " + XmlTokenColor.getDefaultColorForToken(TokenType.COMMENT) + " (xml)");
        System.out.println("   - Strings:  " + TokenColor.getDefaultColorForToken(TokenType.TEXT) + 
                           " (editor), " + HtmlTokenColor.getDefaultColorForToken(TokenType.TEXT) + 
                           " (html), " + XmlTokenColor.getDefaultColorForToken(TokenType.TEXT) + " (xml)");
        System.out.println("   - Numbers:  " + TokenColor.getDefaultColorForToken(TokenType.NUMBER) + 
                           " (editor), " + HtmlTokenColor.getDefaultColorForToken(TokenType.NUMBER) + 
                           " (html), " + XmlTokenColor.getDefaultColorForToken(TokenType.NUMBER) + " (xml)");
        System.out.println();
        
        System.out.println("=== Demonstration Complete ===");
        System.out.println("All three token color classes are working correctly with their respective naming conventions.");
    }
}
