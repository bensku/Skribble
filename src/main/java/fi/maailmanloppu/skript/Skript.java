package fi.maailmanloppu.skript;

import org.spongepowered.api.plugin.Plugin;

import fi.maailmanloppu.skript.value.SimpleValueParser;
import fi.maailmanloppu.skript.value.ValueParser;
import fi.maailmanloppu.skript.value.skript.NumberType;
import fi.maailmanloppu.skript.value.skript.StringType;

@Plugin(id = "sponge-skript", name = "Skribble", version = "dev-1")
public class Skript {
    
    private static Skript instance;
    
    private SimpleValueParser valueParser;
    
    public Skript() {
        instance = this;
        
        valueParser = new SimpleValueParser();
        valueParser.addValueType(new NumberType());
        valueParser.addValueType(new StringType());
    }
    
    public static Skript getPlugin() {
        return instance;
    }
    
    public ValueParser getValueParser() {
        return this.valueParser;
    }
}
