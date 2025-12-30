package com.eb.script.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * MdTokenColor defines color names and default color values for each token type
 * in Markdown format contexts. These colors are intended for syntax highlighting in
 * Markdown-based documentation and editors.
 * 
 * Color names follow the pattern: "md.{category}.{type}"
 * 
 * Default colors use hex RGB format: #RRGGBB
 * 
 * @version 2.0.0
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
    private static final Map<String, String> DEFAULT_COLORS = new HashMap<>();
    
    static {
        // Comments - gray/green
        DEFAULT_COLORS.put(MD_COMMENT, "#6A9955");
        
        // Strings - orange/red
        DEFAULT_COLORS.put(MD_STRING, "#CE9178");
        
        // Numbers - light green
        DEFAULT_COLORS.put(MD_NUMBER, "#B5CEA8");
        
        // Booleans - blue
        DEFAULT_COLORS.put(MD_BOOLEAN, "#569CD6");
        
        // Keywords - purple/magenta
        DEFAULT_COLORS.put(MD_KEYWORD, "#C586C0");
        DEFAULT_COLORS.put(MD_KEYWORD_CONTROL, "#C586C0");      // if, for, while, etc.
        DEFAULT_COLORS.put(MD_KEYWORD_DECLARATION, "#569CD6");  // var, function, procedure
        DEFAULT_COLORS.put(MD_KEYWORD_TYPE, "#4EC9B0");         // number, text, array, record
        
        // Operators - white/light gray
        DEFAULT_COLORS.put(MD_OPERATOR, "#D4D4D4");
        DEFAULT_COLORS.put(MD_OPERATOR_ARITHMETIC, "#D4D4D4");  // +, -, *, /
        DEFAULT_COLORS.put(MD_OPERATOR_COMPARISON, "#D4D4D4");  // =, !=, <, >
        DEFAULT_COLORS.put(MD_OPERATOR_LOGICAL, "#C586C0");     // and, or, not
        DEFAULT_COLORS.put(MD_OPERATOR_ASSIGNMENT, "#D4D4D4");  // =
        
        // Delimiters - white/light gray
        DEFAULT_COLORS.put(MD_DELIMITER, "#D4D4D4");
        
        // Identifiers - light blue/white
        DEFAULT_COLORS.put(MD_IDENTIFIER, "#9CDCFE");
        
        // Functions - yellow
        DEFAULT_COLORS.put(MD_FUNCTION, "#DCDCAA");
        DEFAULT_COLORS.put(MD_FUNCTION_BUILTIN, "#DCDCAA");     // Built-in functions
        
        // Errors - red
        DEFAULT_COLORS.put(MD_ERROR, "#F44747");
        
        // Default - white
        DEFAULT_COLORS.put(MD_DEFAULT, "#D4D4D4");
    }
    
    // Mapping from TokenType to color name
    private static final Map<TokenType, String> TOKEN_TO_COLOR = new HashMap<>();
    
    static {
        // Comments
        TOKEN_TO_COLOR.put(TokenType.COMMENT, MD_COMMENT);
        
        // Literals
        TOKEN_TO_COLOR.put(TokenType.TEXT, MD_STRING);
        TOKEN_TO_COLOR.put(TokenType.NUMBER, MD_NUMBER);
        TOKEN_TO_COLOR.put(TokenType.TRUE, MD_BOOLEAN);
        TOKEN_TO_COLOR.put(TokenType.FALSE, MD_BOOLEAN);
        
        // Keywords - Control Flow
        TOKEN_TO_COLOR.put(TokenType.IF, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THEN, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.ELSE, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.FOR, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.EACH, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.IN, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TO, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.LOOP, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.WHILE, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.UNTIL, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DO, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.REPEAT, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TIMES, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.BREAK, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CONTINUE, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.RETURN, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.SWITCH, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CASE, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DEFAULT, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TRY, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CATCH, MD_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THROW, MD_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        TOKEN_TO_COLOR.put(TokenType.VAR, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.VARIABLE, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONST, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONSTANT, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.LET, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FUNCTION, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.PROCEDURE, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CLASS, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.IMPORT, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.EXPORT, MD_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FROM, MD_KEYWORD_DECLARATION);
        
        // Keywords - Type
        TOKEN_TO_COLOR.put(TokenType.AS, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.IS, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPE, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.ARRAY, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TEXT_TYPE, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.NUMBER_TYPE, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.FLAG, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.RECORD, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.DATE, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.MAP, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.JSON, MD_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPEOF, MD_KEYWORD_TYPE);
        
        // Keywords - Other
        TOKEN_TO_COLOR.put(TokenType.PROGRAM, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.END, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.WITH, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.CALL, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.PRINT, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LOG, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.HIDE, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASK, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.SCREEN, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BUTTON, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LABEL, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.TEXTBOX, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.INDICATOR, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.STEP, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BY, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.DOWN, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.EXTENDS, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.NEW, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASYNC, MD_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.AWAIT, MD_KEYWORD);
        
        // Operators - Arithmetic
        TOKEN_TO_COLOR.put(TokenType.PLUS, MD_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MINUS, MD_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MULTIPLY, MD_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DIVIDE, MD_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MOD, MD_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.INCREMENT, MD_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DECREMENT, MD_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        TOKEN_TO_COLOR.put(TokenType.EQUAL, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.NOT_EQUAL, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_THAN, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_THAN, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_EQUAL, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_EQUAL, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_EQUAL_TO, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_NOT_EQUAL_TO, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_THAN, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_THAN, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_EQUAL, MD_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_EQUAL, MD_OPERATOR_COMPARISON);
        
        // Operators - Logical
        TOKEN_TO_COLOR.put(TokenType.AND, MD_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.OR, MD_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.NOT, MD_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        TOKEN_TO_COLOR.put(TokenType.ASSIGN, MD_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        TOKEN_TO_COLOR.put(TokenType.LPAREN, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RPAREN, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACE, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACE, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACKET, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACKET, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COMMA, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.SEMICOLON, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COLON, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.DOT, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RANGE, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.ARROW, MD_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.QUESTION, MD_DELIMITER);
        
        // Identifiers
        TOKEN_TO_COLOR.put(TokenType.IDENTIFIER, MD_IDENTIFIER);
        
        // Functions
        TOKEN_TO_COLOR.put(TokenType.BUILTIN_FUNCTION, MD_FUNCTION_BUILTIN);
        
        // Errors
        TOKEN_TO_COLOR.put(TokenType.ILLEGAL, MD_ERROR);
        
        // Special tokens (typically not colored)
        TOKEN_TO_COLOR.put(TokenType.EOF, MD_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.NEWLINE, MD_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.WHITESPACE, MD_DEFAULT);
    }
    
    /**
     * Gets the color name for a given token type.
     * 
     * @param tokenType The token type
     * @return The color name (e.g., "md.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        return TOKEN_TO_COLOR.getOrDefault(tokenType, MD_DEFAULT);
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "md.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        return DEFAULT_COLORS.getOrDefault(colorName, DEFAULT_COLORS.get(MD_DEFAULT));
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
