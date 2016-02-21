package fi.maailmanloppu.skript.parser;

import java.util.ArrayList;
import java.util.List;

import fi.maailmanloppu.skript.parser.block.Function;

/**
 * Simple parsed script.
 * @author bensku
 *
 */
public class SimpleScript implements ParsedScript {
    
    private List<Function> functions;
    
    public SimpleScript(List<Function> functions) {
        this.functions = functions;
    }
    
    @Override
    public List<Function> getFunctions() {
        return new ArrayList<Function>(functions);
    }

    @Override
    public List<Function> getFunctions(String name) {
        List<Function> nameCorrect = new ArrayList<Function>();
        for (Function func : functions) {
            if (func.getName().equals(name)) {
                nameCorrect.add(func);
            }
        }
        
        return nameCorrect;
    }

}
