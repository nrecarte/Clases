/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Types;

import Semantic.Environment;

/**
 *
 * @author Noel Recarte
 */
public class Custom extends Type{

    java.lang.String id;

    public Custom() {
    }

    public Custom(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    
    @Override
    public java.lang.String toAssembly() {
        //return Env.getIntance().getType(id);
        return id;
    }
    
    @Override
    public java.lang.String toStr() {
        return id;
    }
    
}
