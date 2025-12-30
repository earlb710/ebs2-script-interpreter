package com.eb.script.lexer;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * HtmlTokenColor defines color names and default color values for each token type.
 * These colors are intended for syntax highlighting in editors and IDEs.
 * 
 * Color names follow the pattern: "html.{category}.{type}"
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
public class HtmlTokenColor {
    
    // Color name constants
    public static final String HTML_COMMENT = "html.comment";
    public static final String HTML_STRING = "html.string";
    public static final String HTML_NUMBER = "html.number";
    public static final String HTML_BOOLEAN = "html.boolean";
    public static final String HTML_KEYWORD = "html.keyword";
    public static final String HTML_KEYWORD_CONTROL = "html.keyword.control";
    public static final String HTML_KEYWORD_DECLARATION = "html.keyword.declaration";
    public static final String HTML_KEYWORD_TYPE = "html.keyword.type";
    public static final String HTML_OPERATOR = "html.operator";
    public static final String HTML_OPERATOR_ARITHMETIC = "html.operator.arithmetic";
    public static final String HTML_OPERATOR_COMPARISON = "html.operator.comparison";
    public static final String HTML_OPERATOR_LOGICAL = "html.operator.logical";
    public static final String HTML_OPERATOR_ASSIGNMENT = "html.operator.assignment";
    public static final String HTML_DELIMITER = "html.delimiter";
    public static final String HTML_IDENTIFIER = "html.identifier";
    public static final String HTML_FUNCTION = "html.function";
    public static final String HTML_FUNCTION_BUILTIN = "html.function.builtin";
    public static final String HTML_ERROR = "html.error";
    public static final String HTML_DEFAULT = "html.default";
    
    // Default color values (hex RGB format)
    private static final Map<String, String> DEFAULT_COLORS;
    
    // Mapping from TokenType to color name (EnumMap for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR;
    
    // Direct mapping from TokenType to color value (cached for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR_VALUE;
    
