# EBS2 JSON Datatype Documentation

**Version:** 1.0.0  
**Last Updated:** December 31, 2025

## Overview

The JSON datatype in EBS2 provides a powerful and flexible way to work with JSON data. It supports the full JSON specification including objects, arrays, strings, numbers, booleans, and null values.

## Creating JSON Values

### JSON Objects

```javascript
// Create empty JSON object
var data as json = json.object()

// Create and populate a JSON object
var person as json = json.object()
person.set("name", json.string("Alice"))
person.set("age", json.number(30))
person.set("active", json.bool(true))
```

### JSON Arrays

```javascript
// Create empty JSON array
var items as json = json.array()

// Add items to array
items.append(json.number(1))
items.append(json.number(2))
items.append(json.number(3))
```

### JSON Primitive Values

```javascript
// String value
var name as json = json.string("Hello World")

// Number value
var count as json = json.number(42)
var price as json = json.number(19.99)

// Boolean value
var isActive as json = json.bool(true)

// Null value
var empty as json = json.null()
```

## Parsing and Serialization

### Parse JSON from Text

```javascript
// Parse JSON string
var jsonText as text = '{"name":"Alice","age":30,"city":"NYC"}'
var data as json = json.parse(jsonText)

// Access parsed data
var name = data.get("name").asString()      // "Alice"
var age = data.get("age").asNumber()        // 30
```

### Serialize JSON to Text

```javascript
// Convert JSON to string
var person as json = json.object()
person.set("name", json.string("Alice"))
person.set("age", json.number(30))

var jsonText = json.stringify(person)
// Result: {"name":"Alice","age":30}
```

### Pretty Print JSON

```javascript
// Pretty print with indentation
var prettyJson = json.prettyPrint(person)
// Result:
// {
//   "name": "Alice",
//   "age": 30
// }
```

## JSON Object Methods

### get(key)
Get a value from a JSON object by key.

```javascript
var person as json = json.parse('{"name":"Alice","age":30}')
var name = person.get("name")         // Returns JSON string value
var nameText = name.asString()        // "Alice"
```

### set(key, value)
Set a value in a JSON object.

```javascript
var person as json = json.object()
person.set("name", json.string("Bob"))
person.set("age", json.number(25))
```

### has(key)
Check if a key exists in a JSON object.

```javascript
var person as json = json.parse('{"name":"Alice"}')
var hasName = person.has("name")      // true
var hasEmail = person.has("email")    // false
```

### keys()
Get all keys from a JSON object.

```javascript
var person as json = json.parse('{"name":"Alice","age":30,"city":"NYC"}')
var keyList = person.keys()
// Result: ["name", "age", "city"]
```

### values()
Get all values from a JSON object.

```javascript
var person as json = json.parse('{"name":"Alice","age":30}')
var valueList = person.values()
// Result: [JSON string "Alice", JSON number 30]
```

### remove(key)
Remove a key from a JSON object.

```javascript
var person as json = json.parse('{"name":"Alice","age":30}')
person.remove("age")
// person now only has: {"name":"Alice"}
```

### size()
Get the number of keys in a JSON object.

```javascript
var person as json = json.parse('{"name":"Alice","age":30,"city":"NYC"}')
var count = person.size()             // 3
```

### isEmpty()
Check if a JSON object is empty.

```javascript
var empty as json = json.object()
var check = empty.isEmpty()           // true

empty.set("key", json.string("value"))
check = empty.isEmpty()               // false
```

## JSON Array Methods

### get(index)
Get a value from a JSON array by index.

```javascript
var items as json = json.parse('[1,2,3,4,5]')
var first = items.get(0)              // JSON number 1
var value = first.asNumber()          // 1
```

### set(index, value)
Set a value in a JSON array at a specific index.

```javascript
var items as json = json.parse('[1,2,3]')
items.set(1, json.number(20))
// items is now: [1,20,3]
```

### append(value)
Add a value to the end of a JSON array.

```javascript
var items as json = json.array()
items.append(json.number(1))
items.append(json.number(2))
items.append(json.number(3))
// items is now: [1,2,3]
```

### remove(index)
Remove an element from a JSON array.

```javascript
var items as json = json.parse('[1,2,3,4,5]')
items.remove(2)
// items is now: [1,2,4,5]
```

### size()
Get the number of elements in a JSON array.

```javascript
var items as json = json.parse('[1,2,3,4,5]')
var count = items.size()              // 5
```

### isEmpty()
Check if a JSON array is empty.

```javascript
var empty as json = json.array()
var check = empty.isEmpty()           // true
```

## JSON Manipulation Methods

### copy()
Create a deep copy of a JSON value.

```javascript
var original as json = json.parse('{"name":"Alice","age":30}')
var copy as json = original.copy()

// Modify the copy
copy.set("name", json.string("Bob"))

// Original is unchanged
print original.get("name").asString()  // "Alice"
print copy.get("name").asString()      // "Bob"
```

### merge(other)
Merge two JSON objects (only for objects).

