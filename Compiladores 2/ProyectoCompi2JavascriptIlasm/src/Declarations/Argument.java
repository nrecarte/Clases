/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Expressions.Id;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class Argument extends Declarations{
    
    Id name;
    Type t;

    public Argument(Id name, Type t) {
        this.name = name;
        this.t = t;
    }
    
    @Override
    public void semanticValidation() {
        Environment.getIntance().putArg(name.getIdentifier(), t);
    }

    @Override
    public String codeGenerationStament() {
        //return Env.getIntance().getTable().getArgs();
        return "";
    }
}
    
    
    