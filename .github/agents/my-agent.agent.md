---
# Fill in the fields below to create a basic custom agent for your repository.
# The Copilot CLI can be used for local testing: https://gh.io/customagents/cli
# To make this agent available, merge this file into the default repository branch.
# For format details, see: https://gh.io/customagents/config

name: EBS2 Development Agent
description: Agent for developing EBS2 Script Interpreter with focus on testing and documentation
---

# EBS2 Development Agent

This agent assists with development of the EBS2 Script Interpreter project, following project conventions and best practices.

## Important Document Locations

### Core Documentation (`doc/` directory)
No document should be in ebs2-script-interpreter directory they all should be in ebs2-script-interpreter/doc, which would be reffered to as the doc directory.
For sets of documents that goes together create a sub directory for them and update this document with the details : like test and implementation documents.
Try to keep the doc direcoty uncluttered.
 
All documentation are located in the `doc/` directory:

- **Language Specification**: `doc/EBS2_LANGUAGE_SPEC.md` - Complete formal specification
- **Quick Start Guide**: `doc/EBS2_QUICK_START_GUIDE.md` - Beginner tutorial
- **Quick Reference**: `doc/EBS2_QUICK_REFERENCE.md` - One-page reference card
- **Requirements**: `doc/EBS2_REQUIREMENTS.md` - Design principles and requirements
- **Implementation Roadmap**: `doc/EBS2_IMPLEMENTATION_ROADMAP.md` - 12-month implementation plan
- **EBS1 vs EBS2**: `doc/EBS1_VS_EBS2_COMPARISON.md` - Migration guide
- **Specification Summary**: `doc/EBS2_SPECIFICATION_SUMMARY.md` - Executive overview
- **Index**: `doc/EBS2_INDEX.md` - Central navigation guide
- **Documentation Overview**: `doc/README.md` - Documentation suite overview

### Repository Structure Files
- **Main README**: `README.md` - Repository overview, quick start, and key features
- **Repository Structure**: `REPOSITORY_STRUCTURE.md` - Complete directory layout and organization
- **Build & Deployment**: `BUILD_DEPLOYMENT.md` - Build instructions and deployment guide
- **Implementation Summary**: `IMPLEMENTATION_SUMMARY.md` - Implementation progress tracking

### Source Code Structure
- **Java Source**: `src/main/java/com/eb/script/` - Main interpreter source code
  - `ebs2/` - Main interpreter package
  - `lexer/` - Lexical analyzer
  - `parser/` - Parser
  - `ast/` - Abstract Syntax Tree
  - `runtime/` - Runtime engine
  - `types/` - Type system
  - `ui/` - UI components

### Test Structure (`src/test/java/` directory)
All tests follow Maven standard structure:
- **Test Source**: `src/test/java/com/eb/script/` - Test classes mirror source structure
  - `lexer/` - Lexer tests (e.g., `LexerTest.java`, `TokenColorTest.java`)
  - `types/` - Type system tests (e.g., `JSONValueTest.java`)
- **Server Tests**: `src/test/java/com/eb/server/` - Server tests (e.g., `LocalServerTest.java`)

### Examples Location (`doc/examples/` directory)
Example EBS2 scripts demonstrating language features:
- `01_variables_and_types.ebs` - Variables and type system
- `02_operators.ebs` - Operators and expressions
- `03_control_flow.ebs` - Control flow statements
- `04_text_operations.ebs` - Text/string operations
- `05_array_operations.ebs` - Array manipulation
- `06_functions.ebs` - Functions and procedures
- `07_records.ebs` - Record types
- `08_dates.ebs` - Date/time operations
- `09_comprehensive_example.ebs` - Complete program example
- `README.md` - Examples index and descriptions

## Development Workflow

### 1. Create Test Classes by Default

When implementing new features or fixing bugs:

1. **Always create test classes first** following Test-Driven Development (TDD)
2. **Test naming convention**: `[ClassName]Test.java` (e.g., `LexerTest.java`, `JSONFunctionsTest.java`)
3. **Test location**: Place tests in `src/test/java/` mirroring the source structure
   - Source: `src/main/java/com/eb/script/lexer/Lexer.java`
   - Test: `src/test/java/com/eb/script/lexer/LexerTest.java`
4. **Test framework**: Use JUnit for Java tests
5. **Test coverage**: Aim for comprehensive coverage of:
   - Normal/expected cases
   - Edge cases
   - Error conditions
   - Boundary conditions

### 2. Run Tests Before Examples

Before creating or updating examples:

1. **Run all tests**: `mvn test`
2. **Ensure all tests pass**: Fix any failing tests before proceeding
3. **Verify new functionality**: Confirm the feature works correctly through tests
4. **Check test coverage**: Ensure adequate coverage of the new feature

### 3. Create Examples Once Tests Pass

After tests are passing:

1. **Create example scripts** in `doc/examples/` directory
2. **Follow naming convention**: Use descriptive names with number prefix (e.g., `10_new_feature.ebs`)
3. **Include comments**: Document the example clearly for learning purposes
4. **Update examples README**: Add entry to `doc/examples/README.md` describing the example
5. **Example structure**:
   - Clear program name
   - Comments explaining key concepts
   - Demonstrate practical usage
   - Show best practices
   - Include expected output in comments

### Example Workflow Summary

```
1. Write failing tests (TDD approach)
2. Implement feature to make tests pass
3. Run tests to verify implementation
4. Fix any issues until all tests pass
5. Create example script in doc/examples/
6. Update doc/examples/README.md
7. Commit changes
```

## Build and Test Commands

- **Build project**: `mvn clean package`
- **Run tests**: `mvn test`
- **Run specific test**: `mvn test -Dtest=LexerTest`
- **Run JavaFX app**: `mvn javafx:run`
- **Run local server**: `mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp"`

## Code Conventions

- **Java version**: Java 17+
- **Package structure**: `com.eb.script.[component]`
- **Testing framework**: JUnit
- **Build tool**: Maven
- **Code style**: Follow standard Java conventions
- **Documentation**: JavaDoc for public APIs

## Key Principles

1. **Tests First**: Always write tests before or alongside implementation
2. **Documentation**: Keep documentation in sync with code
3. **Examples**: Provide clear, educational examples after validation
4. **Quality**: Ensure all tests pass before creating examples
5. **Structure**: Follow Maven and project conventions consistently
