/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;

import Expressions.Expression;
import Types.Array;
import Types.Record;
import Types.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Noel Recarte
 */
public class SymbolTable {
     //variables
    Hashtable<String,Integer> table = new Hashtable<>();
    Hashtable<String,Type> typeTable = new Hashtable<>();
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> typelist = new ArrayList<String>();
    
    //funciones 
    Hashtable<String,Type> funtions = new Hashtable<>();
    Hashtable<String,Type> arguments = new Hashtable<>();
    Hashtable<String,Type> localFunctions = new Hashtable<>();
    Hashtable<String,Integer> argumentInt = new Hashtable<>();
    
       
    //records
    Hashtable<String, Type> records = new Hashtable<>();

    public SymbolTable() {
    }
    
    public Type getfunction(String name) {
        if (funtions.containsKey(name)) {
            return funtions.get(name);
        }
        return null;
    }

    public void addVariable(String name, Type t)
    {
        if(!table.containsKey(name))
        {
            table.put(name, new Integer(table.size()));
            typeTable.put(name, t);
            list.add(name);
            typelist.add(t.toStr());
        }else
        {
             ErrorLog.getInstance().add("Error: No se puede crear variable '"+name+"', este ya existe.\n");
        }
    }
    
    public void addFunction(String name, Type t)
    {
        if(!funtions.containsKey(name))
        {
            funtions.put(name, t);
        }else
        {
            ErrorLog.getInstance().add("Error: No se puede crear function '"+name+"', este ya existe.\n");
        }
    }
    
    public void addArgument(String name, Type t)
    {
        if(!arguments.containsKey(name))
        {
            arguments.put(name, t);
            argumentInt.put(name, new Integer(argumentInt.size()));
        }else
        {
            ErrorLog.getInstance().add("Error: No se puede crear argumento '"+name+"', duplicado.\n");
        }
    }
    
    public void addRecord(String name, Type t)
    {
        if(!records.containsKey(name))
        {
            records.put(name, t);
        }else
        {
            ErrorLog.getInstance().add("Error: No se puede crear Registro '"+name+"', este ya existe.\n");
        }
    }
    
    public int getArgNumber(String name) {
        
        if (argumentInt.containsKey(name)) {
            return argumentInt.get(name).intValue();
        }    
        return -1;
    }
    
    public int getNumber(String name) {
        if (table.containsKey(name)) {
            return table.get(name).intValue();
        }
        return -1;
    }
    
    public Type getType(String name)
    {       
        Type t = arguments.get(name);
        
        if(t == null)
        {
            t = typeTable.get(name);
        }
        return t;
    }
    
     public String getLocals()
     {
         StringBuilder local = new StringBuilder();
         int offset = 0; 
         String tmp = "                          ";
         
         local.append(".locals init (");
         offset = local.length();
         
         for (int i = 0; i < list.size(); i++) {
             Type ti = typeTable.get(list.get(i));

             if (ti instanceof Array) {
                 
                 //int32[0...,0...,0...] 'array'
                 Array a = (Array)ti;
                 local.append("[");
                 local.append(i);
                 local.append("] ");
                 
                 local.append(a.getExprs().getType().toAssembly());
                 local.append(" ");
                 
                 for (int b = 0; b < a.count(); b++) {
                     
                     local.append("[");
                     local.append("0...");
                     local.append("]");
                 }
                 local.append(list.get(i));
                 
             } else {

                 local.append("[");
                 local.append(i);
                 local.append("] ");
                 local.append(typelist.get(i));
                 local.append(" ");
                 local.append(list.get(i));
                
             }
             
              if (i < list.size() - 1) {
                 local.append(",\n");
                 local.append(tmp, 0, offset);
             }
         }
         local.append(")\n");
         return local.toString();
     }
     
     public String getArgs() {
        String tmp = "";
        Enumeration e = this.arguments.keys();
        Object clave;
        Type valor;
        while (e.hasMoreElements()) {
            clave = e.nextElement();
            valor = this.arguments.get(clave);
            tmp += valor.toAssembly() + " " + clave + ",";
        }

        if (tmp.length() > 0) {
            tmp = tmp.substring(0, tmp.length() - 1);
        }
        return tmp;
    }

}
