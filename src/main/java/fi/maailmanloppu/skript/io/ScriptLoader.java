package fi.maailmanloppu.skript.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Utilies for loading scripts from files.
 * @author bensku
 *
 */
//TODO Allow other plugins provide scripts as strings
public class ScriptLoader {
    
    private File directory;
    
    public ScriptLoader(File dir) {
        this.directory = dir;
    }
    
    public Optional<List<String>> load(String name) {
        Path path = Paths.get(directory.getAbsolutePath(), name);
        List<String> script = null;
        try {
            script = Files.readAllLines(path);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(script);
    }
    
    public ScriptStorage loadAll() {
        ScriptStorage storage = new ScriptStorage();
        
        for (File file : directory.listFiles()) {
            Optional<List<String>> code = load(file.getName());
            if (code.isPresent()) {
                storage.addScript(file.getName(), code.get());
            }
        }
        
        return storage;
    }
}
