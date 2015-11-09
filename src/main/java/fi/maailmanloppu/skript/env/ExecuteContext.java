package fi.maailmanloppu.skript.env;

import java.util.List;

/**
 * Execution context.
 * @author Benjami
 *
 */
public interface ExecuteContext {
    /**
     * Gets list of local variable names.
     * @return Local variable names
     */
    List<String> getLocals();
    
    /**
     * Gets list of parameter variable names.
     * @return Parameter names
     */
    List<String> getParams();
    
    /**
     * Gets environment of this context.
     * @return Environment
     */
    Environment getEnvironment();
    
    /**
     * Gets unique identifier of this context.
     * @return Unique identifier
     */
    String getId();
}
