package fi.maailmanloppu.skript.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spongepowered.api.util.Tuple;

/**
 * Parses given match for script usage.
 * @author bensku
 *
 */
public class SkriptPattern {
    
    private String match;
    private String code;
    
    public SkriptPattern(String pattern, String code) {
        this.match = pattern;
        this.code = code;
    }
    
    public Optional<List<String>> parse() {
        RegexComponent checks = createRegex(match);
        boolean fail = false;
        for (String check : checks.parts) {
            Pattern p = Pattern.compile(check, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(code);
            fail = !m.find();
        }
        
        if (fail) return Optional.empty();
        
        List<String> varsToCheck = new ArrayList<String>();
        for (int i = 0; i < checks.ends.size(); i++) {
            varsToCheck.add(code.substring(checks.starts.get(i), checks.ends.get(i)));
        }
        
        return Optional.of(varsToCheck);
    }
    
    class RegexComponent {
        
        public RegexComponent(List<String> parts, List<Integer> starts, List<Integer> ends) {
            this.parts = parts;
            this.starts = starts;
            this.ends = ends;
        }
        
        public List<String> parts;
        public List<Integer> starts;
        public List<Integer> ends;
    }
    
    private RegexComponent createRegex(String pattern) {
        pattern = pattern.replaceAll("\\[", "(").replaceAll("\\]", ")");
                
        int start = -1;
        List<Integer> starts = new ArrayList<Integer>();
        int end = -1;
        List<Integer> ends = new ArrayList<Integer>();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.substring(i) == "%") {
                if (start == -1) start = i;
                else if (end == -1) end = i;
                else {
                    starts.add(start);
                    ends.add(end);
                    start = -1;
                    end = -1;
                }
            }
        }
        
        List<String> parts = new ArrayList<String>();
        parts.add(pattern.substring((0), starts.get(0) - 1)); //From start to first parameter
        for (int i = 0; i < ends.size() - 1; i++) {
            parts.add(pattern.substring(ends.get(i) + 1, starts.get(i) - 1));
        }
        
        
        return new RegexComponent(parts, starts, ends);
    }
}
