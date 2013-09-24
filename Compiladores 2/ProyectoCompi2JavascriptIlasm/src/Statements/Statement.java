/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Noel Recarte
 */
public abstract class Statement { 
    Statement next=null;
    public String fname = "";
    
    public abstract void semanticValidation(); 
    
    public void semantic()
    {
        this.semanticValidation();
        
        if(next!=null)
        {
            next.semantic();
        }
    }

    public void setNext(Statement next) {
        this.next = next;
    }

    public Statement getNext() {
        return next;
    }
    
    public abstract java.lang.String codeGenerationStatement();
    
    public java.lang.String codeGeneration()
    {
        String valor = this.codeGenerationStatement();
        if(next!= null)
        {
            valor+=next.codeGeneration();
        }
        return valor;
    }
    
}
