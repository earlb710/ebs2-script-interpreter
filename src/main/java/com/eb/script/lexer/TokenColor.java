package com.eb.script.lexer;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * TokenColor defines color names and default color values for each token type.
 * These colors are intended for syntax highlighting in editors and IDEs.
 * 
 * Color names follow the pattern: "editor.{category}.{type}"
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
public class TokenColor {
    
    // Color name constants
    public static final String EDITOR_COMMENT = "editor.comment";
    public static final String EDITOR_STRING = "editor.string";
    public static final String EDITOR_NUMBER = "editor.number";
    public static final String EDITOR_BOOLEAN = "editor.boolean";
    public static final String EDITOR_KEYWORD = "editor.keyword";
    public static final String EDITOR_KEYWORD_CONTROL = "editor.keyword.control";
    public static final String EDITOR_KEYWORD_DECLARATION = "editor.keyword.declaration";
    public static final String EDITOR_KEYWORD_TYPE = "editor.keyword.type";
    public static final String EDITOR_OPERATOR = "editor.operator";
    public static final String EDITOR_OPERATOR_ARITHMETIC = "editor.operator.arithmetic";
    public static final String EDITOR_OPERATOR_COMPARISON = "editor.operator.comparison";
    public static final String EDITOR_OPERATOR_LOGICAL = "editor.operator.logical";
    public static final String EDITOR_OPERATOR_ASSIGNMENT = "editor.operator.assignment";
    public static final String EDITOR_DELIMITER = "editor.delimiter";
    public static final String EDITOR_IDENTIFIER = "editor.identifier";
    public static final String EDITOR_FUNCTION = "editor.function";
    public static final String EDITOR_FUNCTION_BUILTIN = "editor.function.builtin";
    public static final String EDITOR_ERROR = "editor.error";
    public static final String EDITOR_DEFAULT = "editor.default";
    
    // Default color values (hex RGB format)
    private static final Map<String, String> DEFAULT_COLORS;
    
    // Mapping from TokenType to color name (EnumMap for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR;
    
    // Direct mapping from TokenType to color value (cached for performance)
    private static final Map<TokenType, String> TOKEN_TO_COLOR_VALUE;
    
