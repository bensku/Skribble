package fi.maailmanloppu.skript.value.skript;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ValueType;

public class NumberType implements ValueType {

    @Override
    public boolean accepts(String code) {
        try {
            NumberFormat.getInstance().parse(code);
        } catch (ParseException e) {
            return false;
        }
        
        return true;
    }

    @Override
    public Optional<Object> parseValue(String code) {
        try {
            return Optional.of(NumberFormat.getInstance().parse(code));
        } catch (ParseException e) {
            return Optional.empty();
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
    
    @Override
    public int getTypeGroup(Object obj) {
        if (obj instanceof Double) {
            return Type.DOUBLE;
        } else if (obj instanceof Float) {
            return Type.FLOAT;
        } else if (obj instanceof Long) {
            return Type.LONG;
        } else if (obj instanceof Integer) {
            return Type.INT;
        } else {
            return Type.OBJECT;
        }
    }
}
