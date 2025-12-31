package com.eb.script.lexer;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * CssTokenColor defines color names and default color values for each token type.
 * These colors are intended for syntax highlighting in editors and IDEs.
 * 
 * Color names follow the pattern: "css.{category}.{type}"
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
public class CssTokenColor {
    
    // Color name constants
    public static final String CSS_COMMENT = "css.comment";
    public static final String CSS_STRING = "css.string";
    public static final String CSS_NUMBER = "css.number";
    public static final String CSS_BOOLEAN = "css.boolean";
    public static final String CSS_KEYWORD = "css.keyword";
    public static final String CSS_KEYWORD_CONTROL = "css.keyword.control";
    public static final String CSS_KEYWORD_DECLARATION = "css.keyword.declaration";
    public static final String CSS_KEYWORD_TYPE = "css.keyword.type";
    public static final String CSS_OPERATOR = "css.operator";
    public static final String CSS_OPERATOR_ARITHMETIC = "css.operator.arithmetic";
    public static final String CSS_OPERATOR_COMPARISON = "css.operator.comparison";
    public static final String CSS_OPERATOR_LOGICAL = "css.operator.logical";
    public static final String CSS_OPERATOR_ASSIGNMENT = "css.operator.assignment";
    public static final String CSS_DELIMITER = "css.delimiter";
    public static final String CSS_IDENTIFIER = "css.identifier";
    public static final String CSS_FUNCTION = "css.function";
    public static final String CSS_FUNCTION_BUILTIN = "css.function.builtin";
    public static final String CSS_ERROR = "css.error";
    public static final String CSS_DEFAULT = "css.default";
    
    // Default color values (hex RGB format)
    private static final Map<String, String> DEFAULT_COLORS;
    
    // Mapping from TokenType to color name (EnumMap for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR;
    
    // Direct mapping from TokenType to color value (cached for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR_VALUE;
    
