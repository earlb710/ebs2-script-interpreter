package com.eb.script.json;

import java.util.*;

/**
 * JSONValue represents a JSON datatype in EBS2.
 * 
 * Supports the full JSON specification including:
 * - Objects (key-value pairs)
 * - Arrays
 * - Strings
 * - Numbers
 * - Booleans
 * - Null values
 * 
 * This class provides methods for:
 * - Parsing JSON from text
 * - Serializing to JSON text
 * - Accessing and modifying JSON data
 * - Introspection (keys, values, type checking)
 * 
 * @version 1.0.0
 * @since 2025-12-31
 */
public class JSONValue {
    
    /**
     * JSON value types
     */
    public enum JSONType {
        OBJECT,
        ARRAY,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL
    }
    
    private final JSONType type;
    private Object value;
    
    /**
     * Creates a JSON object (empty)
     */
    public JSONValue() {
        this.type = JSONType.OBJECT;
        this.value = new LinkedHashMap<String, JSONValue>();
    }
    
    /**
     * Creates a JSON value with specified type and value
     */
    private JSONValue(JSONType type, Object value) {
        this.type = type;
        this.value = value;
    }
    
    /**
     * Creates a JSON object
     */
    public static JSONValue object() {
        return new JSONValue(JSONType.OBJECT, new LinkedHashMap<String, JSONValue>());
    }
    
    /**
     * Creates a JSON object from a map
     */
    public static JSONValue object(Map<String, JSONValue> map) {
        return new JSONValue(JSONType.OBJECT, new LinkedHashMap<>(map));
    }
    
    /**
     * Creates a JSON array
     */
    public static JSONValue array() {
        return new JSONValue(JSONType.ARRAY, new ArrayList<JSONValue>());
    }
    
    /**
     * Creates a JSON array from a list
     */
    public static JSONValue array(List<JSONValue> list) {
        return new JSONValue(JSONType.ARRAY, new ArrayList<>(list));
    }
    
    /**
     * Creates a JSON string value
     */
    public static JSONValue string(String value) {
        return new JSONValue(JSONType.STRING, value);
    }
    
    /**
     * Creates a JSON number value
     */
    public static JSONValue number(Number value) {
        return new JSONValue(JSONType.NUMBER, value);
    }
    
    /**
     * Creates a JSON boolean value
     */
    public static JSONValue bool(boolean value) {
        return new JSONValue(JSONType.BOOLEAN, value);
    }
    
    /**
     * Creates a JSON null value
     */
    public static JSONValue nullValue() {
        return new JSONValue(JSONType.NULL, null);
    }
    
    /**
     * Gets the JSON type
     */
    public JSONType getType() {
        return type;
    }
    
    /**
     * Gets a value from a JSON object by key
     * EBS2 method: json.get(key)
     */
    public JSONValue get(String key) {
        if (type != JSONType.OBJECT) {
            throw new IllegalStateException("Cannot get key from non-object JSON");
        }
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> map = (Map<String, JSONValue>) value;
        return map.getOrDefault(key, JSONValue.nullValue());
    }
    
    /**
     * Gets a value from a JSON array by index
     * EBS2 method: json.get(index)
     */
    public JSONValue get(int index) {
        if (type != JSONType.ARRAY) {
            throw new IllegalStateException("Cannot get index from non-array JSON");
        }
        @SuppressWarnings("unchecked")
        List<JSONValue> list = (List<JSONValue>) value;
        if (index < 0 || index >= list.size()) {
            return JSONValue.nullValue();
        }
        return list.get(index);
    }
    
    /**
     * Sets a value in a JSON object
     * EBS2 method: json.set(key, value)
     */
    public void set(String key, JSONValue value) {
        if (type != JSONType.OBJECT) {
            throw new IllegalStateException("Cannot set key on non-object JSON");
        }
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> map = (Map<String, JSONValue>) this.value;
        map.put(key, value);
    }
    
