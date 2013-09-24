/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

/**
 *
 * @author Noel Recarte
 */
public abstract class Literal extends Expression{

    @Override
    public void semanticValidation() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }  
    
    public abstract String codeGen(); 

    @Override
    public String codeGeneration() {
        
        String tmp = "";
        tmp += codeGen();
        
//        if(next!=null)
//        {
//            tmp += next.codeGeneration();
//        }
        
        return tmp;
    }
}
