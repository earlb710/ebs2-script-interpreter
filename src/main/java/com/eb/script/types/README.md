# EBS2 JSON Datatype Implementation

This package contains the implementation of the JSON datatype for EBS2.

## Structure

### JSONValue.java
The core class representing a JSON value in EBS2. Supports:
- JSON Objects (key-value pairs using LinkedHashMap)
- JSON Arrays (ordered lists using ArrayList)
- JSON Primitives (strings, numbers, booleans, null)

**Key Methods:**
- Object operations: `get(key)`, `set(key, value)`, `has(key)`, `keys()`, `values()`, `remove(key)`
- Array operations: `get(index)`, `set(index, value)`, `append(value)`, `remove(index)`
- Introspection: `size()`, `isEmpty()`, `getType()`
- Manipulation: `copy()`, `merge(other)`
- Serialization: `toString()`, `toJSON()`, `toPrettyJSON()`
- Record conversion: `fromRecord(record)`, `toRecord()`

### JSONFunctions.java (in stdlib package)
Static utility functions for JSON operations:
- `parse(text)` - Parse JSON from string with full JSON specification support
- `stringify(value)` - Convert JSON value to compact JSON string
- `prettyPrint(value)` - Convert JSON value to formatted JSON string with indentation

**Parser Features:**
- Full JSON specification compliance
- Supports objects, arrays, strings, numbers, booleans, null
- Handles escape sequences (\n, \t, \", \\, \/, \b, \f, \r, \uXXXX)
- Supports scientific notation for numbers (1.5e10, 2.5e-5)
- Proper error handling with descriptive error messages

## Design Decisions

1. **Immutability**: Primitive JSON values (string, number, boolean, null) are effectively immutable. The `copy()` method creates new instances for primitive values.

2. **Deep Copy**: The `copy()` method performs a deep copy of objects and arrays, ensuring that modifications to the copy don't affect the original.

3. **Type Safety**: Strong type checking ensures that operations are only performed on appropriate JSON types (e.g., can't append to an object, can't set keys on an array).

4. **Null Handling**: Non-existent keys in objects and out-of-bounds indices in arrays return `JSONValue.nullValue()` rather than throwing exceptions.

5. **Ordered Objects**: JSON objects use `LinkedHashMap` to preserve insertion order, which is helpful for predictable serialization and debugging.

## Usage in EBS2

```javascript
// Creating JSON values
var person as json = json.object()
person.set("name", json.string("Alice"))
person.set("age", json.number(30))

// Parsing JSON
var data as json = json.parse('{"name":"Bob","age":25}')
var name = data.get("name").asString()

// Working with arrays
var items as json = json.array()
items.append(json.number(1))
items.append(json.number(2))
items.append(json.number(3))

// Serialization
var jsonText = json.stringify(person)
var prettyJson = json.prettyPrint(person)

// Record conversion
var record = {"name": "Charlie", "score": 95}
var jsonFromRecord = json.fromRecord(record)
var recordFromJson = jsonFromRecord.toRecord()
```

## Testing

Comprehensive test coverage:
- **JSONValueTest**: 39 tests covering all JSONValue functionality including record conversions
- **JSONFunctionsTest**: 29 tests covering parsing, serialization, and error handling

Run tests with: `mvn test`

## Future Enhancements

Potential improvements for future versions:
1. Support for streaming large JSON files
2. JSON Schema validation
3. JSONPath query support
4. Custom serialization/deserialization hooks
5. Performance optimizations for large datasets
6. Support for comments in JSON (JSON5 style)

## Version History

- **1.0.0** (2025-12-31): Initial implementation with full JSON specification support
