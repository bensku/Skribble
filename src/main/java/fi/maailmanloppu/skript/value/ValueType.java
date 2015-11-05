package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

/**
 * Type of a value, which can be defined using a script. Contains parsing for that type of value
 * (returning Object).
 * @author bensku
 *
 */
public interface ValueType {
    
    /**
     * Checks if this is correct parser for the argument. If parsing multi value,
     * pass the first line, that <i>should</i> be enough to identify.
     * @param code Source code part
     * @return True if value can be accepted, false otherwise.
     */
    boolean accepts(String code);
    
    /**
     * Parses a simple value to an object.
     * @param code Source code part
     * @return Parsed value, or empty optional
     */
    Optional<Object> parseValue(String code);
    
    List<Object> parseMultiValue(String code);
}
