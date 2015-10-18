package fi.maailmanloppu.skript.parser;

/**
 * Basic task, e.g. calling a function.
 * @author bensku
 *
 */
public interface CallTask {
    /**
     * Immediately executes this task.
     * @return Some kind of result...
     */
    Object execute();
}
