/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Types;

/**
 *
 * @author Noel Recarte
 */
public class Function extends Type{

    Field f;
    Type t;

    public Function(Field f, Type t) {
        this.f = f;
        this.t = t;
    }

    public Field getF() {
        return f;
    }

    public void setF(Field f) {
        this.f = f;
    }

    public Type getT() {
        return t;
    }

    public void setT(Type t) {
        this.t = t;
    }
    
    public java.lang.String fieldStr() {
        StringBuilder tmp = new StringBuilder("");
        java.lang.String _tmp = "";
        if (f != null) {
            for (java.lang.String i : f.getIds()) {
                tmp.append(i);
                tmp.append(",");
            }

            if (tmp.length() > 0) {
                _tmp = tmp.substring(0, tmp.length() - 1).toString();
            }
        }
        //le quito la ultima coma
        return _tmp;
    }
    
    @Override
    public java.lang.String toAssembly() {
        return t.toStr();
    }
    
    @Override
    public java.lang.String toStr() {
        return t.toStr();
    }
    
}
