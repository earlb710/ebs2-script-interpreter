package com.eb.script.console;

import com.eb.script.stdlib.JSONFunctions;
import com.eb.script.types.JSONValue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration loader for console color properties.
 * Loads console.cfg JSON file from the root directory and provides access to color settings.
 * 
 * @author Earl Bosch
 */
public class ConsoleConfig {
    
    private static final String CONFIG_FILE = "console.cfg";
    private final Map<String, Object> config;
    private final Map<String, String> colors;
    private final boolean loadedFromFile;
    
    /**
     * Load console configuration from console.cfg file.
     * Falls back to default configuration if file is missing or invalid.
     */
    public ConsoleConfig() {
        this.colors = new LinkedHashMap<>();
        Map<String, Object> loadedConfig = loadConfigFile();
        
        if (loadedConfig != null) {
            this.config = loadedConfig;
            this.loadedFromFile = true;
            loadColors();
        } else {
            this.config = getDefaultConfig();
            this.loadedFromFile = false;
            loadColors();
        }
    }
    
    /**
     * Load the configuration file from the root directory.
     * Searches in current directory and parent directory.
     * @return Map containing configuration, or null if loading fails
     */
    private Map<String, Object> loadConfigFile() {
        try {
            // Try current directory first
            Path configPath = Paths.get(CONFIG_FILE);
            
            // If not found in current directory, try parent directory
            if (!Files.exists(configPath)) {
                configPath = Paths.get("..", CONFIG_FILE);
            }
            
            // If still not found, report and use defaults
            if (!Files.exists(configPath)) {
                System.out.println("Console config file not found: " + CONFIG_FILE + ", using defaults.");
                System.out.println("Searched in: " + Paths.get("").toAbsolutePath() + " and parent directory");
                return null;
            }
            
            String jsonContent = Files.readString(configPath);
            JSONValue parsed = JSONFunctions.parse(jsonContent);
            
            if (parsed.getType() == JSONValue.JSONType.OBJECT) {
                // Convert JSONValue to Map<String, Object>
                Map<String, Object> configMap = new LinkedHashMap<>();
                for (String key : parsed.keys()) {
                    configMap.put(key, convertJSONValue(parsed.get(key)));
                }
                System.out.println("Loaded console configuration from " + CONFIG_FILE);
                return configMap;
            } else {
                System.err.println("Invalid console.cfg format: expected JSON object");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error reading console.cfg: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error parsing console.cfg: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Convert JSONValue to Java Object for easier manipulation.
     */
    private Object convertJSONValue(JSONValue jsonValue) {
        switch (jsonValue.getType()) {
            case OBJECT:
                Map<String, Object> map = new LinkedHashMap<>();
                for (String key : jsonValue.keys()) {
                    map.put(key, convertJSONValue(jsonValue.get(key)));
                }
                return map;
            case ARRAY:
                List<Object> list = new ArrayList<>();
                for (JSONValue item : jsonValue.values()) {
                    list.add(convertJSONValue(item));
                }
                return list;
            case STRING:
                return jsonValue.asString();
            case NUMBER:
                return jsonValue.asNumber();
            case BOOLEAN:
                return jsonValue.asBoolean();
            case NULL:
            default:
                return null;
        }
    }
    
    /**
     * Extract color properties from the configuration.
     * Supports both the new profile-based structure (with currentProfile, profiles) 
     * and the old flat structure (with colors).
     */
    private void loadColors() {
        if (config == null) {
            return;
        }
        
        // First, try the new profile-based structure
        Object profilesObj = config.get("profiles");
        if (profilesObj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> profilesMap = (Map<String, Object>) profilesObj;
            
            // Get the current profile name, default to "default" if not specified
            String currentProfile = "default";
            Object currentProfileObj = config.get("currentProfile");
            if (currentProfileObj != null && !currentProfileObj.toString().isEmpty()) {
                currentProfile = currentProfileObj.toString();
            }
            
            // Get the colors from the current profile
            Object profileColorsObj = profilesMap.get(currentProfile);
            if (profileColorsObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> colorMap = (Map<String, Object>) profileColorsObj;
                
                for (Map.Entry<String, Object> entry : colorMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        colors.put(key, value.toString());
                    }
                }
                System.out.println("Loaded colors from profile: " + currentProfile);
                return;
            } else {
                // Profile not found, try "default"
                profileColorsObj = profilesMap.get("default");
                if (profileColorsObj instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> colorMap = (Map<String, Object>) profileColorsObj;
                    
                    for (Map.Entry<String, Object> entry : colorMap.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value != null) {
                            colors.put(key, value.toString());
                        }
                    }
                    System.out.println("Profile '" + currentProfile + "' not found, loaded colors from 'default' profile");
                    return;
                }
            }
        }
        
        // Fall back to the old flat structure with "colors" key
        Object colorsObj = config.get("colors");
        if (colorsObj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> colorMap = (Map<String, Object>) colorsObj;
            
            for (Map.Entry<String, Object> entry : colorMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    colors.put(key, value.toString());
                }
            }
            System.out.println("Loaded colors from legacy 'colors' structure");
        }
    }
    
    /**
     * Get the default configuration.
     * These are the default color values from console.css
     */
    private Map<String, Object> getDefaultConfig() {
        Map<String, Object> defaultConfig = new LinkedHashMap<>();
        Map<String, String> defaultColors = new LinkedHashMap<>();
        
        // Default colors from console.css
        defaultColors.put("info", "#e6e6e6");
        defaultColors.put("comment", "#ffffcc");
        defaultColors.put("error", "#ee0000");
        defaultColors.put("warn", "#eeee00");
        defaultColors.put("ok", "#00ee00");
        defaultColors.put("code", "white");
        defaultColors.put("datatype", "#D070FF");
        defaultColors.put("data", "pink");
        defaultColors.put("keyword", "#00FFFF");
        defaultColors.put("builtin", "#99e0e0");
        defaultColors.put("literal", "blue");
        defaultColors.put("identifier", "white");
        defaultColors.put("sql", "#00ee66");
        defaultColors.put("custom", "#eeee90");
        defaultColors.put("function", "#DCDCAA");
        defaultColors.put("syntax-error", "#DCDCAA");
        defaultColors.put("background", "#000000");
        defaultColors.put("text", "#e6e6e6");
        defaultColors.put("caret", "white");
        
        defaultConfig.put("colors", defaultColors);
        return defaultConfig;
    }
    
    /**
     * Get a color value by name.
     * @param name The color property name (e.g., "info", "error", "warn")
     * @return The color value as a string, or null if not found
     */
    public String getColor(String name) {
        return colors.get(name);
    }
    
    /**
     * Get all color properties as a map.
     * @return Map of color name to color value
     */
    public Map<String, String> getAllColors() {
        return new LinkedHashMap<>(colors);
    }
    
    /**
     * Check if the configuration was loaded successfully from file.
     * @return true if loaded from file, false if using defaults
     */
    public boolean isLoadedFromFile() {
        return loadedFromFile;
    }
    
    /**
     * Get the raw configuration map.
     * @return Unmodifiable map containing the configuration data
     */
    public Map<String, Object> getConfig() {
        return Collections.unmodifiableMap(config);
    }
}
