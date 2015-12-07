package fi.maailmanloppu.skript.parser.skript.event;

import java.util.List;

import org.objectweb.asm.Type;

/**
 * The event representation of an Skript function. We need this, since Skript's syntax
 * doesn't have event annotations or their parameter types...
 * @author bensku
 *
 */
public interface EventFunction {
    
    /**
     * Gets list of parameters used in this event's function.
     * @return A list of parameter types.
     */
    List<Type> getParameters();
}
