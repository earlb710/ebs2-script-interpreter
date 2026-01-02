# EBS2 Console Package

This package contains console-related classes for the EBS2 Script Interpreter.

## Classes

### Core Classes
- **Console.java** - Main console with styled output and multiline input areas
- **ScriptArea.java** - Enhanced StyleClassedTextArea with line numbers and syntax highlighting
- **Handler.java** - Interface for console command handling

### Configuration
- **ConsoleConfig.java** - Configuration loader for console color properties

### Autocomplete
- **AutocompletePopup.java** - Popup UI for autocomplete suggestions
- **AutocompleteSuggestions.java** - Provides keyword and command suggestions
- **JsonSchemaAutocomplete.java** - JSON-specific autocomplete support

### Utilities
- **StyledTextAreaOutputStream.java** - Redirects output streams to styled text areas

## Based on EBS1

These classes are adapted from the EBS1 console implementation in maven-script-interpreter, ported to work with the EBS2 architecture.

## Usage

```java
// Create a handler (implement the Handler interface)
Handler handler = new MyConsoleHandler();

// Create console
Console console = new Console(handler);

// Get the console tab for JavaFX TabPane
Tab consoleTab = console.getConsoleTab();

// Write to console
console.println("Hello from EBS2!");
console.printlnError("This is an error");
console.printlnOk("Success!");
```

## Testing

Tests are located in `src/test/java/com/eb/script/console/`:
- **ConsoleConfigTest.java** - Tests for console configuration

Run tests with:
```bash
mvn test -Dtest=ConsoleConfigTest
```
