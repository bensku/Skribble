package fi.maailmanloppu.skript.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Script storage, which optimizes it's memory usage by removing unnecessary
 * string scripts from memory after compiling them.
 * @author bensku
 *
 */
public class ScriptStorage {
    
    private Map<String,List<String>> uncompiled;
    
    public ScriptStorage() {
        uncompiled = new HashMap<String,List<String>>();
    }
    
    public void addScript(String name, List<String> code) {
        uncompiled.put(name, code);
    }
}
