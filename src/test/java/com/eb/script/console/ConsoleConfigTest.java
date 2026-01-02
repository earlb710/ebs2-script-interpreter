package com.eb.script.console;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ConsoleConfig.
 */
public class ConsoleConfigTest {

    @Test
    public void testDefaultConfig() {
        ConsoleConfig config = new ConsoleConfig();
        
        // Should have default colors
        assertNotNull(config.getColor("info"));
        assertNotNull(config.getColor("error"));
        assertNotNull(config.getColor("warn"));
        assertNotNull(config.getColor("ok"));
        
        // Default colors should match expected values
        assertEquals("#e6e6e6", config.getColor("info"));
        assertEquals("#ee0000", config.getColor("error"));
        assertEquals("#eeee00", config.getColor("warn"));
        assertEquals("#00ee00", config.getColor("ok"));
    }

    @Test
    public void testGetAllColors() {
        ConsoleConfig config = new ConsoleConfig();
        
        var colors = config.getAllColors();
        assertNotNull(colors);
        assertFalse(colors.isEmpty());
        assertTrue(colors.containsKey("info"));
        assertTrue(colors.containsKey("error"));
        assertTrue(colors.containsKey("warn"));
        assertTrue(colors.containsKey("ok"));
    }

    @Test
    public void testGetConfig() {
        ConsoleConfig config = new ConsoleConfig();
        
        var rawConfig = config.getConfig();
        assertNotNull(rawConfig);
        // Config should either have "colors" or "profiles" key
        assertTrue(rawConfig.containsKey("colors") || rawConfig.containsKey("profiles"));
    }
}
