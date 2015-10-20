package fi.maailmanloppu.skript.parser;

/**
 * Basic task, e.g. calling a function. Some languages only need few, while
 * others may need one for every function provided from Java. Neither approach is
 * necessarily bad.
 * @author bensku
 *
 */
public interface CallTask {
    /**
     * Immediately executes this task.
     * @return Some kind of result
     */
    Object execute();
}
