package com.eb.script.types;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test class for JSONValue - EBS2 JSON datatype
 * 
 * Tests all JSON methods including:
 * - Object operations (get, set, has, keys, values)
 * - Array operations (get, set, append, remove)
 * - Introspection (size, isEmpty, keys, values)
 * - Manipulation (copy, merge)
 * - Serialization (toString, toJSON, toPrettyJSON)
 * 
 * @version 1.0.0
 * @since 2025-12-31
 */
public class JSONValueTest {
    
    @Test
    @DisplayName("Create empty JSON object")
    public void testCreateEmptyObject() {
        JSONValue json = JSONValue.object();
        assertEquals(JSONValue.JSONType.OBJECT, json.getType());
        assertTrue(json.isEmpty());
        assertEquals(0, json.size());
    }
    
    @Test
    @DisplayName("Create empty JSON array")
    public void testCreateEmptyArray() {
        JSONValue json = JSONValue.array();
        assertEquals(JSONValue.JSONType.ARRAY, json.getType());
        assertTrue(json.isEmpty());
        assertEquals(0, json.size());
    }
    
    @Test
    @DisplayName("Create JSON string value")
    public void testCreateString() {
        JSONValue json = JSONValue.string("Hello World");
        assertEquals(JSONValue.JSONType.STRING, json.getType());
        assertEquals("Hello World", json.asString());
    }
    
    @Test
    @DisplayName("Create JSON number value")
    public void testCreateNumber() {
        JSONValue json = JSONValue.number(42);
        assertEquals(JSONValue.JSONType.NUMBER, json.getType());
        assertEquals(42, json.asNumber().intValue());
    }
    
    @Test
    @DisplayName("Create JSON boolean value")
    public void testCreateBoolean() {
        JSONValue jsonTrue = JSONValue.bool(true);
        JSONValue jsonFalse = JSONValue.bool(false);
        assertEquals(JSONValue.JSONType.BOOLEAN, jsonTrue.getType());
        assertTrue(jsonTrue.asBoolean());
        assertFalse(jsonFalse.asBoolean());
    }
    
    @Test
    @DisplayName("Create JSON null value")
    public void testCreateNull() {
        JSONValue json = JSONValue.nullValue();
        assertEquals(JSONValue.JSONType.NULL, json.getType());
        assertTrue(json.isNull());
    }
    
    @Test
    @DisplayName("JSON object: set and get values")
    public void testObjectSetGet() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        json.set("active", JSONValue.bool(true));
        
