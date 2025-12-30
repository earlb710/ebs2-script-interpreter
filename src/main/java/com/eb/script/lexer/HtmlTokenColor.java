package com.eb.script.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * HtmlTokenColor defines color names and default color values for each token type
 * in HTML format contexts. These colors are intended for syntax highlighting in
 * HTML-based editors and web interfaces.
 * 
 * Color names follow the pattern: "html.{category}.{type}"
 * 
 * Default colors use hex RGB format: #RRGGBB
 * 
 * @version 2.0.0
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
    private static final Map<String, String> DEFAULT_COLORS = new HashMap<>();
    
    static {
        // Comments - gray/green
        DEFAULT_COLORS.put(HTML_COMMENT, "#6A9955");
        
        // Strings - orange/red
        DEFAULT_COLORS.put(HTML_STRING, "#CE9178");
        
        // Numbers - light green
        DEFAULT_COLORS.put(HTML_NUMBER, "#B5CEA8");
        
        // Booleans - blue
        DEFAULT_COLORS.put(HTML_BOOLEAN, "#569CD6");
        
        // Keywords - purple/magenta
        DEFAULT_COLORS.put(HTML_KEYWORD, "#C586C0");
        DEFAULT_COLORS.put(HTML_KEYWORD_CONTROL, "#C586C0");      // if, for, while, etc.
        DEFAULT_COLORS.put(HTML_KEYWORD_DECLARATION, "#569CD6");  // var, function, procedure
        DEFAULT_COLORS.put(HTML_KEYWORD_TYPE, "#4EC9B0");         // number, text, array, record
        
        // Operators - white/light gray
        DEFAULT_COLORS.put(HTML_OPERATOR, "#D4D4D4");
        DEFAULT_COLORS.put(HTML_OPERATOR_ARITHMETIC, "#D4D4D4");  // +, -, *, /
        DEFAULT_COLORS.put(HTML_OPERATOR_COMPARISON, "#D4D4D4");  // =, !=, <, >
        DEFAULT_COLORS.put(HTML_OPERATOR_LOGICAL, "#C586C0");     // and, or, not
        DEFAULT_COLORS.put(HTML_OPERATOR_ASSIGNMENT, "#D4D4D4");  // =
        
        // Delimiters - white/light gray
        DEFAULT_COLORS.put(HTML_DELIMITER, "#D4D4D4");
        
        // Identifiers - light blue/white
        DEFAULT_COLORS.put(HTML_IDENTIFIER, "#9CDCFE");
        
        // Functions - yellow
        DEFAULT_COLORS.put(HTML_FUNCTION, "#DCDCAA");
        DEFAULT_COLORS.put(HTML_FUNCTION_BUILTIN, "#DCDCAA");     // Built-in functions
        
        // Errors - red
        DEFAULT_COLORS.put(HTML_ERROR, "#F44747");
        
        // Default - white
        DEFAULT_COLORS.put(HTML_DEFAULT, "#D4D4D4");
    }
    
    // Mapping from TokenType to color name
    private static final Map<TokenType, String> TOKEN_TO_COLOR = new HashMap<>();
    
    static {
        // Comments
        TOKEN_TO_COLOR.put(TokenType.COMMENT, HTML_COMMENT);
        
        // Literals
        TOKEN_TO_COLOR.put(TokenType.TEXT, HTML_STRING);
        TOKEN_TO_COLOR.put(TokenType.NUMBER, HTML_NUMBER);
        TOKEN_TO_COLOR.put(TokenType.TRUE, HTML_BOOLEAN);
        TOKEN_TO_COLOR.put(TokenType.FALSE, HTML_BOOLEAN);
        
        // Keywords - Control Flow
        TOKEN_TO_COLOR.put(TokenType.IF, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THEN, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.ELSE, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.FOR, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.EACH, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.IN, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TO, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.LOOP, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.WHILE, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.UNTIL, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DO, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.REPEAT, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TIMES, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.BREAK, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CONTINUE, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.RETURN, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.SWITCH, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CASE, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DEFAULT, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TRY, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CATCH, HTML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THROW, HTML_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        TOKEN_TO_COLOR.put(TokenType.VAR, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.VARIABLE, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONST, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONSTANT, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.LET, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FUNCTION, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.PROCEDURE, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CLASS, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.IMPORT, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.EXPORT, HTML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FROM, HTML_KEYWORD_DECLARATION);
        
        // Keywords - Type
        TOKEN_TO_COLOR.put(TokenType.AS, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.IS, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPE, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.ARRAY, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TEXT_TYPE, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.NUMBER_TYPE, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.FLAG, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.RECORD, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.DATE, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.MAP, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.JSON, HTML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPEOF, HTML_KEYWORD_TYPE);
        
        // Keywords - Other
        TOKEN_TO_COLOR.put(TokenType.PROGRAM, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.END, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.WITH, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.CALL, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.PRINT, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LOG, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.HIDE, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASK, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.SCREEN, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BUTTON, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LABEL, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.TEXTBOX, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.INDICATOR, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.STEP, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BY, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.DOWN, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.EXTENDS, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.NEW, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASYNC, HTML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.AWAIT, HTML_KEYWORD);
        
        // Operators - Arithmetic
        TOKEN_TO_COLOR.put(TokenType.PLUS, HTML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MINUS, HTML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MULTIPLY, HTML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DIVIDE, HTML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MOD, HTML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.INCREMENT, HTML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DECREMENT, HTML_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        TOKEN_TO_COLOR.put(TokenType.EQUAL, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.NOT_EQUAL, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_THAN, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_THAN, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_EQUAL, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_EQUAL, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_EQUAL_TO, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_NOT_EQUAL_TO, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_THAN, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_THAN, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_EQUAL, HTML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_EQUAL, HTML_OPERATOR_COMPARISON);
        
        // Operators - Logical
        TOKEN_TO_COLOR.put(TokenType.AND, HTML_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.OR, HTML_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.NOT, HTML_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        TOKEN_TO_COLOR.put(TokenType.ASSIGN, HTML_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        TOKEN_TO_COLOR.put(TokenType.LPAREN, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RPAREN, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACE, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACE, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACKET, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACKET, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COMMA, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.SEMICOLON, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COLON, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.DOT, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RANGE, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.ARROW, HTML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.QUESTION, HTML_DELIMITER);
        
        // Identifiers
        TOKEN_TO_COLOR.put(TokenType.IDENTIFIER, HTML_IDENTIFIER);
        
        // Functions
        TOKEN_TO_COLOR.put(TokenType.BUILTIN_FUNCTION, HTML_FUNCTION_BUILTIN);
        
        // Errors
        TOKEN_TO_COLOR.put(TokenType.ILLEGAL, HTML_ERROR);
        
        // Special tokens (typically not colored)
        TOKEN_TO_COLOR.put(TokenType.EOF, HTML_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.NEWLINE, HTML_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.WHITESPACE, HTML_DEFAULT);
    }
    
    /**
     * Gets the color name for a given token type.
     * 
     * @param tokenType The token type
     * @return The color name (e.g., "html.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        return TOKEN_TO_COLOR.getOrDefault(tokenType, HTML_DEFAULT);
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "html.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        return DEFAULT_COLORS.getOrDefault(colorName, DEFAULT_COLORS.get(HTML_DEFAULT));
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
