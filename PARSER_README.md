# EBS2 Statement Parser

## Overview

The EBS2 Statement Parser is a recursive descent parser that converts a stream of tokens from the lexer into an Abstract Syntax Tree (AST). It features detailed error handling, error recovery, and comprehensive reporting.

## Architecture

### Package Structure

```
com.eb.script.parser/
├── Parser.java              - Main parser class
├── ParserException.java     - Parser exception with location info
└── ParseError.java          - Error collection for batch reporting

com.eb.script.ast/
├── ASTNode.java            - Base interface for all AST nodes
├── ASTVisitor.java         - Visitor pattern for AST traversal
├── Statement.java          - Base interface for statements
├── Expression.java         - Base interface for expressions
├── BlockStatement.java     - Sequence of statements
├── PrintStatement.java     - Print statement
├── VarStatement.java       - Variable declaration
├── IfStatement.java        - If-then-else statement
├── ExpressionStatement.java - Standalone expression
├── LiteralExpression.java  - Literal values
├── VariableExpression.java - Variable references
├── BinaryExpression.java   - Binary operations
├── UnaryExpression.java    - Unary operations
└── GroupingExpression.java - Parenthesized expressions
```

## Features

### 1. Statement Parsing

The parser supports the following statements:

- **Variable Declarations**: `var name as type = value`
- **Print Statements**: `print expression`
- **If Statements**: `if condition then statement [else statement] [end if]`
- **Expression Statements**: Standalone expressions

### 2. Expression Parsing

Expressions are parsed with proper operator precedence:

1. **Logical OR**: `or`, `||`
2. **Logical AND**: `and`, `&&`
3. **Equality**: `==`, `!=`, `is equal to`, `is not equal to`
4. **Comparison**: `<`, `>`, `<=`, `>=`, `is less than`, etc.
5. **Addition/Subtraction**: `+`, `-`
6. **Multiplication/Division**: `*`, `/`, `mod`
7. **Unary**: `-`, `not`, `!`
8. **Primary**: literals, variables, grouping `()`

### 3. Detailed Error Handling

Every parse error includes:
- **Line and column** where the error occurred
- **Error message** describing what went wrong
- **Token information** showing the problematic token
- **Error type** classification (UNEXPECTED_TOKEN, MISSING_TOKEN, etc.)
- **Context** additional information about the error

Example error output:
```
[Line 1, Column 5] UNEXPECTED_TOKEN: Expected variable name after 'var' at '='
```

### 4. Error Recovery

The parser uses **synchronization points** to recover from errors:
- Advances to statement boundaries (semicolons, newlines)
- Resumes parsing at the next statement keyword
- Allows detection of multiple errors in a single parse

### 5. AST Visitor Pattern

The AST supports the visitor pattern for easy traversal and operations:

```java
public interface ASTVisitor<R, C> {
    R visitPrintStatement(PrintStatement stmt, C context);
    R visitVarStatement(VarStatement stmt, C context);
    R visitIfStatement(IfStatement stmt, C context);
    // ... more visitor methods
}
```

## Usage

### Basic Usage

```java
// 1. Tokenize source code
Lexer lexer = new Lexer(sourceCode);
List<Token> tokens = lexer.scanTokens();

// 2. Parse tokens into AST
Parser parser = new Parser(tokens);
List<Statement> statements = parser.parse();

// 3. Check for errors
if (parser.hadError()) {
    for (ParseError error : parser.getErrors()) {
        System.err.println(error);
    }
}

// 4. Process AST
for (Statement stmt : statements) {
    // Process each statement
}
```

### Error Handling Example

```java
Parser parser = new Parser(tokens);
List<Statement> statements = parser.parse();

if (parser.hadError()) {
    System.out.println("Parse errors found:");
    for (ParseError error : parser.getErrors()) {
        System.out.println(error.getDetailedReport());
    }
} else {
    System.out.println("Parsing successful!");
}
```

## Testing

The parser includes comprehensive test coverage:

