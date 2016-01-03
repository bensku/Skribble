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
    
    /**
     * Gets type, which is able to write given object to stack in bytecode.
     * @param obj Any object
     * @return Type, or empty optional if no type can handle object
     */
    Optional<ValueType> getVisitType(Object obj);
}