```javascript
var person as json = json.parse('{"name":"Alice","age":30}')
var contact as json = json.parse('{"email":"alice@example.com","phone":"555-1234"}')

var merged as json = person.merge(contact)
// Result: {"name":"Alice","age":30,"email":"alice@example.com","phone":"555-1234"}
```

## Type Checking and Conversion

### getType()
Get the type of a JSON value.

```javascript
var obj as json = json.object()
var objType = obj.getType()           // OBJECT

var arr as json = json.array()
var arrType = arr.getType()           // ARRAY

var str as json = json.string("Hello")
var strType = str.getType()           // STRING
```

### Type Conversion Methods

```javascript
// For STRING type
var name as json = json.string("Alice")
var text = name.asString()            // "Alice"

// For NUMBER type
var age as json = json.number(30)
var num = age.asNumber()              // 30

// For BOOLEAN type
var active as json = json.bool(true)
var flag = active.asBoolean()         // true

// Check for NULL
var empty as json = json.null()
var check = empty.isNull()            // true
```

## Working with Nested JSON

```javascript
// Parse nested JSON
var jsonText = '{"user":{"name":"Alice","address":{"city":"NYC","zip":"10001"}}}'
var data as json = json.parse(jsonText)

// Access nested values
var name = data.get("user").get("name").asString()
// Result: "Alice"

var city = data.get("user").get("address").get("city").asString()
// Result: "NYC"

// Create nested structure
var person as json = json.object()
person.set("name", json.string("Bob"))

var address as json = json.object()
address.set("city", json.string("Boston"))
address.set("state", json.string("MA"))

person.set("address", address)

// Result: {"name":"Bob","address":{"city":"Boston","state":"MA"}}
```

## JSON Arrays with Objects

```javascript
// Parse array of objects
var jsonText = '[{"name":"Alice","age":30},{"name":"Bob","age":25}]'
var users as json = json.parse(jsonText)

// Access array elements
var firstUser = users.get(0)
var firstName = firstUser.get("name").asString()
// Result: "Alice"

// Iterate through array (conceptual)
for i = 0 to users.size() - 1 loop
    var user = users.get(i)
    var name = user.get("name").asString()
    print name
end for
```

## Error Handling

### Parsing Errors

```javascript
try
    var data as json = json.parse('invalid json')
catch error
    print "JSON parsing failed: " + error.message
end try
```

### Type Errors

```javascript
var data as json = json.string("Hello")

// This will throw an error because data is a STRING, not an OBJECT
try
    data.set("key", json.string("value"))
catch error
    print "Cannot set key on non-object JSON"
end try
```

## Practical Examples

### Example 1: Configuration File

```javascript
// Load and parse configuration
var configText as text = read file "config.json"
var config as json = json.parse(configText)

// Access configuration values
var theme = config.get("theme").asString()
var fontSize = config.get("fontSize").asNumber()
var autoSave = config.get("autoSave").asBoolean()

print "Theme: " + theme
print "Font Size: " + fontSize
```

### Example 2: API Response

```javascript
// Parse API response
var response = '{"status":"success","data":{"users":[{"id":1,"name":"Alice"},{"id":2,"name":"Bob"}]}}'
var json as json = json.parse(response)

// Check status
if json.get("status").asString() = "success" then
    var users = json.get("data").get("users")
    
    for i = 0 to users.size() - 1 loop
        var user = users.get(i)
        var id = user.get("id").asNumber()
        var name = user.get("name").asString()
        print "User " + id + ": " + name
    end for
end if
```

### Example 3: Building JSON for Export

```javascript
// Create a data structure
var data as json = json.object()
data.set("timestamp", json.number(currentTime()))
data.set("version", json.string("1.0"))

// Add array of items
var items as json = json.array()
items.append(json.string("item1"))
items.append(json.string("item2"))
items.append(json.string("item3"))
data.set("items", items)

// Convert to JSON string and save
var jsonText = json.prettyPrint(data)
write file "export.json" with jsonText
```

## Method Reference Summary

### Object Methods
- `get(key)` - Get value by key
- `set(key, value)` - Set value by key
- `has(key)` - Check if key exists
- `keys()` - Get all keys
- `values()` - Get all values
- `remove(key)` - Remove key
- `size()` - Get number of keys
- `isEmpty()` - Check if empty
- `copy()` - Deep copy
- `merge(other)` - Merge objects

### Array Methods
- `get(index)` - Get value by index
- `set(index, value)` - Set value by index
- `append(value)` - Add to end
- `remove(index)` - Remove by index
- `size()` - Get number of elements
- `isEmpty()` - Check if empty
- `copy()` - Deep copy
- `values()` - Get all values

### Parsing/Serialization
- `json.parse(text)` - Parse JSON string
- `json.stringify(value)` - Convert to JSON string
- `json.prettyPrint(value)` - Pretty-print JSON

### Type Conversion
- `asString()` - Get string value
- `asNumber()` - Get number value
- `asBoolean()` - Get boolean value
- `isNull()` - Check if null
- `getType()` - Get JSON type

---

*Part of EBS2 Script Interpreter - Making programming accessible to everyone*
