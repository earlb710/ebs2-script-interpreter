package com.eb.script.lexer;

/**
 * Simple performance benchmark for TokenColor optimizations.
 * Demonstrates the speed improvements critical for editor highlighting.
 * 
 * @version 1.0.0
 * @since 2025-12-30
 */
public class TokenColorBenchmark {
    
    public static void main(String[] args) {
        System.out.println("=== TokenColor Performance Benchmark ===\n");
        
        // Warm up JVM
        System.out.println("Warming up JVM...");
        for (int i = 0; i < 10000; i++) {
            TokenColor.getDefaultColorForToken(TokenType.VAR);
            HtmlTokenColor.getDefaultColorForToken(TokenType.VAR);
        }
        
        // Benchmark getDefaultColorForToken (most critical for editor performance)
        int iterations = 1000000;
        TokenType[] testTokens = {
            TokenType.VAR, TokenType.IF, TokenType.FOR, TokenType.FUNCTION,
            TokenType.NUMBER, TokenType.TEXT, TokenType.IDENTIFIER,
            TokenType.PLUS, TokenType.LPAREN, TokenType.COMMENT
        };
        
        System.out.println("\nBenchmarking getDefaultColorForToken():");
        System.out.println("Iterations: " + iterations);
        System.out.println("Test tokens: " + testTokens.length);
        System.out.println();
        
        // Test TokenColor (editor format)
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (TokenType token : testTokens) {
                String color = TokenColor.getDefaultColorForToken(token);
            }
        }
        long editorTime = System.nanoTime() - start;
        
        // Test HtmlTokenColor
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (TokenType token : testTokens) {
                String color = HtmlTokenColor.getDefaultColorForToken(token);
            }
        }
        long htmlTime = System.nanoTime() - start;
        
        // Test XmlTokenColor
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (TokenType token : testTokens) {
                String color = XmlTokenColor.getDefaultColorForToken(token);
            }
        }
        long xmlTime = System.nanoTime() - start;
        
        // Test CssTokenColor
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (TokenType token : testTokens) {
                String color = CssTokenColor.getDefaultColorForToken(token);
            }
        }
        long cssTime = System.nanoTime() - start;
        
        // Test MdTokenColor
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (TokenType token : testTokens) {
                String color = MdTokenColor.getDefaultColorForToken(token);
            }
        }
        long mdTime = System.nanoTime() - start;
        
        // Calculate and display results
        long totalOps = (long)iterations * testTokens.length;
        
        System.out.println("Results (total operations: " + totalOps + "):");
        System.out.println("  TokenColor (editor):  " + formatTime(editorTime) + " ms  (" + formatOpsPerSec(totalOps, editorTime) + " ops/sec)");
        System.out.println("  HtmlTokenColor:       " + formatTime(htmlTime) + " ms  (" + formatOpsPerSec(totalOps, htmlTime) + " ops/sec)");
        System.out.println("  XmlTokenColor:        " + formatTime(xmlTime) + " ms  (" + formatOpsPerSec(totalOps, xmlTime) + " ops/sec)");
        System.out.println("  CssTokenColor:        " + formatTime(cssTime) + " ms  (" + formatOpsPerSec(totalOps, cssTime) + " ops/sec)");
        System.out.println("  MdTokenColor:         " + formatTime(mdTime) + " ms  (" + formatOpsPerSec(totalOps, mdTime) + " ops/sec)");
        
        System.out.println("\n✓ Performance test complete!");
        System.out.println("\nOptimizations applied:");
        System.out.println("  ✓ EnumMap for O(1) token lookups (vs HashMap)");
        System.out.println("  ✓ Direct color value cache (single lookup instead of two)");
        System.out.println("  ✓ Unmodifiable map wrappers (no copying overhead)");
        System.out.println("  ✓ Eliminated getOrDefault() object allocations");
    }
    
    private static String formatTime(long nanos) {
        return String.format("%.2f", nanos / 1_000_000.0);
    }
    
    private static String formatOpsPerSec(long ops, long nanos) {
        double opsPerSec = (ops * 1_000_000_000.0) / nanos;
        if (opsPerSec >= 1_000_000) {
            return String.format("%.2fM", opsPerSec / 1_000_000);
        } else if (opsPerSec >= 1_000) {
            return String.format("%.2fK", opsPerSec / 1_000);
        } else {
            return String.format("%.2f", opsPerSec);
        }
    }
}
