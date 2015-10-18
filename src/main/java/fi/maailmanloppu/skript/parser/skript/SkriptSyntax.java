package fi.maailmanloppu.skript.parser.skript;

import java.util.ArrayList;
import java.util.List;

import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.FunctionSyntax;

/**
 * Function syntax for Skript.
 * @author bensku
 *
 */
public class SkriptSyntax implements FunctionSyntax {
    
    private List<EffectProcessor> processors;
    
    /**
     * Get this from Skript plugin instance.
     */
    public SkriptSyntax() {
        this.processors = new ArrayList<EffectProcessor>();
    }
    
    @Override
    public List<CallTask> getTasks(String line) {
        return null; //TODO Implement this
    }

}
