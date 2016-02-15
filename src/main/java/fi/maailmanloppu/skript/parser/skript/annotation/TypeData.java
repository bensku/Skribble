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
     * Pattern to match for this type.
     * @return
     */
    String pattern();
}
