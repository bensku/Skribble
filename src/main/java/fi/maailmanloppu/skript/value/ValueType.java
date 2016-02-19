package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.parser.skript.annotation.TypeData;
import fi.maailmanloppu.skript.util.SkriptPattern;

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
     * @return True if value can be accepted, false otherwise
     */
    boolean accepts(String code);
    
    /**
     * Checks if this parser can write given value into bytecode using
     * visitMethod. Naturally, actual parsing for object is not needed.
     * @param obj Object needed in stack
     * @return True if object can be put to stack, false otherwise
     */
    boolean canVisit(Object obj);
    
    /**
     * Parses a simple value to an object.
     * @param code Source code part
     * @return Parsed value, or empty optional
     */
    Optional<Object> parseValue(String code);
    
    List<Object> parseMultiValue(String code);
    
    /**
     * Puts the value to stack of the method. Can't handle variable references.
     * @param mv Method visitor
     * @param value Value of this type
     */
    void visitMethod(MethodVisitor mv, Object value);
    
    /**
     * Puts value to stack of the method. If needed, handles variable
     * references.
     * @param mv Method visitor
     * @param value Value of this type
     * @param context Execute context, for handling variable references
     */
    default void visitMethod(MethodVisitor mv, Object value, ExecuteContext context) {
        this.visitMethod(mv, value); //ReferenceType overrides this
    }
    
    /**
     * Gets type that is needed for fast local variables. By default, returns
     * Type.OBJECT which can be used for everything but may
     * not give best performance.
     * @return Type number, as in Type class
     */
    default int getTypeGroup(Object obj) {
        return Type.OBJECT;
    }
    
    /**
     * Check if this is correct type for given identifier. By default,
     * always returns false. Implementation/scripting language specific.
     * @return True, if given type id is correct.
     */
    boolean matches(String id);
    
}
