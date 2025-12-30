package com.eb.script.lexer;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * XmlTokenColor defines color names and default color values for each token type.
 * These colors are intended for syntax highlighting in editors and IDEs.
 * 
 * Color names follow the pattern: "xml.{category}.{type}"
 * 
 * Default colors use hex RGB format: #RRGGBB
 * 
 * Performance optimizations:
 * - Uses EnumMap for O(1) token lookups
 * - Direct color value cache for single lookup
 * - Unmodifiable map wrappers to avoid copying
 * 
 * @version 2.1.0
 * @since 2025-12-30
 */
public class XmlTokenColor {
    
    // Color name constants
    public static final String XML_COMMENT = "xml.comment";
    public static final String XML_STRING = "xml.string";
    public static final String XML_NUMBER = "xml.number";
    public static final String XML_BOOLEAN = "xml.boolean";
    public static final String XML_KEYWORD = "xml.keyword";
    public static final String XML_KEYWORD_CONTROL = "xml.keyword.control";
    public static final String XML_KEYWORD_DECLARATION = "xml.keyword.declaration";
    public static final String XML_KEYWORD_TYPE = "xml.keyword.type";
    public static final String XML_OPERATOR = "xml.operator";
    public static final String XML_OPERATOR_ARITHMETIC = "xml.operator.arithmetic";
    public static final String XML_OPERATOR_COMPARISON = "xml.operator.comparison";
    public static final String XML_OPERATOR_LOGICAL = "xml.operator.logical";
    public static final String XML_OPERATOR_ASSIGNMENT = "xml.operator.assignment";
    public static final String XML_DELIMITER = "xml.delimiter";
    public static final String XML_IDENTIFIER = "xml.identifier";
    public static final String XML_FUNCTION = "xml.function";
    public static final String XML_FUNCTION_BUILTIN = "xml.function.builtin";
    public static final String XML_ERROR = "xml.error";
    public static final String XML_DEFAULT = "xml.default";
    
    // Default color values (hex RGB format)
    private static final Map<String, String> DEFAULT_COLORS;
    
    // Mapping from TokenType to color name (EnumMap for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR;
    
    // Direct mapping from TokenType to color value (cached for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR_VALUE;
    
