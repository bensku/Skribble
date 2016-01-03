package fi.maailmanloppu.skript.parser;

public enum VariableType {
    /**
     * Global variable, which can changed freely runtime.
     */
    GLOBAL,
    
    /**
     * Variable is defined as static field.
     */
    LOCAL
}
