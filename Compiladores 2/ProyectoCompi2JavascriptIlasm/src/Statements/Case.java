/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Expressions.Expression;

/**
 *
 * @author Noel Recarte
 */
public class Case extends Statement {

    Expression elist;
    Statement stms;

    public Case(Expression elist, Statement stms) {
        this.elist = elist;
        this.stms = stms;
    }

    public Expression getEList() {
        return elist;
    }

    public Statement getStms() {
        return stms;
    }

    public void setExprs(Expression exprs) {
        this.elist = exprs;
    }

    public void setStms(Statement stms) {
        this.stms = stms;
    }    

    @Override
    public void semanticValidation() {
        
        this.elist.semantic();
        
        if (this.stms != null) {
            this.stms.semantic();
        }
    }

    @Override
    public String codeGenerationStatement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
