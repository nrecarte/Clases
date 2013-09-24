/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Statements.Statement;
import java.util.ArrayList;

/**
 *
 * @author Noel Recarte
 */
public class Block extends Declarations{
    ArrayList<Declarations> decls;
    Statement stms;

    public Block(ArrayList<Declarations> decls, Statement stms) {
        this.decls = decls;
        this.stms = stms;
    }

    public ArrayList<Declarations> getDecls() {
        return decls;
    }

    public Statement getStms() {
        return stms;
    }

    public void setDecls(ArrayList<Declarations> decls) {
        this.decls = decls;
    }

    public void setStms(Statement stms) {
        this.stms = stms;
    } 

    @Override
    public void semanticValidation() {
        
        for(Declarations d:decls)
        {
            d.semantic();
        }
        
        if(stms != null)
        {
            this.stms.semantic();
        }       
    }

    @Override
    public String codeGenerationStament() {
        String tmp = "", funcion = "";

        tmp += ".method static void  Main() cil managed {\n";
        
        for (Declarations d : decls) {
            
            if(d instanceof DeclarationFunction)
            {
                funcion += d.codeGeneration();
            }else
            {
                tmp += d.codeGeneration();
            }  
        }

        if (stms != null) {
            tmp += this.stms.codeGeneration();
        }
        
        tmp +="}\n";

        return funcion+tmp;
    }
}