package com.eb.script.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * CssTokenColor defines color names and default color values for each token type
 * in CSS format contexts. These colors are intended for syntax highlighting in
 * CSS-based stylesheets and web interfaces.
 * 
 * Color names follow the pattern: "css.{category}.{type}"
 * 
 * Default colors use hex RGB format: #RRGGBB
 * 
 * @version 2.0.0
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
    private static final Map<String, String> DEFAULT_COLORS = new HashMap<>();
    
    static {
        // Comments - gray/green
        DEFAULT_COLORS.put(CSS_COMMENT, "#6A9955");
        
        // Strings - orange/red
        DEFAULT_COLORS.put(CSS_STRING, "#CE9178");
        
        // Numbers - light green
        DEFAULT_COLORS.put(CSS_NUMBER, "#B5CEA8");
        
        // Booleans - blue
        DEFAULT_COLORS.put(CSS_BOOLEAN, "#569CD6");
        
        // Keywords - purple/magenta
        DEFAULT_COLORS.put(CSS_KEYWORD, "#C586C0");
        DEFAULT_COLORS.put(CSS_KEYWORD_CONTROL, "#C586C0");      // if, for, while, etc.
        DEFAULT_COLORS.put(CSS_KEYWORD_DECLARATION, "#569CD6");  // var, function, procedure
        DEFAULT_COLORS.put(CSS_KEYWORD_TYPE, "#4EC9B0");         // number, text, array, record
        
        // Operators - white/light gray
        DEFAULT_COLORS.put(CSS_OPERATOR, "#D4D4D4");
        DEFAULT_COLORS.put(CSS_OPERATOR_ARITHMETIC, "#D4D4D4");  // +, -, *, /
        DEFAULT_COLORS.put(CSS_OPERATOR_COMPARISON, "#D4D4D4");  // =, !=, <, >
        DEFAULT_COLORS.put(CSS_OPERATOR_LOGICAL, "#C586C0");     // and, or, not
        DEFAULT_COLORS.put(CSS_OPERATOR_ASSIGNMENT, "#D4D4D4");  // =
        
        // Delimiters - white/light gray
        DEFAULT_COLORS.put(CSS_DELIMITER, "#D4D4D4");
        
        // Identifiers - light blue/white
        DEFAULT_COLORS.put(CSS_IDENTIFIER, "#9CDCFE");
        
        // Functions - yellow
        DEFAULT_COLORS.put(CSS_FUNCTION, "#DCDCAA");
        DEFAULT_COLORS.put(CSS_FUNCTION_BUILTIN, "#DCDCAA");     // Built-in functions
        
        // Errors - red
        DEFAULT_COLORS.put(CSS_ERROR, "#F44747");
        
        // Default - white
        DEFAULT_COLORS.put(CSS_DEFAULT, "#D4D4D4");
    }
    
    // Mapping from TokenType to color name
    private static final Map<TokenType, String> TOKEN_TO_COLOR = new HashMap<>();
    
    static {
        // Comments
        TOKEN_TO_COLOR.put(TokenType.COMMENT, CSS_COMMENT);
        
        // Literals
        TOKEN_TO_COLOR.put(TokenType.TEXT, CSS_STRING);
        TOKEN_TO_COLOR.put(TokenType.NUMBER, CSS_NUMBER);
        TOKEN_TO_COLOR.put(TokenType.TRUE, CSS_BOOLEAN);
        TOKEN_TO_COLOR.put(TokenType.FALSE, CSS_BOOLEAN);
        
        // Keywords - Control Flow
        TOKEN_TO_COLOR.put(TokenType.IF, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THEN, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.ELSE, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.FOR, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.EACH, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.IN, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TO, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.LOOP, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.WHILE, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.UNTIL, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DO, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.REPEAT, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TIMES, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.BREAK, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CONTINUE, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.RETURN, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.SWITCH, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CASE, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DEFAULT, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TRY, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CATCH, CSS_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THROW, CSS_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        TOKEN_TO_COLOR.put(TokenType.VAR, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.VARIABLE, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONST, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONSTANT, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.LET, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FUNCTION, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.PROCEDURE, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CLASS, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.IMPORT, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.EXPORT, CSS_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FROM, CSS_KEYWORD_DECLARATION);
        
        // Keywords - Type
        TOKEN_TO_COLOR.put(TokenType.AS, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.IS, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPE, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.ARRAY, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TEXT_TYPE, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.NUMBER_TYPE, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.FLAG, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.RECORD, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.DATE, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.MAP, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.JSON, CSS_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPEOF, CSS_KEYWORD_TYPE);
        
        // Keywords - Other
        TOKEN_TO_COLOR.put(TokenType.PROGRAM, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.END, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.WITH, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.CALL, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.PRINT, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LOG, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.HIDE, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASK, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.SCREEN, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BUTTON, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LABEL, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.TEXTBOX, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.INDICATOR, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.STEP, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BY, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.DOWN, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.EXTENDS, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.NEW, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASYNC, CSS_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.AWAIT, CSS_KEYWORD);
        
        // Operators - Arithmetic
        TOKEN_TO_COLOR.put(TokenType.PLUS, CSS_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MINUS, CSS_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MULTIPLY, CSS_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DIVIDE, CSS_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MOD, CSS_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.INCREMENT, CSS_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DECREMENT, CSS_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        TOKEN_TO_COLOR.put(TokenType.EQUAL, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.NOT_EQUAL, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_THAN, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_THAN, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_EQUAL, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_EQUAL, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_EQUAL_TO, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_NOT_EQUAL_TO, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_THAN, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_THAN, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_EQUAL, CSS_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_EQUAL, CSS_OPERATOR_COMPARISON);
        
        // Operators - Logical
        TOKEN_TO_COLOR.put(TokenType.AND, CSS_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.OR, CSS_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.NOT, CSS_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        TOKEN_TO_COLOR.put(TokenType.ASSIGN, CSS_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        TOKEN_TO_COLOR.put(TokenType.LPAREN, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RPAREN, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACE, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACE, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACKET, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACKET, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COMMA, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.SEMICOLON, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COLON, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.DOT, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RANGE, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.ARROW, CSS_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.QUESTION, CSS_DELIMITER);
        
        // Identifiers
        TOKEN_TO_COLOR.put(TokenType.IDENTIFIER, CSS_IDENTIFIER);
        
        // Functions
        TOKEN_TO_COLOR.put(TokenType.BUILTIN_FUNCTION, CSS_FUNCTION_BUILTIN);
        
        // Errors
        TOKEN_TO_COLOR.put(TokenType.ILLEGAL, CSS_ERROR);
        
        // Special tokens (typically not colored)
        TOKEN_TO_COLOR.put(TokenType.EOF, CSS_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.NEWLINE, CSS_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.WHITESPACE, CSS_DEFAULT);
    }
    
    /**
     * Gets the color name for a given token type.
     * 
     * @param tokenType The token type
     * @return The color name (e.g., "css.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        return TOKEN_TO_COLOR.getOrDefault(tokenType, CSS_DEFAULT);
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "css.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        return DEFAULT_COLORS.getOrDefault(colorName, DEFAULT_COLORS.get(CSS_DEFAULT));
    }
    
    /**
     * Gets the default color value for a given token type.
     * 
     * @param tokenType The token type
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColorForToken(TokenType tokenType) {
        String colorName = getColorName(tokenType);
        return getDefaultColor(colorName);
    }
    
    /**
     * Gets all color name constants.
     * 
     * @return Map of color names to default color values
     */
    public static Map<String, String> getAllColors() {
        return new HashMap<>(DEFAULT_COLORS);
    }
    
    /**
     * Gets the mapping from token types to color names.
     * 
     * @return Map of token types to color names
     */
    public static Map<TokenType, String> getTokenColorMapping() {
        return new HashMap<>(TOKEN_TO_COLOR);
    }
}
