package fi.maailmanloppu.skript.var;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.env.ExecuteContext;

public class SimpleLocalVariable implements LocalVariable {
    
    private String name;
    private ExecuteContext context;
    
    public SimpleLocalVariable(String name, ExecuteContext context) {
        this.name = name;
        this.context = context;
    }
    
    @Override
    public void visitLoad(MethodVisitor mv) {
        
    }

    @Override
    public void visitStore(MethodVisitor mv) {
        
    }

    @Override
    public void visitSet(MethodVisitor mv, Object obj) {
        
    }

}
