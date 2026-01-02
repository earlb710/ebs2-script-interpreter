# EBS2 Script Interpreter - Repository Structure

**Version:** 1.0.0  
**Last Updated:** January 2, 2026

---

## Overview

This document defines the complete directory structure for the EBS2 Script Interpreter repository, including documentation, source code (Maven), tests, examples, and EBS2 project organization.

---

## Repository Root Structure

```
ebs2-script-interpreter/
├── doc/                          # Documentation
│   ├── examples/                 # Example EBS2 scripts
│   ├── implementation/           # Implementation-specific docs
│   └── *.md                      # Specification and guide documents
│
├── src/                          # Maven source directory
│   ├── main/
│   │   ├── java/                 # Java source code
│   │   │   └── com/eb/script/
│   │   │       ├── ebs2/         # EBS2 interpreter core
│   │   │       ├── lexer/        # Lexical analyzer
│   │   │       ├── parser/       # Parser
│   │   │       ├── ast/          # Abstract Syntax Tree
│   │   │       ├── runtime/      # Runtime engine
│   │   │       ├── stdlib/       # Standard library
│   │   │       └── ui/           # UI components
│   │   │
│   │   └── resources/            # Application resources
│   │       ├── icons/            # Application icons
│   │       ├── templates/        # Project templates
│   │       ├── css/              # Stylesheet resources
│   │       └── config/           # Configuration files
│   │
│   └── test/
│       ├── java/                 # Test source code
│       │   └── com/eb/script/
│       │       ├── lexer/        # Lexer tests
│       │       ├── parser/       # Parser tests
│       │       ├── runtime/      # Runtime tests
│       │       ├── stdlib/       # Standard library tests
│       │       └── integration/  # Integration tests
│       │
│       └── resources/            # Test resources
│           ├── scripts/          # Test EBS2 scripts
│           ├── fixtures/         # Test data fixtures
│           └── expected/         # Expected output files
│
├── examples/                     # Sample EBS2 projects (separate from doc/examples)
│   ├── hello-world/              # Basic hello world project
│   ├── calculator/               # Calculator application
│   ├── tic-tac-toe/              # Game example
│   └── todo-list/                # Todo list application
│
├── projects/                     # User EBS2 projects directory (for development/testing)
│   └── [user-projects]/          # Individual project directories
│
├── target/                       # Maven build output (gitignored)
│   ├── classes/                  # Compiled classes
│   ├── test-classes/             # Compiled test classes
│   └── ebs2-interpreter.jar      # Built JAR file
│
├── .mvn/                         # Maven wrapper files
├── .git/                         # Git repository data
├── .gitignore                    # Git ignore rules
├── pom.xml                       # Maven project configuration
├── nbactions.xml                 # NetBeans actions
├── README.md                     # Repository README
└── REPOSITORY_STRUCTURE.md       # This file

```

---

## 1. Documentation Directory (`doc/`)

Contains all language specification, guides, and examples.

```
doc/
├── examples/                                    # Example EBS2 scripts
│   ├── 01_variables_and_types.ebs
│   ├── 02_operators.ebs
│   ├── 03_control_flow.ebs
│   ├── 04_text_operations.ebs
│   ├── 05_array_operations.ebs
│   ├── 06_functions.ebs
│   ├── 07_records.ebs
│   ├── 08_dates.ebs
│   ├── 09_comprehensive_example.ebs
│   └── README.md                               # Examples index
│
├── implementation/                             # Implementation-specific documentation
│   ├── PARSER_README.md                        # Parser documentation
│   ├── PARSER_COMPARISON.md                    # EBS1 vs EBS2 parser comparison
│   ├── PARSER_OPTIMIZATIONS.md                 # Parser performance optimizations
│   ├── JSON_IMPLEMENTATION_SUMMARY.md          # JSON datatype implementation
│   └── README.md                               # Implementation docs index
│
├── EBS2_INDEX.md                               # Central navigation guide
├── EBS2_SPECIFICATION_SUMMARY.md               # Executive summary
├── EBS2_REQUIREMENTS.md                        # Design principles and requirements
├── EBS2_LANGUAGE_SPEC.md                       # Complete language specification
├── EBS2_IMPLEMENTATION_ROADMAP.md              # Implementation plan
├── EBS2_QUICK_REFERENCE.md                     # Quick reference card
├── EBS2_QUICK_START_GUIDE.md                   # Beginner tutorial
├── EBS1_VS_EBS2_COMPARISON.md                  # Migration guide
├── COMPLEX_RECORD_DEFINITIONS.md               # Complex record structures guide
├── ANSWER_COMPLEX_RECORDS.md                   # Quick answer: complex records
├── EBS2_SCREEN_DEFINITION.md                   # Screen definition documentation
├── EBS2_JSON_DATATYPE.md                       # JSON datatype user documentation
├── RUNNING_APPLICATIONS.md                     # Guide for running applications
├── JSON_PACKAGE_README.md                      # JSON package documentation
├── CONSOLE_PACKAGE_README.md                   # Console package documentation
└── README.md                                   # Documentation overview
```

