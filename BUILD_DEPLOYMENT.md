# EBS2 Script Interpreter - Build and Deployment Guide

This document describes how to build and deploy the EBS2 Script Interpreter for both JavaFX (desktop) and HTML (web) frontends.

## Prerequisites

- Java 17 or later
- Maven 3.8 or later
- For JavaFX: JavaFX SDK 17 or later (automatically downloaded by Maven)
- For Local Server: No additional requirements (uses embedded Grizzly HTTP server)
- For WAR: Jakarta EE compatible application server (e.g., Tomcat 10, Jetty 11, WildFly 27)

## Running the Local Server (Embedded HTTP Server)

The EBS2 Script Interpreter includes an embedded HTTP server for local development and testing. This is the easiest way to get started with the REST API without needing a separate application server.

### 1. Build the Project

```bash
mvn clean compile
```

### 2. Start the Local Server

**Using Maven (recommended):**
```bash
# Default settings (localhost:8080)
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp"

# Custom port
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp" -Dexec.args="--port 9000"

# Custom host and port
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp" -Dexec.args="--host 0.0.0.0 --port 9000"
```

**Using Java directly:**
```bash
java -cp "target/classes:$(mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout)" \
  com.eb.server.ServerApp --port 8080
```

**Using environment variables:**
```bash
export EBS2_PORT=9000
export EBS2_HOST=localhost
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp"
```

### 3. Access the API

Once started, the server displays available endpoints:
```
=== EBS2 Local Server ===
Server is running at: http://localhost:8080/

Available API endpoints:
  POST http://localhost:8080/api/script/execute   - Execute EBS2 script
  POST http://localhost:8080/api/script/validate  - Validate EBS2 script
  GET  http://localhost:8080/api/script/version   - Get version info
  GET  http://localhost:8080/api/script/health    - Health check
```

### 4. Test the API

**Health check:**
```bash
curl http://localhost:8080/api/script/health
```

**Execute script:**
```bash
curl -X POST http://localhost:8080/api/script/execute \
  -H "Content-Type: application/json" \
  -d '{"code": "program Test\n\nmain\n    print \"Hello, World!\"\nend main"}'
```

### 5. Stop the Server

Press `Ctrl+C` to stop the server gracefully.

## Building for JavaFX (Desktop Application)

### 1. Build Fat JAR with Dependencies

Build a standalone JAR file that includes all dependencies:

```bash
mvn clean package
```

This creates `target/ebs2-interpreter-javafx.jar` containing all required dependencies.

### 2. Run the JavaFX Application

```bash
java -jar target/ebs2-interpreter-javafx.jar
```

Or using Maven:

```bash
mvn javafx:run
```

### 3. Distribution

Distribute the `ebs2-interpreter-javafx.jar` file. Users only need Java 17+ installed to run it:

```bash
java -jar ebs2-interpreter-javafx.jar
```

## Building for HTML Frontend (Web Application)

### 1. Build WAR File

Build the web application archive using the `war` profile:

```bash
mvn clean package -P war
```

This creates `target/ebs2-interpreter.war` for deployment to a servlet container.

### 2. Deploy to Application Server

#### Tomcat 10.x

1. Copy the WAR file to Tomcat's webapps directory:
```bash
cp target/ebs2-interpreter.war $TOMCAT_HOME/webapps/
```

2. Start Tomcat:
```bash
$TOMCAT_HOME/bin/startup.sh  # Linux/Mac
$TOMCAT_HOME/bin/startup.bat  # Windows
```

3. Access the application:
```
http://localhost:8080/ebs2-interpreter/
```

#### Jetty 11.x

1. Deploy the WAR:
```bash
cp target/ebs2-interpreter.war $JETTY_BASE/webapps/
```

2. Start Jetty:
```bash
java -jar $JETTY_HOME/start.jar
```

3. Access the application:
```
http://localhost:8080/ebs2-interpreter/
```

#### WildFly 27.x

1. Deploy using CLI:
```bash
$WILDFLY_HOME/bin/jboss-cli.sh --connect
deploy target/ebs2-interpreter.war
```

2. Or copy to deployments:
```bash
cp target/ebs2-interpreter.war $WILDFLY_HOME/standalone/deployments/
```

3. Access the application:
```
http://localhost:8080/ebs2-interpreter/
```

## API Endpoints

The HTML frontend exposes the following REST API endpoints:

### Execute Script
- **URL**: `/api/script/execute`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Body**:
```json
{
  "code": "program HelloWorld\n\nmain\n    print \"Hello, World!\"\nend main"
}
```
- **Response**:
```json
{
  "success": true,
  "output": "Hello, World!",
  "message": "Execution completed"
}
```

### Validate Script
- **URL**: `/api/script/validate`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Body**:
```json
{
  "code": "program Test\n\nmain\nend main"
}
```
- **Response**:
```json
{
  "valid": true,
  "message": "Script is valid"
}
```

