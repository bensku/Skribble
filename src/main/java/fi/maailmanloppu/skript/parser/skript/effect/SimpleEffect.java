package fi.maailmanloppu.skript.parser.skript.effect;

import java.util.List;
import java.util.Optional;

import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.skript.EffectProcessor;
import fi.maailmanloppu.skript.parser.skript.annotation.SkriptStatement;
import fi.maailmanloppu.skript.util.SkriptPattern;

public interface SimpleEffect extends EffectProcessor {
    
    @Override
    default Optional<CallTask> check(String line) {
        Class<? extends SimpleEffect> tc = this.getClass();
        SkriptStatement statement = tc.getAnnotation(SkriptStatement.class);
        
        String pattern = statement.pattern();
        SkriptPattern parser = SkriptPattern.of(pattern);
        parser.parse();
        int result = parser.match(line);
        if (result != SkriptPattern.MatchResult.ALLOW) return Optional.empty();
        
        List<String> vars = parser.findVariables();
        String[] neededTypes = statement.types();
        if (vars.size() != neededTypes.length) return Optional.empty(); //Different amount of variables
        
        for (int i = 0; i < vars.size(); i++) {
            SkriptPattern varPattern = SkriptPattern.of(neededTypes[i]);
        }
        
        return null;
    }
}
