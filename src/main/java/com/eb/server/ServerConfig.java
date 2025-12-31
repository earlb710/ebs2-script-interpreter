package com.eb.server;

/**
 * Configuration class for the local HTTP server.
 * Provides settings for host, port, and other server parameters.
 */
public class ServerConfig {
    
    private final String host;
    private final int port;
    private final String baseUri;
    
    /**
     * Creates a server configuration with default settings.
     * Default: localhost:8080
     */
    public ServerConfig() {
        this("localhost", 8080);
    }
    
    /**
     * Creates a server configuration with custom port.
     * 
     * @param port The port number to listen on
     */
    public ServerConfig(int port) {
        this("localhost", port);
    }
    
    /**
     * Creates a server configuration with custom host and port.
     * 
     * @param host The host address to bind to
     * @param port The port number to listen on
     */
    public ServerConfig(String host, int port) {
        this.host = host;
        this.port = port;
        this.baseUri = String.format("http://%s:%d/", host, port);
    }
    
    public String getHost() {
        return host;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getBaseUri() {
        return baseUri;
    }
    
    @Override
    public String toString() {
        return String.format("ServerConfig{host='%s', port=%d, baseUri='%s'}", 
                             host, port, baseUri);
    }
}