        assertEquals("Alice", json.get("name").asString());
        assertEquals(30, json.get("age").asNumber().intValue());
        assertTrue(json.get("active").asBoolean());
        assertEquals(3, json.size());
    }
    
    @Test
    @DisplayName("JSON object: has method")
    public void testObjectHas() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Bob"));
        
        assertTrue(json.has("name"));
        assertFalse(json.has("email"));
    }
    
    @Test
    @DisplayName("JSON object: keys method")
    public void testObjectKeys() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        json.set("city", JSONValue.string("NYC"));
        
        List<String> keys = json.keys();
        assertEquals(3, keys.size());
        assertTrue(keys.contains("name"));
        assertTrue(keys.contains("age"));
        assertTrue(keys.contains("city"));
    }
    
    @Test
    @DisplayName("JSON object: values method")
    public void testObjectValues() {
        JSONValue json = JSONValue.object();
        json.set("a", JSONValue.number(1));
        json.set("b", JSONValue.number(2));
        json.set("c", JSONValue.number(3));
        
        List<JSONValue> values = json.values();
        assertEquals(3, values.size());
    }
    
    @Test
    @DisplayName("JSON object: remove method")
    public void testObjectRemove() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        
        assertEquals(2, json.size());
        
        JSONValue removed = json.remove("name");
        assertEquals("Alice", removed.asString());
        assertEquals(1, json.size());
        assertFalse(json.has("name"));
    }
    
    @Test
    @DisplayName("JSON array: append and get")
    public void testArrayAppendGet() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        json.append(JSONValue.number(2));
        json.append(JSONValue.number(3));
        
        assertEquals(3, json.size());
        assertEquals(1, json.get(0).asNumber().intValue());
        assertEquals(2, json.get(1).asNumber().intValue());
        assertEquals(3, json.get(2).asNumber().intValue());
    }
    
    @Test
    @DisplayName("JSON array: set method")
    public void testArraySet() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        json.append(JSONValue.number(2));
        json.append(JSONValue.number(3));
        
        json.set(1, JSONValue.number(20));
        assertEquals(20, json.get(1).asNumber().intValue());
    }
    
    @Test
    @DisplayName("JSON array: remove method")
    public void testArrayRemove() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        json.append(JSONValue.number(2));
        json.append(JSONValue.number(3));
        
        assertEquals(3, json.size());
        
        JSONValue removed = json.remove(1);
        assertEquals(2, removed.asNumber().intValue());
        assertEquals(2, json.size());
        assertEquals(3, json.get(1).asNumber().intValue());
    }
    
    @Test
    @DisplayName("JSON array: values method")
    public void testArrayValues() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        json.append(JSONValue.number(2));
        json.append(JSONValue.number(3));
        
        List<JSONValue> values = json.values();
        assertEquals(3, values.size());
        assertEquals(1, values.get(0).asNumber().intValue());
        assertEquals(2, values.get(1).asNumber().intValue());
        assertEquals(3, values.get(2).asNumber().intValue());
    }
    
    @Test
    @DisplayName("JSON object: copy method")
    public void testObjectCopy() {
        JSONValue original = JSONValue.object();
        original.set("name", JSONValue.string("Alice"));
        original.set("age", JSONValue.number(30));
        
        JSONValue copy = original.copy();
        
        // Modify copy
        copy.set("name", JSONValue.string("Bob"));
        
        // Original should be unchanged
        assertEquals("Alice", original.get("name").asString());
        assertEquals("Bob", copy.get("name").asString());
    }
    
    @Test
    @DisplayName("JSON array: copy method")
    public void testArrayCopy() {
        JSONValue original = JSONValue.array();
        original.append(JSONValue.number(1));
        original.append(JSONValue.number(2));
        
        JSONValue copy = original.copy();
        
        // Modify copy
        copy.set(0, JSONValue.number(10));
        
        // Original should be unchanged
        assertEquals(1, original.get(0).asNumber().intValue());
        assertEquals(10, copy.get(0).asNumber().intValue());
    }
    
    @Test
    @DisplayName("JSON object: merge method")
    public void testObjectMerge() {
        JSONValue obj1 = JSONValue.object();
        obj1.set("name", JSONValue.string("Alice"));
        obj1.set("age", JSONValue.number(30));
        
        JSONValue obj2 = JSONValue.object();
        obj2.set("city", JSONValue.string("NYC"));
        obj2.set("age", JSONValue.number(31)); // This should override
        
        JSONValue merged = obj1.merge(obj2);
        
        assertEquals("Alice", merged.get("name").asString());
        assertEquals(31, merged.get("age").asNumber().intValue()); // obj2's age wins
        assertEquals("NYC", merged.get("city").asString());
        assertEquals(3, merged.size());
    }
    
    @Test
    @DisplayName("JSON object: isEmpty method")
    public void testObjectIsEmpty() {
        JSONValue json = JSONValue.object();
        assertTrue(json.isEmpty());
        
        json.set("key", JSONValue.string("value"));
        assertFalse(json.isEmpty());
    }
    
    @Test
    @DisplayName("JSON array: isEmpty method")
    public void testArrayIsEmpty() {
        JSONValue json = JSONValue.array();
        assertTrue(json.isEmpty());
        
        json.append(JSONValue.number(1));
        assertFalse(json.isEmpty());
    }
    
    @Test
    @DisplayName("JSON object: toString method")
    public void testObjectToString() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        
        String jsonStr = json.toString();
        assertTrue(jsonStr.contains("\"name\":\"Alice\""));
        assertTrue(jsonStr.contains("\"age\":30"));
    }
    
    @Test
    @DisplayName("JSON array: toString method")
    public void testArrayToString() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        json.append(JSONValue.number(2));
        json.append(JSONValue.number(3));
        
        String jsonStr = json.toString();
        assertEquals("[1,2,3]", jsonStr);
    }
    
    @Test
    @DisplayName("JSON nested structures")
    public void testNestedStructures() {
        JSONValue person = JSONValue.object();
        person.set("name", JSONValue.string("Alice"));
        
        JSONValue address = JSONValue.object();
        address.set("street", JSONValue.string("123 Main St"));
        address.set("city", JSONValue.string("NYC"));
        
        person.set("address", address);
        
        JSONValue hobbies = JSONValue.array();
        hobbies.append(JSONValue.string("reading"));
        hobbies.append(JSONValue.string("coding"));
        
        person.set("hobbies", hobbies);
        
        assertEquals("Alice", person.get("name").asString());
        assertEquals("NYC", person.get("address").get("city").asString());
        assertEquals("reading", person.get("hobbies").get(0).asString());
        assertEquals(3, person.size());
    }
    
    @Test
    @DisplayName("JSON pretty print")
    public void testPrettyPrint() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        json.set("age", JSONValue.number(30));
        
        String pretty = json.toPrettyJSON();
        assertTrue(pretty.contains("\n"));
        assertTrue(pretty.contains("  ")); // Should have indentation
    }
    
    @Test
    @DisplayName("JSON get non-existent key returns null")
    public void testGetNonExistentKey() {
        JSONValue json = JSONValue.object();
        json.set("name", JSONValue.string("Alice"));
        
        JSONValue result = json.get("email");
        assertTrue(result.isNull());
    }
    
    @Test
    @DisplayName("JSON get out of bounds array index returns null")
    public void testGetOutOfBoundsIndex() {
        JSONValue json = JSONValue.array();
        json.append(JSONValue.number(1));
        
        JSONValue result = json.get(5);
        assertTrue(result.isNull());
    }
    
    @Test
    @DisplayName("JSON equals method")
    public void testEquals() {
        JSONValue json1 = JSONValue.object();
        json1.set("name", JSONValue.string("Alice"));
        
        JSONValue json2 = JSONValue.object();
        json2.set("name", JSONValue.string("Alice"));
        
        assertEquals(json1, json2);
        
        json2.set("age", JSONValue.number(30));
        assertNotEquals(json1, json2);
    }
    
    @Test
    @DisplayName("JSON with special characters in strings")
    public void testSpecialCharacters() {
        JSONValue json = JSONValue.object();
        json.set("text", JSONValue.string("Hello\n\"World\"\t!"));
        
        String jsonStr = json.toString();
        assertTrue(jsonStr.contains("\\n"));
        assertTrue(jsonStr.contains("\\\""));
        assertTrue(jsonStr.contains("\\t"));
    }
    
    @Test
    @DisplayName("JSON error: set on non-object")
    public void testSetOnNonObject() {
        JSONValue json = JSONValue.array();
        assertThrows(IllegalStateException.class, () -> {
            json.set("key", JSONValue.string("value"));
        });
    }
    
    @Test
    @DisplayName("JSON error: append on non-array")
    public void testAppendOnNonArray() {
        JSONValue json = JSONValue.object();
        assertThrows(IllegalStateException.class, () -> {
            json.append(JSONValue.string("value"));
        });
    }
}
