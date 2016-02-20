package fi.maailmanloppu.skript.value;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleParseResult implements ParseResult {
    
    private boolean present;
    private Object obj;
    private int offset;
    
    protected SimpleParseResult(boolean present, Object obj, int offset) {
        this.present = present;
        this.obj = obj;
        this.offset = offset;
    }
    
    @Override
    public boolean isPresent() {
        return present;
    }

    @Override
    public Object get() {
        checkNotNull(obj, "Tried to get an parse result object which is not present!");
        return obj;
    }

    @Override
    public int getOffset() {
        return offset;
    }

}
