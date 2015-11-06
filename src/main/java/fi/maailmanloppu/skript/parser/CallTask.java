package fi.maailmanloppu.skript.parser;

import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import fi.maailmanloppu.skript.env.ExecuteContext;

/**
 * Basic task, e.g. calling a function. Some languages only need few, while
 * others may need one for every function provided from Java. Neither approach is
 * necessarily bad.
 * @author bensku
 *
 */
public interface CallTask {
    /**
     * Immediately executes this task.
     * @param context Execution context
     * @return Some kind of result, or empty optional
     */
    Optional<Object> execute(ExecuteContext context);
    
    /**
     * Visits a method, adding this task to it in compiled form.
     * @param mv Method visitor
     */
    void visitMethod(MethodVisitor mv, ExecuteContext context);
}
