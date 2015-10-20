package fi.maailmanloppu.skript.parser.skript.processor;

import java.util.Optional;

import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.skript.EffectProcessor;

public class SetVarProcessor implements EffectProcessor {

    @Override
    public Optional<CallTask> check(String line) {
        if (line.startsWith("set")) {
            String[] parts = line.split(" ");
            byte targetEnd; //set TARGET to VALUE
            byte valueStart;
            for (byte i = 0; i < parts.length; i++) {
                String part = parts[i];
                switch(part) {
                case "to":
                    targetEnd = (byte) (i - 1);
                }
            }
        }
        return null;
    }

}