    static {
        // Initialize color values map
        Map<String, String> colors = new HashMap<>(19);
        colors.put(XML_COMMENT, "#6A9955");
        colors.put(XML_STRING, "#CE9178");
        colors.put(XML_NUMBER, "#B5CEA8");
        colors.put(XML_BOOLEAN, "#569CD6");
        colors.put(XML_KEYWORD, "#C586C0");
        colors.put(XML_KEYWORD_CONTROL, "#C586C0");
        colors.put(XML_KEYWORD_DECLARATION, "#569CD6");
        colors.put(XML_KEYWORD_TYPE, "#4EC9B0");
        colors.put(XML_OPERATOR, "#D4D4D4");
        colors.put(XML_OPERATOR_ARITHMETIC, "#D4D4D4");
        colors.put(XML_OPERATOR_COMPARISON, "#D4D4D4");
        colors.put(XML_OPERATOR_LOGICAL, "#C586C0");
        colors.put(XML_OPERATOR_ASSIGNMENT, "#D4D4D4");
        colors.put(XML_DELIMITER, "#D4D4D4");
        colors.put(XML_IDENTIFIER, "#9CDCFE");
        colors.put(XML_FUNCTION, "#DCDCAA");
        colors.put(XML_FUNCTION_BUILTIN, "#DCDCAA");
        colors.put(XML_ERROR, "#F44747");
        colors.put(XML_DEFAULT, "#D4D4D4");
        DEFAULT_COLORS = Collections.unmodifiableMap(colors);
        
        // Initialize token-to-color-name map (using EnumMap for performance)
        Map<TokenType, String> tokenColors = new EnumMap<>(TokenType.class);
        
        // Comments
        tokenColors.put(TokenType.COMMENT, XML_COMMENT);
        
        // Literals
        tokenColors.put(TokenType.TEXT, XML_STRING);
        tokenColors.put(TokenType.NUMBER, XML_NUMBER);
        tokenColors.put(TokenType.TRUE, XML_BOOLEAN);
        tokenColors.put(TokenType.FALSE, XML_BOOLEAN);
        
        // Keywords - Control Flow
        tokenColors.put(TokenType.IF, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THEN, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.ELSE, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.FOR, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.EACH, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.IN, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TO, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.LOOP, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.WHILE, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.UNTIL, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DO, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.REPEAT, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TIMES, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.BREAK, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CONTINUE, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.RETURN, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.SWITCH, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CASE, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DEFAULT, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TRY, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CATCH, XML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THROW, XML_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        tokenColors.put(TokenType.VAR, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.VARIABLE, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONST, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONSTANT, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.LET, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FUNCTION, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.PROCEDURE, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CLASS, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.IMPORT, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.EXPORT, XML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FROM, XML_KEYWORD_DECLARATION);
        
        // Keywords - Type
        tokenColors.put(TokenType.AS, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.IS, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPE, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.ARRAY, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.TEXT_TYPE, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.NUMBER_TYPE, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.FLAG, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.RECORD, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.DATE, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.MAP, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.JSON, XML_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPEOF, XML_KEYWORD_TYPE);
        
        // Keywords - Other
        tokenColors.put(TokenType.PROGRAM, XML_KEYWORD);
        tokenColors.put(TokenType.END, XML_KEYWORD);
        tokenColors.put(TokenType.WITH, XML_KEYWORD);
        tokenColors.put(TokenType.CALL, XML_KEYWORD);
        tokenColors.put(TokenType.PRINT, XML_KEYWORD);
        tokenColors.put(TokenType.LOG, XML_KEYWORD);
        tokenColors.put(TokenType.HIDE, XML_KEYWORD);
        tokenColors.put(TokenType.ASK, XML_KEYWORD);
        tokenColors.put(TokenType.SCREEN, XML_KEYWORD);
        tokenColors.put(TokenType.BUTTON, XML_KEYWORD);
        tokenColors.put(TokenType.LABEL, XML_KEYWORD);
        tokenColors.put(TokenType.TEXTBOX, XML_KEYWORD);
        tokenColors.put(TokenType.INDICATOR, XML_KEYWORD);
        tokenColors.put(TokenType.STEP, XML_KEYWORD);
        tokenColors.put(TokenType.BY, XML_KEYWORD);
        tokenColors.put(TokenType.DOWN, XML_KEYWORD);
        tokenColors.put(TokenType.EXTENDS, XML_KEYWORD);
        tokenColors.put(TokenType.NEW, XML_KEYWORD);
        tokenColors.put(TokenType.ASYNC, XML_KEYWORD);
        tokenColors.put(TokenType.AWAIT, XML_KEYWORD);
        
        // Operators - Arithmetic
        tokenColors.put(TokenType.PLUS, XML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MINUS, XML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MULTIPLY, XML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DIVIDE, XML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MOD, XML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.INCREMENT, XML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DECREMENT, XML_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        tokenColors.put(TokenType.EQUAL, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.NOT_EQUAL, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_THAN, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_THAN, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_EQUAL, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_EQUAL, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_EQUAL_TO, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_NOT_EQUAL_TO, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_THAN, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_THAN, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_EQUAL, XML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_EQUAL, XML_OPERATOR_COMPARISON);
        
        // Operators - Logical
        tokenColors.put(TokenType.AND, XML_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.OR, XML_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.NOT, XML_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        tokenColors.put(TokenType.ASSIGN, XML_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        tokenColors.put(TokenType.LPAREN, XML_DELIMITER);
        tokenColors.put(TokenType.RPAREN, XML_DELIMITER);
        tokenColors.put(TokenType.LBRACE, XML_DELIMITER);
        tokenColors.put(TokenType.RBRACE, XML_DELIMITER);
        tokenColors.put(TokenType.LBRACKET, XML_DELIMITER);
        tokenColors.put(TokenType.RBRACKET, XML_DELIMITER);
        tokenColors.put(TokenType.COMMA, XML_DELIMITER);
        tokenColors.put(TokenType.SEMICOLON, XML_DELIMITER);
        tokenColors.put(TokenType.COLON, XML_DELIMITER);
        tokenColors.put(TokenType.DOT, XML_DELIMITER);
        tokenColors.put(TokenType.RANGE, XML_DELIMITER);
        tokenColors.put(TokenType.ARROW, XML_DELIMITER);
        tokenColors.put(TokenType.QUESTION, XML_DELIMITER);
        
        // Identifiers
        tokenColors.put(TokenType.IDENTIFIER, XML_IDENTIFIER);
        
        // Functions
        tokenColors.put(TokenType.BUILTIN_FUNCTION, XML_FUNCTION_BUILTIN);
        
        // Errors
        tokenColors.put(TokenType.ILLEGAL, XML_ERROR);
        
        // Special tokens (typically not colored)
        tokenColors.put(TokenType.EOF, XML_DEFAULT);
        tokenColors.put(TokenType.NEWLINE, XML_DEFAULT);
        tokenColors.put(TokenType.WHITESPACE, XML_DEFAULT);
        TOKEN_TO_COLOR = Collections.unmodifiableMap(tokenColors);
        
        // Build direct token-to-color-value cache for performance
        Map<TokenType, String> colorValues = new EnumMap<>(TokenType.class);
        for (Map.Entry<TokenType, String> entry : tokenColors.entrySet()) {
            String colorValue = colors.get(entry.getValue());
            if (colorValue != null) {
                colorValues.put(entry.getKey(), colorValue);
            }
        }
        TOKEN_TO_COLOR_VALUE = Collections.unmodifiableMap(colorValues);
    }
    
    /**
     * Gets the color name for a given token type.
     * Fast O(1) lookup using EnumMap.
     * 
     * @param tokenType The token type
     * @return The color name (e.g., "xml.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        String colorName = TOKEN_TO_COLOR.get(tokenType);
        return colorName != null ? colorName : XML_DEFAULT;
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "xml.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        String color = DEFAULT_COLORS.get(colorName);
        return color != null ? color : DEFAULT_COLORS.get(XML_DEFAULT);
    }
    
    /**
     * Gets the default color value for a given token type.
     * Optimized for fast single-lookup operation - critical for editor highlighting performance.
     * 
     * @param tokenType The token type
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColorForToken(TokenType tokenType) {
        String color = TOKEN_TO_COLOR_VALUE.get(tokenType);
        return color != null ? color : DEFAULT_COLORS.get(XML_DEFAULT);
    }
    
    /**
     * Gets all color name constants.
     * Returns unmodifiable map to avoid copying overhead.
     * 
     * @return Map of color names to default color values
     */
    public static Map<String, String> getAllColors() {
        return DEFAULT_COLORS;
    }
    
    /**
     * Gets the mapping from token types to color names.
     * Returns unmodifiable map to avoid copying overhead.
     * 
     * @return Map of token types to color names
     */
    public static Map<TokenType, String> getTokenColorMapping() {
        return TOKEN_TO_COLOR;
    }
}
