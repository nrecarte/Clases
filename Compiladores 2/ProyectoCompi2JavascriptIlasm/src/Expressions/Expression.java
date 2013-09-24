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
public abstract class Expression {
    
    Expression next = null;
    Type t;

    public abstract void semanticValidation();  
    
    public void semantic()
    {
        semanticValidation();
        
        if(next!=null)
        {
            next.semantic();
        }
    }
    
    public Expression getNext() {
        return next;
    }

    public void setNext(Expression next) {
        this.next = next;
    } 
    
    public Type getType(){
        return this.t;
    }
    
    public void setType(Type t){
        this.t = t;
    }
    
    public abstract String codeGeneration();
    
    public String code()
    {
        String tmp = codeGeneration();  
        if(next!=null)
        {
            tmp += next.code();
        }
        return tmp;
    }
}
