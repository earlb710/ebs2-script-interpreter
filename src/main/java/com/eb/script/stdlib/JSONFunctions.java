package com.eb.script.stdlib;

import com.eb.script.types.JSONValue;

/**
 * JSONFunctions provides static utility functions for JSON operations in EBS2.
 * 
 * These functions support:
 * - Parsing JSON from text
 * - Creating JSON objects and arrays
 * - Converting between EBS2 types and JSON
 * 
 * EBS2 Syntax Examples:
 * - var data as json = json.parse(text)
 * - var config as json = json.load("config.json")
 * - var jsonText as text = json.stringify(data)
 * 
 * @version 1.0.0
 * @since 2025-12-31
 */
public class JSONFunctions {
    
    /**
     * Parses a JSON string and returns a JSONValue
     * EBS2 function: json.parse(text)
     * 
     * @param jsonText the JSON text to parse
     * @return parsed JSONValue
     * @throws JSONParseException if parsing fails
     */
    public static JSONValue parse(String jsonText) {
        if (jsonText == null || jsonText.trim().isEmpty()) {
            throw new JSONParseException("Cannot parse empty or null JSON text");
        }
        
        JSONParser parser = new JSONParser(jsonText);
        return parser.parse();
    }
    
    /**
     * Converts a JSONValue to a JSON string
     * EBS2 function: json.stringify(value)
     * 
     * @param value the JSON value to stringify
     * @return JSON string representation
     */
    public static String stringify(JSONValue value) {
        if (value == null) {
            return "null";
        }
        return value.toJSON();
    }
    
    /**
     * Converts a JSONValue to a pretty-printed JSON string
     * EBS2 function: json.prettyPrint(value)
     * 
     * @param value the JSON value to pretty print
     * @return pretty-printed JSON string
     */
    public static String prettyPrint(JSONValue value) {
        if (value == null) {
            return "null";
        }
        return value.toPrettyJSON();
    }
    
    /**
     * Simple JSON parser for EBS2
     */
    private static class JSONParser {
        private final String input;
        private int pos;
        private char current;
        
        public JSONParser(String input) {
            this.input = input;
            this.pos = 0;
            this.current = input.length() > 0 ? input.charAt(0) : '\0';
        }
        
        public JSONValue parse() {
            skipWhitespace();
            JSONValue result = parseValue();
            skipWhitespace();
            if (pos < input.length()) {
                throw new JSONParseException("Unexpected characters after JSON value at position " + pos);
            }
            return result;
        }
        
        private JSONValue parseValue() {
            skipWhitespace();
            
            if (current == '{') {
                return parseObject();
            } else if (current == '[') {
                return parseArray();
            } else if (current == '"') {
                return JSONValue.string(parseString());
            } else if (current == 't') {
                return parseTrue();
            } else if (current == 'f') {
                return parseFalse();
            } else if (current == 'n') {
                return parseNull();
            } else if (current == '-' || Character.isDigit(current)) {
                return parseNumber();
            } else {
                throw new JSONParseException("Unexpected character '" + current + "' at position " + pos);
            }
        }
        
        private JSONValue parseObject() {
            JSONValue obj = JSONValue.object();
            advance(); // skip '{'
            skipWhitespace();
            
            if (current == '}') {
                advance();
                return obj;
            }
            
            while (true) {
                skipWhitespace();
                
                if (current != '"') {
                    throw new JSONParseException("Expected string key at position " + pos);
                }
                
                String key = parseString();
                skipWhitespace();
                
                if (current != ':') {
                    throw new JSONParseException("Expected ':' after key at position " + pos);
                }
                advance(); // skip ':'
                
                JSONValue value = parseValue();
                obj.set(key, value);
                
                skipWhitespace();
                
                if (current == '}') {
                    advance();
                    break;
                } else if (current == ',') {
                    advance();
                } else {
                    throw new JSONParseException("Expected ',' or '}' at position " + pos);
                }
            }
            
            return obj;
        }
        
        private JSONValue parseArray() {
            JSONValue arr = JSONValue.array();
            advance(); // skip '['
            skipWhitespace();
            
            if (current == ']') {
                advance();
                return arr;
            }
            
            while (true) {
                JSONValue value = parseValue();
                arr.append(value);
                
                skipWhitespace();
                
                if (current == ']') {
                    advance();
                    break;
                } else if (current == ',') {
                    advance();
                } else {
                    throw new JSONParseException("Expected ',' or ']' at position " + pos);
                }
            }
            
            return arr;
        }
        
