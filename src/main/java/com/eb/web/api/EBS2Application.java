package com.eb.web.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS Application configuration for EBS2 REST API.
 */
@ApplicationPath("/api")
public class EBS2Application extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ScriptResource.class);
        return classes;
    }
}