**Purpose:**
- Language specifications and requirements
- Tutorials and quick start guides
- Example programs demonstrating language features
- Migration guides and comparisons

---

## 2. Source Directory (`src/`)

Maven standard directory layout for Java source code.

### 2.1 Main Source (`src/main/java/`)

```
src/main/java/com/eb/script/
├── ebs2/                          # Main interpreter package
│   ├── EBS2Interpreter.java       # Main interpreter class
│   ├── EBS2Application.java       # Application entry point
│   ├── EBS2Engine.java            # Core engine
│   └── EBS2Context.java           # Execution context
│
├── lexer/                         # Lexical analysis
│   ├── Lexer.java                 # Main lexer
│   ├── Token.java                 # Token representation
│   ├── TokenType.java             # Token types enum
│   └── LexerException.java        # Lexer errors
│
├── parser/                        # Syntax analysis
│   ├── Parser.java                # Main parser
│   ├── ParserException.java       # Parser errors
│   └── ParseTree.java             # Parse tree structure
│
├── ast/                           # Abstract Syntax Tree
│   ├── ASTNode.java               # Base AST node
│   ├── expressions/               # Expression nodes
│   ├── statements/                # Statement nodes
│   └── visitors/                  # Visitor pattern implementations
│
├── runtime/                       # Runtime execution
│   ├── Runtime.java               # Main runtime
│   ├── Interpreter.java           # Interpreter
│   ├── Environment.java           # Variable environment
│   ├── Value.java                 # Runtime value
│   ├── html5/                     # HTML5 runtime
│   │   └── HTML5Runtime.java
│   └── java/                      # Java runtime
│       └── JavaRuntime.java
│
├── stdlib/                        # Standard library
│   ├── ArrayFunctions.java        # Array methods
│   ├── TextFunctions.java         # Text methods
│   ├── RecordFunctions.java       # Record methods
│   ├── DateFunctions.java         # Date methods
│   ├── MathFunctions.java         # Math functions
│   └── IOFunctions.java           # Input/Output functions
│
└── ui/                            # User interface
    ├── Editor.java                # Code editor
    ├── ProjectManager.java        # Project management
    ├── DebugPanel.java            # Debugger UI
    └── components/                # UI components
```

### 2.2 Main Resources (`src/main/resources/`)

```
src/main/resources/
├── icons/                         # Application icons
│   ├── app-icon.png
│   ├── file-icon.png
│   └── toolbar/                   # Toolbar icons
│
├── templates/                     # Project templates
│   ├── hello-world/               # Basic template
│   ├── gui-app/                   # GUI application template
│   └── console-app/               # Console app template
│
├── css/                           # Stylesheets
│   ├── editor.css                 # Editor styles
│   └── themes/                    # UI themes
│
├── config/                        # Configuration files
│   ├── default-settings.properties
│   └── keywords.txt               # Language keywords
│
└── help/                          # Help resources
    └── documentation/             # Built-in help
```

### 2.3 Test Source (`src/test/java/`)

