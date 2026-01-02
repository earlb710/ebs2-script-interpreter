package com.eb.script.console;

import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import java.util.List;

/**
 * Enhanced StyleClassedTextArea with line numbers, syntax highlighting,
 * and custom undo/redo functionality.
 *
 * @author Earl Bosch
 */
public class ScriptArea extends StyleClassedTextArea {

    private boolean showLineNumbers = true; // default: on

    public ScriptArea() {
        setParagraphGraphicFactory(LineNumberFactory.get(this)); // initial state ON
    }

    /**
     * Toggle line numbers on/off.
     */
    public void setShowLineNumbers(boolean show) {
        this.showLineNumbers = show;
        if (show) {
            setParagraphGraphicFactory(LineNumberFactory.get(this));
        } else {
            setParagraphGraphicFactory(null);
        }
    }

    /**
     * Current state for line numbers.
     */
    public boolean isShowLineNumbers() {
        return showLineNumbers;
    }

    /**
     * Convenience toggle.
     */
    public void toggleLineNumbers() {
        setShowLineNumbers(!showLineNumbers);
    }

    public void print(String line) {
        javafx.application.Platform.runLater(() -> {
            printStyled(line, "info");
        });
    }

    public void println(String line) {
        javafx.application.Platform.runLater(() -> {
            printStyled(line + "\n", "info");
        });
    }

    public void printlnInfo(String s) {
        printStyled(s + "\n", "info");
    }

    public void printlnWarn(String s) {
        printStyled(s + "\n", "warn");
    }

    public void printlnError(String s) {
        printStyled(s + "\n", "error");
    }

    public void printlnOk(String s) {
        printStyled(s + "\n", "ok");
    }

    public void printStyled(String text, String... styleClasses) {
        printStyled(text, java.util.Arrays.asList(styleClasses));
    }

    public void printStyled(String text, List<String> styleClasses) {
        javafx.application.Platform.runLater(() -> {
            int start = this.getLength();
            this.appendText(text);
            int end = start + text.length();

            if (styleClasses == null || styleClasses.isEmpty()) {
                this.setStyleClass(start, end, "info");
            } else if (styleClasses.size() == 1) {
                String s = styleClasses.get(0);
                this.setStyleClass(start, end, s);
            } else {
                this.setStyle(start, end, styleClasses); // multi-class API
            }

            this.moveTo(this.getLength());
            this.requestFollowCaret();
        });
    }

    public void addStyleToRange(int start, int endExclusive, String styleClass) {
        int actualStart = Math.max(0, start);
        int actualEnd = Math.min(getLength(), endExclusive);
        
        if (actualStart >= actualEnd) {
            return;
        }
        
        org.fxmisc.richtext.model.StyleSpans<java.util.Collection<String>> existingSpans = 
            getStyleSpans(actualStart, actualEnd);
        
        org.fxmisc.richtext.model.StyleSpansBuilder<java.util.Collection<String>> builder = 
            new org.fxmisc.richtext.model.StyleSpansBuilder<>();
        
        for (org.fxmisc.richtext.model.StyleSpan<java.util.Collection<String>> span : existingSpans) {
            java.util.Collection<String> existingStyles = span.getStyle();
            java.util.List<String> newStyles = new java.util.ArrayList<>(existingStyles);
            if (!newStyles.contains(styleClass)) {
                newStyles.add(styleClass);
            }
            builder.add(newStyles, span.getLength());
        }
        
        setStyleSpans(actualStart, builder.create());
    }

    public void removeStyleFromRange(int start, int endExclusive, String styleClass) {
        int actualStart = Math.max(0, start);
        int actualEnd = Math.min(getLength(), endExclusive);
        
        if (actualStart >= actualEnd) {
            return;
        }
        
        org.fxmisc.richtext.model.StyleSpans<java.util.Collection<String>> existingSpans = 
            getStyleSpans(actualStart, actualEnd);
        
        org.fxmisc.richtext.model.StyleSpansBuilder<java.util.Collection<String>> builder = 
            new org.fxmisc.richtext.model.StyleSpansBuilder<>();
        
        for (org.fxmisc.richtext.model.StyleSpan<java.util.Collection<String>> span : existingSpans) {
            java.util.Collection<String> existingStyles = span.getStyle();
            java.util.List<String> newStyles = new java.util.ArrayList<>(existingStyles);
            newStyles.removeIf(c -> c.equals(styleClass));
            builder.add(newStyles, span.getLength());
        }
        
        setStyleSpans(actualStart, builder.create());
    }
}
