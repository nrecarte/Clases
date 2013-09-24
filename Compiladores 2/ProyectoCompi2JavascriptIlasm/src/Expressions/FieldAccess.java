/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import Semantic.Environment;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class FieldAccess extends Expression{

    Id atribute;

    public FieldAccess(Id atribute) {
        this.atribute = atribute;
    }

    public Id getAtribute() {
        return atribute;
    }

    public void setAtribute(Id atribute) {
        this.atribute = atribute;
    } 

    @Override
    public void semanticValidation() {
        throw new UnsupportedOperationException("Not supported yet. field access");
    }   

    @Override
    public String codeGeneration() {
        
        //ldfld int32 Simple::Position
        return "ldfld "+ t.toAssembly() +" "+this.atribute.getIdentifier()+"\n";
    }
    
}
