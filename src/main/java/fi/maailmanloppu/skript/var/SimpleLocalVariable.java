package fi.maailmanloppu.skript.var;

import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.value.ValueParser;
import fi.maailmanloppu.skript.value.ValueType;

public class SimpleLocalVariable implements LocalVariable, Opcodes {
    
    private String id;
    private ExecuteContext context;
    private ValueParser parser;
    
    public SimpleLocalVariable(String id, ExecuteContext context, ValueParser valueParser) {
        this.id = id;
        this.context = context;
        this.parser = valueParser;
    }
    
    @Override
    public void visitLoad(MethodVisitor mv) {
        int type = context.getVarType(id);
        
        int opcode = 0;
        switch (type) {
        case Type.VOID:
            //Special handling TODO
            break;
        case Type.BOOLEAN:
            opcode = ILOAD;
            break;
        case Type.CHAR:
            opcode = ILOAD;
            break;
        case Type.BYTE:
            opcode = ILOAD;
            break;
        case Type.SHORT:
            opcode = ILOAD;
            break;
        case Type.INT:
            opcode = ILOAD;
            break;
        case Type.FLOAT:
            opcode = FLOAD;
            break;
        case Type.LONG:
            opcode = LLOAD;
            break;
        case Type.DOUBLE:
            opcode = DLOAD;
            break;
        case Type.ARRAY:
            opcode = ALOAD;
            break;
        case Type.OBJECT:
            opcode = ALOAD;
            break;
        case Type.METHOD:
            throw new UnsupportedOperationException("METHOD type is not supported for local variables!");
        default:
            throw new UnsupportedOperationException("Unsupported type!");
        }
        
        int stack = context.getVarStack(id);
        
        mv.visitVarInsn(opcode, stack);
    }

    @Override
    public void visitStore(MethodVisitor mv, int type) {
        int opcode = 0;
        switch (type) {
        case Type.VOID:
            //Special handling TODO
            break;
        case Type.BOOLEAN:
            opcode = ISTORE;
            break;
        case Type.CHAR:
            opcode = ISTORE;
            break;
        case Type.BYTE:
            opcode = ISTORE;
            break;
        case Type.SHORT:
            opcode = ISTORE;
            break;
        case Type.INT:
            opcode = ISTORE;
            break;
        case Type.FLOAT:
            opcode = FSTORE;
            break;
        case Type.LONG:
            opcode = LSTORE;
            break;
        case Type.DOUBLE:
            opcode = DSTORE;
            break;
        case Type.ARRAY:
            opcode = ASTORE;
            break;
        case Type.OBJECT:
            opcode = ASTORE;
            break;
        case Type.METHOD:
            throw new UnsupportedOperationException("METHOD type is not supported for local variables!");
        default:
            throw new UnsupportedOperationException("Unsupported type!");
        }
        
        int stack = context.getVarStack(id);
        
        mv.visitVarInsn(opcode, stack);
        context.setVarType(id, type);
    }

    @Override
    public void visitSet(MethodVisitor mv, Object obj) {
        Optional<ValueType> type = parser.getVisitType(obj);
        
        if (!type.isPresent()) throw new UnsupportedOperationException("Given object is not of supported type.");
        
        type.get().visitMethod(mv, obj);
        visitStore(mv, type.get().getTypeGroup(obj));
    }

}
