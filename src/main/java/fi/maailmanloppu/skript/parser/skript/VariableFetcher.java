package fi.maailmanloppu.skript.parser.skript;

import java.util.List;

import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.parser.VariableType;

/**
 * Used to fetch variable names from Skript's syntax.
 * @author bensku
 *
 */
public class VariableFetcher {
    
    private String ref;
    private List<String> locals;

    
    /**
     * Creates new variable fetcher instance.
     * @param ref Reference, which needs parsing
     * @param locals List of cleaned variable names which are always locals
     */
    public VariableFetcher(String ref, List<String> locals) {
        this.ref = ref;
        this.locals = locals;
    }
    
    public VariableFetcher(String ref, ExecuteContext context) {
        this(ref, context.getLocals());
    }
    
    public String getCleanName() {
        String clean = null;
        if (ref.startsWith("{") || ref.startsWith("%")) {
            clean = ref.replace("{", "").replace("}", "").replace("%", "");
    	}
        clean = clean.replace(" ", "_");
        
    	return clean;
    }
    
    /**
     * Checks the variable type.
     * @return Type of variable
     */
    public VariableType getType() {
        String clean = getCleanName();
        
        if (clean.startsWith("_") || locals.contains(clean)) {
            return VariableType.LOCAL;
        }
        
        return VariableType.GLOBAL;
    }
}
