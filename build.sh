#!/bin/bash
# Build script for EBS2 Script Interpreter
# Builds both JavaFX JAR and HTML WAR packages

set -e

echo "======================================"
echo "EBS2 Script Interpreter Build Script"
echo "======================================"
echo ""

# Function to print section headers
print_header() {
    echo ""
    echo "--------------------------------------"
    echo "$1"
    echo "--------------------------------------"
}

# Clean previous builds
print_header "Cleaning previous builds..."
mvn clean

# Build JavaFX JAR
print_header "Building JavaFX JAR with dependencies..."
mvn package -DskipTests

# Check if JAR was created
if [ -f "target/ebs2-interpreter-javafx.jar" ]; then
    echo "✓ JavaFX JAR created successfully: target/ebs2-interpreter-javafx.jar"
    ls -lh target/ebs2-interpreter-javafx.jar
else
    echo "✗ Failed to create JavaFX JAR"
    exit 1
fi

# Build HTML WAR
print_header "Building HTML WAR for deployment..."
mvn package -P war -DskipTests

# Check if WAR was created
if [ -f "target/ebs2-interpreter.war" ]; then
    echo "✓ HTML WAR created successfully: target/ebs2-interpreter.war"
    ls -lh target/ebs2-interpreter.war
else
    echo "✗ Failed to create HTML WAR"
    exit 1
fi

# Summary
print_header "Build Summary"
echo "JavaFX JAR: target/ebs2-interpreter-javafx.jar"
echo "HTML WAR:   target/ebs2-interpreter.war"
echo ""
echo "To run JavaFX application:"
echo "  java -jar target/ebs2-interpreter-javafx.jar"
echo ""
echo "To deploy HTML application:"
echo "  Copy target/ebs2-interpreter.war to your application server's webapps directory"
echo "  Example: cp target/ebs2-interpreter.war \$TOMCAT_HOME/webapps/"
echo ""
echo "For detailed deployment instructions, see BUILD_DEPLOYMENT.md"
echo ""
echo "======================================"
echo "Build completed successfully!"
echo "======================================"
