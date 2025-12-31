package com.eb.script.stdlib;

import com.eb.script.types.JSONValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for JSONFunctions - EBS2 JSON parsing and utility functions
 * 
 * Tests JSON parsing, stringification, and pretty printing.
 * 
 * @version 1.0.0
 * @since 2025-12-31
 */
public class JSONFunctionsTest {
    
    @Test
    @DisplayName("Parse simple JSON object")
    public void testParseSimpleObject() {
        String jsonText = "{\"name\":\"Alice\",\"age\":30}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.OBJECT, json.getType());
        assertEquals("Alice", json.get("name").asString());
        assertEquals(30, json.get("age").asNumber().intValue());
    }
    
    @Test
    @DisplayName("Parse simple JSON array")
    public void testParseSimpleArray() {
        String jsonText = "[1,2,3,4,5]";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.ARRAY, json.getType());
        assertEquals(5, json.size());
        assertEquals(1, json.get(0).asNumber().intValue());
        assertEquals(5, json.get(4).asNumber().intValue());
    }
    
    @Test
    @DisplayName("Parse JSON string")
    public void testParseString() {
        String jsonText = "\"Hello World\"";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.STRING, json.getType());
        assertEquals("Hello World", json.asString());
    }
    
    @Test
    @DisplayName("Parse JSON number")
    public void testParseNumber() {
        String jsonText = "42";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.NUMBER, json.getType());
        assertEquals(42, json.asNumber().intValue());
    }
    
    @Test
    @DisplayName("Parse JSON decimal number")
    public void testParseDecimalNumber() {
        String jsonText = "3.14159";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.NUMBER, json.getType());
        assertEquals(3.14159, json.asNumber().doubleValue(), 0.00001);
    }
    
    @Test
    @DisplayName("Parse JSON boolean true")
    public void testParseTrue() {
        String jsonText = "true";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.BOOLEAN, json.getType());
        assertTrue(json.asBoolean());
    }
    
    @Test
    @DisplayName("Parse JSON boolean false")
    public void testParseFalse() {
        String jsonText = "false";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.BOOLEAN, json.getType());
        assertFalse(json.asBoolean());
    }
    
    @Test
    @DisplayName("Parse JSON null")
    public void testParseNull() {
        String jsonText = "null";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.NULL, json.getType());
        assertTrue(json.isNull());
    }
    
    @Test
    @DisplayName("Parse nested JSON object")
    public void testParseNestedObject() {
        String jsonText = "{\"name\":\"Alice\",\"address\":{\"city\":\"NYC\",\"zip\":\"10001\"}}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals("Alice", json.get("name").asString());
        assertEquals("NYC", json.get("address").get("city").asString());
        assertEquals("10001", json.get("address").get("zip").asString());
    }
    
    @Test
    @DisplayName("Parse JSON array of objects")
    public void testParseArrayOfObjects() {
        String jsonText = "[{\"name\":\"Alice\"},{\"name\":\"Bob\"}]";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.ARRAY, json.getType());
        assertEquals(2, json.size());
        assertEquals("Alice", json.get(0).get("name").asString());
        assertEquals("Bob", json.get(1).get("name").asString());
    }
    
    @Test
    @DisplayName("Parse JSON with whitespace")
    public void testParseWithWhitespace() {
        String jsonText = "  { \"name\" : \"Alice\" , \"age\" : 30 }  ";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals("Alice", json.get("name").asString());
        assertEquals(30, json.get("age").asNumber().intValue());
    }
    
    @Test
    @DisplayName("Parse JSON with escaped characters")
    public void testParseEscapedCharacters() {
        String jsonText = "{\"text\":\"Hello\\nWorld\\t!\"}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals("Hello\nWorld\t!", json.get("text").asString());
    }
    
    @Test
    @DisplayName("Parse JSON with escaped quotes")
    public void testParseEscapedQuotes() {
        String jsonText = "{\"quote\":\"She said \\\"Hello\\\"\"}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals("She said \"Hello\"", json.get("quote").asString());
    }
    
    @Test
    @DisplayName("Parse JSON with negative numbers")
    public void testParseNegativeNumbers() {
        String jsonText = "{\"temp\":-5.5,\"count\":-10}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(-5.5, json.get("temp").asNumber().doubleValue(), 0.01);
        assertEquals(-10, json.get("count").asNumber().intValue());
    }
    
    @Test
    @DisplayName("Parse JSON with scientific notation")
    public void testParseScientificNotation() {
        String jsonText = "{\"big\":1.5e10,\"small\":2.5e-5}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(1.5e10, json.get("big").asNumber().doubleValue(), 1e5);
        assertEquals(2.5e-5, json.get("small").asNumber().doubleValue(), 1e-10);
    }
    
    @Test
    @DisplayName("Parse empty JSON object")
    public void testParseEmptyObject() {
        String jsonText = "{}";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.OBJECT, json.getType());
        assertTrue(json.isEmpty());
    }
    
    @Test
    @DisplayName("Parse empty JSON array")
    public void testParseEmptyArray() {
        String jsonText = "[]";
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(JSONValue.JSONType.ARRAY, json.getType());
        assertTrue(json.isEmpty());
    }
    
    @Test
    @DisplayName("Stringify simple JSON object")
    public void testStringifyObject() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        
        String result = JSONFunctions.stringify(json);
        assertTrue(result.contains("\"name\":\"Alice\""));
        assertTrue(result.contains("\"age\":30"));
    }
    
    @Test
    @DisplayName("Stringify simple JSON array")
    public void testStringifyArray() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        json.append(JSONValue.number(2));
        json.append(JSONValue.number(3));
        
        String result = JSONFunctions.stringify(json);
        assertEquals("[1,2,3]", result);
    }
    
    @Test
    @DisplayName("Stringify null value")
    public void testStringifyNull() {
        String result = JSONFunctions.stringify(null);
        assertEquals("null", result);
    }
    
    @Test
    @DisplayName("Pretty print JSON")
    public void testPrettyPrint() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        
        String result = JSONFunctions.prettyPrint(json);
        assertTrue(result.contains("\n"));
        assertTrue(result.contains("  ")); // Should have indentation
    }
    
    @Test
    @DisplayName("Round trip: parse and stringify")
    public void testRoundTrip() {
        String original = "{\"name\":\"Alice\",\"age\":30,\"active\":true}";
        JSONValue json = JSONFunctions.parse(original);
        String stringified = JSONFunctions.stringify(json);
        
        // Parse the stringified version to verify it's valid
        JSONValue reparsed = JSONFunctions.parse(stringified);
        assertEquals("Alice", reparsed.get("name").asString());
        assertEquals(30, reparsed.get("age").asNumber().intValue());
        assertTrue(reparsed.get("active").asBoolean());
    }
    
    @Test
    @DisplayName("Parse error: empty string")
    public void testParseErrorEmptyString() {
        assertThrows(JSONFunctions.JSONParseException.class, () -> {
            JSONFunctions.parse("");
        });
    }
    
    @Test
    @DisplayName("Parse error: invalid JSON")
    public void testParseErrorInvalidJSON() {
        assertThrows(JSONFunctions.JSONParseException.class, () -> {
            JSONFunctions.parse("{invalid}");
        });
    }
    
    @Test
    @DisplayName("Parse error: unterminated string")
    public void testParseErrorUnterminatedString() {
        assertThrows(JSONFunctions.JSONParseException.class, () -> {
            JSONFunctions.parse("{\"name\":\"Alice}");
        });
    }
    
    @Test
    @DisplayName("Parse error: missing colon")
    public void testParseErrorMissingColon() {
        assertThrows(JSONFunctions.JSONParseException.class, () -> {
            JSONFunctions.parse("{\"name\" \"Alice\"}");
        });
    }
    
    @Test
    @DisplayName("Parse error: trailing comma")
    public void testParseErrorTrailingComma() {
        assertThrows(JSONFunctions.JSONParseException.class, () -> {
            JSONFunctions.parse("{\"name\":\"Alice\",}");
        });
    }
    
    @Test
    @DisplayName("Parse complex nested structure")
    public void testParseComplexStructure() {
        String jsonText = "{\n" +
            "  \"users\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Alice\",\n" +
            "      \"email\": \"alice@example.com\",\n" +
            "      \"active\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"Bob\",\n" +
            "      \"email\": \"bob@example.com\",\n" +
            "      \"active\": false\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total\": 2\n" +
            "}";
        
        JSONValue json = JSONFunctions.parse(jsonText);
        
        assertEquals(2, json.get("total").asNumber().intValue());
        assertEquals(2, json.get("users").size());
        assertEquals("Alice", json.get("users").get(0).get("name").asString());
        assertEquals("bob@example.com", json.get("users").get(1).get("email").asString());
        assertTrue(json.get("users").get(0).get("active").asBoolean());
        assertFalse(json.get("users").get(1).get("active").asBoolean());
    }
}
