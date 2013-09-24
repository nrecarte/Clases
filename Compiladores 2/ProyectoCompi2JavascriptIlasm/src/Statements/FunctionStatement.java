/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Semantic.Environment;
import Semantic.ErrorLog;
import Expressions.Expression;
import Expressions.Id;
import Types.Function;
import Types.Type;
import javax.xml.stream.events.EndDocument;

/**
 *
 * @author Noel Recarte
 */
public class FunctionStatement extends Statement{
   
    Id name;
    Expression elist;

    public FunctionStatement(Id name, Expression elist) {
        this.name = name;
        this.elist = elist;
    }

    public Id getName() {
        return name;
    }

    public Expression getElist() {
        return elist;
    }

    public void setName(Id name) {
        this.name = name;
    }

    public void setElist(Expression elist) {
        this.elist = elist;
    }
    
    private int count()
    {
        Expression e = this.elist;
        
        int cont = 0;
        while(e!=null)
        {
            cont++;
            e = e.getNext();
        }
        
        return cont;
    }

    @Override
    public void semanticValidation() {
        Environment.newEnv();
        
        Type t = Environment.getIntance().getType(this.name.getIdentifier());
        Type t2 = new Function(null, null);

        if (!(t.getClass() != t2.getClass())) {
            ErrorLog.getInstance().add("Error: El identificador '" + this.name.getIdentifier() + "' no es una Function");
        } else {
            int paramSize = ((Types.Function) t).getF().size();

            //verifica cantidad de parametros
            if (paramSize != count()) {
                ErrorLog.getInstance().add("Error: La function '" + this.name.getIdentifier() + "' esperaba " + paramSize + " parametros.");
            }
            //verifica orden parametros
        }
        
        Environment.restoreEnvironment();
    }

    @Override
    public String codeGenerationStatement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
