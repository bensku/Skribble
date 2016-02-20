package fi.maailmanloppu.skript.value.skript;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.Skript;
import fi.maailmanloppu.skript.parser.skript.annotation.TypeData;
import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ParseResult;
import fi.maailmanloppu.skript.value.SimpleType;
import fi.maailmanloppu.skript.value.ValueType;

@TypeData(pattern = "num[ber][s]")
public class NumberType extends SimpleType {

    @Override
    public boolean accepts(String code) {
        return Character.isDigit(code.charAt(0));
    }

    @Override
    public ParseResult parseValue(String code) {
        String[] numbers = code.replaceAll("[^-?0-9]+", " ").split(" ");
        if (numbers.length > 0) {
            try {
                return ParseResult.of(NumberFormat.getInstance().parse(numbers[0]), numbers[0].length());
            } catch (ParseException e) {
                Skript.getPlugin().getLogger().warn("Could not parse number " + numbers[0]);
            }
        }
        
        return ParseResult.empty();
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
