# EBS2 Script Interpreter

**Version:** 2.0.0-SPEC  
**Status:** Specification Phase  
**Last Updated:** December 29, 2025

A redesigned scripting language and interpreter focused on making programming accessible to children (ages 8-16) while maintaining power for advanced users. EBS2 runs on both HTML5 (browser) and Java (desktop) platforms.

---

## 🚀 Quick Start

### For Users
- **New to EBS2?** → [Quick Start Guide](doc/EBS2_QUICK_START_GUIDE.md)
- **Need a Reference?** → [Quick Reference Card](doc/EBS2_QUICK_REFERENCE.md)
- **Want Examples?** → [Example Programs](doc/examples/)

### For Developers
- **Language Specification** → [EBS2_LANGUAGE_SPEC.md](doc/EBS2_LANGUAGE_SPEC.md)
- **Implementation Roadmap** → [EBS2_IMPLEMENTATION_ROADMAP.md](doc/EBS2_IMPLEMENTATION_ROADMAP.md)
- **Repository Structure** → [REPOSITORY_STRUCTURE.md](REPOSITORY_STRUCTURE.md)

### For Decision Makers
- **Executive Summary** → [EBS2_SPECIFICATION_SUMMARY.md](doc/EBS2_SPECIFICATION_SUMMARY.md)
- **Requirements & Goals** → [EBS2_REQUIREMENTS.md](doc/EBS2_REQUIREMENTS.md)

---

## 📚 What is EBS2?

EBS2 is a scripting language designed with three core principles:

1. **👶 Child-Friendly**: Natural language syntax that reads like English
2. **🌐 Cross-Platform**: Write once, run on web browsers or desktop
3. **📈 Progressive Complexity**: Start simple, grow into advanced features

### Example Program

```javascript
program HelloWorld

main
    var name as text = "Alice"
    var age as number = 10
    
    if age > 12 then
        print "You're a teenager!"
    else
        print "You're a child!"
    end if
end main
```

### Key Features

- ✅ **Natural syntax**: `if age is greater than 12 then` or `if age > 12 then`
- ✅ **Type safety**: Strong typing with type inference
- ✅ **Modern features**: Functions, records, arrays, dates
- ✅ **Dual runtime**: HTML5 browser + Java desktop
- ✅ **Fixed & dynamic arrays**: Choose based on your needs
- ✅ **Record methods**: Introspection and manipulation

---

## 📖 Documentation

### Core Documents
| Document | Description | Size |
|----------|-------------|------|
| [EBS2_INDEX.md](doc/EBS2_INDEX.md) | Central navigation guide | 8KB |
| [EBS2_LANGUAGE_SPEC.md](doc/EBS2_LANGUAGE_SPEC.md) | Complete language specification | 105KB |
| [EBS2_QUICK_REFERENCE.md](doc/EBS2_QUICK_REFERENCE.md) | One-page reference card | 18KB |
| [EBS2_QUICK_START_GUIDE.md](doc/EBS2_QUICK_START_GUIDE.md) | Beginner tutorial | 18KB |
| [REPOSITORY_STRUCTURE.md](REPOSITORY_STRUCTURE.md) | Directory layout guide | 18KB |

### Additional Resources
- [EBS1 vs EBS2 Comparison](doc/EBS1_VS_EBS2_COMPARISON.md) - Migration guide
- [Implementation Roadmap](doc/EBS2_IMPLEMENTATION_ROADMAP.md) - 12-month plan
- [Requirements Document](doc/EBS2_REQUIREMENTS.md) - Design principles
- [Example Programs](doc/examples/) - Working code samples

**Full Documentation Index:** [doc/README.md](doc/README.md)

---

## 🏗️ Repository Structure

```
ebs2-script-interpreter/
├── doc/                          # Documentation
│   ├── examples/                 # Example EBS2 scripts
│   ├── EBS2_LANGUAGE_SPEC.md    # Language specification
│   ├── EBS2_QUICK_REFERENCE.md  # Quick reference
│   └── ...                       # Other documentation
│
├── src/                          # Maven source (future)
│   ├── main/java/               # Java interpreter source
│   └── test/java/               # Test source
│
├── examples/                     # Sample projects (future)
├── REPOSITORY_STRUCTURE.md      # Complete layout guide
└── README.md                    # This file
```

See [REPOSITORY_STRUCTURE.md](REPOSITORY_STRUCTURE.md) for complete directory layout including Maven structure, test organization, and EBS2 project templates.

---

## 🎯 Language Highlights

### Loop Syntax
```javascript
// Repeat loop
repeat 10 times with counter
    print counter
end repeat

// For loop with range
for i = 1 to 10 loop
    print i
end for

// For each loop
for each item in items
    print item
end for

// While loop
while count > 0 loop
    print count
    count--
end while
```

### Functions and Procedures
```javascript
// Function (returns a value)
function add(a as number, b as number) as number {
    return a + b
}

// Procedure (no return value)
procedure greet(name as text) {
    print "Hello " + name
}

// Usage
var sum = add(5, 3)
greet("Alice")
```

### Arrays
```javascript
// Dynamic array (size can change)
var dynamicArray as array = {1, 2, 3}
dynamicArray.append(4)              // {1, 2, 3, 4}

// Fixed-size array (size cannot change)
var fixedArray as array[5] = {1, 2, 3, 4, 5}
fixedArray[0] = 10                  // OK: modify element
// fixedArray.append(6)             // ERROR: size is fixed

// Array methods
var filled = {}.fill(0, 5)          // {0, 0, 0, 0, 0}
var expanded = {1, 2, 3}.expand(2)  // {1, 2, 3, 0, 0}
var shrunk = {1, 2, 3, 4, 5}.shrink(2)  // {1, 2, 3}
```

