package fi.maailmanloppu.skript.parser.skript;

import java.util.ArrayList;
import java.util.List;

import fi.maailmanloppu.skript.parser.CodeFunction;
import fi.maailmanloppu.skript.parser.Function;
import fi.maailmanloppu.skript.parser.ParsedScript;
import fi.maailmanloppu.skript.parser.Parser;

/**
 * Parser for Skript format scripts.
 * @author bensku
 *
 */
public class SkriptParser implements Parser {
    
    @Override
    public ParsedScript parseScript(List<String> code) {
        List<Function> functions = new ArrayList<Function>();
        List<String> currentFunc = null;
        
        for (int i = 0; i < code.size(); i++) {
            String line = code.get(i);
            if (line.startsWith("on")) {
                //New function is starting here!
                if (currentFunc != null) {
                    functions.add(parseFunction(currentFunc));
                }
                currentFunc = new ArrayList<String>();
            }
            currentFunc.add(line);
        }
        return null;
    }

    @Override
    public Function parseFunction(List<String> code) {
        String name = code.get(0);
        code.remove(0); //TODO Check performance!
        CodeFunction func = new CodeFunction(name, code);
        return func;
    }
}