```
src/test/java/com/eb/script/
├── lexer/                         # Lexer tests
│   ├── LexerTest.java
│   ├── TokenTest.java
│   └── KeywordTest.java
│
├── parser/                        # Parser tests
│   ├── ParserTest.java
│   ├── ExpressionParserTest.java
│   └── StatementParserTest.java
│
├── runtime/                       # Runtime tests
│   ├── InterpreterTest.java
│   ├── EnvironmentTest.java
│   └── ValueTest.java
│
├── stdlib/                        # Standard library tests
│   ├── ArrayFunctionsTest.java
│   ├── TextFunctionsTest.java
│   ├── RecordFunctionsTest.java
│   └── DateFunctionsTest.java
│
└── integration/                   # Integration tests
    ├── FullProgramTest.java
    ├── CrossPlatformTest.java
    └── PerformanceTest.java
```

### 2.4 Test Resources (`src/test/resources/`)

```
src/test/resources/
├── scripts/                       # Test EBS2 scripts
│   ├── valid/                     # Valid programs
│   │   ├── hello-world.ebs
│   │   ├── loops.ebs
│   │   └── functions.ebs
│   │
│   └── invalid/                   # Invalid programs for error testing
│       ├── syntax-errors.ebs
│       └── runtime-errors.ebs
│
├── fixtures/                      # Test data
│   ├── sample-data.json
│   └── test-records.csv
│
└── expected/                      # Expected outputs
    ├── hello-world.out
    └── loops.out
```

---

## 3. Examples Directory (`examples/`)

Sample EBS2 projects demonstrating complete applications.

```
examples/
├── hello-world/                   # Basic hello world
│   ├── hello.ebs
│   ├── project.ebs2               # Project configuration
│   └── README.md
│
├── calculator/                    # Calculator application
│   ├── src/
│   │   └── calculator.ebs
│   ├── doc/
│   │   └── user-guide.md
│   ├── resources/
│   │   └── icons/
│   ├── project.ebs2
│   └── README.md
│
├── tic-tac-toe/                   # Game example
│   ├── src/
│   │   ├── game.ebs
│   │   ├── board.ebs
│   │   └── ai.ebs
│   ├── doc/
│   │   └── rules.md
│   ├── resources/
│   │   └── images/
│   ├── project.ebs2
│   └── README.md
│
└── todo-list/                     # Todo list app
    ├── src/
    │   └── todo.ebs
    ├── doc/
    │   └── features.md
    ├── resources/
    │   └── data/
    ├── project.ebs2
    └── README.md
```

---

## 4. EBS2 Project Structure

Standard structure for EBS2 user projects.

```
[project-name]/                    # Root project directory
├── src/                           # Source code
│   ├── main.ebs                   # Main entry point
│   ├── utils.ebs                  # Utility functions
│   └── [modules]/                 # Additional modules
│
├── doc/                           # Project documentation
│   ├── README.md                  # Project overview
│   ├── user-guide.md              # User documentation
│   └── api.md                     # API documentation (if applicable)
│
├── resources/                     # Project resources
│   ├── images/                    # Image files
│   ├── data/                      # Data files
│   ├── config/                    # Configuration files
│   └── templates/                 # Templates
│
├── log/                           # Log files (gitignored)
│   ├── app.log                    # Application logs
│   ├── error.log                  # Error logs
│   └── debug.log                  # Debug logs
│
├── build/                         # Build output (gitignored)
│   ├── compiled/                  # Compiled output
│   └── package/                   # Packaged application
│
├── test/                          # Project tests (optional)
│   ├── test-main.ebs              # Test scripts
│   └── fixtures/                  # Test data
│
├── project.ebs2                   # Project configuration file
├── .gitignore                     # Git ignore rules
└── README.md                      # Project README
```

### 4.1 Project Configuration File (`project.ebs2`)

```javascript
project "My EBS2 Application"
    version "1.0.0"
    author "Your Name"
    description "Description of your application"
    
    settings
        main "src/main.ebs"
        runtime "html5"              // or "java"
        
    dependencies
        // Future: external libraries
    end dependencies
    
    build
        output "build/"
        package "app.ebs2pkg"
    end build
    
    resources
        path "resources/"
    end resources
end project
```

### 4.2 Project Subdirectories

