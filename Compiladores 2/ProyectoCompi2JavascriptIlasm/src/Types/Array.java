/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Types;

import Expressions.Expression;
import java.util.ArrayList;

/**
 *
 * @author Noel Recarte
 */
public class Array extends Type{
    Expression exprs;
    Type t;

    public Array(Expression exprs, Type t) {
        this.exprs = exprs;
        this.t = t;
    }

    public Expression getExprs() {
        return exprs;
    }

    public Type getT() {
        return t;
    }

    public void setExprs(Expression exprs) {
        this.exprs = exprs;
    }

    public void setT(Type t) {
        this.t = t;
    }
    
    public int count()
    {
        Expression e = this.exprs;
        
        int cont = 0;
        while(e!=null)
        {
            cont++;
            e = e.getNext();
        }
        
        return cont;     
    }

    @Override
    public java.lang.String toAssembly() {
        return "Array";
    }

    @Override
    public java.lang.String toStr() {
        return "Array";
    }

}
