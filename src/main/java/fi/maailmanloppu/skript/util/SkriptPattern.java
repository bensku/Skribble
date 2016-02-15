package fi.maailmanloppu.skript.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Skript pattern utils.
 * @author bensku
 *
 */
public class SkriptPattern {
    
    private static Map<String,SkriptPattern> cache = new HashMap<String,SkriptPattern>();
    
    /**
     * Creates new Skript pattern which can then be matched.
     * @param pattern Pattern text
     */
    public static SkriptPattern of(String pattern) {
        if (cache.containsKey(pattern)) {
            return cache.get(pattern);
        } else {
            return new SkriptPattern(pattern);
        }
    }
    
    private boolean parsed = false;
    private String str;
    private Section section;
    
    /**
     * Internal initializer.
     * @param pattern Pattern text
     */
    private SkriptPattern(String pattern) {
        this.str = pattern;
        cache.put(pattern, this); //Cache pattern so only one parse is needed
    }
    
    /**
     * Parses the pattern, if needed.
     */
    public void parse() {
        if (parsed) return;
        
        this.section = parseSection(str, false);
    }
    
    private Section parseSection(String partStr, boolean optional) {
        boolean escape = false; //Is the char before \
        
        boolean plainText = true;
        
        int optStart = 0;
        boolean optMode = false; //Option parsing mode; do not do anything else
        int grpStart = 0;
        boolean grpMode = false;
        int freeStart = 0; //Outside of sections or groups
        
        boolean isVar = false;
        int varStart = 0;
        
        Section sec = new Section();
        sec.optional = optional;
        
        char[] chars = partStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\') {
                escape = true;
                continue;
            }
            
            if (!escape) {
                if (!optMode && !grpMode) {
                    switch (c) {
                    case '[':
                        optStart = i;
                        optMode = true;
                        sec.childs.add(parseSection(partStr.substring(freeStart + 1, i), false));
                        plainText = false;
                    case '(':
                        grpStart = i;
                        grpMode = true;
                        sec.childs.add(parseSection(partStr.substring(freeStart + 1, i), false));
                        plainText = false;
                    }
                } else if (optMode) {
                    if (c == ']') {
                        Section child = parseSection(partStr.substring(optStart + 1, i), true);
                        sec.childs.add(child);
                        optMode = false;
                        freeStart = i;
                    }
                } else if (grpMode) {
                    if (c == ')') {
                        Section child = parseGroup(partStr.substring(grpStart + 1, i));
                        sec.childs.add(child);
                        grpMode = false;
                        freeStart = i;
                    }
                }
                
                if (c == '%') {
                    if (isVar) {
                        sec.variables.add(str.substring(varStart, i));
                        isVar = false;
                    } else {
                        varStart = i + 1;
                        isVar = true;
                    }
                }
                
            } else {
                escape = false;
            }
        }
        
        if (plainText) {
            sec.text = partStr;
        }
        
        return sec;
    }
    
    public Section parseGroup(String grpStr) {
        Group group = new Group();
        
        String[] optStrs = grpStr.split("|");
        for (String opt : optStrs) {
            Section sec = parseSection(opt, false);
            group.options.add(sec);
        }
        
        return group;
    }
    
    /**
     * Section is a part, which contains child sections and/or text to match.
     * @author bensku
     *
     */
    class Section {
        
        public Section() {}
        
        public boolean optional = false;
        public List<Section> childs = new ArrayList<Section>();
        public String text;
        public List<String> variables;
    }
    
    /**
     * Group is subclass of section, which also has optional parts.
     * @author bensku
     *
     */
    class Group extends Section {
        
        public List<Section> options = new ArrayList<Section>();
    }
    
    public class MatchResult {
        
        /**
         * Match is found.
         */
        public static final int ALLOW = 0;
        
        /**
         * Match is not found, at all.
         */
        public static final int DENY = 1;
    }
    
    public int match(String code) {
        SectionResult result = matchSection(section, code);
        
        if (result.success) return MatchResult.ALLOW;
        else return MatchResult.DENY;
    }
    
    class SectionResult {
        
        public int offset = 0;
        public boolean success = false;
    }
    
    public SectionResult matchSection(Section sec, String part) {
        if (sec.text != null) {
            SectionResult result = new SectionResult();
            result.offset = part.length();
            result.success = part.toLowerCase().startsWith(sec.text.toLowerCase());
            return result;
        }
        
        if (sec instanceof Group) {
            for (Section opt : ((Group) sec).options) {
                SectionResult result = matchSection(opt, part);
                if (result.success) return result;
            }
        }
        
        int offset = 0;
        for (Section child : sec.childs) {
            SectionResult result = matchSection(child, part.substring(offset - 1));
            
            if (result.success) {
                offset += result.offset;
            } else if (!child.optional) {
                return new SectionResult();
            }
        }
        
        SectionResult result = new SectionResult();
        result.offset = offset;
        result.success = true;
        return result;
    }
    
    public List<String> findVariables() {
        boolean escape = false;
        List<String> vars = new ArrayList<String>();
        boolean isVar = false;
        int varStart = 0;
        
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\') {
                escape = true;
                continue;
            }
            
            if (!escape && c == '%') {
                if (isVar) {
                    vars.add(str.substring(varStart, i));
                    isVar = false;
                } else {
                    varStart = i + 1;
                    isVar = true;
                }
            } else if (escape) {
                escape = false;
            }
        }
        
        return vars;
    }
}
