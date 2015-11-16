package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

/**
 * Values parser, which takes value declarations as Strings and creates
 * objects for them. Usually, this is done by getting correct {@link ValueType}
 * and using it to do actual parsing.
 * @author bensku
 *
 */
public interface ValueParser {
    
    /**
     * Gets type of the given value (for parsing).
     * @param code Source code part
     * @return Type, or empty optional if corresponding one is not found
     */
    Optional<ValueType> getValueType(String code);
    
}