        private String parseString() {
            StringBuilder sb = new StringBuilder();
            advance(); // skip opening '"'
            
            while (current != '"' && current != '\0') {
                if (current == '\\') {
                    advance();
                    switch (current) {
                        case '"':
                            sb.append('"');
                            break;
                        case '\\':
                            sb.append('\\');
                            break;
                        case '/':
                            sb.append('/');
                            break;
                        case 'b':
                            sb.append('\b');
                            break;
                        case 'f':
                            sb.append('\f');
                            break;
                        case 'n':
                            sb.append('\n');
                            break;
                        case 'r':
                            sb.append('\r');
                            break;
                        case 't':
                            sb.append('\t');
                            break;
                        case 'u':
                            // Unicode escape
                            advance();
                            String hex = "";
                            for (int i = 0; i < 4; i++) {
                                if (!isHexDigit(current)) {
                                    throw new JSONParseException("Invalid unicode escape at position " + pos);
                                }
                                hex += current;
                                advance();
                            }
                            sb.append((char) Integer.parseInt(hex, 16));
                            continue; // Already advanced
                        default:
                            throw new JSONParseException("Invalid escape sequence '\\" + current + "' at position " + pos);
                    }
                } else {
                    sb.append(current);
                }
                advance();
            }
            
            if (current != '"') {
                throw new JSONParseException("Unterminated string at position " + pos);
            }
            advance(); // skip closing '"'
            
            return sb.toString();
        }
        
        private JSONValue parseNumber() {
            int start = pos;
            
            if (current == '-') {
                advance();
            }
            
            if (current == '0') {
                advance();
            } else if (Character.isDigit(current)) {
                while (Character.isDigit(current)) {
                    advance();
                }
            } else {
                throw new JSONParseException("Invalid number at position " + pos);
            }
            
            boolean isDouble = false;
            
            if (current == '.') {
                isDouble = true;
                advance();
                if (!Character.isDigit(current)) {
                    throw new JSONParseException("Expected digits after decimal point at position " + pos);
                }
                while (Character.isDigit(current)) {
                    advance();
                }
            }
            
            if (current == 'e' || current == 'E') {
                isDouble = true;
                advance();
                if (current == '+' || current == '-') {
                    advance();
                }
                if (!Character.isDigit(current)) {
                    throw new JSONParseException("Expected digits in exponent at position " + pos);
                }
                while (Character.isDigit(current)) {
                    advance();
                }
            }
            
            String numStr = input.substring(start, pos);
            
            try {
                if (isDouble) {
                    return JSONValue.number(Double.parseDouble(numStr));
                } else {
                    long longVal = Long.parseLong(numStr);
                    if (longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE) {
                        return JSONValue.number((int) longVal);
                    } else {
                        return JSONValue.number(longVal);
                    }
                }
            } catch (NumberFormatException e) {
                throw new JSONParseException("Invalid number format: " + numStr);
            }
        }
        
        private JSONValue parseTrue() {
            if (match("true")) {
                return JSONValue.bool(true);
            }
            throw new JSONParseException("Invalid literal at position " + pos);
        }
        
        private JSONValue parseFalse() {
            if (match("false")) {
                return JSONValue.bool(false);
            }
            throw new JSONParseException("Invalid literal at position " + pos);
        }
        
        private JSONValue parseNull() {
            if (match("null")) {
                return JSONValue.nullValue();
            }
            throw new JSONParseException("Invalid literal at position " + pos);
        }
        
        private boolean match(String expected) {
            if (pos + expected.length() > input.length()) {
                return false;
            }
            
            for (int i = 0; i < expected.length(); i++) {
                if (input.charAt(pos + i) != expected.charAt(i)) {
                    return false;
                }
            }
            
            pos += expected.length();
            current = pos < input.length() ? input.charAt(pos) : '\0';
            return true;
        }
        
        private void advance() {
            pos++;
            current = pos < input.length() ? input.charAt(pos) : '\0';
        }
        
        private void skipWhitespace() {
            while (Character.isWhitespace(current)) {
                advance();
            }
        }
        
        private boolean isHexDigit(char c) {
            return Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
        }
    }
    
    /**
     * Exception thrown when JSON parsing fails
     */
    public static class JSONParseException extends RuntimeException {
        public JSONParseException(String message) {
            super(message);
        }
        
        public JSONParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
