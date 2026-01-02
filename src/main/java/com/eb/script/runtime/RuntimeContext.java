package com.eb.script.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * RuntimeContext manages variable storage and scoping for the EBS2 interpreter.
 * 
 * Provides a simple environment for storing and retrieving variables during
 * script execution. Supports nested scopes for block statements.
 * 
 * @version 1.0.0
 * @since 2026-01-01
 */
public class RuntimeContext {
    private final RuntimeContext parent;
    private final Map<String, Object> variables;
    
    /**
     * Creates a new global runtime context.
     */
    public RuntimeContext() {
        this.parent = null;
        this.variables = new HashMap<>();
    }
    
    /**
     * Creates a new runtime context with a parent scope.
     * 
     * @param parent The parent context for nested scopes
     */
    public RuntimeContext(RuntimeContext parent) {
        this.parent = parent;
        this.variables = new HashMap<>();
    }
    
    /**
     * Defines a new variable in the current scope.
     * 
     * @param name The variable name
     * @param value The variable value
     */
    public void define(String name, Object value) {
        variables.put(name, value);
    }
    
    /**
     * Gets the value of a variable, searching parent scopes if necessary.
     * 
     * @param name The variable name
     * @return The variable value
     * @throws RuntimeException if the variable is not defined
     */
    public Object get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        
        if (parent != null) {
            return parent.get(name);
        }
        
        throw new RuntimeException("Undefined variable: " + name);
    }
    
    /**
     * Assigns a value to an existing variable, searching parent scopes if necessary.
     * 
     * @param name The variable name
     * @param value The new value
     * @throws RuntimeException if the variable is not defined
     */
    public void assign(String name, Object value) {
        if (variables.containsKey(name)) {
            variables.put(name, value);
            return;
        }
        
        if (parent != null) {
            parent.assign(name, value);
            return;
        }
        
        throw new RuntimeException("Undefined variable: " + name);
    }
    
    /**
     * Checks if a variable is defined in this context or parent scopes.
     * 
     * @param name The variable name
     * @return true if the variable is defined
     */
    public boolean isDefined(String name) {
        if (variables.containsKey(name)) {
            return true;
        }
        
        if (parent != null) {
            return parent.isDefined(name);
        }
        
        return false;
    }
    
    /**
     * Creates a new child context for nested scopes.
     * 
     * @return A new RuntimeContext with this context as parent
     */
    public RuntimeContext createChild() {
        return new RuntimeContext(this);
    }
}
