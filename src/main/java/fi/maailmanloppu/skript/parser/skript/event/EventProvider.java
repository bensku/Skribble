package fi.maailmanloppu.skript.parser.skript.event;

import java.util.Optional;

/**
 * Provides event functions when needed.
 * @author bensku
 *
 */
public interface EventProvider {
    /**
     * Gets correct EventFunction for given declaration.
     * @param declaration A first line of function
     * @return
     */
    Optional<EventFunction> provide(String declaration);
}
