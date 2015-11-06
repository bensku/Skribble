package fi.maailmanloppu.skript.value.skript;

import java.util.List;
import java.util.Optional;

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

}
