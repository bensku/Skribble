package fi.maailmanloppu.skript.value;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import fi.maailmanloppu.skript.parser.skript.annotation.TypeData;
import fi.maailmanloppu.skript.util.SkriptPattern;

/**
 * Simple type, which provides some basic
 * parsing and detection features.
 * @author bensku
 *
 */
public abstract class SimpleType implements ValueType {
    
    /**
     * Helper function to find string between certain other strings.
     * @param content Content for search
     * @param start String marking start point
     * @param end String marking end point
     * @return The string between, or empty Optional
     */
    public static Optional<String> findBetween(String content, String start, String end) {
        int pos1 = content.indexOf(start) + start.length();
        int pos2 = content.indexOf(end);
        
        if (pos1 == -1 || pos2 == -1) return Optional.empty();
        return Optional.of(content.substring(pos1, pos2));
    }
    
    protected String pattern;
    protected String start;
    protected String end;
    protected Class<?>[] visitors;
    
    public SimpleType() {
        TypeData data = this.getClass().getAnnotation(TypeData.class);
        this.pattern = data.pattern();
        this.start = data.start();
        this.end = data.end();
        this.visitors = data.visitors();
    }

    @Override
    public boolean accepts(String code) {
        String lower = code.toLowerCase();
        
        if (lower.startsWith(start)) {
            if (end != "" && lower.contains(end)) return true;
            else if (end != "") return false;
            else return true;
        }
        
        return false;
    }

    @Override
    public abstract Optional<Object> parseValue(String code);

    @Override
    public abstract List<Object> parseMultiValue(String code);

    @Override
    public abstract void visitMethod(MethodVisitor mv, Object value);
    
    @Override
    public boolean canVisit(Object obj) {
        return Arrays.asList(visitors).contains(obj.getClass());
    }

    @Override
    public boolean matches(String id) {
        return SkriptPattern.of(pattern).match(id) == SkriptPattern.MatchResult.ALLOW;
    }
    
    
}
