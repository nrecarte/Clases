/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Semantic.SymbolTable;

/**
 *
 * @author Noel Recarte
 */
public abstract class Declarations {   
    Declarations next=null;
    public SymbolTable table=null;
    public Environment environment=null;
    public String tmp = "";

    
    public void setNext(Declarations next) {
        this.next = next;
    }

    public Declarations getNext() {
        return next;
    }
    
    public abstract void semanticValidation();   
    
    public void semantic()
    {
        this.semanticValidation();
        
        if(next!= null)
        {
            next.semantic();
        }
    }
    
    public abstract String codeGenerationStament();
    
    public String codeGeneration()
    {
        String valor = this.codeGenerationStament();
        if(next!= null)
        {
            valor+=next.codeGeneration();
        }
        return valor;
    }      
}
