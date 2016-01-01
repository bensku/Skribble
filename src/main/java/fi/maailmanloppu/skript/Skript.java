package fi.maailmanloppu.skript;

import java.io.IOException;
import java.net.URL;

import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import fi.maailmanloppu.skript.parser.skript.event.ConfigEventProvider;
import fi.maailmanloppu.skript.parser.skript.event.EventProvider;
import fi.maailmanloppu.skript.value.SimpleValueParser;
import fi.maailmanloppu.skript.value.ValueParser;
import fi.maailmanloppu.skript.value.skript.NumberType;
import fi.maailmanloppu.skript.value.skript.StringType;

@Plugin(id = "sponge-skript", name = "Skribble", version = "dev-1")
public class Skript {
    
    private static Skript instance;
    
    private SimpleValueParser valueParser;
    private EventProvider eventProvider;
    
    @Inject
    private Logger logger;
    @Inject
    private Game game;
    
    public Skript() {
        instance = this;
        
        valueParser = new SimpleValueParser();
        valueParser.addValueType(new NumberType());
        valueParser.addValueType(new StringType());
        
        URL eventConf = this.getClass().getResource("eventhandlers.yml");
        try {
            eventProvider = new ConfigEventProvider(YAMLConfigurationLoader.builder().setURL(eventConf).build().load());
        } catch (IOException e) {
            logger.error("Failed to load event handler data!");
            logger.error("Either fix the problem or REMOVE Skribble.");
            game.getServer().shutdown();
            e.printStackTrace();
        }
    }
    
    public static Skript getPlugin() {
        return instance;
    }
    
    public ValueParser getValueParser() {
        return this.valueParser;
    }
    
    public EventProvider getEventProvider() {
        return eventProvider;
    }
    
    public Logger getLogger() {
        return logger;
    }
}