    /**
     * Sets a value in a JSON array
     * EBS2 method: json.set(index, value)
     */
    public void set(int index, JSONValue value) {
        if (type != JSONType.ARRAY) {
            throw new IllegalStateException("Cannot set index on non-array JSON");
        }
        @SuppressWarnings("unchecked")
        List<JSONValue> list = (List<JSONValue>) this.value;
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for array of size " + list.size());
        }
        list.set(index, value);
    }
    
    /**
     * Checks if a key exists in a JSON object
     * EBS2 method: json.has(key)
     */
    public boolean has(String key) {
        if (type != JSONType.OBJECT) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> map = (Map<String, JSONValue>) value;
        return map.containsKey(key);
    }
    
    /**
     * Gets all keys from a JSON object
     * EBS2 method: json.keys()
     */
    public List<String> keys() {
        if (type != JSONType.OBJECT) {
            return new ArrayList<>();
        }
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> map = (Map<String, JSONValue>) value;
        return new ArrayList<>(map.keySet());
    }
    
    /**
     * Gets all values from a JSON object
     * EBS2 method: json.values()
     */
    public List<JSONValue> values() {
        if (type == JSONType.OBJECT) {
            @SuppressWarnings("unchecked")
            Map<String, JSONValue> map = (Map<String, JSONValue>) value;
            return new ArrayList<>(map.values());
        } else if (type == JSONType.ARRAY) {
            @SuppressWarnings("unchecked")
            List<JSONValue> list = (List<JSONValue>) value;
            return new ArrayList<>(list);
        }
        return new ArrayList<>();
    }
    
    /**
     * Gets the size of a JSON object or array
     * EBS2 method: json.size()
     */
    public int size() {
        if (type == JSONType.OBJECT) {
            @SuppressWarnings("unchecked")
            Map<String, JSONValue> map = (Map<String, JSONValue>) value;
            return map.size();
        } else if (type == JSONType.ARRAY) {
            @SuppressWarnings("unchecked")
            List<JSONValue> list = (List<JSONValue>) value;
            return list.size();
        }
        return 0;
    }
    
    /**
     * Removes a key from a JSON object
     * EBS2 method: json.remove(key)
     */
    public JSONValue remove(String key) {
        if (type != JSONType.OBJECT) {
            throw new IllegalStateException("Cannot remove key from non-object JSON");
        }
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> map = (Map<String, JSONValue>) value;
        return map.remove(key);
    }
    
    /**
     * Removes an element from a JSON array
     * EBS2 method: json.remove(index)
     */
    public JSONValue remove(int index) {
        if (type != JSONType.ARRAY) {
            throw new IllegalStateException("Cannot remove index from non-array JSON");
        }
        @SuppressWarnings("unchecked")
        List<JSONValue> list = (List<JSONValue>) value;
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for array of size " + list.size());
        }
        return list.remove(index);
    }
    
    /**
     * Adds a value to a JSON array
     * EBS2 method: json.append(value)
     */
    public void append(JSONValue value) {
        if (type != JSONType.ARRAY) {
            throw new IllegalStateException("Cannot append to non-array JSON");
        }
        @SuppressWarnings("unchecked")
        List<JSONValue> list = (List<JSONValue>) this.value;
        list.add(value);
    }
    
    /**
     * Creates a copy of this JSON value
     * EBS2 method: json.copy()
     */
    public JSONValue copy() {
        switch (type) {
            case OBJECT:
                @SuppressWarnings("unchecked")
                Map<String, JSONValue> map = (Map<String, JSONValue>) value;
                Map<String, JSONValue> newMap = new LinkedHashMap<>();
                for (Map.Entry<String, JSONValue> entry : map.entrySet()) {
                    newMap.put(entry.getKey(), entry.getValue().copy());
                }
                return new JSONValue(JSONType.OBJECT, newMap);
            
            case ARRAY:
                @SuppressWarnings("unchecked")
                List<JSONValue> list = (List<JSONValue>) value;
                List<JSONValue> newList = new ArrayList<>();
                for (JSONValue item : list) {
                    newList.add(item.copy());
                }
                return new JSONValue(JSONType.ARRAY, newList);
            
            default:
                // Primitive values are immutable, can return new instance with same value
                return new JSONValue(type, value);
        }
    }
    
    /**
     * Merges another JSON object into this one
     * EBS2 method: json.merge(other)
     */
    public JSONValue merge(JSONValue other) {
        if (type != JSONType.OBJECT || other.type != JSONType.OBJECT) {
            throw new IllegalStateException("Can only merge JSON objects");
        }
        
        JSONValue result = this.copy();
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> resultMap = (Map<String, JSONValue>) result.value;
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> otherMap = (Map<String, JSONValue>) other.value;
        
        for (Map.Entry<String, JSONValue> entry : otherMap.entrySet()) {
            resultMap.put(entry.getKey(), entry.getValue().copy());
        }
        
        return result;
    }
    
    /**
     * Checks if this JSON value is empty
     * EBS2 method: json.isEmpty()
     */
    public boolean isEmpty() {
        if (type == JSONType.OBJECT) {
            @SuppressWarnings("unchecked")
            Map<String, JSONValue> map = (Map<String, JSONValue>) value;
            return map.isEmpty();
        } else if (type == JSONType.ARRAY) {
            @SuppressWarnings("unchecked")
            List<JSONValue> list = (List<JSONValue>) value;
            return list.isEmpty();
        } else if (type == JSONType.STRING) {
            return ((String) value).isEmpty();
        }
        return false;
    }
    
    /**
     * Gets the string value (for STRING type)
     */
    public String asString() {
        if (type != JSONType.STRING) {
            throw new IllegalStateException("JSON value is not a string");
        }
        return (String) value;
    }
    
    /**
     * Gets the number value (for NUMBER type)
     */
    public Number asNumber() {
        if (type != JSONType.NUMBER) {
            throw new IllegalStateException("JSON value is not a number");
        }
        return (Number) value;
    }
    
    /**
     * Gets the boolean value (for BOOLEAN type)
     */
    public boolean asBoolean() {
        if (type != JSONType.BOOLEAN) {
            throw new IllegalStateException("JSON value is not a boolean");
        }
        return (Boolean) value;
    }
    
    /**
     * Checks if this is a null value
     */
    public boolean isNull() {
        return type == JSONType.NULL;
    }
    
    /**
     * Converts this JSON value to a string representation
     * EBS2 method: json.toString()
     */
    @Override
    public String toString() {
        return toJSONString(0);
    }
    
    /**
     * Converts this JSON value to a formatted JSON string
     * EBS2 method: json.toJSON()
     */
    public String toJSON() {
        return toString();
    }
    
    /**
     * Converts this JSON value to a pretty-printed JSON string
     * EBS2 method: json.toPrettyJSON()
     */
    public String toPrettyJSON() {
        return toPrettyString(0);
    }
    
    private String toJSONString(int indent) {
        switch (type) {
            case OBJECT:
                @SuppressWarnings("unchecked")
                Map<String, JSONValue> map = (Map<String, JSONValue>) value;
                if (map.isEmpty()) {
                    return "{}";
                }
                StringBuilder sb = new StringBuilder("{");
                boolean first = true;
                for (Map.Entry<String, JSONValue> entry : map.entrySet()) {
                    if (!first) sb.append(",");
                    first = false;
                    sb.append("\"").append(escapeString(entry.getKey())).append("\":");
                    sb.append(entry.getValue().toJSONString(indent));
                }
                sb.append("}");
                return sb.toString();
            
            case ARRAY:
                @SuppressWarnings("unchecked")
                List<JSONValue> list = (List<JSONValue>) value;
                if (list.isEmpty()) {
                    return "[]";
                }
                StringBuilder ab = new StringBuilder("[");
                boolean afirst = true;
                for (JSONValue item : list) {
                    if (!afirst) ab.append(",");
                    afirst = false;
                    ab.append(item.toJSONString(indent));
                }
                ab.append("]");
                return ab.toString();
            
            case STRING:
                return "\"" + escapeString((String) value) + "\"";
            
            case NUMBER:
                return value.toString();
            
            case BOOLEAN:
                return value.toString();
            
            case NULL:
                return "null";
            
            default:
                return "null";
        }
    }
    
    private String toPrettyString(int indent) {
        String indentStr = "  ".repeat(indent);
        String nextIndentStr = "  ".repeat(indent + 1);
        
        switch (type) {
            case OBJECT:
                @SuppressWarnings("unchecked")
                Map<String, JSONValue> map = (Map<String, JSONValue>) value;
                if (map.isEmpty()) {
                    return "{}";
                }
                StringBuilder sb = new StringBuilder("{\n");
                boolean first = true;
                for (Map.Entry<String, JSONValue> entry : map.entrySet()) {
                    if (!first) sb.append(",\n");
                    first = false;
                    sb.append(nextIndentStr)
                      .append("\"").append(escapeString(entry.getKey())).append("\": ")
                      .append(entry.getValue().toPrettyString(indent + 1));
                }
                sb.append("\n").append(indentStr).append("}");
                return sb.toString();
            
            case ARRAY:
                @SuppressWarnings("unchecked")
                List<JSONValue> list = (List<JSONValue>) value;
                if (list.isEmpty()) {
                    return "[]";
                }
                StringBuilder ab = new StringBuilder("[\n");
                boolean afirst = true;
                for (JSONValue item : list) {
                    if (!afirst) ab.append(",\n");
                    afirst = false;
                    ab.append(nextIndentStr).append(item.toPrettyString(indent + 1));
                }
                ab.append("\n").append(indentStr).append("]");
                return ab.toString();
            
            default:
                return toJSONString(indent);
        }
    }
    
    private String escapeString(String str) {
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t")
                  .replace("\b", "\\b")
                  .replace("\f", "\\f");
    }
    
    /**
     * Creates a JSONValue from a record (Map-based representation)
     * EBS2 method: json.fromRecord(record)
     * 
     * This allows converting EBS2 records to JSON objects.
     * 
     * @param record Map representing a record with String keys
     * @return JSONValue representing the record as a JSON object
     */
    public static JSONValue fromRecord(Map<String, Object> record) {
        if (record == null) {
            return JSONValue.nullValue();
        }
        
        JSONValue json = JSONValue.object();
        for (Map.Entry<String, Object> entry : record.entrySet()) {
            json.set(entry.getKey(), convertToJSONValue(entry.getValue()));
        }
        return json;
    }
    
    /**
     * Converts this JSON object to a record (Map-based representation)
     * EBS2 method: json.toRecord()
     * 
     * This allows converting JSON objects to EBS2 records.
     * Only works for JSON objects, not arrays or primitives.
     * 
     * @return Map representing the JSON object as a record
     * @throws IllegalStateException if called on non-object JSON
     */
    public Map<String, Object> toRecord() {
        if (type != JSONType.OBJECT) {
            throw new IllegalStateException("Cannot convert non-object JSON to record");
        }
        
        Map<String, Object> record = new LinkedHashMap<>();
        @SuppressWarnings("unchecked")
        Map<String, JSONValue> map = (Map<String, JSONValue>) value;
        
        for (Map.Entry<String, JSONValue> entry : map.entrySet()) {
            record.put(entry.getKey(), convertToNativeValue(entry.getValue()));
        }
        
        return record;
    }
    
    /**
     * Helper method to convert Java objects to JSONValue
     */
    private static JSONValue convertToJSONValue(Object obj) {
        if (obj == null) {
            return JSONValue.nullValue();
        } else if (obj instanceof String) {
            return JSONValue.string((String) obj);
        } else if (obj instanceof Number) {
            return JSONValue.number((Number) obj);
        } else if (obj instanceof Boolean) {
            return JSONValue.bool((Boolean) obj);
        } else if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            return fromRecord(map);
        } else if (obj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) obj;
            JSONValue arr = JSONValue.array();
            for (Object item : list) {
                arr.append(convertToJSONValue(item));
            }
            return arr;
        } else if (obj instanceof JSONValue) {
            return (JSONValue) obj;
        } else {
            // For other types, convert to string
            return JSONValue.string(obj.toString());
        }
    }
    
    /**
     * Helper method to convert JSONValue to native Java objects
     */
    private static Object convertToNativeValue(JSONValue json) {
        switch (json.getType()) {
            case OBJECT:
                return json.toRecord();
            case ARRAY:
                List<Object> list = new ArrayList<>();
                for (JSONValue item : json.values()) {
                    list.add(convertToNativeValue(item));
                }
                return list;
            case STRING:
                return json.asString();
            case NUMBER:
                return json.asNumber();
            case BOOLEAN:
                return json.asBoolean();
            case NULL:
                return null;
            default:
                return null;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JSONValue other = (JSONValue) obj;
        if (type != other.type) return false;
        if (value == null) return other.value == null;
        return value.equals(other.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
