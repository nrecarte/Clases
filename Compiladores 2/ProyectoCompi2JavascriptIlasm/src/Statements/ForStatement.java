/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import proyectocompi2javascriptilasm.AssemblyTemplate;
import Semantic.Environment;
import Semantic.ErrorLog;
import Expressions.Expression;
import Expressions.Id;
import Expressions.L;
import Expressions.IntegerLiteral;
import Expressions.Sum;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class ForStatement extends Statement{
    Expression expr;
    Assign ass;
    Statement stms;

    public ForStatement(Assign ass, Expression expr, Statement stms) {
        this.expr = expr;
        this.ass = ass;
        this.stms = stms;
    }

    public Expression getExpr() {
        return expr;
    }

    public Assign getAss() {
        return ass;
    }

    public Statement getStms() {
        return stms;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public void setAss(Assign ass) {
        this.ass = ass;
    }

    public void setStms(Statement stms) {
        this.stms = stms;
    }    

    @Override
    public void semanticValidation() {

        this.ass.semantic();
        this.expr.semantic();

        Type t = new Types.Integer();
        if (this.expr.getType().getClass() != t.getClass()) {
            ErrorLog.getInstance().add("Error: Expresion de la sentecia for esperaba tipo entero, se encontro: " + this.expr.getType().toStr());
        } else {
            if (this.ass.getRight().getType().getClass() != this.expr.getType().getClass()) {
                ErrorLog.getInstance().add("Error: El rango de la intruccion FOR debe poseer tipos Entero, se encontro: " + this.ass.right.getType().toStr() + " y " + this.expr.getType().toStr());
            }
        }

        if (stms != null) {
            stms.semantic();
        }
        //Env.restoreEnv();
    }

    @Override
    public String codeGenerationStatement() {
        String etiqueta1 = AssemblyTemplate.getInstance().getLabel("Condicion");
        String etiqueta2 = AssemblyTemplate.getInstance().getLabel("For");
        
        // FOR ID:i ASSIGN expr:r TO expr:e DO compound:c
        Expression e = new L(ass.i,expr);
        
        //incrementador
        Assign a = new Assign(ass.i, new Sum(ass.i, new IntegerLiteral(1)));
        
        return ass.codeGeneration()+"br "+etiqueta1+"\n"+etiqueta2+":\n"+stms.codeGeneration()+a.codeGeneration()+etiqueta1+":\n"+e.codeGeneration()+"brtrue "+etiqueta2+"\n";
   
    }
    
}
