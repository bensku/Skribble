package fi.maailmanloppu.skript.parser.skript;

import java.util.Optional;

import fi.maailmanloppu.skript.parser.CallTask;

/**
 * Since Skript format does not have simple function syntax, all
 * functions (known as "effects") need custom Java-based processors.
 * @author bensku
 *
 */
public interface EffectProcessor {
    
    /**
     * Checks and gets call task for this line.
     * @return Call task or empty optional
     */
    Optional<CallTask> check();
}
