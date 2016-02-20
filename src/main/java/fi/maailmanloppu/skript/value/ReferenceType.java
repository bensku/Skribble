package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import fi.maailmanloppu.skript.env.Environment;
import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.parser.skript.VariableFetcher;
import fi.maailmanloppu.skript.util.MethodUtils;

/**
 * Value type for values which are actually references to variables.
 * This is a hack, but makes so many things easier.
 * @author bensku
 *
 */
public class ReferenceType implements ValueType, Opcodes {

    @Override
    public boolean accepts(String code) { //Per scripting language: override and check here
        return false;
    }

    @Override
    public ParseResult parseValue(String code) {
        return ParseResult.of(code, code.length()); //Keep original; override if needed
    }

    @Override
    public List<Object> parseMultiValue(String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitMethod(MethodVisitor mv, Object value) {
        //This simply does NOT work
    }
    
    @Override
    public void visitMethod(MethodVisitor mv, Object value, ExecuteContext context) {
        String varId = (String) value;
        VariableFetcher varFetcher = new VariableFetcher(varId, context);
        String cleanName = varFetcher.getCleanName();
        MethodUtils utils = new MethodUtils(mv);
        Environment env = context.getEnvironment();
        
        switch (varFetcher.getType()) {
        case GLOBAL: //Get from map
            mv.visitFieldInsn(GETFIELD, "fi/maailmanloppu/skript/env/GenericEnvironment", "env", "Ljava/util/Map;");
            utils.putToStack(cleanName);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        case LOCAL:
            
        }
    }

    @Override
    public boolean canVisit(Object obj) {
        return false; //Since you can already provide the name as string...
    }

    @Override
    public boolean matches(String id) {
        return true; //Reference type has to match, always... TODO Checking must be done elsewhere
    }

}
