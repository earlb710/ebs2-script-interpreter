package com.eb.script.lexer;

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
 * @version 2.0.0
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
    private static final Map<String, String> DEFAULT_COLORS = new HashMap<>();
    
    static {
        // Comments - gray/green
        DEFAULT_COLORS.put(EDITOR_COMMENT, "#6A9955");
        
        // Strings - orange/red
        DEFAULT_COLORS.put(EDITOR_STRING, "#CE9178");
        
        // Numbers - light green
        DEFAULT_COLORS.put(EDITOR_NUMBER, "#B5CEA8");
        
        // Booleans - blue
        DEFAULT_COLORS.put(EDITOR_BOOLEAN, "#569CD6");
        
        // Keywords - purple/magenta
        DEFAULT_COLORS.put(EDITOR_KEYWORD, "#C586C0");
        DEFAULT_COLORS.put(EDITOR_KEYWORD_CONTROL, "#C586C0");      // if, for, while, etc.
        DEFAULT_COLORS.put(EDITOR_KEYWORD_DECLARATION, "#569CD6");  // var, function, procedure
        DEFAULT_COLORS.put(EDITOR_KEYWORD_TYPE, "#4EC9B0");         // number, text, array, record
        
        // Operators - white/light gray
        DEFAULT_COLORS.put(EDITOR_OPERATOR, "#D4D4D4");
        DEFAULT_COLORS.put(EDITOR_OPERATOR_ARITHMETIC, "#D4D4D4");  // +, -, *, /
        DEFAULT_COLORS.put(EDITOR_OPERATOR_COMPARISON, "#D4D4D4");  // =, !=, <, >
        DEFAULT_COLORS.put(EDITOR_OPERATOR_LOGICAL, "#C586C0");     // and, or, not
        DEFAULT_COLORS.put(EDITOR_OPERATOR_ASSIGNMENT, "#D4D4D4");  // =
        
        // Delimiters - white/light gray
        DEFAULT_COLORS.put(EDITOR_DELIMITER, "#D4D4D4");
        
        // Identifiers - light blue/white
        DEFAULT_COLORS.put(EDITOR_IDENTIFIER, "#9CDCFE");
        
        // Functions - yellow
        DEFAULT_COLORS.put(EDITOR_FUNCTION, "#DCDCAA");
        DEFAULT_COLORS.put(EDITOR_FUNCTION_BUILTIN, "#DCDCAA");     // Built-in functions
        
        // Errors - red
        DEFAULT_COLORS.put(EDITOR_ERROR, "#F44747");
        
        // Default - white
        DEFAULT_COLORS.put(EDITOR_DEFAULT, "#D4D4D4");
    }
    
    // Mapping from TokenType to color name
    private static final Map<TokenType, String> TOKEN_TO_COLOR = new HashMap<>();
    
    static {
        // Comments
        TOKEN_TO_COLOR.put(TokenType.COMMENT, EDITOR_COMMENT);
        
        // Literals
        TOKEN_TO_COLOR.put(TokenType.TEXT, EDITOR_STRING);
        TOKEN_TO_COLOR.put(TokenType.NUMBER, EDITOR_NUMBER);
        TOKEN_TO_COLOR.put(TokenType.TRUE, EDITOR_BOOLEAN);
        TOKEN_TO_COLOR.put(TokenType.FALSE, EDITOR_BOOLEAN);
        
        // Keywords - Control Flow
        TOKEN_TO_COLOR.put(TokenType.IF, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THEN, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.ELSE, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.FOR, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.EACH, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.IN, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TO, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.LOOP, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.WHILE, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.UNTIL, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DO, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.REPEAT, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TIMES, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.BREAK, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CONTINUE, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.RETURN, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.SWITCH, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CASE, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DEFAULT, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TRY, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CATCH, EDITOR_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THROW, EDITOR_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        TOKEN_TO_COLOR.put(TokenType.VAR, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.VARIABLE, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONST, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONSTANT, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.LET, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FUNCTION, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.PROCEDURE, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CLASS, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.IMPORT, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.EXPORT, EDITOR_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FROM, EDITOR_KEYWORD_DECLARATION);
        
        // Keywords - Type
        TOKEN_TO_COLOR.put(TokenType.AS, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.IS, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPE, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.ARRAY, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TEXT_TYPE, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.NUMBER_TYPE, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.FLAG, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.RECORD, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.DATE, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.MAP, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.JSON, EDITOR_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPEOF, EDITOR_KEYWORD_TYPE);
        
        // Keywords - Other
        TOKEN_TO_COLOR.put(TokenType.PROGRAM, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.END, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.WITH, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.CALL, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.PRINT, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LOG, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.HIDE, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASK, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.SCREEN, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BUTTON, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LABEL, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.TEXTBOX, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.INDICATOR, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.STEP, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BY, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.DOWN, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.EXTENDS, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.NEW, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASYNC, EDITOR_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.AWAIT, EDITOR_KEYWORD);
        
        // Operators - Arithmetic
        TOKEN_TO_COLOR.put(TokenType.PLUS, EDITOR_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MINUS, EDITOR_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MULTIPLY, EDITOR_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DIVIDE, EDITOR_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MOD, EDITOR_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.INCREMENT, EDITOR_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DECREMENT, EDITOR_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        TOKEN_TO_COLOR.put(TokenType.EQUAL, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.NOT_EQUAL, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_THAN, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_THAN, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_EQUAL, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_EQUAL, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_EQUAL_TO, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_NOT_EQUAL_TO, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_THAN, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_THAN, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_EQUAL, EDITOR_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_EQUAL, EDITOR_OPERATOR_COMPARISON);
        
        // Operators - Logical
        TOKEN_TO_COLOR.put(TokenType.AND, EDITOR_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.OR, EDITOR_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.NOT, EDITOR_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        TOKEN_TO_COLOR.put(TokenType.ASSIGN, EDITOR_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        TOKEN_TO_COLOR.put(TokenType.LPAREN, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RPAREN, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACE, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACE, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACKET, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACKET, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COMMA, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.SEMICOLON, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COLON, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.DOT, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RANGE, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.ARROW, EDITOR_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.QUESTION, EDITOR_DELIMITER);
        
        // Identifiers
        TOKEN_TO_COLOR.put(TokenType.IDENTIFIER, EDITOR_IDENTIFIER);
        
        // Functions
        TOKEN_TO_COLOR.put(TokenType.BUILTIN_FUNCTION, EDITOR_FUNCTION_BUILTIN);
        
        // Errors
        TOKEN_TO_COLOR.put(TokenType.ILLEGAL, EDITOR_ERROR);
        
        // Special tokens (typically not colored)
        TOKEN_TO_COLOR.put(TokenType.EOF, EDITOR_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.NEWLINE, EDITOR_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.WHITESPACE, EDITOR_DEFAULT);
    }
    
    /**
     * Gets the color name for a given token type.
     * 
     * @param tokenType The token type
     * @return The color name (e.g., "editor.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        return TOKEN_TO_COLOR.getOrDefault(tokenType, EDITOR_DEFAULT);
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "editor.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        return DEFAULT_COLORS.getOrDefault(colorName, DEFAULT_COLORS.get(EDITOR_DEFAULT));
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
