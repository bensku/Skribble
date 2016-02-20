package fi.maailmanloppu.skript.value.skript;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import fi.maailmanloppu.skript.parser.skript.annotation.TypeData;
import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ParseResult;
import fi.maailmanloppu.skript.value.SimpleType;
import fi.maailmanloppu.skript.value.ValueType;

/**
 * String/text value type. Translates into Java String.
 * @author bensku
 *
 */
@TypeData(pattern = "(text|string)[s]", start = "\"", end = "\"")
public class StringType extends SimpleType {

    @Override
    public ParseResult parseValue(String code) {
        Optional<String> str = findBetween(code, "\"", "\"");
        if (str.isPresent()) {
            return ParseResult.of(str.get(), str.get().length());
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
        return (obj instanceof String);
    }

}
