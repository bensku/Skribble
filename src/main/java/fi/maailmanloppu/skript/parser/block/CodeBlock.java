package fi.maailmanloppu.skript.parser.block;

import java.util.List;

/**
 * Represents a block of code, usually a function or conditional (if) statement.
 * @author bensku
 *
 */
public interface CodeBlock {
    
    /**
     * Gets children blocks of this block. Mostly for conditional statements
     * inside functions or other statements...
     * @return
     */
    List<CodeBlock> getChildren();
}
