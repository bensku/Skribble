package fi.maailmanloppu.skript.env;

import java.util.Optional;

import org.objectweb.asm.ClassVisitor;

/**
 * Script environment which stores variables and defines fields. Variables are
 * dynamic, meaning that they can be added/removed after compilation and can hold
 * everything. Field are NOT dynamic, but faster.
 * @author bensku
 *
 */
public interface Environment {
    /**
     * Gets variable with given id if it exists.
     * @param id Identifier for variable, as string
     * @return Variable or empty optional
     */
    Optional<Object> getVariable(String id);
    
    /**
     * Sets variable with given id to given value.
     * @param id Identifier of variable, as string
     * @param value New value
     * @return True in case of success, false otherwise
     */
    boolean setVariable(String id, Object value);
    
    /**
     * Defines a static field to this environment. If you want to define
     * a primitive type, use object of it; it will be converted
     * to a real, fast primitive when creating the class.
     * @param id Identifier of field
     * @param type
     * @return True in case of success, false otherwise
     */
    boolean define(String id, Class<?> type);
    
    /**
     * Adds all known static fields to a class.
     * @param visitor Class visitor
     * @return True if adding succeeded, false otherwise
     */
    boolean setToClass(ClassVisitor cw);
}
