/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;

import Expressions.Expression;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class Environment {
    
    private static Environment instance=null;
    private static Environment tmp=null;
    public SymbolTable table;   
    private Environment prev;
    
    private Environment(Environment prev) {
        this.prev = prev;
        this.table = new SymbolTable();
    }

    public static void setInstance(Environment instance) {
        Environment.instance = instance;
    }
    
    public Type getFunction(String name)
    {
        Type found = null;
        for(Environment e=this; e!=null; e=e.prev)
        {
            found = e.table.getfunction(name);
            
            if(found != null)
            {
                return found;
            }
        } 
        return found;
    }

    public static Environment getIntance() {
        
        if(instance==null)
        {
            instance = new Environment(null);
        }
        return instance;
    }

    public Environment getPrev() {
        return prev;
    }

    public static void newEnvironment() 
    {
        tmp = instance;
        instance = new Environment(instance);
    }
    
    public static Environment newEnv()
    {
        return new Environment(instance);
    }
    
    public static void restoreEnvironment()
    {
        instance = tmp;
    }
    
    public void put(String id, Type t)
    {
        this.table.addVariable(id, t);
    }
    
    public void putFunction(String id, Type t)
    {
        this.table.addFunction(id, t);
    }
    
    public void putRecord(String id, Type t)
    {
        this.table.addRecord(id, t);
    }
    
    public void putArg(String id, Type t)
    {
        this.table.addArgument(id, t);
    }
    
    public int getNumber(String name)
    {
        int found = -1;
        for(Environment e=this; e!=null; e=e.prev)
        {   
            found = e.table.getNumber(name);           
            if( found != -1)
            {
                return found;
            }
        } 
        return found;
    }
    
    public int getArgNumber(String name)
    {
        int found = -1;
        for(Environment e=this; e!=null; e=e.prev)
        {   
            found = e.table.getArgNumber(name);           
            if( found != -1)
            {
                return found;
            }
        } 
        return found;
    }
    
    public Type getType(String name)
    {
        Type found = null;
        for(Environment e=this; e!=null; e=e.prev)
        {
            found = e.table.getType(name);
            
            if(found != null)
            {
                return found;
            }
        } 
        return found;
    }

    public SymbolTable getTable() {
        return table;
    }
}