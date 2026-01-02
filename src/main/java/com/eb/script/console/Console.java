package com.eb.script.console;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Console with styled output and a multiline, styled input area.
 *
 * @author Earl Bosch
 */
public final class Console {

    private ScriptArea outputArea;
    private ScriptArea inputArea;
    private final Tab consoleTab;
    private final List<String> history = new ArrayList<>();
    private int historyIndex = -1;
    private final Handler handler;

    public Console(Handler handler) {
        this.handler = handler;
        consoleTab = buildConsoleTab();
        this.handler.setUI_outputArea(outputArea);
        hookSystemStreams();
    }

    public Tab getConsoleTab() {
        return consoleTab;
    }

    public ScriptArea getOutputArea() {
        return outputArea;
    }

    public void requestFocus() {
        Platform.runLater(() -> inputArea.requestFocus());
    }

    /**
     * Redirect System.out and System.err to the TextArea.
     */
    public void hookSystemStreams() {
        try {
            System.setOut(new java.io.PrintStream(new StyledTextAreaOutputStream(outputArea, "info"), true, "UTF-8"));
            System.setErr(new java.io.PrintStream(new StyledTextAreaOutputStream(outputArea, "error"), true, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            printlnError("Error setting up system streams: " + ex.getMessage());
        }
    }

    private Tab buildConsoleTab() {
        // ---- Output area ----
        outputArea = new ScriptArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(false);
        outputArea.getStyleClass().add("console-out");

        var outputScroller = new VirtualizedScrollPane<>(outputArea);
        outputScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        outputScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        StackPane outputFrame = new StackPane(outputScroller);
        outputFrame.getStyleClass().addAll("console-frame", "bevel-lowered");
        outputFrame.setPadding(new Insets(0));

        // ---- Input area ----
        inputArea = new ScriptArea();
        inputArea.getStyleClass().clear();
        inputArea.getStyleClass().add("console-in");
        inputArea.setWrapText(true);
        inputArea.setUseInitialStyleForInsertion(true);
        inputArea.replaceText("");

        var inputScroller = new VirtualizedScrollPane<>(inputArea);
        inputScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        inputScroller.setPrefHeight(90);

        StackPane inputFrame = new StackPane(inputScroller);
        inputFrame.getStyleClass().addAll("console-frame", "bevel-lowered");
        inputFrame.setPadding(new Insets(0));
        HBox.setHgrow(inputFrame, Priority.ALWAYS);

        // Buttons
        Button btnClear = new Button(" Clear ");
        btnClear.setOnAction(e -> outputArea.clear());
        
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> submitInputBuffer());

        HBox bottom = new HBox(5, inputFrame, new VBox(5, btnClear, btnSubmit));
        bottom.setPadding(new Insets(3));
        inputEvents();
        
        BorderPane content = new BorderPane(outputFrame);
        content.setBottom(bottom);

        Tab t = new Tab("Console", content);
        t.setId("consoleTab");
        t.setClosable(false);
        t.setOnCloseRequest(ev -> ev.consume());
        return t;
    }

    private void inputEvents() {
        inputArea.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER && e.isControlDown()) {
                submitInputBuffer();
                e.consume();
                return;
            }

            // History: Ctrl+Up / Ctrl+Down
            if (e.isControlDown() && e.getCode() == KeyCode.UP) {
                if (!history.isEmpty()) {
                    if (historyIndex < 0) {
                        historyIndex = history.size() - 1;
                    } else if (historyIndex > 0) {
                        historyIndex--;
                    }
                    inputArea.clear();
                    inputArea.replaceText(history.get(historyIndex));
                    inputArea.moveTo(inputArea.getLength());
                }
                e.consume();
                return;
            }

            if (e.isControlDown() && e.getCode() == KeyCode.DOWN) {
                if (!history.isEmpty()) {
                    if (historyIndex >= 0 && historyIndex < history.size() - 1) {
                        historyIndex++;
                        inputArea.clear();
                        inputArea.replaceText(history.get(historyIndex));
                    } else {
                        historyIndex = -1;
                        inputArea.clear();
                    }
                    inputArea.moveTo(inputArea.getLength());
                }
                e.consume();
                return;
            }
        });
    }

    private void submitInputBuffer() {
        String block = inputArea.getText();
        if (block == null) {
            block = "";
        }
        try {
            // de-duplicate last history entry
            if (history.isEmpty() || !history.get(history.size() - 1).trim().equals(block.trim())) {
                history.add(block);
            }
            historyIndex = -1;

            // split into lines for the varargs handler
            String[] lines = block.replace("\r\n", "\n").split("\n", -1);
            handler.submit(lines);
            inputArea.clear();
        } catch (Exception ex) {
            printlnWarn("Submitted : " + (block.length() > 80 ? block.substring(0, 78) + "…" : block));
            printlnError("Error: " + ex.getMessage());
        }
    }

    public void submit(String... lines) throws Exception {
        handler.submit(lines);
    }

    // --- Output helpers ---
    public void println(String line) {
        outputArea.println(line);
    }

    public void printlnInfo(String s) {
        outputArea.printlnInfo(s);
    }

    public void printlnWarn(String s) {
        outputArea.printlnWarn(s);
    }

    public void printlnError(String s) {
        outputArea.printlnError(s);
    }

    public void printlnOk(String s) {
        outputArea.printlnOk(s);
    }

    void clear() {
        Platform.runLater(() -> {
            outputArea.clear();
            inputArea.clear();
        });
    }
}
