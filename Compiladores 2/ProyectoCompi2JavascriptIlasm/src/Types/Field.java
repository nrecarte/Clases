/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Types;

import java.util.ArrayList;
import java.util.Hashtable;
import sun.misc.JavaLangAccess;


/**
 *
 * @author Noel Recarte
 */
public class Field extends Hashtable<java.lang.String, Type> {
    ArrayList<java.lang.String> ids= new ArrayList<>();

    @Override
    public synchronized Type put(java.lang.String key, Type value) {
        ids.add(key);
        return super.put(key, value);
    }

    public ArrayList<java.lang.String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<java.lang.String> ids) {
        this.ids = ids;
    }
    
    public void addVars(Object [] p)
    {
        ArrayList<java.lang.String> ids = (ArrayList<java.lang.String>)p[0];
        Type t = (Type)p[1];
        for(int i=0;i<ids.size();i++)
        {
            this.put(ids.get(i), t);
        }
    }
}
