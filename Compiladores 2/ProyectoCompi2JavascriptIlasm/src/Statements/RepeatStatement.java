/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import proyectocompi2javascriptilasm.AssemblyTemplate;
import Semantic.ErrorLog;
import Expressions.Expression;
import Types.Bool;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class RepeatStatement extends Statement{
    Expression expr;
    Statement stms;
    

    public RepeatStatement(Expression expr, Statement stms) {
        this.expr = expr;
        this.stms = stms;
    }

    public Expression Expr() {
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
              
        Type t = new Bool();      
        
        if (stms != null) {
            stms.semantic();
        }
        
        this.expr.semantic();
         
        if(this.expr.getType().getClass() != t.getClass())
        {
            ErrorLog.getInstance().add("Error: While no soporta tipo "+this.expr.getType().toString());
        }  
    }

    @Override
    public String codeGenerationStatement() {
        java.lang.String etiqueta1 = AssemblyTemplate.getInstance().getLabel("repeat");

        java.lang.String codeRepeat = stms != null ? stms.codeGeneration() : "";

        return etiqueta1 + ":\n" + codeRepeat + this.expr.codeGeneration() + "brtrue " + etiqueta1 + "\n";

    }

}
