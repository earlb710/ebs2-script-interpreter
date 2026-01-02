package com.eb.script.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides autocomplete suggestions for keywords, builtins, and console commands.
 *
 * @author Earl Bosch
 */
public class AutocompleteSuggestions {

    private static final List<String> KEYWORDS = Arrays.asList(
            "var", "if", "else", "while", "for", "repeat", "break", 
            "continue", "function", "return", "import", "print"
    );

    private static final List<String> CONSOLE_COMMANDS = Arrays.asList(
            "/help", "/?",
            "/clear",
            "/reset",
            "/exit"
    );

    /**
     * Get all available suggestions (keywords + console commands).
     */
    public static List<String> getAllSuggestions() {
        List<String> all = new ArrayList<>(KEYWORDS);
        all.addAll(CONSOLE_COMMANDS);
        return all.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Get suggestions filtered by the given prefix (case-insensitive).
     */
    public static List<String> getSuggestionsWithPrefix(List<String> suggestions, String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return suggestions;
        }

        String cleanPrefix = prefix;
        while (cleanPrefix.endsWith(".")) {
            cleanPrefix = cleanPrefix.substring(0, cleanPrefix.length() - 1);
        }
        
        if (cleanPrefix.isEmpty()) {
            return suggestions;
        }

        String lowerPrefix = cleanPrefix.toLowerCase();
        return suggestions.stream()
                .filter(s -> s.toLowerCase().startsWith(lowerPrefix))
                .collect(Collectors.toList());
    }

    /**
     * Determine what suggestions to show based on the current input context.
     */
    public static List<String> getSuggestionsForContext(String text, int caretPosition) {
        String beforeCaret = text.substring(0, Math.min(caretPosition, text.length()));
        String currentWord = getCurrentWord(beforeCaret);

        // Check if the current line starts with '/' (console command)
        if (currentWord.startsWith("/")) {
            currentWord = currentWord.substring(1);
            return getSuggestionsWithPrefix(CONSOLE_COMMANDS, currentWord);
        }

        // Default: return keywords
        return getSuggestionsWithPrefix(KEYWORDS, currentWord);
    }

    /**
     * Extract the current word being typed at the end of the text.
     */
    private static String getCurrentWord(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        int end = text.length();
        int start = end;

        while (start > 0) {
            char c = text.charAt(start - 1);
            if (Character.isLetterOrDigit(c) || c == '.' || c == '_' || c == '/' || c == '?') {
                start--;
            } else {
                break;
            }
        }

        return text.substring(start, end);
    }

    /**
     * Check if a given name is a builtin function.
     */
    public static boolean isBuiltin(String name) {
        return false; // Placeholder
    }

    /**
     * Generate parameter signature for a builtin function.
     */
    public static String getBuiltinParameterSignature(String builtinName) {
        return null; // Placeholder
    }
}
