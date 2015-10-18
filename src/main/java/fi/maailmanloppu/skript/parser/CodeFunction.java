package fi.maailmanloppu.skript.parser;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Function which is still in source code format.
 * @author bensku
 *
 */
public class CodeFunction implements Function {
    
    private List<String> code;
    private String name;
    
    public CodeFunction(String name, List<String> code) {
        checkNotNull(name, "Function name cannot be null!");
        checkNotNull(code, "Function source code cannot be null!");
        this.code = code;
        this.name = name;
    }
    
    @Override
    public Optional<Object> call(Object args) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public void parse(FunctionSyntax syntax) {
        
    }
}
