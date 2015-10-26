package fi.maailmanloppu.skript.parser.skript;

/**
 * Used to fetch variable names from Skript's syntax.
 * @author bensku
 *
 */
public class VariableFetcher {
    
    private String ref;
    
    public VariableFetcher(String ref) {
        this.ref = ref;
    }
    
    public String getCleanName() {
        return ref.replace("{", "").replace("}", "").replace("%", "");
        
    }
    
    /**
     * Checks if variable is global or local. Local variables can be
     * translated into bytecode fields.
     * @return
     */
    public boolean isLocal() {
        String clean = getCleanName();
        if (clean.startsWith("_")) return true;
        return false;
    }
}
