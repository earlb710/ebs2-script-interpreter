package com.eb.script.lexer;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * MdTokenColor defines color names and default color values for each token type.
 * These colors are intended for syntax highlighting in editors and IDEs.
 * 
 * Color names follow the pattern: "md.{category}.{type}"
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
public class MdTokenColor {
    
    // Color name constants
    public static final String MD_COMMENT = "md.comment";
    public static final String MD_STRING = "md.string";
    public static final String MD_NUMBER = "md.number";
    public static final String MD_BOOLEAN = "md.boolean";
    public static final String MD_KEYWORD = "md.keyword";
    public static final String MD_KEYWORD_CONTROL = "md.keyword.control";
    public static final String MD_KEYWORD_DECLARATION = "md.keyword.declaration";
    public static final String MD_KEYWORD_TYPE = "md.keyword.type";
    public static final String MD_OPERATOR = "md.operator";
    public static final String MD_OPERATOR_ARITHMETIC = "md.operator.arithmetic";
    public static final String MD_OPERATOR_COMPARISON = "md.operator.comparison";
    public static final String MD_OPERATOR_LOGICAL = "md.operator.logical";
    public static final String MD_OPERATOR_ASSIGNMENT = "md.operator.assignment";
    public static final String MD_DELIMITER = "md.delimiter";
    public static final String MD_IDENTIFIER = "md.identifier";
    public static final String MD_FUNCTION = "md.function";
    public static final String MD_FUNCTION_BUILTIN = "md.function.builtin";
    public static final String MD_ERROR = "md.error";
    public static final String MD_DEFAULT = "md.default";
    
    // Default color values (hex RGB format)
    private static final Map<String, String> DEFAULT_COLORS;
    
    // Mapping from TokenType to color name (EnumMap for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR;
    
    // Direct mapping from TokenType to color value (cached for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR_VALUE;
    
