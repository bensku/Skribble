package fi.maailmanloppu.skript.compiler;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import fi.maailmanloppu.skript.env.Environment;
import fi.maailmanloppu.skript.env.ExecuteContext;
import fi.maailmanloppu.skript.env.FunctionContext;
import fi.maailmanloppu.skript.parser.CallTask;
import fi.maailmanloppu.skript.parser.CodeFunction;
import fi.maailmanloppu.skript.parser.Function;
import fi.maailmanloppu.skript.parser.FunctionSyntax;
import fi.maailmanloppu.skript.parser.ParsedScript;
import fi.maailmanloppu.skript.parser.Parser;

public class DefaultCompiler implements Compiler, Opcodes {
    
    private Parser parser;
    private FunctionSyntax syntax;
    
    public DefaultCompiler(Parser parser, FunctionSyntax syntax) {
        this.parser = parser;
        this.syntax = syntax;
    }
    
    @Override
    public void compile(List<String> code, String name) {
        ParsedScript script = parser.parseScript(code);
        List<Function> functions = script.getFunctions();
        
        ClassWriter cw = new ClassWriter(ASM5 + ClassWriter.COMPUTE_MAXS);
        
        String signature = "script/compiled/" + name;
        cw.visit(V1_8, ACC_PUBLIC, signature,
                null, "java/lang/Object", null);
        
        for (Function func : functions) {
            if (func instanceof CodeFunction) {
                List<CallTask> tasks = ((CodeFunction) func).parse(syntax);
                
            }
        }
    }
    
    public void createMethod(ClassWriter cw, String name, List<CallTask> tasks, Environment env) {
        ExecuteContext context = new FunctionContext(env, name, new ArrayList<String>());
        
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, name, "()V", null, null); //TODO Fix void type
        mv.visitCode();
        for (CallTask task : tasks) {
            task.visitMethod(mv, context);
        }
        
        mv.visitEnd();
    }

}
