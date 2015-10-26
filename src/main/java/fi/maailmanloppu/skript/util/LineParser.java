package fi.maailmanloppu.skript.util;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

public class LineParser {
    
    private String line;
    private String[] parts;

    public LineParser(String line) {
        checkArgument(line != null && line != "");
        this.line = line;
    }
    
    /**
     * Parses the line and stores the result.
     */
    public void parse() {
        parts = line.split(" ");
    }
    
    /**
     * Returns everything <i>between</i> parameters.
     * @param from
     * @param to
     * @return Everything between
     */
    public String findParts(String from, String to) {
        List<String> between = new ArrayList<String>();
        
        boolean foundYet = false;
        for (String part : parts) {
            if (part.equals(from)) {
                foundYet = true;
                continue;
            } if (!part.equals(to) && foundYet) {
                between.add(part);
                continue;
            } if (part.equals(to)) {
                break;
            }
        }
        
        return String.join(" ", between);
    }
}
