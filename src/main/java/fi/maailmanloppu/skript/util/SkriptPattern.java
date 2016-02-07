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
        boolean escape = false; //Is the char before \
        
        int optStack = 0;
        int partStack = 0;
        
        Map<Integer,Map<?,?>> optMap = new HashMap<Integer,Map<?,?>>();
        
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\') {
                escape = true;
                continue;
            }
            
            if (!escape) {
                switch (c) {
                    
                }
            } else {
                escape = false;
            }
        }
    }
    
    private Section parseSection(String partStr, boolean optional) {
        boolean escape = false; //Is the char before \
        
        boolean plainText = true;
        
        int optStart = 0;
        boolean optMode = false; //Option parsing mode; do not do anything else
        int grpStart = 0;
        boolean grpMode = false;
        int freeStart = 0;
        
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
                        Section child = parseGroup(partStr.substring(optStart + 1, i));
                        sec.childs.add(child);
                        freeStart = i;
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
    
    class Section {
        
        public Section() {}
        
        public boolean optional = false;
        public List<Section> childs = new ArrayList<Section>();
        public String text;
    }
    
    class Group extends Section {
        
        public List<Section> options = new ArrayList<Section>();
    }
}