    static {
        // Initialize color values map
        Map<String, String> colors = new HashMap<>(19);
        colors.put(CSS_COMMENT, "#6A9955");
        colors.put(CSS_STRING, "#CE9178");
        colors.put(CSS_NUMBER, "#B5CEA8");
        colors.put(CSS_BOOLEAN, "#569CD6");
        colors.put(CSS_KEYWORD, "#C586C0");
        colors.put(CSS_KEYWORD_CONTROL, "#C586C0");
        colors.put(CSS_KEYWORD_DECLARATION, "#569CD6");
        colors.put(CSS_KEYWORD_TYPE, "#4EC9B0");
        colors.put(CSS_OPERATOR, "#D4D4D4");
        colors.put(CSS_OPERATOR_ARITHMETIC, "#D4D4D4");
        colors.put(CSS_OPERATOR_COMPARISON, "#D4D4D4");
        colors.put(CSS_OPERATOR_LOGICAL, "#C586C0");
        colors.put(CSS_OPERATOR_ASSIGNMENT, "#D4D4D4");
        colors.put(CSS_DELIMITER, "#D4D4D4");
        colors.put(CSS_IDENTIFIER, "#9CDCFE");
        colors.put(CSS_FUNCTION, "#DCDCAA");
        colors.put(CSS_FUNCTION_BUILTIN, "#DCDCAA");
        colors.put(CSS_ERROR, "#F44747");
        colors.put(CSS_DEFAULT, "#D4D4D4");
        DEFAULT_COLORS = Collections.unmodifiableMap(colors);
        
        // Initialize token-to-color-name map (using EnumMap for performance)
        Map<TokenType, String> tokenColors = new EnumMap<>(TokenType.class);
        
        // Comments
        tokenColors.put(TokenType.COMMENT, CSS_COMMENT);
        
        // Literals
        tokenColors.put(TokenType.TEXT, CSS_STRING);
        tokenColors.put(TokenType.NUMBER, CSS_NUMBER);
        tokenColors.put(TokenType.TRUE, CSS_BOOLEAN);
        tokenColors.put(TokenType.FALSE, CSS_BOOLEAN);
        
        // Keywords - Control Flow
        tokenColors.put(TokenType.IF, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THEN, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.ELSE, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.FOR, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.EACH, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.IN, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TO, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.LOOP, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.WHILE, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.UNTIL, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DO, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.REPEAT, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TIMES, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.BREAK, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CONTINUE, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.RETURN, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.SWITCH, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CASE, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DEFAULT, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TRY, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CATCH, CSS_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THROW, CSS_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        tokenColors.put(TokenType.VAR, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.VARIABLE, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONST, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONSTANT, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.LET, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FUNCTION, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.PROCEDURE, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CLASS, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.IMPORT, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.EXPORT, CSS_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FROM, CSS_KEYWORD_DECLARATION);
        
        // Keywords - Type
        tokenColors.put(TokenType.AS, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.IS, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPE, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.ARRAY, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.TEXT_TYPE, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.NUMBER_TYPE, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.FLAG, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.RECORD, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.DATE, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.MAP, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.JSON, CSS_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPEOF, CSS_KEYWORD_TYPE);
        
        // Keywords - Other
        tokenColors.put(TokenType.PROGRAM, CSS_KEYWORD);
        tokenColors.put(TokenType.END, CSS_KEYWORD);
        tokenColors.put(TokenType.WITH, CSS_KEYWORD);
        tokenColors.put(TokenType.CALL, CSS_KEYWORD);
        tokenColors.put(TokenType.PRINT, CSS_KEYWORD);
        tokenColors.put(TokenType.LOG, CSS_KEYWORD);
        tokenColors.put(TokenType.HIDE, CSS_KEYWORD);
        tokenColors.put(TokenType.ASK, CSS_KEYWORD);
        tokenColors.put(TokenType.SCREEN, CSS_KEYWORD);
        tokenColors.put(TokenType.BUTTON, CSS_KEYWORD);
        tokenColors.put(TokenType.LABEL, CSS_KEYWORD);
        tokenColors.put(TokenType.TEXTBOX, CSS_KEYWORD);
        tokenColors.put(TokenType.INDICATOR, CSS_KEYWORD);
        tokenColors.put(TokenType.STEP, CSS_KEYWORD);
        tokenColors.put(TokenType.BY, CSS_KEYWORD);
        tokenColors.put(TokenType.DOWN, CSS_KEYWORD);
        tokenColors.put(TokenType.EXTENDS, CSS_KEYWORD);
        tokenColors.put(TokenType.NEW, CSS_KEYWORD);
        tokenColors.put(TokenType.ASYNC, CSS_KEYWORD);
        tokenColors.put(TokenType.AWAIT, CSS_KEYWORD);
        
        // Operators - Arithmetic
        tokenColors.put(TokenType.PLUS, CSS_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MINUS, CSS_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MULTIPLY, CSS_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DIVIDE, CSS_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MOD, CSS_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.INCREMENT, CSS_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DECREMENT, CSS_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        tokenColors.put(TokenType.EQUAL, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.NOT_EQUAL, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_THAN, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_THAN, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_EQUAL, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_EQUAL, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_EQUAL_TO, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_NOT_EQUAL_TO, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_THAN, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_THAN, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_EQUAL, CSS_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_EQUAL, CSS_OPERATOR_COMPARISON);
        
        // Operators - Logical
        tokenColors.put(TokenType.AND, CSS_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.OR, CSS_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.NOT, CSS_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        tokenColors.put(TokenType.ASSIGN, CSS_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        tokenColors.put(TokenType.LPAREN, CSS_DELIMITER);
        tokenColors.put(TokenType.RPAREN, CSS_DELIMITER);
        tokenColors.put(TokenType.LBRACE, CSS_DELIMITER);
        tokenColors.put(TokenType.RBRACE, CSS_DELIMITER);
        tokenColors.put(TokenType.LBRACKET, CSS_DELIMITER);
        tokenColors.put(TokenType.RBRACKET, CSS_DELIMITER);
        tokenColors.put(TokenType.COMMA, CSS_DELIMITER);
        tokenColors.put(TokenType.SEMICOLON, CSS_DELIMITER);
        tokenColors.put(TokenType.COLON, CSS_DELIMITER);
        tokenColors.put(TokenType.DOT, CSS_DELIMITER);
        tokenColors.put(TokenType.RANGE, CSS_DELIMITER);
        tokenColors.put(TokenType.ARROW, CSS_DELIMITER);
        tokenColors.put(TokenType.QUESTION, CSS_DELIMITER);
        
        // Identifiers
        tokenColors.put(TokenType.IDENTIFIER, CSS_IDENTIFIER);
        
        // Functions
        tokenColors.put(TokenType.BUILTIN_FUNCTION, CSS_FUNCTION_BUILTIN);
        
        // Errors
        tokenColors.put(TokenType.ILLEGAL, CSS_ERROR);
        
        // Special tokens (typically not colored)
        tokenColors.put(TokenType.EOF, CSS_DEFAULT);
        tokenColors.put(TokenType.NEWLINE, CSS_DEFAULT);
        tokenColors.put(TokenType.WHITESPACE, CSS_DEFAULT);
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
     * @return The color name (e.g., "css.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        String colorName = TOKEN_TO_COLOR.get(tokenType);
        return colorName != null ? colorName : CSS_DEFAULT;
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "css.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        String color = DEFAULT_COLORS.get(colorName);
        return color != null ? color : DEFAULT_COLORS.get(CSS_DEFAULT);
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
        return color != null ? color : DEFAULT_COLORS.get(CSS_DEFAULT);
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
