package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

/**
 * Values parser, which takes value declarations as Strings and creates
 * objects for them. Usually, this is done by getting correct {@link ValueType}
 * and using it to do actual parsing.
 * @author bensku
 *
 */
public interface ValueParser {
    
    /**
     * Parses a simple value to an object.
     * @param code Source code part
     * @return Parsed value, or empty optional
     */
    Optional<Object> parseValue(String code);
    
    List<Object> parseMultiValue(String code);
}
