package fi.maailmanloppu.skript.env;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.Skript;
import fi.maailmanloppu.skript.value.ValueParser;
import fi.maailmanloppu.skript.var.LocalVariable;
import fi.maailmanloppu.skript.var.SimpleLocalVariable;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class FunctionContext implements ExecuteContext {
    
    private List<String> locals;
    private String id;
    private Environment env;
    private ValueParser valueParser;
    
    public FunctionContext(Environment env, String id, ValueParser valueParser) {
        checkNotNull(locals, "Locals cannot be null. Pass empty list if you don't have any.");
        this.locals = new ArrayList<String>();
        checkArgument(id != null && id != "", "Context id must be present");
        this.id = id;
        checkNotNull(env, "Environment must be present");
        this.env = env;
        this.valueParser = valueParser;
    }
    
    @Override
    public List<String> getLocals() {
        return locals;
    }

    @Override
    public Environment getEnvironment() {
        return this.env;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getVarStack(String id) {
        checkArgument(id != null && id != "", "Variable id must be present");
        if (locals.contains(id)) {
            return locals.lastIndexOf(id);
        } else {
            locals.add(id);
            return locals.lastIndexOf(id);
        }
    }

    @Override
    public LocalVariable getLocalVar(String id) {
        LocalVariable var = new SimpleLocalVariable(id, this, valueParser);
        return var;
    }

    @Override
    public int getVarType(String id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