    static {
        // Initialize color values map
        Map<String, String> colors = new HashMap<>(19);
        colors.put(HTML_COMMENT, "#6A9955");
        colors.put(HTML_STRING, "#CE9178");
        colors.put(HTML_NUMBER, "#B5CEA8");
        colors.put(HTML_BOOLEAN, "#569CD6");
        colors.put(HTML_KEYWORD, "#C586C0");
        colors.put(HTML_KEYWORD_CONTROL, "#C586C0");
        colors.put(HTML_KEYWORD_DECLARATION, "#569CD6");
        colors.put(HTML_KEYWORD_TYPE, "#4EC9B0");
        colors.put(HTML_OPERATOR, "#D4D4D4");
        colors.put(HTML_OPERATOR_ARITHMETIC, "#D4D4D4");
        colors.put(HTML_OPERATOR_COMPARISON, "#D4D4D4");
        colors.put(HTML_OPERATOR_LOGICAL, "#C586C0");
        colors.put(HTML_OPERATOR_ASSIGNMENT, "#D4D4D4");
        colors.put(HTML_DELIMITER, "#D4D4D4");
        colors.put(HTML_IDENTIFIER, "#9CDCFE");
        colors.put(HTML_FUNCTION, "#DCDCAA");
        colors.put(HTML_FUNCTION_BUILTIN, "#DCDCAA");
        colors.put(HTML_ERROR, "#F44747");
        colors.put(HTML_DEFAULT, "#D4D4D4");
        DEFAULT_COLORS = Collections.unmodifiableMap(colors);
        
        // Initialize token-to-color-name map (using EnumMap for performance)
        Map<TokenType, String> tokenColors = new EnumMap<>(TokenType.class);
        
        // Comments
        tokenColors.put(TokenType.COMMENT, HTML_COMMENT);
        
        // Literals
        tokenColors.put(TokenType.TEXT, HTML_STRING);
        tokenColors.put(TokenType.NUMBER, HTML_NUMBER);
        tokenColors.put(TokenType.TRUE, HTML_BOOLEAN);
        tokenColors.put(TokenType.FALSE, HTML_BOOLEAN);
        
        // Keywords - Control Flow
        tokenColors.put(TokenType.IF, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THEN, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.ELSE, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.FOR, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.EACH, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.IN, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TO, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.LOOP, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.WHILE, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.UNTIL, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DO, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.REPEAT, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TIMES, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.BREAK, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CONTINUE, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.RETURN, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.SWITCH, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CASE, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DEFAULT, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TRY, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CATCH, HTML_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THROW, HTML_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        tokenColors.put(TokenType.VAR, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.VARIABLE, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONST, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONSTANT, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.LET, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FUNCTION, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.PROCEDURE, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CLASS, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.IMPORT, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.EXPORT, HTML_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FROM, HTML_KEYWORD_DECLARATION);
        
        // Keywords - Type
        tokenColors.put(TokenType.AS, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.IS, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPE, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.ARRAY, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.TEXT_TYPE, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.NUMBER_TYPE, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.FLAG, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.RECORD, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.DATE, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.MAP, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.JSON, HTML_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPEOF, HTML_KEYWORD_TYPE);
        
        // Keywords - Other
        tokenColors.put(TokenType.PROGRAM, HTML_KEYWORD);
        tokenColors.put(TokenType.END, HTML_KEYWORD);
        tokenColors.put(TokenType.WITH, HTML_KEYWORD);
        tokenColors.put(TokenType.CALL, HTML_KEYWORD);
        tokenColors.put(TokenType.PRINT, HTML_KEYWORD);
        tokenColors.put(TokenType.LOG, HTML_KEYWORD);
        tokenColors.put(TokenType.HIDE, HTML_KEYWORD);
        tokenColors.put(TokenType.ASK, HTML_KEYWORD);
        tokenColors.put(TokenType.SCREEN, HTML_KEYWORD);
        tokenColors.put(TokenType.BUTTON, HTML_KEYWORD);
        tokenColors.put(TokenType.LABEL, HTML_KEYWORD);
        tokenColors.put(TokenType.TEXTBOX, HTML_KEYWORD);
        tokenColors.put(TokenType.INDICATOR, HTML_KEYWORD);
        tokenColors.put(TokenType.STEP, HTML_KEYWORD);
        tokenColors.put(TokenType.BY, HTML_KEYWORD);
        tokenColors.put(TokenType.DOWN, HTML_KEYWORD);
        tokenColors.put(TokenType.EXTENDS, HTML_KEYWORD);
        tokenColors.put(TokenType.NEW, HTML_KEYWORD);
        tokenColors.put(TokenType.ASYNC, HTML_KEYWORD);
        tokenColors.put(TokenType.AWAIT, HTML_KEYWORD);
        
        // Operators - Arithmetic
        tokenColors.put(TokenType.PLUS, HTML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MINUS, HTML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MULTIPLY, HTML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DIVIDE, HTML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MOD, HTML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.INCREMENT, HTML_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DECREMENT, HTML_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        tokenColors.put(TokenType.EQUAL, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.NOT_EQUAL, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_THAN, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_THAN, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_EQUAL, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_EQUAL, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_EQUAL_TO, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_NOT_EQUAL_TO, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_THAN, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_THAN, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_EQUAL, HTML_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_EQUAL, HTML_OPERATOR_COMPARISON);
        
        // Operators - Logical
        tokenColors.put(TokenType.AND, HTML_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.OR, HTML_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.NOT, HTML_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        tokenColors.put(TokenType.ASSIGN, HTML_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        tokenColors.put(TokenType.LPAREN, HTML_DELIMITER);
        tokenColors.put(TokenType.RPAREN, HTML_DELIMITER);
        tokenColors.put(TokenType.LBRACE, HTML_DELIMITER);
        tokenColors.put(TokenType.RBRACE, HTML_DELIMITER);
        tokenColors.put(TokenType.LBRACKET, HTML_DELIMITER);
        tokenColors.put(TokenType.RBRACKET, HTML_DELIMITER);
        tokenColors.put(TokenType.COMMA, HTML_DELIMITER);
        tokenColors.put(TokenType.SEMICOLON, HTML_DELIMITER);
        tokenColors.put(TokenType.COLON, HTML_DELIMITER);
        tokenColors.put(TokenType.DOT, HTML_DELIMITER);
        tokenColors.put(TokenType.RANGE, HTML_DELIMITER);
        tokenColors.put(TokenType.ARROW, HTML_DELIMITER);
        tokenColors.put(TokenType.QUESTION, HTML_DELIMITER);
        
        // Identifiers
        tokenColors.put(TokenType.IDENTIFIER, HTML_IDENTIFIER);
        
        // Functions
        tokenColors.put(TokenType.BUILTIN_FUNCTION, HTML_FUNCTION_BUILTIN);
        
        // Errors
        tokenColors.put(TokenType.ILLEGAL, HTML_ERROR);
        
        // Special tokens (typically not colored)
        tokenColors.put(TokenType.EOF, HTML_DEFAULT);
        tokenColors.put(TokenType.NEWLINE, HTML_DEFAULT);
        tokenColors.put(TokenType.WHITESPACE, HTML_DEFAULT);
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
     * @return The color name (e.g., "html.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        String colorName = TOKEN_TO_COLOR.get(tokenType);
        return colorName != null ? colorName : HTML_DEFAULT;
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "html.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        String color = DEFAULT_COLORS.get(colorName);
        return color != null ? color : DEFAULT_COLORS.get(HTML_DEFAULT);
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
        return color != null ? color : DEFAULT_COLORS.get(HTML_DEFAULT);
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
