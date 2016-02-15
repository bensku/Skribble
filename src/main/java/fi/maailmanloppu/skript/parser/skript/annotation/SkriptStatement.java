package fi.maailmanloppu.skript.parser.skript.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
public @interface SkriptStatement {
    
    /**
     * Pattern, using Skript special pattern syntax.
     */
    String pattern();
    
    /**
     * Variables in this array are automatically parsed and added as first locals.
     * Given names will be used for function context variable names.
     */
    String[] variables();
    
    /**
     * Skript variable type names, from left to right.
     */
    String[] types();
}
