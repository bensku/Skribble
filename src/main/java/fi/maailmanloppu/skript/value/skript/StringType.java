package fi.maailmanloppu.skript.value.skript;

import java.util.List;
import java.util.Optional;

import fi.maailmanloppu.skript.value.ValueType;

/**
 * String value type. Translates into Java String.
 * @author bensku
 *
 */
public class StringType implements ValueType {

    @Override
    public boolean accepts(String code) {
        if (code.startsWith("\"")) return true;
        return false;
    }

    @Override
    public Optional<Object> parseValue(String code) {
        return Optional.ofNullable(code.replace("\"", "")); //TODO what about escaping as \"?
    }

    @Override
    public List<Object> parseMultiValue(String code) {
        // TODO Auto-generated method stub
        return null;
    }

}
