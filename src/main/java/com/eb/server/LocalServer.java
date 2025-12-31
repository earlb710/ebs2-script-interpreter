package com.eb.server;

import com.eb.web.api.EBS2Application;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Local HTTP server for EBS2 Script Interpreter.
 * Uses Grizzly HTTP server to host REST API endpoints.
 */
public class LocalServer {
    
    private HttpServer server;
    private final ServerConfig config;
    private volatile boolean running = false;
    
    /**
     * Creates a local server with default configuration.
     */
    public LocalServer() {
        this(new ServerConfig());
    }
    
    /**
     * Creates a local server with custom configuration.
     * 
     * @param config Server configuration
     */
    public LocalServer(ServerConfig config) {
        this.config = config;
    }
    
    /**
     * Starts the HTTP server.
     * 
     * @throws IOException if server fails to start
     */
    public void start() throws IOException {
        if (running) {
            throw new IllegalStateException("Server is already running");
        }
        
        // Create a resource configuration that includes the REST API
        ResourceConfig resourceConfig = ResourceConfig.forApplication(new EBS2Application());
        
        // Create and start the Grizzly HTTP server
        URI baseUri = URI.create(config.getBaseUri());
        server = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig, false);
        server.start();
        
        running = true;
        System.out.println("EBS2 Local Server started");
        System.out.println("  Base URI: " + config.getBaseUri());
        System.out.println("  API endpoints available at: " + config.getBaseUri() + "api/");
    }
    
    /**
     * Stops the HTTP server.
     */
    public void stop() {
        if (server != null && running) {
            server.shutdownNow();
            running = false;
            System.out.println("EBS2 Local Server stopped");
        }
    }
    
    /**
     * Checks if the server is running.
     * 
     * @return true if server is running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }
    
    /**
     * Gets the server configuration.
     * 
     * @return server configuration
     */
    public ServerConfig getConfig() {
        return config;
    }
    
    /**
     * Gets the base URI of the server.
     * 
     * @return base URI string
     */
    public String getBaseUri() {
        return config.getBaseUri();
    }
}
