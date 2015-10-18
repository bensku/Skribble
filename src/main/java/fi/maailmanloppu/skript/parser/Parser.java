package fi.maailmanloppu.skript.parser;

import java.util.List;

/**
 * Abstract script parser...
 * @author bensku
 *
 */
public interface Parser {
    /**
     * Does top level parsing for script provided.
     * @param code Source code, as list of strings
     */
    ParsedScript parseScript(List<String> code);
    
    /**
     * Does function (or event handler) level parsing.
     * @param code Source code, as list of strings
     */
    Function parseFunction(List<String> code);
}
