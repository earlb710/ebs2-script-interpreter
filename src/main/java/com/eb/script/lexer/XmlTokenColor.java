package com.eb.script.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * XmlTokenColor defines color names and default color values for each token type
 * in XML format contexts. These colors are intended for syntax highlighting in
 * XML-based editors and processors.
 * 
 * Color names follow the pattern: "xml.{category}.{type}"
 * 
 * Default colors use hex RGB format: #RRGGBB
 * 
 * @version 2.0.0
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
    private static final Map<String, String> DEFAULT_COLORS = new HashMap<>();
    
    static {
        // Comments - gray/green
        DEFAULT_COLORS.put(XML_COMMENT, "#6A9955");
        
        // Strings - orange/red
        DEFAULT_COLORS.put(XML_STRING, "#CE9178");
        
        // Numbers - light green
        DEFAULT_COLORS.put(XML_NUMBER, "#B5CEA8");
        
        // Booleans - blue
        DEFAULT_COLORS.put(XML_BOOLEAN, "#569CD6");
        
        // Keywords - purple/magenta
        DEFAULT_COLORS.put(XML_KEYWORD, "#C586C0");
        DEFAULT_COLORS.put(XML_KEYWORD_CONTROL, "#C586C0");      // if, for, while, etc.
        DEFAULT_COLORS.put(XML_KEYWORD_DECLARATION, "#569CD6");  // var, function, procedure
        DEFAULT_COLORS.put(XML_KEYWORD_TYPE, "#4EC9B0");         // number, text, array, record
        
        // Operators - white/light gray
        DEFAULT_COLORS.put(XML_OPERATOR, "#D4D4D4");
        DEFAULT_COLORS.put(XML_OPERATOR_ARITHMETIC, "#D4D4D4");  // +, -, *, /
        DEFAULT_COLORS.put(XML_OPERATOR_COMPARISON, "#D4D4D4");  // =, !=, <, >
        DEFAULT_COLORS.put(XML_OPERATOR_LOGICAL, "#C586C0");     // and, or, not
        DEFAULT_COLORS.put(XML_OPERATOR_ASSIGNMENT, "#D4D4D4");  // =
        
        // Delimiters - white/light gray
        DEFAULT_COLORS.put(XML_DELIMITER, "#D4D4D4");
        
        // Identifiers - light blue/white
        DEFAULT_COLORS.put(XML_IDENTIFIER, "#9CDCFE");
        
        // Functions - yellow
        DEFAULT_COLORS.put(XML_FUNCTION, "#DCDCAA");
        DEFAULT_COLORS.put(XML_FUNCTION_BUILTIN, "#DCDCAA");     // Built-in functions
        
        // Errors - red
        DEFAULT_COLORS.put(XML_ERROR, "#F44747");
        
        // Default - white
        DEFAULT_COLORS.put(XML_DEFAULT, "#D4D4D4");
    }
    
    // Mapping from TokenType to color name
    private static final Map<TokenType, String> TOKEN_TO_COLOR = new HashMap<>();
    
    static {
        // Comments
        TOKEN_TO_COLOR.put(TokenType.COMMENT, XML_COMMENT);
        
        // Literals
        TOKEN_TO_COLOR.put(TokenType.TEXT, XML_STRING);
        TOKEN_TO_COLOR.put(TokenType.NUMBER, XML_NUMBER);
        TOKEN_TO_COLOR.put(TokenType.TRUE, XML_BOOLEAN);
        TOKEN_TO_COLOR.put(TokenType.FALSE, XML_BOOLEAN);
        
        // Keywords - Control Flow
        TOKEN_TO_COLOR.put(TokenType.IF, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THEN, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.ELSE, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.FOR, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.EACH, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.IN, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TO, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.LOOP, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.WHILE, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.UNTIL, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DO, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.REPEAT, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TIMES, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.BREAK, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CONTINUE, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.RETURN, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.SWITCH, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CASE, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.DEFAULT, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.TRY, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.CATCH, XML_KEYWORD_CONTROL);
        TOKEN_TO_COLOR.put(TokenType.THROW, XML_KEYWORD_CONTROL);
        
        // Keywords - Declaration
        TOKEN_TO_COLOR.put(TokenType.VAR, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.VARIABLE, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONST, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CONSTANT, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.LET, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FUNCTION, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.PROCEDURE, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.CLASS, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.IMPORT, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.EXPORT, XML_KEYWORD_DECLARATION);
        TOKEN_TO_COLOR.put(TokenType.FROM, XML_KEYWORD_DECLARATION);
        
        // Keywords - Type
        TOKEN_TO_COLOR.put(TokenType.AS, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.IS, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPE, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.ARRAY, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TEXT_TYPE, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.NUMBER_TYPE, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.FLAG, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.RECORD, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.DATE, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.MAP, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.JSON, XML_KEYWORD_TYPE);
        TOKEN_TO_COLOR.put(TokenType.TYPEOF, XML_KEYWORD_TYPE);
        
        // Keywords - Other
        TOKEN_TO_COLOR.put(TokenType.PROGRAM, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.END, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.WITH, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.CALL, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.PRINT, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LOG, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.HIDE, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASK, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.SCREEN, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BUTTON, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.LABEL, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.TEXTBOX, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.INDICATOR, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.STEP, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.BY, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.DOWN, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.EXTENDS, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.NEW, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.ASYNC, XML_KEYWORD);
        TOKEN_TO_COLOR.put(TokenType.AWAIT, XML_KEYWORD);
        
        // Operators - Arithmetic
        TOKEN_TO_COLOR.put(TokenType.PLUS, XML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MINUS, XML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MULTIPLY, XML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DIVIDE, XML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.MOD, XML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.INCREMENT, XML_OPERATOR_ARITHMETIC);
        TOKEN_TO_COLOR.put(TokenType.DECREMENT, XML_OPERATOR_ARITHMETIC);
        
        // Operators - Comparison
        TOKEN_TO_COLOR.put(TokenType.EQUAL, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.NOT_EQUAL, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_THAN, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_THAN, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.LESS_EQUAL, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.GREATER_EQUAL, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_EQUAL_TO, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_NOT_EQUAL_TO, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_THAN, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_THAN, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_GREATER_EQUAL, XML_OPERATOR_COMPARISON);
        TOKEN_TO_COLOR.put(TokenType.IS_LESS_EQUAL, XML_OPERATOR_COMPARISON);
        
        // Operators - Logical
        TOKEN_TO_COLOR.put(TokenType.AND, XML_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.OR, XML_OPERATOR_LOGICAL);
        TOKEN_TO_COLOR.put(TokenType.NOT, XML_OPERATOR_LOGICAL);
        
        // Operators - Assignment
        TOKEN_TO_COLOR.put(TokenType.ASSIGN, XML_OPERATOR_ASSIGNMENT);
        
        // Delimiters
        TOKEN_TO_COLOR.put(TokenType.LPAREN, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RPAREN, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACE, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACE, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.LBRACKET, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RBRACKET, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COMMA, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.SEMICOLON, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.COLON, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.DOT, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.RANGE, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.ARROW, XML_DELIMITER);
        TOKEN_TO_COLOR.put(TokenType.QUESTION, XML_DELIMITER);
        
        // Identifiers
        TOKEN_TO_COLOR.put(TokenType.IDENTIFIER, XML_IDENTIFIER);
        
        // Functions
        TOKEN_TO_COLOR.put(TokenType.BUILTIN_FUNCTION, XML_FUNCTION_BUILTIN);
        
        // Errors
        TOKEN_TO_COLOR.put(TokenType.ILLEGAL, XML_ERROR);
        
        // Special tokens (typically not colored)
        TOKEN_TO_COLOR.put(TokenType.EOF, XML_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.NEWLINE, XML_DEFAULT);
        TOKEN_TO_COLOR.put(TokenType.WHITESPACE, XML_DEFAULT);
    }
    
    /**
     * Gets the color name for a given token type.
     * 
     * @param tokenType The token type
     * @return The color name (e.g., "xml.keyword.control")
     */
    public static String getColorName(TokenType tokenType) {
        return TOKEN_TO_COLOR.getOrDefault(tokenType, XML_DEFAULT);
    }
    
    /**
     * Gets the default color value for a given color name.
     * 
     * @param colorName The color name (e.g., "xml.keyword.control")
     * @return The default color in hex RGB format (e.g., "#C586C0")
     */
    public static String getDefaultColor(String colorName) {
        return DEFAULT_COLORS.getOrDefault(colorName, DEFAULT_COLORS.get(XML_DEFAULT));
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
