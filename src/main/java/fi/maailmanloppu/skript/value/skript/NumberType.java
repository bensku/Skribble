package fi.maailmanloppu.skript.value.skript;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ValueType;

public class NumberType implements ValueType {

    @Override
    public boolean accepts(String code) {
        try {
            Double.valueOf(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Object> parseValue(String code) {
        if (code.contains(".")) {
            return Optional.of(Double.valueOf(code));
        } else {
            return Optional.of(Integer.valueOf(code));
        }
    }

    @Override
    public List<Object> parseMultiValue(String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitMethod(MethodVisitor mv, Object value) {
        new MethodUtils(mv).putToStack(value);
    }

    @Override
    public boolean canVisit(Object obj) {
        return ((obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double));
    }

}
