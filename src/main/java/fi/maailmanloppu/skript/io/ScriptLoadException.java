package fi.maailmanloppu.skript.io;

/**
 * Script load exception. This should be thrown when there are problems
 * accessing the file but not when script is contains invalid syntax.
 * @author bensku
 *
 */
public class ScriptLoadException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -8203265647115526436L;
    
    public ScriptLoadException(String message) {
        super(message);
    }
}
