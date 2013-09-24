/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Expressions.Expression;
import Expressions.Id;
import Types.Custom;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class DeclarationId extends Declarations{
    
    String name;
    Type t;

    public DeclarationId(String name, Type t) {
        this.name = name;
        this.t = t;
        
        Environment.getIntance().put(name, t);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getT() {
        return t;
    }

    public void setT(Type t) {
        this.t = t;
    }

    @Override
    public void semanticValidation() {
        
    }

    @Override
    public String codeGenerationStament() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

