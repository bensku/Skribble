package fi.maailmanloppu.skript.compiler;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import fi.maailmanloppu.skript.env.Environment;
import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.env.FunctionContext;
import fi.maailmanloppu.skript.env.GenericEnvironment;
import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.CodeFunction;
import fi.maailmanloppu.skript.parser.Function;
import fi.maailmanloppu.skript.parser.FunctionSyntax;
import fi.maailmanloppu.skript.parser.ParsedScript;
import fi.maailmanloppu.skript.parser.Parser;
import fi.maailmanloppu.skript.value.ValueParser;

public class DefaultCompiler implements Compiler, Opcodes {
    
    private Parser parser;
    private FunctionSyntax syntax;
    private ValueParser valueParser;
    
    public DefaultCompiler(Parser parser, FunctionSyntax syntax, ValueParser valueParser) {
        this.parser = parser;
        this.syntax = syntax;
        this.valueParser = valueParser;
    }
    
    @Override
    public void compile(List<String> code, String name) {
        ParsedScript script = parser.parseScript(code);
        List<Function> functions = script.getFunctions();
        Environment env = new GenericEnvironment();
        
        ClassWriter cw = new ClassWriter(ASM5 + ClassWriter.COMPUTE_MAXS);
        
        String signature = "script/compiled/" + name;
        cw.visit(V1_8, ACC_PUBLIC, signature,
                null, "java/lang/Object", null);
        
        for (Function func : functions) {
            if (func instanceof CodeFunction) {
                List<CallTask> tasks = ((CodeFunction) func).parse(syntax);
                createMethod(cw, func, tasks, env);
            }
        }
    }
    
    public void createMethod(ClassWriter cw, Function func, List<CallTask> tasks, Environment env) {
        String name = func.getName();
        ExecuteContext context = new FunctionContext(env, name, valueParser);
        
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, name, Type.getMethodDescriptor(func.getReturnType(), 
                (Type[]) func.getParamTypes().toArray()), null, null);
        mv.visitCode();
        for (CallTask task : tasks) {
            task.visitMethod(mv, context);
        }
        
        mv.visitMaxs(0, 0); //COMPUTE_MAXS; these values are ignored but method must still called
        mv.visitEnd();
    }

}
