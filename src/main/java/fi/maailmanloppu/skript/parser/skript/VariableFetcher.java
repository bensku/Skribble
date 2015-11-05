package fi.maailmanloppu.skript.parser.skript;

import java.util.List;

import fi.maailmanloppu.skript.parser.VariableType;

/**
 * Used to fetch variable names from Skript's syntax.
 * @author bensku
 *
 */
public class VariableFetcher {
    
    private String ref;
    private List<String> locals;
    private List<String> params;

    
    /**
     * Creates new variable fetcher instance.
     * @param ref Reference, which needs parsing
     * @param locals List of cleaned variable names which are always locals
     * @param params List of cleaned variable names which are parameter names
     */
    public VariableFetcher(String ref, List<String> locals, List<String> params) {
        this.ref = ref;
        this.locals = locals;
        this.params = params;
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
     * @return
     */
    public VariableType getType() {
        String clean = getCleanName();
        
        if (clean.startsWith("_") || locals.contains(clean)) {
            return VariableType.LOCAL;
        } else if (params.contains(clean)) {
            return VariableType.PARAM;
        }
        
        return VariableType.GLOBAL;
    }
}
