/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import Semantic.ErrorLog;

/**
 *
 * @author Noel Recarte
 */
public class Multiplication extends BinaryOperator {

    public Multiplication(Expression left, Expression right) {
        super(left, right);
    } 
    
    @Override
    public void semantic() {
        left.semanticValidation();
        right.semanticValidation();
        
        if (this.left.getType().getClass() != this.right.getType().getClass()) {
            ErrorLog.getInstance().add("Error: Operador '*' tiene tipos distintos, " + this.left.getType().toStr() + " y " + this.right.getType().toStr());
        }
        
        super.setType(left.getType());
    }
    
    @Override
    public String codeGeneration() {
        return left.codeGeneration()+right.codeGeneration()+"mul\n";
    }
}
