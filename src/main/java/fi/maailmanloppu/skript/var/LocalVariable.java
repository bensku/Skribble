package fi.maailmanloppu.skript.var;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

/**
 * Represents a local variable, which has certain static type.
 * @author bensku
 *
 */
public interface LocalVariable {
    
    /**
     * Loads this variable to stack of given method.
     * @param mv Method visitor
     */
    void visitLoad(MethodVisitor mv);
    
    /**
     * Stores current stack to this variable. Needs to know
     * type of stack before.
     * @param mv Method visitor
     * @param type Type, Type.OBJECT for reference
     */
    void visitStore(MethodVisitor mv, int type);
    
    /**
     * Sets this variable to an object parameter.
     * @param mv Method visitor
     * @param obj
     */
    void visitSet(MethodVisitor mv, Object obj);
}
