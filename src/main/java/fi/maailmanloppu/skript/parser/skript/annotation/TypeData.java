package fi.maailmanloppu.skript.parser.skript.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Contains data for Skript type.
 * @author bensku
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeData {
    
    /**
     * Pattern to match for this type in parser syntax (NOT in scripts).
     * @return
     */
    String pattern() default "";
    
    /**
     * Start string of type, for standard handling.
     * @return
     */
    String start() default "";
    
    /**
     * End string of type, for standard handling.
     * @return
     */
    String end() default "";
    
    /**
     * Classes of which objects this type can visit.
     * @return
     */
    Class<?>[] visitors() default {Object.class};
}
