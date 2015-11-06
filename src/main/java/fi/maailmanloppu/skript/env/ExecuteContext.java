package fi.maailmanloppu.skript.env;

import java.util.List;

/**
 * Execution context.
 * @author Benjami
 *
 */
public interface ExecuteContext {
    List<String> getLocals();
    
    List<String> getParams();
    
    Environment getEnvironment();
}
