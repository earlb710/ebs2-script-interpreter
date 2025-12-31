package com.eb.web.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API endpoint for EBS2 script execution.
 * Provides HTTP endpoints for the HTML frontend.
 */
@Path("/script")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScriptResource {
    
    /**
     * Execute an EBS2 script and return the result.
     * 
     * @param request JSON object containing the script code
     * @return JSON response with execution result
     */
    @POST
    @Path("/execute")
    public Response executeScript(Map<String, Object> request) {
        try {
            String code = (String) request.get("code");
            
            if (code == null || code.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(createErrorResponse("Code cannot be empty"))
                    .build();
            }
            
            // TODO: Implement actual EBS2 script execution
            // For now, return a mock response
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("output", "Script execution not yet implemented.\nReceived code:\n" + code);
            result.put("message", "This is a placeholder response. Full implementation pending.");
            
            return Response.ok(result).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(createErrorResponse("Internal server error: " + e.getMessage()))
                .build();
        }
    }
    
    /**
     * Validate an EBS2 script without executing it.
     * 
     * @param request JSON object containing the script code
     * @return JSON response with validation result
     */
    @POST
    @Path("/validate")
    public Response validateScript(Map<String, Object> request) {
        try {
            String code = (String) request.get("code");
            
            if (code == null || code.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(createErrorResponse("Code cannot be empty"))
                    .build();
            }
            
            // TODO: Implement actual EBS2 script validation
            Map<String, Object> result = new HashMap<>();
            result.put("valid", true);
            result.put("message", "Validation not yet implemented");
            
            return Response.ok(result).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(createErrorResponse("Internal server error: " + e.getMessage()))
                .build();
        }
    }
    
    /**
     * Get interpreter version and status.
     * 
     * @return JSON response with version information
     */
    @GET
    @Path("/version")
    public Response getVersion() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "EBS2 Script Interpreter");
        info.put("version", "2.0.0-SPEC");
        info.put("status", "HTML Frontend Enabled");
        info.put("api", "REST");
        
        return Response.ok(info).build();
    }
    
    /**
     * Health check endpoint.
     * 
     * @return JSON response with health status
     */
    @GET
    @Path("/health")
    public Response healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", System.currentTimeMillis());
        
        return Response.ok(health).build();
    }
    
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("error", message);
        return error;
    }
}
