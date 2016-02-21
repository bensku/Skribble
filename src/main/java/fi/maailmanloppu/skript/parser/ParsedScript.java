package fi.maailmanloppu.skript.parser;

import java.util.List;

import fi.maailmanloppu.skript.parser.block.Function;

/**
 * Parsed script.
 * @author bensku
 *
 */
public interface ParsedScript {
    
    /**
     * Gets list of all functions.
     * @return List of all functions
     */
    List<Function> getFunctions();
    
    /**
     * Gets functions with specific name.
     * @return List of functions with given name, or empty list if none is found
     */
    List<Function> getFunctions(String name);
}
