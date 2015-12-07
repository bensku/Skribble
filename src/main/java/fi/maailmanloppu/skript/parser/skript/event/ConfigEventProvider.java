package fi.maailmanloppu.skript.parser.skript.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import ninja.leaping.configurate.ConfigurationNode;

/**
 * Loads event type data from configuration text file to
 * select correct EventFunction.
 * @author bensku
 *
 */
public class ConfigEventProvider implements EventProvider {
    
    private ConfigurationNode config;
    
    public ConfigEventProvider(ConfigurationNode config) {
        this.config = config;
    }
    
    @Override
    public Optional<EventFunction> provide(String decl) {
        Map<Object,? extends ConfigurationNode> nodes = config.getChildrenMap();

        for (Entry<Object, ? extends ConfigurationNode> entry : nodes.entrySet()) {
            String key = (String) entry.getKey();
            ConfigurationNode value = entry.getValue();
            
            if (key.startsWith(decl)) {
                //TODO Create event function from generic class here
            }
        }
        
        return Optional.empty();
    }

}
