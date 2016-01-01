package fi.maailmanloppu.skript.parser.skript;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.Skript;
import fi.maailmanloppu.skript.parser.CodeFunction;
import fi.maailmanloppu.skript.parser.Function;
import fi.maailmanloppu.skript.parser.ParsedScript;
import fi.maailmanloppu.skript.parser.Parser;
import fi.maailmanloppu.skript.parser.SimpleScript;
import fi.maailmanloppu.skript.parser.skript.event.EventFunction;
import fi.maailmanloppu.skript.parser.skript.event.EventProvider;

/**
 * Parser for Skript format scripts.
 * @author bensku
 *
 */
public class SkriptParser implements Parser {
    
    private EventProvider eventProvider;
    
    /**
     * Constructs new Skript parser.
     * @param eventProvider Event provider, usually from an instance of {@link Skript}
     */
    public SkriptParser(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }
    
    @Override
    public ParsedScript parseScript(List<String> code) {
        List<Function> functions = new ArrayList<Function>();
        List<String> currentFunc = new ArrayList<String>();
        
        for (int i = 0; i < code.size(); i++) {
            String line = code.get(i);
            if (line.startsWith("on")) {
                //New function is starting here!
                if (!currentFunc.isEmpty()) {
                    functions.add(parseFunction(currentFunc));
                }
                currentFunc = new ArrayList<String>();
            }
            currentFunc.add(line);
        }
        
        return new SimpleScript(functions);
    }

    @Override
    public Function parseFunction(List<String> code) {
        String name = code.get(0);
        Optional<EventFunction> eventFunc = eventProvider.provide(name);
        
        code.remove(0); //TODO Check performance!
        CodeFunction func = null;
        if (eventFunc.isPresent()) {
            func = new CodeFunction(name, code, eventFunc.get().getParameters(), Type.VOID_TYPE); //Events won't return anything
        } else {
            throw new UnsupportedOperationException("Non event functions are not YET supported");
            //TODO Non event functions, use invokedynamic?
        }
        return func;
    }
}
