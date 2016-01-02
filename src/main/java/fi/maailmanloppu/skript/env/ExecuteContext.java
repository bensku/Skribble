package fi.maailmanloppu.skript.env;

import java.util.List;

import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.var.LocalVariable;

/**
 * Execution context.
 * @author bensku
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
    
    /**
     * Gets local variable stack id based on source code id.
     * @param id
     * @return
     */
    int getLocalVar(String id);
    
    /**
     * Gets handle of local variable with given id.
     * @param id
     * @return
     */
    LocalVariable getLocalVar1(String id);
    
    /**
     * Gets type of variable with given id. If variable is not available, VOID is
     * returned.
     * @param id
     * @return Type id, as int; use Type.TYPENAME for to compare
     */
    int getVarType(String id);
}
