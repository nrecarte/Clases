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
public abstract class BinaryOperator extends Expression{
    
    Expression left, right;

    public BinaryOperator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
    
    public abstract void semantic();

    @Override
    public void semanticValidation() {
        this.semantic();      
    }  
    
}
