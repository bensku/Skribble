package fi.maailmanloppu.skript.parser.skript.processor;

import java.util.Optional;

import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.skript.EffectProcessor;
import fi.maailmanloppu.skript.util.LineParser;

public class SetVarProcessor implements EffectProcessor {

    @Override
    public Optional<CallTask> check(String line) {
        if (line.startsWith("set")) {
            LineParser parser = new LineParser(line);
            String varId = parser.findParts("set", "to");
            String newValue = parser.findParts("to", ""); //From "to" to end
            
            
        }
        return null;
    }

}
