package fi.maailmanloppu.skript.parser.skript.effect;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.util.Tuple;

import fi.maailmanloppu.skript.Skript;
import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.skript.EffectProcessor;
import fi.maailmanloppu.skript.parser.skript.annotation.SkriptStatement;
import fi.maailmanloppu.skript.util.SkriptPatternOld;

public interface SimpleEffect extends EffectProcessor {
    
    static Tuple<Boolean, List<String>> matchPattern(String pattern, String code) {
        SkriptPatternOld parser = new SkriptPatternOld(pattern, code);
        Optional<List<String>> resultOptional = parser.parse();
        if (resultOptional.isPresent()) {
            List<String> result = resultOptional.get();
            
            return new Tuple<Boolean, List<String>>(true, result);
        }
        
        return new Tuple<Boolean, List<String>>(false, null);
    }
    
    @Override
    default Optional<CallTask> check(String line) {
        Class<? extends SimpleEffect> tc = this.getClass();
        SkriptStatement annotation = tc.getAnnotation(SkriptStatement.class);
        
        Tuple<Boolean, List<String>> result = matchPattern(annotation.pattern(), line);
        List<String> varsToParse = result.getSecond();
        
        return null;
    }
}
