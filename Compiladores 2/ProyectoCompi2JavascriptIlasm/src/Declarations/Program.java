/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import proyectocompi2javascriptilasm.AssemblyTemplate;
import Semantic.Environment;
import Expressions.Id;
import Statements.Statement;
import Types.Field;
import Types.Type;
import java.util.ArrayList;
/**
 *
 * @author Noel Recarte
 */
public class Program extends Declarations{
    Id name;
    ArrayList<Argument> args;
    ArrayList<Declarations> decls;
    Statement stms;

    public Program(Id name, ArrayList<Argument> args, ArrayList<Declarations> decls, Statement stms) {
        this.name = name;
        this.args = args;
        this.decls = decls;
        this.stms = stms;
    }

    public Id getName() {
        return name;
    }
    
    @Override
    public void semanticValidation() {
        
        //argumentos del programa
        if (args != null) {
            for (Argument d : args) {
                d.semantic();
            }
        }

        //declaraciones typos, variables
        for (Declarations d : decls) {
            d.semantic();
        }

        //compound
        if (stms != null) {
            this.stms.semantic();
        }
    }

    @Override
    public String codeGenerationStament() {
         
        AssemblyTemplate.getInstance().setInfo(this.name.getIdentifier(), "1:0:1:0");
        
        String main = "", funcion = "", types = "";

        main += ".method static void  Main() cil managed {\n\n";
        main += ".entrypoint\n.maxstack  100\n\n";

        for (Declarations d : decls) {

            if (d instanceof DeclarationFunction) {
                funcion += d.codeGeneration();
            } else if (d instanceof DeclarationType) {
                types += d.codeGeneration();
            } else {
                //main += Env.getIntance().getTable().getLocals();
            }
        }

        Environment te = Environment.getIntance().getPrev();
        main += te.getTable().getLocals();
        
        if (stms != null) {
            main += this.stms.codeGeneration();
        }

        main += "ret\n";
        main += "}\n";
        
        AssemblyTemplate.getInstance().setBody(types + funcion + main);

        return AssemblyTemplate.getInstance().getProgram();
    }
    
}
