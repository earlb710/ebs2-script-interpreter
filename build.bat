@echo off
REM Build script for EBS2 Script Interpreter
REM Builds both JavaFX JAR and HTML WAR packages

echo ======================================
echo EBS2 Script Interpreter Build Script
echo ======================================
echo.

REM Clean previous builds
echo --------------------------------------
echo Cleaning previous builds...
echo --------------------------------------
call mvn clean
if errorlevel 1 goto error

REM Build JavaFX JAR
echo.
echo --------------------------------------
echo Building JavaFX JAR with dependencies...
echo --------------------------------------
call mvn package -DskipTests
if errorlevel 1 goto error

if exist "target\ebs2-interpreter-javafx.jar" (
    echo √ JavaFX JAR created successfully: target\ebs2-interpreter-javafx.jar
    dir target\ebs2-interpreter-javafx.jar
) else (
    echo × Failed to create JavaFX JAR
    goto error
)

REM Build HTML WAR
echo.
echo --------------------------------------
echo Building HTML WAR for deployment...
echo --------------------------------------
call mvn package -P war -DskipTests
if errorlevel 1 goto error

if exist "target\ebs2-interpreter.war" (
    echo √ HTML WAR created successfully: target\ebs2-interpreter.war
    dir target\ebs2-interpreter.war
) else (
    echo × Failed to create HTML WAR
    goto error
)

REM Summary
echo.
echo --------------------------------------
echo Build Summary
echo --------------------------------------
echo JavaFX JAR: target\ebs2-interpreter-javafx.jar
echo HTML WAR:   target\ebs2-interpreter.war
echo.
echo To run JavaFX application:
echo   java -jar target\ebs2-interpreter-javafx.jar
echo.
echo To deploy HTML application:
echo   Copy target\ebs2-interpreter.war to your application server's webapps directory
echo   Example: copy target\ebs2-interpreter.war %%TOMCAT_HOME%%\webapps\
echo.
echo For detailed deployment instructions, see BUILD_DEPLOYMENT.md
echo.
echo ======================================
echo Build completed successfully!
echo ======================================
goto end

:error
echo.
echo ======================================
echo Build failed!
echo ======================================
exit /b 1

:end
