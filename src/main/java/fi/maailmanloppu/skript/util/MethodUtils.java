package fi.maailmanloppu.skript.util;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Bytecode method utils
 * @author bensku
 *
 */
public class MethodUtils implements Opcodes {
    
    private MethodVisitor mv;
    
    public MethodUtils(MethodVisitor mv) {
        this.mv = mv;
    }
    
    /**
     * Put a value to stack.
     * @param obj
     */
    public void putToStack(Object obj) {
        if (obj instanceof Integer) {
            mv.visitLdcInsn(obj);
        } else if (obj instanceof Float) {
            mv.visitLdcInsn(obj);
        } else if (obj instanceof Long) {
            mv.visitLdcInsn(obj);
        } else if (obj instanceof Double) {
            mv.visitLdcInsn(obj);
        } else if (obj instanceof String) {
            mv.visitLdcInsn(obj);
        } else if (obj instanceof Handle) {
            mv.visitLdcInsn(obj);
        } else {
            throw new IllegalArgumentException("Cannot put value to stack!");
        }
    }
}
