/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Types;

import Semantic.Environment;
import Statements.Statement;
import Declarations.Declarations;
import Declarations.DeclarationType;
import Declarations.DeclarationVariable;
import Expressions.Expression;
import Expressions.Id;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Noel Recarte
 */
public class Record extends Type{
    
    Hashtable<java.lang.String, Type> table;

    public Record(Hashtable<java.lang.String, Type> table) {
        this.table = table;
    }

    public Hashtable<java.lang.String, Type> getTable() {
        return table;
    }

    public void setTable(Hashtable<java.lang.String, Type> table) {
        this.table = table;
    }
        
    @Override
    public java.lang.String toAssembly() {
        
        java.lang.String tmp = "";
        Enumeration e = table.keys();
        Object clave;
        Types.Type valor;
        while(e.hasMoreElements() ){
            clave = e.nextElement();
            valor = table.get( clave );
            tmp += ".field public " + valor.toAssembly() +" "+ clave + "\n";            
        }
        return tmp;
    }
    
    @Override
    public java.lang.String toStr() {
        return "Record";
    }
    
}
