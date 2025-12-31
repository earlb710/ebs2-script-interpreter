package com.eb.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Simple test for the LocalServer functionality.
 * Tests that the server can start, respond to requests, and stop cleanly.
 */
public class LocalServerTest {
    
    public static void main(String[] args) {
        System.out.println("=== EBS2 Local Server Test ===\n");
        
        testServerStartStop();
        testHealthEndpoint();
        testVersionEndpoint();
        
        System.out.println("\n=== All Tests Completed Successfully ===");
    }
    
    private static void testServerStartStop() {
        System.out.println("Test 1: Server Start/Stop");
        
        LocalServer server = new LocalServer(new ServerConfig(9999));
        
        try {
            // Test starting the server
            server.start();
            assert server.isRunning() : "Server should be running after start()";
            System.out.println("  ✓ Server started successfully");
            
            // Wait a moment for server to fully initialize
            Thread.sleep(500);
            
            // Test stopping the server
            server.stop();
            assert !server.isRunning() : "Server should not be running after stop()";
            System.out.println("  ✓ Server stopped successfully");
            
        } catch (IOException e) {
            System.err.println("  ✗ Failed to start server: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.println("  ✗ Test interrupted: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        System.out.println();
    }
    
    private static void testHealthEndpoint() {
        System.out.println("Test 2: Health Endpoint");
        
        LocalServer server = new LocalServer(new ServerConfig(9999));
        
        try {
            server.start();
            Thread.sleep(500); // Give server time to start
            
            // Test the health endpoint
            URL url = new URL("http://localhost:9999/api/script/health");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            int responseCode = conn.getResponseCode();
            assert responseCode == 200 : "Health endpoint should return 200 OK";
            System.out.println("  ✓ Health endpoint returned: " + responseCode);
            
            conn.disconnect();
            server.stop();
            
        } catch (IOException | InterruptedException e) {
            System.err.println("  ✗ Test failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        System.out.println();
    }
    
    private static void testVersionEndpoint() {
        System.out.println("Test 3: Version Endpoint");
        
        LocalServer server = new LocalServer(new ServerConfig(9999));
        
        try {
            server.start();
            Thread.sleep(500); // Give server time to start
            
            // Test the version endpoint
            URL url = new URL("http://localhost:9999/api/script/version");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            int responseCode = conn.getResponseCode();
            assert responseCode == 200 : "Version endpoint should return 200 OK";
            System.out.println("  ✓ Version endpoint returned: " + responseCode);
            
            conn.disconnect();
            server.stop();
            
        } catch (IOException | InterruptedException e) {
            System.err.println("  ✗ Test failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        System.out.println();
    }
}
