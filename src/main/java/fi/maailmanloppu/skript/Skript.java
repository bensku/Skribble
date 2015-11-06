package fi.maailmanloppu.skript;

import org.spongepowered.api.plugin.Plugin;

import fi.maailmanloppu.skript.value.SimpleValueParser;
import fi.maailmanloppu.skript.value.ValueParser;

@Plugin(id = "sponge-skript", name = "Sponge Skript", version = "dev-1")
public class Skript {
    
    private static Skript instance;
    
    private ValueParser valueParser;
    private ValueParser parser2;
    
    public Skript() {
        instance = this;
        
        valueParser = new SimpleValueParser();
        valueParser = parser2;
    }
    
    public static Skript getPlugin() {
        return instance;
    }
    
    public ValueParser getValueParser() {
        return this.valueParser;
    }
}
