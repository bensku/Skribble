package fi.maailmanloppu.skript.env;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class GenericEnvironment implements Environment, Opcodes {
    
    private Map<String,Object> variables;
    private Map<String,Class<?>> defines;
    
    public GenericEnvironment() {
        this.variables = new HashMap<String,Object>();
        this.defines = new HashMap<String,Class<?>>();
    }
    
    @Override
    public Optional<Object> getVariable(String id) {
        checkArgument(id != null && id != "");
        return Optional.ofNullable(variables.get(id));
    }

    @Override
    public boolean setVariable(String id, Object value) {
        checkArgument(id != null && id != "");
        variables.put(id, value);
        return true;
    }

    @Override
    public boolean define(String id, Class<?> type) {
        checkArgument(id != null && id != "");
        checkNotNull(type);
        
        if (defines.containsKey(id)) {
            return false;
        } else {
            defines.put(id, type);
            return true;
        }
    }

    @Override
    public boolean setToClass(ClassVisitor cw) {
        FieldVisitor fv;
        for (Entry<String, Class<?>> entry : defines.entrySet()) { //Set static fields
            String id = entry.getKey();
            Class<?> type = entry.getValue();
            
            fv = cw.visitField(ACC_PUBLIC, id, Type.getDescriptor(type), null, null);
            fv.visitEnd();
        }
        
        //Set dynamic variable storage
        fv = cw.visitField(ACC_PUBLIC, "variables", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;", null);
        
        return true;
    }

    @Override
    public String getVisitingName() {
        return null;
    }

    @Override
    public boolean hasField(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Type getFieldType(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
