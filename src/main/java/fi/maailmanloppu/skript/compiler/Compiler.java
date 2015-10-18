package fi.maailmanloppu.skript.compiler;

import java.util.List;

/**
 * Abstract script compiler interface.
 * @author bensku
 *
 */
public interface Compiler {
    void compile(List<String> code);
    
    
}