package com.eb.server;

import java.io.IOException;

/**
 * Main application class for starting the EBS2 local server.
 * Starts an embedded HTTP server that listens for REST API requests.
 */
public class ServerApp {
    
    private static final String DEFAULT_PORT = "8080";
    private static final String DEFAULT_HOST = "localhost";
    
    public static void main(String[] args) {
        // Parse command-line arguments
        int port = parsePort(args);
        String host = parseHost(args);
        
        // Create server configuration
        ServerConfig config = new ServerConfig(host, port);
        LocalServer server = new LocalServer(config);
        
        try {
            // Start the server
            server.start();
            
            // Print usage information
            printUsageInfo(config);
            
            // Add shutdown hook to gracefully stop the server
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\nShutting down server...");
                server.stop();
            }));
            
            // Keep the server running
            System.out.println("\nPress Ctrl+C to stop the server");
            Thread.currentThread().join();
            
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("\nServer interrupted");
            server.stop();
        }
    }
    
    /**
     * Parses the port number from command-line arguments.
     * 
     * @param args command-line arguments
     * @return port number
     */
    private static int parsePort(String[] args) {
        String portStr = DEFAULT_PORT;
        
        // Look for --port argument
        for (int i = 0; i < args.length - 1; i++) {
            if ("--port".equals(args[i]) || "-p".equals(args[i])) {
                portStr = args[i + 1];
                break;
            }
        }
        
        // Check environment variable
        String envPort = System.getenv("EBS2_PORT");
        if (envPort != null && !envPort.isEmpty()) {
            portStr = envPort;
        }
        
        try {
            int port = Integer.parseInt(portStr);
            if (port < 1 || port > 65535) {
                System.err.println("Invalid port number: " + port + ". Using default: " + DEFAULT_PORT);
                return Integer.parseInt(DEFAULT_PORT);
            }
            return port;
        } catch (NumberFormatException e) {
            System.err.println("Invalid port format: " + portStr + ". Using default: " + DEFAULT_PORT);
            return Integer.parseInt(DEFAULT_PORT);
        }
    }
    
    /**
     * Parses the host from command-line arguments.
     * 
     * @param args command-line arguments
     * @return host string
     */
    private static String parseHost(String[] args) {
        String host = DEFAULT_HOST;
        
        // Look for --host argument
        for (int i = 0; i < args.length - 1; i++) {
            if ("--host".equals(args[i]) || "-h".equals(args[i])) {
                host = args[i + 1];
                break;
            }
        }
        
        // Check environment variable
        String envHost = System.getenv("EBS2_HOST");
        if (envHost != null && !envHost.isEmpty()) {
            host = envHost;
        }
        
        return host;
    }
    
    /**
     * Prints usage information for the server.
     * 
     * @param config server configuration
     */
    private static void printUsageInfo(ServerConfig config) {
        System.out.println("\n=== EBS2 Local Server ===");
        System.out.println("Server is running at: " + config.getBaseUri());
        System.out.println("\nAvailable API endpoints:");
        System.out.println("  POST " + config.getBaseUri() + "api/script/execute   - Execute EBS2 script");
        System.out.println("  POST " + config.getBaseUri() + "api/script/validate  - Validate EBS2 script");
        System.out.println("  GET  " + config.getBaseUri() + "api/script/version   - Get version info");
        System.out.println("  GET  " + config.getBaseUri() + "api/script/health    - Health check");
        System.out.println("\nExample curl command:");
        System.out.println("  curl -X POST " + config.getBaseUri() + "api/script/execute \\");
        System.out.println("    -H \"Content-Type: application/json\" \\");
        System.out.println("    -d '{\"code\": \"program Test\\n\\nmain\\n    print \\\"Hello\\\"\\nend main\"}'");
    }
}
