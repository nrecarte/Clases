/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Expressions.Expression;
import Expressions.Id;
import Types.Custom;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class DeclarationVariable extends Declarations{
    Expression ids;
    Type t;

    public DeclarationVariable(Expression ids, Type t) {
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
    
    public Id find(String id)
    {
        Expression e = ids;
        while (e != null) {
            Id i = (Id) e;

            if(i.getIdentifier().contains(id))
            {
                return i;
            }

            e = e.getNext();
        }
        
        return null;
    }

    @Override
    public void semanticValidation() {
        
        Expression e=ids;
        while (e != null) {
            Id i = (Id) e;

            //parte obscura
            Type _t = new Custom();
            if (_t.getClass() == t.getClass()) {
                t = Environment.getIntance().getType(((Custom)t).getId());
            }

            Environment.getIntance().put(i.getIdentifier(), t);

            e = e.getNext();
        }
    }

    @Override
    public String codeGenerationStament() {
        return Environment.getIntance().getTable().getLocals();
    }
    
}

