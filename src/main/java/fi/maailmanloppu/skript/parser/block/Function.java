package fi.maailmanloppu.skript.parser.block;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.Type;

/**
 * Base of functions.
 * @author bensku
 *
 */
public interface Function extends CodeBlock {
    
    /**
     * Calls function. Remember, calling functions from Java is usually bad
     * idea, since it is pretty slow.
     * @param args Argument(s) for function
     * @return Result(s) from function
     */
    public Optional<Object> call(Object args);
    
    /**
     * Gets name of this function
     * @return Name of function or empty string
     */
    public String getName();
    
    public List<Type> getParamTypes();
    
    public Type getReturnType();
}