### ParserTest.java (17 tests)
- Basic statement parsing
- Expression parsing with precedence
- Variable declarations with types
- If-then-else statements
- Literal values (numbers, text, booleans)
- Binary and unary expressions
- Grouped expressions
- Natural language operators

### ParserErrorHandlingTest.java (18 tests)
- Missing tokens detection
- Invalid syntax reporting
- Multiple error collection
- Error recovery verification
- Location information accuracy
- User-friendly error messages
- Error type classification

**Total: 35 parser tests, all passing**

## Examples

### Example 1: Variable Declaration

**Source:**
```javascript
var count as number = 42
```

**AST:**
```
VarStatement(
  name: "count",
  type: "number",
  initializer: LiteralExpression(42)
)
```

### Example 2: If Statement

**Source:**
```javascript
if age > 18 then print "Adult" else print "Minor"
```

**AST:**
```
IfStatement(
  condition: BinaryExpression(
    left: VariableExpression("age"),
    operator: ">",
    right: LiteralExpression(18)
  ),
  thenBranch: PrintStatement(LiteralExpression("Adult")),
  elseBranch: PrintStatement(LiteralExpression("Minor"))
)
```

### Example 3: Complex Expression

**Source:**
```javascript
var result = (10 + 20) * 3
```

**AST:**
```
VarStatement(
  name: "result",
  initializer: BinaryExpression(
    left: GroupingExpression(
      BinaryExpression(
        left: LiteralExpression(10),
        operator: "+",
        right: LiteralExpression(20)
      )
    ),
    operator: "*",
    right: LiteralExpression(3)
  )
)
```

## Design Decisions

### 1. Recursive Descent Parsing
- **Why**: Simple, readable, easy to maintain
- **Benefits**: Natural mapping from grammar to code
- **Trade-offs**: Left-recursion must be eliminated

### 2. Error Collection vs Throwing
- **Why**: Report multiple errors in one pass
- **Benefits**: Better developer experience
- **Implementation**: Record errors, synchronize, continue

### 3. Visitor Pattern for AST
- **Why**: Separate traversal logic from node structure
- **Benefits**: Easy to add new operations without modifying nodes
- **Use cases**: Evaluation, code generation, optimization

### 4. Token Location Tracking
- **Why**: Essential for error reporting
- **Benefits**: Precise error messages
- **Implementation**: Each token and AST node tracks position

## Performance

- **Parsing speed**: O(n) where n is the number of tokens
- **Memory usage**: O(n) for AST nodes
- **Error recovery**: Minimal overhead, continues at next statement

## Future Enhancements

### Planned Features
1. **Loop statements**: `for`, `while`, `repeat`
2. **Function definitions**: `function name(params) { body }`
3. **Array literals**: `{1, 2, 3, 4, 5}`
4. **Record literals**: `{name: "Alice", age: 25}`
5. **Method calls**: `object.method(args)`
6. **Block statements**: Multiple statements grouped
7. **Program structure**: `program`, `variables`, `functions`, `main` sections

### Potential Improvements
1. **Better error messages**: Suggest fixes for common mistakes
2. **Error recovery strategies**: More sophisticated synchronization
3. **AST optimizations**: Constant folding, dead code elimination
4. **Syntax sugar**: Expanded natural language support

## References

- **EBS2 Language Specification**: `doc/EBS2_LANGUAGE_SPEC.md`
- **Parser Tests**: `src/test/java/com/eb/script/parser/`
- **Parser Demo**: `src/test/java/com/eb/script/parser/ParserDemo.java`
- **Example Script**: `doc/examples/10_parser_example.ebs`

## Contributing

When extending the parser:

1. **Add AST nodes** for new constructs in `com.eb.script.ast/`
2. **Add parsing methods** in `Parser.java`
3. **Update visitor interface** with new methods
4. **Add tests** in `ParserTest.java` or new test files
5. **Update documentation** including this README

### Test-Driven Development

Always follow TDD when adding features:

1. Write failing tests first
2. Implement the feature
3. Run tests to verify
4. Refactor if needed
5. Update documentation

## License

Part of the EBS2 Script Interpreter project.
