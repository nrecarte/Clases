/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Expressions.*;

/**
 *
 * @author Noel Recarte
 */
public class DeclarationConstant extends Declarations{
    
    Id name;
    Expression expr;

    public DeclarationConstant(Id name, Expression expr) {
        this.name = name;
        this.expr = expr;
    } 

    @Override
    public void semanticValidation() {
        //throw new UnsupportedOperationException("Not supported yet.");
        //Env.getIntance().put(name.getIdentifier(), null);
    }

    @Override
    public String codeGenerationStament() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

