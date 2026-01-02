# EBS2 Script Interpreter - Running the Applications

The EBS2 Script Interpreter provides three main executable applications:

## 1. CommandLineApp - Command-Line Script Executor

Execute EBS2 scripts from the command line.

### Usage

```bash
# Using Maven
mvn exec:java -Dexec.mainClass="com.eb.cli.CommandLineApp" -Dexec.args="script.ebs"

# Using compiled classes
java -cp "target/classes:..." com.eb.cli.CommandLineApp script.ebs

# Show help
mvn exec:java -Dexec.mainClass="com.eb.cli.CommandLineApp" -Dexec.args="--help"

# Show version
mvn exec:java -Dexec.mainClass="com.eb.cli.CommandLineApp" -Dexec.args="--version"
```

### Example

```bash
# Create a simple EBS2 script
cat > hello.ebs << 'EOF'
var name = "World"
print "Hello, " + name + "!"
EOF

# Run it
mvn exec:java -Dexec.mainClass="com.eb.cli.CommandLineApp" -Dexec.args="hello.ebs" -q
```

## 2. ConsoleApp - Interactive JavaFX Console

Launch an interactive console with syntax highlighting and command history.

### Usage

```bash
# Using Maven (JavaFX plugin)
mvn javafx:run

# The ConsoleApp will launch a JavaFX window with:
# - Output area for displaying results
# - Input area for typing EBS2 code
# - Syntax highlighting
# - Command history (Up/Down arrows)
# - Execute with Ctrl+Enter
# - Clear output with Ctrl+L
```

### Features

- **Interactive REPL**: Type code and execute immediately
- **Syntax Highlighting**: Color-coded tokens for better readability
- **Command History**: Navigate previous commands with arrow keys
- **Multiple Tabs**: Support for multiple console tabs (future)
- **Configurable Colors**: Load custom color schemes from `console.cfg`

## 3. ServerApp - HTTP REST API Server

Start an embedded HTTP server providing REST API endpoints.

### Usage

```bash
# Using Maven (default port 8080)
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp"

# Custom port
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp" -Dexec.args="--port 9000"

# Custom host and port
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp" -Dexec.args="--host 0.0.0.0 --port 9000"

# Using environment variables
export EBS2_PORT=9000
export EBS2_HOST=localhost
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp"
```

### API Endpoints

Once started, the server provides these REST endpoints:

- **POST** `/api/script/execute` - Execute EBS2 script
- **POST** `/api/script/validate` - Validate EBS2 script syntax
- **GET** `/api/script/version` - Get interpreter version
- **GET** `/api/script/health` - Health check

### Example API Usage

```bash
# Health check
curl http://localhost:8080/api/script/health

# Execute script
curl -X POST http://localhost:8080/api/script/execute \
  -H "Content-Type: application/json" \
  -d '{"code": "var name = \"World\"\nprint \"Hello, \" + name + \"!\""}'

# Validate script
curl -X POST http://localhost:8080/api/script/validate \
  -H "Content-Type: application/json" \
  -d '{"code": "var x = 5\nprint x"}'
```

## Building and Deployment

### Build All

```bash
# Clean and build
mvn clean package

# This creates:
# - target/classes/ - Compiled classes
# - target/ebs2-interpreter-javafx.jar - Fat JAR with dependencies
```

### Run from JAR

```bash
# After building, you can run from the JAR:
java -jar target/ebs2-interpreter-javafx.jar
# Note: This launches ConsoleApp (JavaFX console)

# For CommandLineApp, use:
java -cp target/ebs2-interpreter-javafx.jar com.eb.cli.CommandLineApp script.ebs

# For ServerApp, use:
java -cp target/ebs2-interpreter-javafx.jar com.eb.server.ServerApp --port 8080
```

## Quick Reference

| Application      | Main Class                    | Purpose                          |
|------------------|-------------------------------|----------------------------------|
| CommandLineApp   | com.eb.cli.CommandLineApp     | Execute scripts from CLI         |
| ConsoleApp       | com.eb.cli.ConsoleApp         | Interactive JavaFX console       |
| ServerApp        | com.eb.server.ServerApp       | HTTP REST API server             |

## Environment Variables

- **EBS2_DEBUG** - Enable debug output in CommandLineApp
- **EBS2_PORT** - Default port for ServerApp (default: 8080)
- **EBS2_HOST** - Default host for ServerApp (default: localhost)

## See Also

- [BUILD_DEPLOYMENT.md](BUILD_DEPLOYMENT.md) - Detailed build and deployment guide
- [README.md](README.md) - Main project README
- [doc/examples/](doc/examples/) - Example EBS2 scripts
