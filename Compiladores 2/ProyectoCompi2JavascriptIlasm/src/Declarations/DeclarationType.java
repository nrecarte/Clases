/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Declarations;

import Semantic.Environment;
import Expressions.Expression;
import Expressions.Id;
import Types.Array;
import Types.Custom;
import Types.Record;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class DeclarationType extends Declarations{
    
    Id id;
    Type t;
    public String record;
    public String arr;

    public DeclarationType(Id id, Type t) {
        this.id = id;
        this.t = t;
        record = "";
    }

    public Expression getId() {
        return id;
    }

    public Type getT() {
        return t;
    }

    public void setIds(Id id) {
        this.id = id;
    }

    public void setT(Type t) {
        this.t = t;
    }
    
    public Id find(String id)
    {
        if(this.id.getIdentifier().equals(id))
        {
            return this.id;
        }
        
        return null;
    }

    @Override
    public void semanticValidation() {
        //id.setType(t);
        
        if(t instanceof Record )
        {
            Environment.getIntance().putRecord(id.getIdentifier(), t);
        }else {
            Environment.getIntance().put(id.getIdentifier(), t);
        }
    }

    @Override
    public String codeGenerationStament() {
        String tmp = "";

        if (t instanceof Record) {
            Record r = (Record) t;
            tmp += ".class public auto ansi beforefieldinit " + this.id.getIdentifier() + "\n"
                    + "extends [mscorlib]System.Object\n"
                    + "{\n";
            tmp += r.toAssembly();
            tmp += "}\n";
        }else if(t instanceof Array)
        {
            Array a = (Array) t;
        }

        return tmp;
    }
    
}
