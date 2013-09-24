/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import java.util.ArrayList;

/**
 *
 * @author Noel Recarte
 */
public class ArrayExpression extends Expression{
    
    Expression indexs;

    public ArrayExpression(Expression indexs) {
        this.indexs = indexs;
    }

    public Expression getIndexs() {
        return indexs;
    }

    public void setIndexs(Expression indexs) {
        this.indexs = indexs;
    }  
    
    public int count()
    {
        Expression e = this.indexs;
        
        int cont = 0;
        while(e!=null)
        {
            cont++;
            e = e.getNext();
        }
        
        return cont;     
    }

    @Override
    public void semanticValidation() {
        //throw new UnsupportedOperationException("Not supported yet
        //TO DO:
    }

    @Override
    public String codeGeneration() {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        StringBuilder str = new StringBuilder("");
        // si los indices son mayores a 1 hace una llamada a un vector
        //call instance void int32[0...,0...,0...]::Set(int32,int32,int32,int32)
        if(count() > 1)
        {
           str.append(this.indexs.codeGeneration()); 
           str.append("call instance void ");
           str.append("[");
          
        }else
        {
            
        }
        
        return str.toString();
    }
    
}
