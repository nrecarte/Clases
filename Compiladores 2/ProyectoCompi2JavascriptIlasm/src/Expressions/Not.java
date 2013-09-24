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
public class Not extends Expression{

   Expression expr;

    public Not(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void semanticValidation() {
        
        expr.semantic();
        
        if(!(expr.getType() instanceof Types.Bool))
        {
            ErrorLog.getInstance().add("Error: Negacion requiere Tipo "+this.expr.getType().toString()+".");
        }
    }
    
    @Override
    public String codeGeneration() {
        return expr.codeGeneration()+"ldc.i4.0\nceq\n";
    }
   
}