    static {
        // Initialize color values map
        Map<String, String> colors = new HashMap<>(19);
        colors.put(MD_COMMENT, "#6A9955");
        colors.put(MD_STRING, "#CE9178");
        colors.put(MD_NUMBER, "#B5CEA8");
        colors.put(MD_BOOLEAN, "#569CD6");
        colors.put(MD_KEYWORD, "#C586C0");
        colors.put(MD_KEYWORD_CONTROL, "#C586C0");
        colors.put(MD_KEYWORD_DECLARATION, "#569CD6");
        colors.put(MD_KEYWORD_TYPE, "#4EC9B0");
        colors.put(MD_OPERATOR, "#D4D4D4");
        colors.put(MD_OPERATOR_ARITHMETIC, "#D4D4D4");
        colors.put(MD_OPERATOR_COMPARISON, "#D4D4D4");
        colors.put(MD_OPERATOR_LOGICAL, "#C586C0");
        colors.put(MD_OPERATOR_ASSIGNMENT, "#D4D4D4");
        colors.put(MD_DELIMITER, "#D4D4D4");
        colors.put(MD_IDENTIFIER, "#9CDCFE");
        colors.put(MD_FUNCTION, "#DCDCAA");
        colors.put(MD_FUNCTION_BUILTIN, "#DCDCAA");
        colors.put(MD_ERROR, "#F44747");
        colors.put(MD_DEFAULT, "#D4D4D4");
        DEFAULT_COLORS = Collections.unmodifiableMap(colors);
        
        // Initialize token-to-color-name map (using EnumMap for performance)
        Map<TokenType, String> tokenColors = new EnumMap<>(TokenType.class);
        
        // Comments
        tokenColors.put(TokenType.COMMENT, MD_COMMENT);
        
        // Literals
        tokenColors.put(TokenType.TEXT, MD_STRING);
        tokenColors.put(TokenType.NUMBER, MD_NUMBER);
        tokenColors.put(TokenType.TRUE, MD_BOOLEAN);
        tokenColors.put(TokenType.FALSE, MD_BOOLEAN);
        
        // Keywords - Control Flow
        tokenColors.put(TokenType.IF, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THEN, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.ELSE, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.FOR, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.EACH, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.IN, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TO, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.LOOP, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.WHILE, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.UNTIL, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DO, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.REPEAT, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TIMES, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.BREAK, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CONTINUE, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.RETURN, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.SWITCH, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CASE, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DEFAULT, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TRY, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CATCH, MD_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THROW, MD_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        tokenColors.put(TokenType.VAR, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.VARIABLE, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONST, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONSTANT, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.LET, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FUNCTION, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.PROCEDURE, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CLASS, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.IMPORT, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.EXPORT, MD_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FROM, MD_KEYWORD_DECLARATION);
        
        // Keywords - Type
        tokenColors.put(TokenType.AS, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.IS, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPE, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.ARRAY, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.TEXT_TYPE, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.NUMBER_TYPE, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.FLAG, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.RECORD, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.DATE, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.MAP, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.JSON, MD_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPEOF, MD_KEYWORD_TYPE);
        
        // Keywords - Other
        tokenColors.put(TokenType.PROGRAM, MD_KEYWORD);
        tokenColors.put(TokenType.END, MD_KEYWORD);
        tokenColors.put(TokenType.WITH, MD_KEYWORD);
        tokenColors.put(TokenType.CALL, MD_KEYWORD);
        tokenColors.put(TokenType.PRINT, MD_KEYWORD);
        tokenColors.put(TokenType.LOG, MD_KEYWORD);
        tokenColors.put(TokenType.HIDE, MD_KEYWORD);
        tokenColors.put(TokenType.ASK, MD_KEYWORD);
        tokenColors.put(TokenType.SCREEN, MD_KEYWORD);
        tokenColors.put(TokenType.BUTTON, MD_KEYWORD);
        tokenColors.put(TokenType.LABEL, MD_KEYWORD);
        tokenColors.put(TokenType.TEXTBOX, MD_KEYWORD);
        tokenColors.put(TokenType.INDICATOR, MD_KEYWORD);
        tokenColors.put(TokenType.STEP, MD_KEYWORD);
        tokenColors.put(TokenType.BY, MD_KEYWORD);
        tokenColors.put(TokenType.DOWN, MD_KEYWORD);
        tokenColors.put(TokenType.EXTENDS, MD_KEYWORD);
        tokenColors.put(TokenType.NEW, MD_KEYWORD);
        tokenColors.put(TokenType.ASYNC, MD_KEYWORD);
        tokenColors.put(TokenType.AWAIT, MD_KEYWORD);
        
        // Operators - Arithmetic
        tokenColors.put(TokenType.PLUS, MD_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MINUS, MD_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MULTIPLY, MD_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DIVIDE, MD_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MOD, MD_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.INCREMENT, MD_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DECREMENT, MD_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        tokenColors.put(TokenType.EQUAL, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.NOT_EQUAL, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_THAN, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_THAN, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_EQUAL, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_EQUAL, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_EQUAL_TO, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_NOT_EQUAL_TO, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_THAN, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_THAN, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_EQUAL, MD_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_EQUAL, MD_OPERATOR_COMPARISON);
        
        // Operators - Logical
        tokenColors.put(TokenType.AND, MD_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.OR, MD_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.NOT, MD_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        tokenColors.put(TokenType.ASSIGN, MD_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        tokenColors.put(TokenType.LPAREN, MD_DELIMITER);
        tokenColors.put(TokenType.RPAREN, MD_DELIMITER);
        tokenColors.put(TokenType.LBRACE, MD_DELIMITER);
        tokenColors.put(TokenType.RBRACE, MD_DELIMITER);
        tokenColors.put(TokenType.LBRACKET, MD_DELIMITER);
        tokenColors.put(TokenType.RBRACKET, MD_DELIMITER);
        tokenColors.put(TokenType.COMMA, MD_DELIMITER);
        tokenColors.put(TokenType.SEMICOLON, MD_DELIMITER);
        tokenColors.put(TokenType.COLON, MD_DELIMITER);
        tokenColors.put(TokenType.DOT, MD_DELIMITER);
        tokenColors.put(TokenType.RANGE, MD_DELIMITER);
        tokenColors.put(TokenType.ARROW, MD_DELIMITER);
        tokenColors.put(TokenType.QUESTION, MD_DELIMITER);
        
        // Identifiers
        tokenColors.put(TokenType.IDENTIFIER, MD_IDENTIFIER);
        
        // Functions
        tokenColors.put(TokenType.BUILTIN_FUNCTION, MD_FUNCTION_BUILTIN);
        
        // Errors
        tokenColors.put(TokenType.ILLEGAL, MD_ERROR);
        
        // Special tokens (typically not colored)
        tokenColors.put(TokenType.EOF, MD_DEFAULT);
        tokenColors.put(TokenType.NEWLINE, MD_DEFAULT);
        tokenColors.put(TokenType.WHITESPACE, MD_DEFAULT);
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
     * @return The color name (e.g., "md.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        String colorName = TOKEN_TO_COLOR.get(tokenType);
        return colorName != null ? colorName : MD_DEFAULT;
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "md.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        String color = DEFAULT_COLORS.get(colorName);
        return color != null ? color : DEFAULT_COLORS.get(MD_DEFAULT);
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
        return color != null ? color : DEFAULT_COLORS.get(MD_DEFAULT);
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
