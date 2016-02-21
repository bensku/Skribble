package fi.maailmanloppu.skript.parser.skript;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.FunctionSyntax;
import fi.maailmanloppu.skript.parser.block.CodeBlock;

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

    @Override
    public CodeBlock parse(List<String> lines) {
        char indentChar = ' ';
        int indentMultip = 4;
        List<Integer> indents = new ArrayList<Integer>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            
            int indent = 0;
            for (char c : line.toCharArray()) {
                if (c != indentChar) break;
                indent++;
            }
            indents.add(indent);
        }
        
        return null;
    }
}