### Records
```javascript
// Define record type
record type PersonType
    name as text
    age as number
    email as text
end record

// Create instance
var person as PersonType = {
    name: "Alice",
    age: 30,
    email: "alice@example.com"
}

// Record methods
var fields = person.fields()        // {"name", "age", "email"}
var hasAge = person.hasField("age") // true
person.addField("phone", "555-1234")
var copy = person.copy()
var json = person.toJSON()
```

---

## 🛠️ Development

### Prerequisites
- Java 21+
- Maven 3.8+
- JavaFX 21

### Build (Future)
```bash
mvn clean install
mvn test
mvn package
```

### Run (Future)
```bash
java -jar target/ebs2-interpreter.jar
```

---

## 📋 Project Status

### Current Phase: Specification Complete ✅

- ✅ **Language Specification**: Complete and documented
- ✅ **Documentation**: Comprehensive guides and references
- ✅ **Example Programs**: 9 working examples
- ✅ **Repository Structure**: Defined and documented
- ⏳ **Implementation**: Not started (awaiting approval)

### Recent Updates (December 2025)

1. **Loop Syntax Standardization**: All loops now use `loop` keyword
   - `for i = 1 to 5 loop` instead of `for i from 1 to 5`
   - `while condition loop` instead of `while condition then`

2. **Function Declaration Updates**: Must use `function` keyword
   - `function add(a as number, b as number) as number { ... }`
   - Removed "to...returns" long form syntax

3. **Array Enhancements**: Dynamic and fixed-size arrays
   - Dynamic: `var arr as array = {1, 2, 3}`
   - Fixed: `var arr as array[5] = {1, 2, 3, 4, 5}`
   - New methods: `.fill()`, `.expand()`, `.shrink()`

4. **Record Methods**: Introspection and manipulation
   - `.fields()`, `.hasField()`, `.getField()`, `.setField()`
   - `.addField()`, `.removeField()`, `.copy()`, `.merge()`
   - `.toJSON()` and `record.fromJSON(json)`

5. **Repository Structure**: Complete directory layout defined
   - Maven standard structure
   - EBS2 project templates
   - Documentation organization

### Next Steps

1. ⏳ Stakeholder review and approval
2. ⏳ Assemble development team
3. ⏳ Begin Phase 1 implementation (Lexer, Parser, Basic Interpreter)

---

## 🎓 Learning Resources

### For Complete Beginners
1. Start with [EBS2_QUICK_START_GUIDE.md](doc/EBS2_QUICK_START_GUIDE.md)
2. Practice with [Example Programs](doc/examples/)
3. Reference [EBS2_QUICK_REFERENCE.md](doc/EBS2_QUICK_REFERENCE.md)

### For Experienced Programmers
1. Review [EBS2_LANGUAGE_SPEC.md](doc/EBS2_LANGUAGE_SPEC.md)
2. Check [EBS1_VS_EBS2_COMPARISON.md](doc/EBS1_VS_EBS2_COMPARISON.md) for differences
3. Explore [Implementation Roadmap](doc/EBS2_IMPLEMENTATION_ROADMAP.md)

### For Project Stakeholders
1. Read [EBS2_SPECIFICATION_SUMMARY.md](doc/EBS2_SPECIFICATION_SUMMARY.md)
2. Review [EBS2_REQUIREMENTS.md](doc/EBS2_REQUIREMENTS.md)
3. Examine [EBS2_IMPLEMENTATION_ROADMAP.md](doc/EBS2_IMPLEMENTATION_ROADMAP.md)

---

## 📊 Statistics

- **Documentation Size**: 300KB+ total
- **Code Examples**: 200+
- **Specification Lines**: 10,000+
- **Example Programs**: 9 complete programs
- **Implementation Timeline**: 12 months (5 phases)
- **Estimated Budget**: $1.0-1.6M USD
- **Team Size**: 5-7 developers

---

## 🤝 Contributing

This project is currently in the specification phase. Implementation will begin upon stakeholder approval.

### Documentation
- All documentation is in the `doc/` directory
- Follow the structure outlined in [REPOSITORY_STRUCTURE.md](REPOSITORY_STRUCTURE.md)
- Use Markdown for all documentation

### Code (Future)
- Follow Maven standard directory layout
- Java 21+ with JavaFX for UI
- Comprehensive unit tests required
- See [REPOSITORY_STRUCTURE.md](REPOSITORY_STRUCTURE.md) for details

---

## 📄 License

[License information to be added]

---

## 📞 Contact

[Contact information to be added]

---

## 🔗 Quick Links

- **Documentation**: [doc/README.md](doc/README.md)
- **Language Spec**: [EBS2_LANGUAGE_SPEC.md](doc/EBS2_LANGUAGE_SPEC.md)
- **Quick Start**: [EBS2_QUICK_START_GUIDE.md](doc/EBS2_QUICK_START_GUIDE.md)
- **Examples**: [doc/examples/](doc/examples/)
- **Repository Structure**: [REPOSITORY_STRUCTURE.md](REPOSITORY_STRUCTURE.md)

---

*EBS2 - Making programming accessible to everyone, starting with children.*
