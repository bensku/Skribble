package fi.maailmanloppu.skript.value;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

/**
 * A simple value parser, which calls for {@link ValueType}s.
 * @author bensku
 *
 */
public class SimpleValueParser implements ValueParser {
    
    private List<ValueType> valueTypes;
    
    public SimpleValueParser() {
        
    }
    
    public void addValueType(ValueType type) {
        valueTypes.add(type);
    }

    @Override
    public Optional<Object> parseValue(String code) {
        for (ValueType type : valueTypes) {
            if (type.accepts(code)) {
                return Optional.of(type.parseValue(code));
            }
        }
        
        return Optional.empty();
    }

    @Override
    public List<Object> parseMultiValue(String code) {
        //TODO Multi value support
        return null;
    }

    @Override
    public void visitMethod(MethodVisitor mv, Object value) {
        // TODO Auto-generated method stub
        
    }
    
    
}
