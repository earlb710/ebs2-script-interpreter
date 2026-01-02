package com.eb.script.console;

/**
 * Interface for console command handling.
 * 
 * @author Earl Bosch
 */
public interface Handler {

    /**
     * Submit lines of input to the handler for processing.
     * @param lines One or more lines of input
     * @throws Exception if processing fails
     */
    public void submit(String... lines) throws Exception;

    /**
     * Submit error messages to the handler.
     * @param lines One or more error message lines
     */
    public void submitErrors(String... lines);

    /**
     * Call a builtin function.
     * @param builtin The builtin function name
     * @param args Arguments to pass to the builtin
     * @return The result of the builtin call
     * @throws Exception if the call fails
     */
    public Object callBuiltin(String builtin, Object... args) throws Exception;

    /**
     * Set the output area for console messages.
     * @param outputArea The ScriptArea to use for output
     */
    public void setUI_outputArea(ScriptArea outputArea);
}
