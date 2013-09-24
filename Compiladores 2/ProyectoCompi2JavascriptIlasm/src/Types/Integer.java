/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Types;

/**
 *
 * @author Noel Recarte
 */
public class Integer extends Type{

    @Override
    public java.lang.String toAssembly() {
        return "int32";
    }
    
    @Override
    public java.lang.String toStr() {
        return "int32";
    }
    
}
