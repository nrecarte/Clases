/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import proyectocompi2javascriptilasm.AssemblyTemplate;
import Semantic.*;
import Expressions.Expression;
import Types.*;

/**
 *
 * @author Noel Recarte
 */
public class WhileStatement extends Statement{
    Expression expr;
    Statement stms;

    public WhileStatement(Expression param, Statement stms) {
        this.expr = param;
        this.stms = stms;
    }

    public Expression getExpr() {
        return expr;
    }

    public Statement getStms() {
        return stms;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public void setStms(Statement stms) {
        this.stms = stms;
    }
    
    @Override
    public void semanticValidation() {
  
        this.expr.semantic();
        
        Type t = new Types.Bool();
        if(this.expr.getType().getClass() != t.getClass())
        {
            ErrorLog.getInstance().add("Error: While no soporta tipo "+this.expr.getType().toStr());
        }
        
        if(this.stms != null)
        {
            this.stms.semantic();
        }     
    }

    @Override
    public java.lang.String codeGenerationStatement() {
        java.lang.String etiqueta1 = AssemblyTemplate.getInstance().getLabel("Condicion");
        java.lang.String etiqueta2 = AssemblyTemplate.getInstance().getLabel("While");

        java.lang.String codeWhile = stms != null ? stms.codeGeneration() : "";

        return "br " + etiqueta1 + "\n" + etiqueta2 + ":\n" + codeWhile + etiqueta1 + ":\n" + this.expr.codeGeneration() + "brtrue " + etiqueta2+"\n";
        //return etiqueta1+":\n"+this.expr.codeGeneration()+"brzero "+etiqueta2+"\n"+codeWhile+"br "+etiqueta1+"\n"+etiqueta2+":\n";  
    }
    
}
