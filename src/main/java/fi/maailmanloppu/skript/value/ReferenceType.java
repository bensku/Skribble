package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

/**
 * Value type for values which are actually references to variables. This is a hack, but makes so many things easier.
 * @author bensku
 *
 */
public class ReferenceType implements ValueType {

    @Override
    public boolean accepts(String code) { //Per scripting language: override and check here
        return false;
    }

    @Override
    public Optional<Object> parseValue(String code) {
        return Optional.of(code); //Keep original
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

}
