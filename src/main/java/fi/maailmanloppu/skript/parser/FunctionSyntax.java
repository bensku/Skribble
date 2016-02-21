package fi.maailmanloppu.skript.parser;

import java.util.List;

import fi.maailmanloppu.skript.parser.block.CodeBlock;


/**
 * Base syntax of function.
 * @author bensku
 *
 */
public interface FunctionSyntax {
    /**
     * Gets all tasks for this line.
     * @param line
     * @return
     */
    List<CallTask> getTasks(String line);
    
    /**
     * Parses a block of code from given lines.
     * @param lines
     * @return
     */
    CodeBlock parse(List<String> lines);
}
