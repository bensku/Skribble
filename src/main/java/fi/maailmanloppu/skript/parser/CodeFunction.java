package fi.maailmanloppu.skript.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.Type;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Function which is still in source code format.
 * @author bensku
 *
 */
public class CodeFunction implements Function {
    
    private List<String> code;
    private String name;
    private List<Type> params;
    private Type ret;
    
    public CodeFunction(String name, List<String> code, List<Type> params, Type ret) {
        checkNotNull(name, "Function name cannot be null!");
        checkNotNull(code, "Function source code cannot be null!");
        checkNotNull(params, "Function parameters cannot be null, use VOID type!");
        checkNotNull(ret, "Function return cannot be null, use VOID type!");
        this.code = code;
        this.name = name;
        this.params = params;
        this.ret = ret;
    }
    
    @Override
    public Optional<Object> call(Object args) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public List<CallTask> parse(FunctionSyntax syntax) {
        List<CallTask> tasks = new ArrayList<CallTask>();
        for (String line : code) {
            tasks.addAll(syntax.getTasks(line));
        }
        
        return tasks;
    }

    @Override
    public List<Type> getParamTypes() {
        return params;
    }

    @Override
    public Type getReturnType() {
        return ret;
    }
}
