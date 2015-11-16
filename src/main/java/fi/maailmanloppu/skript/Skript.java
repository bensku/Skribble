package fi.maailmanloppu.skript;

import org.spongepowered.api.plugin.Plugin;

import fi.maailmanloppu.skript.value.SimpleValueParser;
import fi.maailmanloppu.skript.value.ValueParser;

@Plugin(id = "sponge-skript", name = "Sponge Skript", version = "dev-1")
public class Skript {
    
    private static Skript instance;
    
    private ValueParser valueParser;
    
    public Skript() {
        instance = this;
        
        valueParser = new SimpleValueParser();
    }
    
    public static Skript getPlugin() {
        return instance;
    }
    
    public ValueParser getValueParser() {
        return this.valueParser;
    }
}