**`src/`** - Source Code
- Contains all EBS2 source files
- Main entry point must be defined in project.ebs2
- Can have subdirectories for organization

**`doc/`** - Documentation
- Project-specific documentation
- User guides, API docs, design docs
- README.md is the entry point

**`resources/`** - Resources
- Images, icons, sounds
- Data files (CSV, JSON, etc.)
- Configuration files
- Templates

**`log/`** - Logs (Gitignored)
- Application runtime logs
- Error logs for debugging
- Debug output
- **Should be added to .gitignore**

**`build/`** - Build Output (Gitignored)
- Compiled/transpiled output
- Packaged applications
- **Should be added to .gitignore**

**`test/`** - Tests (Optional)
- Test scripts for the project
- Test data and fixtures
- Test configuration

---

## 5. Git Ignore Rules

Standard `.gitignore` for the repository:

```gitignore
# Maven build output
target/
*.jar
*.war
*.ear

# IDE files
.idea/
*.iml
.vscode/
*.swp
*.swo
*~
.DS_Store

# NetBeans
nbproject/private/
nbbuild/
dist/
nbdist/

# Logs
*.log
log/

# Build output
build/
*.class

# EBS2 project logs
projects/*/log/
projects/*/build/

# Temporary files
*.tmp
*.bak
*.backup
~*

# OS files
Thumbs.db
.DS_Store
```

---

## 6. Maven Project Configuration

The `pom.xml` defines the Maven project structure:

```xml
<project>
    <groupId>com.eb</groupId>
    <artifactId>EBS2-ScriptInterpreter</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    
    <build>
        <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>
        <testResources>
            <testResource>
                <directory>src/test/resources</directory>
            </testResource>
        </testResources>
    </build>
</project>
```

---

## 7. Directory Creation Checklist

When setting up a new repository or EBS2 project:

### Repository Setup:
- [ ] Create `doc/` directory with examples subdirectory
- [ ] Create Maven standard `src/main/java/` structure
- [ ] Create Maven standard `src/main/resources/` structure
- [ ] Create Maven standard `src/test/java/` structure
- [ ] Create Maven standard `src/test/resources/` structure
- [ ] Create `examples/` directory for sample projects
- [ ] Create `projects/` directory for user projects
- [ ] Set up `.gitignore` with proper rules
- [ ] Create `REPOSITORY_STRUCTURE.md` (this file)

### EBS2 Project Setup:
- [ ] Create `src/` directory for source code
- [ ] Create `doc/` directory for documentation
- [ ] Create `resources/` directory for resources
- [ ] Create `log/` directory (add to .gitignore)
- [ ] Create `build/` directory (add to .gitignore)
- [ ] Create `project.ebs2` configuration file
- [ ] Create `.gitignore` for project-specific rules
- [ ] Create `README.md` with project overview

---

## 8. Best Practices

### Documentation:
- Keep all language specs in `doc/`
- Example scripts in `doc/examples/` are for documentation
- Full sample projects go in `examples/` directory

### Source Code:
- Follow Maven standard directory layout
- Keep packages organized by functionality
- Tests mirror main source structure

### EBS2 Projects:
- Use standard project structure
- Always include `project.ebs2` configuration
- Add `log/` and `build/` to `.gitignore`
- Document in `doc/README.md`

### Resources:
- Organize by type (images/, data/, config/)
- Keep resources separate from source code
- Use relative paths in code

---

## 9. Quick Reference

| Directory | Purpose | Gitignored |
|-----------|---------|------------|
| `doc/` | Documentation and specs | No |
| `doc/examples/` | Example scripts for docs | No |
| `src/main/java/` | Java source code | No |
| `src/main/resources/` | Application resources | No |
| `src/test/java/` | Test source code | No |
| `src/test/resources/` | Test resources | No |
| `examples/` | Sample EBS2 projects | No |
| `projects/` | User projects | Varies |
| `target/` | Maven build output | Yes |
| `*/log/` | Log files | Yes |
| `*/build/` | Build output | Yes |

---

## Version History

- **1.0.0** (2025-12-29): Initial repository structure definition

---

*This document serves as the official directory structure guide for the EBS2 Script Interpreter repository.*
