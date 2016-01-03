package fi.maailmanloppu.skript.parser.skript.processor;

import java.util.Optional;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import fi.maailmanloppu.skript.Skript;
import fi.maailmanloppu.skript.env.Environment;
import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.skript.EffectProcessor;
import fi.maailmanloppu.skript.parser.skript.VariableFetcher;
import fi.maailmanloppu.skript.util.LineParser;
import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ValueParser;
import fi.maailmanloppu.skript.value.ValueType;

public class SetVarProcessor implements EffectProcessor {

    @Override
    public Optional<CallTask> check(String line) {
        if (line.startsWith("set")) {
            LineParser parser = new LineParser(line);
            String varId = parser.findParts("set", "to");
            String newValue = parser.findParts("to", ""); //From "to" to end
            
            SetVarTask task = new SetVarTask(varId, newValue);
            return Optional.of(task);
        }
        return Optional.empty();
    }
    
    class SetVarTask implements CallTask, Opcodes {
        
        private String varId;
        private String newValue;
        private ValueParser parser;
        private ValueType type;
        
        public SetVarTask(String varId, String newValue) {
            this.varId = varId;
            this.newValue = newValue;
            this.parser = Skript.getPlugin().getValueParser();
            
            Optional<ValueType> type = parser.getValueType(newValue);
            if (type.isPresent()) {
                this.type = type.get();
            }
        }

        @Override
        public Optional<Object> execute(ExecuteContext context) {
            VariableFetcher varFetcher = new VariableFetcher(varId, context);
            String cleanName = varFetcher.getCleanName();
            Environment env = context.getEnvironment();
            Object valueObj = type.parseValue(newValue);
            
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
            MethodUtils utils = new MethodUtils(mv);
            Optional<Object> parsed = type.parseValue(newValue);
            
            switch (varFetcher.getType()) {
            case GLOBAL:
                mv.visitFieldInsn(GETFIELD, "fi/maailmanloppu/skript/env/GenericEnvironment", "env", "Ljava/util/Map;");
                utils.putToStack(cleanName);
                type.visitMethod(mv, parsed); //Parse and put to stack
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", 
                        "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
            case LOCAL:
                type.visitMethod(mv, parsed); //Parse and put to stack
                utils.setLocal(parsed, context.getVarStack(cleanName));
                //mv.visitFieldInsn(PUTFIELD, env.getVisitingName(), context.getId() + cleanName,
                //        env.getFieldType(cleanName).getDescriptor());
            case PARAM:
                break; //TODO Not possible, maybe set function flag to merge params to locals when found?
            }
        }
    }

}
