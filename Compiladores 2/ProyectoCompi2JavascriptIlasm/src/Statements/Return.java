/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Expressions.Expression;

/**
 *
 * @author Noel Recarte
 */
public class Return extends Statement{

    Expression e;

    public Return(Expression e) {
        this.e = e;
    }  
    
    @Override
    public void semanticValidation() {
        e.semantic();
    }

    @Override
    public String codeGenerationStatement() {
        return e.codeGeneration()+"ret\n";
    }
    
}
