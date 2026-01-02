/**
 * EBS2 Script Interpreter Module
 * 
 * This module provides the EBS2 (Earl Bosch Script) interpreter with support for:
 * - Desktop JavaFX applications
 * - Web-based execution
 * - REST API server
 * - Command-line interface
 */
module com.eb.scriptinterpreter {
    // Java Platform Modules
    requires java.base;
    requires java.sql;
    requires java.xml;
    requires java.desktop;
    requires java.logging;
    requires java.net.http;
    
    // JavaFX Modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.media;
    
    // RichTextFX for editor support
    requires org.fxmisc.richtext;
    requires org.fxmisc.flowless;
    requires org.fxmisc.undo;
    requires reactfx;
    requires wellbehavedfx;
    
    // Database Drivers
    requires mysql.connector.j;
    requires org.postgresql.jdbc;
    requires com.google.protobuf;
    
    // Email Support
    requires org.eclipse.angus.mail;
    requires jakarta.mail;
    requires jakarta.activation;
    
    // FTP Support
    requires org.apache.commons.net;
    
    // Markdown Support
    requires org.commonmark;
    
    // SVG Support
    requires javafxsvg;
    requires xmlgraphics.commons;
    requires commons.io;
    requires commons.logging;
    
    // Jakarta EE APIs (optional - only needed for web deployment)
    requires static jakarta.servlet;
    requires static jakarta.ws.rs;
    requires static jakarta.json.bind;
    requires static jakarta.inject;
    requires static jakarta.annotation;
    requires static jakarta.validation;
    
    // Jersey REST Framework (optional - only needed for REST API)
    requires static jersey.server;
    requires static jersey.common;
    requires static jersey.client;
    requires static jersey.container.servlet;
    requires static jersey.container.servlet.core;
    requires static jersey.container.grizzly2.http;
    requires static jersey.media.json.binding;
    requires static jersey.hk2;
    
    // HK2 Dependency Injection (optional - only needed for REST API)
    requires static org.glassfish.hk2.api;
    requires static org.glassfish.hk2.locator;
    requires static org.glassfish.hk2.utilities;
    requires static org.aopalliance;
    requires static osgi.resource.locator;
    
    // Grizzly HTTP Server (optional - only needed for embedded server)
    requires static grizzly.http.server;
    requires static grizzly.http;
    requires static grizzly.framework;
    
    // JSON-B Implementation (optional - only needed for JSON binding)
    requires static org.eclipse.yasson;
    requires static jakarta.json;
    
    // Export Public API Packages
    // Core interpreter packages
    exports com.eb.script.ast;
    exports com.eb.script.parser;
    exports com.eb.script.lexer;
    exports com.eb.script.runtime;
    exports com.eb.script.json;
    
    // Console/UI packages
    exports com.eb.script.console;
    
    // Application packages
    exports com.eb.cli;
    exports com.eb.server;
    
    // Web API packages
    exports com.eb.web.api;
    exports com.eb.web.filter;
    
    // Open packages for reflection by frameworks
    // Jersey needs reflection access for REST resources
    opens com.eb.web.api to jersey.server, org.glassfish.hk2.locator, org.glassfish.hk2.utilities;
    opens com.eb.web.filter to jersey.server, org.glassfish.hk2.locator, org.glassfish.hk2.utilities;
    opens com.eb.server to jersey.server, org.glassfish.hk2.locator, org.glassfish.hk2.utilities;
    
    // Open console package to JavaFX for reflection
    opens com.eb.script.console to javafx.graphics;
}