### Version Information
- **URL**: `/api/script/version`
- **Method**: `GET`
- **Response**:
```json
{
  "name": "EBS2 Script Interpreter",
  "version": "2.0.0-SPEC",
  "status": "HTML Frontend Enabled",
  "api": "REST"
}
```

### Health Check
- **URL**: `/api/script/health`
- **Method**: `GET`
- **Response**:
```json
{
  "status": "UP",
  "timestamp": 1672531200000
}
```

## Configuration

### Context Path

By default, the WAR deploys to `/ebs2-interpreter`. To change this:

1. Rename the WAR file (e.g., `ROOT.war` for root context)
2. Or configure in your application server

### CORS Settings

⚠️ **Security Warning**: The default configuration allows requests from any origin (`Access-Control-Allow-Origin: *`). This is suitable for development but **must be restricted in production**.

CORS is configured in `com.eb.web.filter.CorsFilter`. For production, modify the filter to restrict origins:

```java
// Option 1: Single domain
httpResponse.setHeader("Access-Control-Allow-Origin", "https://your-domain.com");

// Option 2: Multiple domains (requires custom logic)
String origin = httpRequest.getHeader("Origin");
if (origin != null && allowedOrigins.contains(origin)) {
    httpResponse.setHeader("Access-Control-Allow-Origin", origin);
}

// Option 3: Use environment variable
String allowedOrigin = System.getenv("ALLOWED_ORIGIN");
httpResponse.setHeader("Access-Control-Allow-Origin", allowedOrigin);
```

## Development

### Running in Development Mode

For local development with auto-reload:

#### Local Server (REST API)
```bash
# Start the embedded server (fastest for API development)
mvn exec:java -Dexec.mainClass="com.eb.server.ServerApp"
```

#### JavaFX
```bash
mvn javafx:run
```

#### WAR (using Maven Jetty plugin)
Add to pom.xml and run:
```bash
mvn jetty:run
```

### Build Both Versions

To build both JAR and WAR:
```bash
mvn clean package
mvn package -P war
```

## Troubleshooting

### JavaFX JAR Issues

**Problem**: Application doesn't start
- **Solution**: Ensure Java 17+ is installed: `java -version`

**Problem**: Missing JavaFX modules
- **Solution**: The Shade plugin includes all dependencies. Ensure build completed successfully.

### Local Server Issues

**Problem**: Port already in use
- **Solution**: Use a different port with `--port` argument
- **Solution**: Find and stop the process using the port: `lsof -i :8080` (Linux/Mac) or `netstat -ano | findstr :8080` (Windows)

**Problem**: Server starts but API not responding
- **Solution**: Check firewall settings are not blocking the port
- **Solution**: Verify you're using the correct base URL (shown when server starts)

**Problem**: Cannot access from other machines
- **Solution**: Bind to all interfaces with `--host 0.0.0.0`
- **Solution**: Ensure firewall allows incoming connections on the port

### WAR Deployment Issues

**Problem**: 404 Not Found
- **Solution**: Check the deployment path matches your WAR file name
- **Solution**: Verify the application server logs for deployment errors

**Problem**: API endpoints not responding
- **Solution**: Ensure Jersey and JSON-B dependencies are loaded
- **Solution**: Check `web.xml` servlet mapping is correct

**Problem**: CORS errors
- **Solution**: Verify `CorsFilter` is active in `web.xml`
- **Solution**: Check browser console for specific CORS error details

## Architecture

### Local Server Architecture
```
Local Server (Embedded Grizzly)
├── ServerApp (Main Class)
├── LocalServer (HTTP Server Manager)
├── ServerConfig (Configuration)
├── Grizzly HTTP Server
│   └── Jersey JAX-RS Container
│       ├── REST API (ScriptResource)
│       ├── EBS2 Lexer/Parser
│       └── EBS2 Runtime
└── Dependencies (classpath)
```

### JavaFX Architecture
```
ebs2-interpreter-javafx.jar
├── JavaFX UI (Desktop)
├── EBS2 Lexer/Parser
├── EBS2 Runtime
└── All Dependencies
```

### HTML Architecture
```
ebs2-interpreter.war
├── index.html (Frontend)
├── WEB-INF/
│   ├── web.xml
│   └── classes/
│       ├── REST API (ScriptResource)
│       ├── CORS Filter
│       └── EBS2 Interpreter
└── Dependencies
```

## Production Deployment

### Security Considerations

1. **CORS**: Restrict origins in production
2. **Authentication**: Add authentication for script execution
3. **Rate Limiting**: Implement rate limiting on API endpoints
4. **Input Validation**: Validate and sanitize all script input
5. **Resource Limits**: Set execution timeouts and memory limits

### Performance

1. **Connection Pooling**: Configure in application server
2. **Caching**: Enable static resource caching
3. **Compression**: Enable gzip compression in application server
4. **Monitoring**: Use APM tools to monitor performance

## Support

For issues, questions, or contributions, please refer to the main project documentation.
