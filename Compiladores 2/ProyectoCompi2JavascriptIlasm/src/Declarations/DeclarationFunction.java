/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Expressions.Id;
import Statements.Statement;
import Types.Field;
import Types.Function;
import Types.Type;
import java.util.ArrayList;

/**
 *
 * @author Noel Recarte
 */
public class DeclarationFunction extends Declarations{
    
    Id name;
    ArrayList<Argument> args;
    Type t;
    Declarations vars;
    Statement stms;

    public DeclarationFunction(Id name, ArrayList<Argument> args, Type t, Declarations vars, Statement stms) {
        this.name = name;
        this.args = args;
        this.t = t;
        this.vars = vars;
        this.stms = stms;
    }  
    
    @Override
    public void semanticValidation() {
          
        Environment.getIntance().putFunction(this.name.getIdentifier(), this.t);
        //Nuevo environtment
        Environment.newEnvironment();  
        
        //validacion de argumentos dentro del entorno
        if (this.args != null) {
            
            for(int i = this.args.size()-1; i >= 0; i--)
            {
                Declarations d = this.args.get(i);
                d.semantic();
            }
        }
        
        //variables locales
        if (this.vars != null) {
            this.vars.semantic();
        }
        
        if (this.stms != null) {
            this.stms.semantic();
        }       
        super.environment = Environment.getIntance();
        Environment.restoreEnvironment();
    }

    @Override
    public String codeGenerationStament() {
        
        Environment te = Environment.getIntance();
        Environment.setInstance(super.environment);
        String tmp = "", ret = "";
        
        tmp += ".method public static "+this.t.toAssembly()+" "+this.name.getIdentifier()+"("+Environment.getIntance().getTable().getArgs()+") cil managed\n";
        tmp += "{\n";
        tmp += ".maxstack  100\n";
        tmp += Environment.getIntance().getTable().getLocals() +"\n"+ this.stms.codeGeneration();
        
        tmp += "ret\n}\n";
        
        Environment.setInstance(te);
        
        return tmp;
    }
    
}