    static {
        // Initialize color values map
        Map<String, String> colors = new HashMap<>(19);
        colors.put(EDITOR_COMMENT, "#6A9955");
        colors.put(EDITOR_STRING, "#CE9178");
        colors.put(EDITOR_NUMBER, "#B5CEA8");
        colors.put(EDITOR_BOOLEAN, "#569CD6");
        colors.put(EDITOR_KEYWORD, "#C586C0");
        colors.put(EDITOR_KEYWORD_CONTROL, "#C586C0");
        colors.put(EDITOR_KEYWORD_DECLARATION, "#569CD6");
        colors.put(EDITOR_KEYWORD_TYPE, "#4EC9B0");
        colors.put(EDITOR_OPERATOR, "#D4D4D4");
        colors.put(EDITOR_OPERATOR_ARITHMETIC, "#D4D4D4");
        colors.put(EDITOR_OPERATOR_COMPARISON, "#D4D4D4");
        colors.put(EDITOR_OPERATOR_LOGICAL, "#C586C0");
        colors.put(EDITOR_OPERATOR_ASSIGNMENT, "#D4D4D4");
        colors.put(EDITOR_DELIMITER, "#D4D4D4");
        colors.put(EDITOR_IDENTIFIER, "#9CDCFE");
        colors.put(EDITOR_FUNCTION, "#DCDCAA");
        colors.put(EDITOR_FUNCTION_BUILTIN, "#DCDCAA");
        colors.put(EDITOR_ERROR, "#F44747");
        colors.put(EDITOR_DEFAULT, "#D4D4D4");
        DEFAULT_COLORS = Collections.unmodifiableMap(colors);
        
        // Initialize token-to-color-name map (using EnumMap for performance)
        Map<TokenType, String> tokenColors = new EnumMap<>(TokenType.class);
        
        // Comments
        tokenColors.put(TokenType.COMMENT, EDITOR_COMMENT);
        
        // Literals
        tokenColors.put(TokenType.TEXT, EDITOR_STRING);
        tokenColors.put(TokenType.NUMBER, EDITOR_NUMBER);
        tokenColors.put(TokenType.TRUE, EDITOR_BOOLEAN);
        tokenColors.put(TokenType.FALSE, EDITOR_BOOLEAN);
        
        // Keywords - Control Flow
        tokenColors.put(TokenType.IF, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THEN, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.ELSE, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.FOR, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.EACH, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.IN, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TO, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.LOOP, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.WHILE, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.UNTIL, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DO, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.REPEAT, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TIMES, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.BREAK, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CONTINUE, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.RETURN, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.SWITCH, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CASE, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.DEFAULT, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.TRY, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.CATCH, EDITOR_KEYWORD_CONTROL);
        tokenColors.put(TokenType.THROW, EDITOR_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        tokenColors.put(TokenType.VAR, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.VARIABLE, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONST, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CONSTANT, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.LET, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FUNCTION, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.PROCEDURE, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.CLASS, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.IMPORT, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.EXPORT, EDITOR_KEYWORD_DECLARATION);
        tokenColors.put(TokenType.FROM, EDITOR_KEYWORD_DECLARATION);
        
        // Keywords - Type
        tokenColors.put(TokenType.AS, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.IS, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPE, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.ARRAY, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.TEXT_TYPE, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.NUMBER_TYPE, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.FLAG, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.RECORD, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.DATE, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.MAP, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.JSON, EDITOR_KEYWORD_TYPE);
        tokenColors.put(TokenType.TYPEOF, EDITOR_KEYWORD_TYPE);
        
        // Keywords - Other
        tokenColors.put(TokenType.PROGRAM, EDITOR_KEYWORD);
        tokenColors.put(TokenType.END, EDITOR_KEYWORD);
        tokenColors.put(TokenType.WITH, EDITOR_KEYWORD);
        tokenColors.put(TokenType.CALL, EDITOR_KEYWORD);
        tokenColors.put(TokenType.PRINT, EDITOR_KEYWORD);
        tokenColors.put(TokenType.LOG, EDITOR_KEYWORD);
        tokenColors.put(TokenType.HIDE, EDITOR_KEYWORD);
        tokenColors.put(TokenType.ASK, EDITOR_KEYWORD);
        tokenColors.put(TokenType.SCREEN, EDITOR_KEYWORD);
        tokenColors.put(TokenType.BUTTON, EDITOR_KEYWORD);
        tokenColors.put(TokenType.LABEL, EDITOR_KEYWORD);
        tokenColors.put(TokenType.TEXTBOX, EDITOR_KEYWORD);
        tokenColors.put(TokenType.INDICATOR, EDITOR_KEYWORD);
        tokenColors.put(TokenType.STEP, EDITOR_KEYWORD);
        tokenColors.put(TokenType.BY, EDITOR_KEYWORD);
        tokenColors.put(TokenType.DOWN, EDITOR_KEYWORD);
        tokenColors.put(TokenType.EXTENDS, EDITOR_KEYWORD);
        tokenColors.put(TokenType.NEW, EDITOR_KEYWORD);
        tokenColors.put(TokenType.ASYNC, EDITOR_KEYWORD);
        tokenColors.put(TokenType.AWAIT, EDITOR_KEYWORD);
        
        // Operators - Arithmetic
        tokenColors.put(TokenType.PLUS, EDITOR_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MINUS, EDITOR_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MULTIPLY, EDITOR_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DIVIDE, EDITOR_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.MOD, EDITOR_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.INCREMENT, EDITOR_OPERATOR_ARITHMETIC);
        tokenColors.put(TokenType.DECREMENT, EDITOR_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        tokenColors.put(TokenType.EQUAL, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.NOT_EQUAL, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_THAN, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_THAN, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.LESS_EQUAL, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.GREATER_EQUAL, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_EQUAL_TO, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_NOT_EQUAL_TO, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_THAN, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_THAN, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_GREATER_EQUAL, EDITOR_OPERATOR_COMPARISON);
        tokenColors.put(TokenType.IS_LESS_EQUAL, EDITOR_OPERATOR_COMPARISON);
        
        // Operators - Logical
        tokenColors.put(TokenType.AND, EDITOR_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.OR, EDITOR_OPERATOR_LOGICAL);
        tokenColors.put(TokenType.NOT, EDITOR_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        tokenColors.put(TokenType.ASSIGN, EDITOR_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        tokenColors.put(TokenType.LPAREN, EDITOR_DELIMITER);
        tokenColors.put(TokenType.RPAREN, EDITOR_DELIMITER);
        tokenColors.put(TokenType.LBRACE, EDITOR_DELIMITER);
        tokenColors.put(TokenType.RBRACE, EDITOR_DELIMITER);
        tokenColors.put(TokenType.LBRACKET, EDITOR_DELIMITER);
        tokenColors.put(TokenType.RBRACKET, EDITOR_DELIMITER);
        tokenColors.put(TokenType.COMMA, EDITOR_DELIMITER);
        tokenColors.put(TokenType.SEMICOLON, EDITOR_DELIMITER);
        tokenColors.put(TokenType.COLON, EDITOR_DELIMITER);
        tokenColors.put(TokenType.DOT, EDITOR_DELIMITER);
        tokenColors.put(TokenType.RANGE, EDITOR_DELIMITER);
        tokenColors.put(TokenType.ARROW, EDITOR_DELIMITER);
        tokenColors.put(TokenType.QUESTION, EDITOR_DELIMITER);
        
        // Identifiers
        tokenColors.put(TokenType.IDENTIFIER, EDITOR_IDENTIFIER);
        
        // Functions
        tokenColors.put(TokenType.BUILTIN_FUNCTION, EDITOR_FUNCTION_BUILTIN);
        
        // Errors
        tokenColors.put(TokenType.ILLEGAL, EDITOR_ERROR);
        
        // Special tokens (typically not colored)
        tokenColors.put(TokenType.EOF, EDITOR_DEFAULT);
        tokenColors.put(TokenType.NEWLINE, EDITOR_DEFAULT);
        tokenColors.put(TokenType.WHITESPACE, EDITOR_DEFAULT);
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
     * @return The color name (e.g., "editor.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        String colorName = TOKEN_TO_COLOR.get(tokenType);
        return colorName != null ? colorName : EDITOR_DEFAULT;
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "editor.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        String color = DEFAULT_COLORS.get(colorName);
        return color != null ? color : DEFAULT_COLORS.get(EDITOR_DEFAULT);
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
        return color != null ? color : DEFAULT_COLORS.get(EDITOR_DEFAULT);
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
