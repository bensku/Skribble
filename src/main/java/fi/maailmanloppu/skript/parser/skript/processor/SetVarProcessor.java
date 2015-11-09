package fi.maailmanloppu.skript.parser.skript.processor;

import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import fi.maailmanloppu.skript.Skript;
import fi.maailmanloppu.skript.env.Environment;
import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.VariableType;
import fi.maailmanloppu.skript.parser.skript.EffectProcessor;
import fi.maailmanloppu.skript.parser.skript.VariableFetcher;
import fi.maailmanloppu.skript.util.LineParser;
import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ValueParser;

public class SetVarProcessor implements EffectProcessor {

    @Override
    public Optional<CallTask> check(String line) {
        if (line.startsWith("set")) {
            LineParser parser = new LineParser(line);
            String varId = parser.findParts("set", "to");
            String newValue = parser.findParts("to", ""); //From "to" to end
            
            Object valueObj = Skript.getPlugin().getValueParser().parseValue(newValue);
        }
        return null;
    }
    
    class SetVarTask implements CallTask, Opcodes {
        
        private String varId;
        private String newValue;
        private ValueParser parser;
        
        public SetVarTask(String varId, String newValue) {
            this.varId = varId;
            this.newValue = newValue;
            this.parser = Skript.getPlugin().getValueParser();
        }

        @Override
        public Optional<Object> execute(ExecuteContext context) {
            VariableFetcher varFetcher = new VariableFetcher(varId, context);
            String cleanName = varFetcher.getCleanName();
            Environment env = context.getEnvironment();
            Object valueObj = Skript.getPlugin().getValueParser().parseValue(newValue);
            
            switch (varFetcher.getType()) {
            case GLOBAL:
                env.setVariable(cleanName, valueObj);
            case LOCAL:
                env.setVariable(cleanName, valueObj); //TODO Need to fix this
            case PARAM:
                break; //TODO Not possible currently...
            }
            
            return Optional.empty();
        }

        @Override
        public void visitMethod(MethodVisitor mv, ExecuteContext context) {
            VariableFetcher varFetcher = new VariableFetcher(varId, context);
            String cleanName = varFetcher.getCleanName();
            Environment env = context.getEnvironment();
            
            parser.visitMethod(mv, parser.parseValue(newValue)); //Parse and put to stack
            
            switch (varFetcher.getType()) {
            case GLOBAL:
                
            case LOCAL:
                mv.visitFieldInsn(PUTFIELD, env.getVisitingName(), context.getId() + cleanName,
                        env.getFieldType(cleanName).getDescriptor());
            case PARAM:
                break; //TODO Not possible
            }
        }
    }

}
