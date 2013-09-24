/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Expressions.Expression;
import Statements.Statement;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class ReferenceVariable extends Declarations{
    
    Expression ids;
    Type t;

    public ReferenceVariable(Expression ids, Type t) {
        this.ids = ids;
        this.t = t;
    }

    public Expression getIds() {
        return ids;
    }

    public Type getT() {
        return t;
    }

    public void setIds(Expression ids) {
        this.ids = ids;
    }

    public void setT(Type t) {
        this.t = t;
    }

    @Override
    public void semanticValidation() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String codeGenerationStament() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
