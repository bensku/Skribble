package fi.maailmanloppu.skript.value;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Result of parsing value using value type.
 * @author bensku
 *
 */
public interface ParseResult {
    
    /**
     * Creates a new parse result.
     * @param obj Object, which must be available.
     * @param offset Parsing offset, or 0.
     * @return Parse result.
     */
    public static ParseResult of(Object obj, int offset) {
        checkNotNull(obj, "Cannot create ParseResult from null object!");
        return new SimpleParseResult(true, obj, offset);
    }
    
    /**
     * Creates a new parse result from possibly null object.
     * @param obj Object, which may or may not be available.
     * @param offset Parsing offset, or 0
     * @return Parse result.
     */
    public static ParseResult ofNullable(@Nullable Object obj, int offset) {
        boolean present = obj != null;
        return new SimpleParseResult(present, obj, offset);
    }
    
    /**
     * Creates empty (not present) parse result.
     * @return Empty parse result.
     */
    public static ParseResult empty() {
        return new SimpleParseResult(false, null, 0);
    }
    
    /**
     * Is the result present. If it is not, something probably went wrong
     * while parsing value.
     * @return If the result is available.
     */
    boolean isPresent();
    
    /**
     * Gets parse result. Always remember to check if it is present,
     * otherwise something odd might happen!
     * @return Result, if present.
     */
    Object get();
    
    /**
     * Gets parse offset of this result. Normally, it is same as length
     * of parsed string. If offset is 0, it basically means it doesn't exist.
     * @return Parse offset.
     */
    int getOffset();
}
