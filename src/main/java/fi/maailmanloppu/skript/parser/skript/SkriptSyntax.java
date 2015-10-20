package fi.maailmanloppu.skript.parser.skript;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<CallTask> taskList = new ArrayList<CallTask>();
        for (EffectProcessor processor : processors) { //Pass line to all effect processors
            Optional<CallTask> task = processor.check(line);
            if (task.isPresent()) taskList.add(task.get());
        }
        
        return taskList;
    }
    
    /**
     * Adds effect processor to use list. Please do this before
     * loading any scripts!
     * @param processor
     */
    public void addEffectProcessor(EffectProcessor processor) {
        processors.add(processor);
    }
}
