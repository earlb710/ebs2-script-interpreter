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
    
    // Jakarta EE APIs
    requires jakarta.servlet;
    requires jakarta.ws.rs;
    requires jakarta.json.bind;
    requires jakarta.inject;
    requires jakarta.annotation;
    requires jakarta.validation;
    
    // Jersey REST Framework
    requires jersey.server;
    requires jersey.common;
    requires jersey.client;
    requires jersey.container.servlet;
    requires jersey.container.servlet.core;
    requires jersey.container.grizzly2.http;
    requires jersey.media.json.binding;
    requires jersey.hk2;
    
    // HK2 Dependency Injection
    requires org.glassfish.hk2.api;
    requires org.glassfish.hk2.locator;
    requires org.glassfish.hk2.utilities;
    requires org.aopalliance;
    requires osgi.resource.locator;
    
    // Grizzly HTTP Server
    requires grizzly.http.server;
    requires grizzly.http;
    requires grizzly.framework;
    
    // JSON-B Implementation
    requires org.eclipse.yasson;
    requires jakarta.json;
    
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
