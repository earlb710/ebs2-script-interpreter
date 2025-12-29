package com.eb.script.lexer;

/**
 * TokenType enum defines all possible token types in the EBS2 language.
 * 
 * EBS2 Language Specification:
 * - Keywords are case-insensitive
 * - Identifiers are case-insensitive
 * - String literals are case-sensitive
 * 
 * @version 2.0.0
 * @since 2025-12-29
 */
public enum TokenType {
    // Special tokens
    EOF,
    NEWLINE,
    WHITESPACE,
    COMMENT,
    
    // Literals
    NUMBER,          // 42, 3.14, -10
    TEXT,            // "Hello World"
    TRUE,            // true
    FALSE,           // false
    
    // Identifiers
    IDENTIFIER,      // variable names, function names
    
    // Keywords (Basic - Beginner Level)
    PROGRAM,
    END,
    VAR,
    VARIABLE,
    AS,
    IS,
    IF,
    THEN,
    ELSE,
    REPEAT,
    TIMES,
    FOR,
    EACH,
    IN,
    TO,
    LOOP,
    CALL,
    WITH,
    AND,
    RETURN,
    PRINT,
    LOG,
    HIDE,
    ASK,
    SCREEN,
    BUTTON,
    LABEL,
    TEXTBOX,
    ARRAY,
    TEXT_TYPE,       // 'text' keyword for type
    NUMBER_TYPE,     // 'number' keyword for type
    FLAG,
    CONST,
    CONSTANT,
    INDICATOR,
    RECORD,
    TYPE,
    FUNCTION,
    PROCEDURE,
    
    // Keywords (Advanced)
    TRY,
    CATCH,
    THROW,
    IMPORT,
    FROM,
    EXPORT,
    MAP,
    JSON,
    WHILE,
    UNTIL,
    BREAK,
    CONTINUE,
    SWITCH,
    CASE,
    DEFAULT,
    LET,
    ASYNC,
    AWAIT,
    CLASS,
    EXTENDS,
    NEW,
    DO,
    DOWN,
    STEP,
    BY,
    TYPEOF,
    DATE,
    
    // Operators (Arithmetic)
    PLUS,            // +
    MINUS,           // -
    MULTIPLY,        // *
    DIVIDE,          // /
    MOD,             // mod
    INCREMENT,       // ++
    DECREMENT,       // --
    
    // Operators (Comparison)
    EQUAL,           // = or ==
    NOT_EQUAL,       // != or <>
    LESS_THAN,       // <
    GREATER_THAN,    // >
    LESS_EQUAL,      // <=
    GREATER_EQUAL,   // >=
    
    // Operators (Logical)
    OR,              // or, ||
    NOT,             // not, !
    
    // Operators (Assignment)
    ASSIGN,          // =
    
    // Delimiters
    LPAREN,          // (
    RPAREN,          // )
    LBRACE,          // {
    RBRACE,          // }
    LBRACKET,        // [
    RBRACKET,        // ]
    COMMA,           // ,
    SEMICOLON,       // ;
    COLON,           // :
    DOT,             // .
    RANGE,           // ..
    
    // Special operators
    ARROW,           // =>
    QUESTION,        // ?
    
    // Long form comparison operators (for natural language)
    IS_EQUAL_TO,         // "is equal to"
    IS_NOT_EQUAL_TO,     // "is not equal to"
    IS_GREATER_THAN,     // "is greater than"
    IS_LESS_THAN,        // "is less than"
    IS_GREATER_EQUAL,    // "is greater than or equal to"
    IS_LESS_EQUAL,       // "is less than or equal to"
    
    // Error token
    ILLEGAL
}
