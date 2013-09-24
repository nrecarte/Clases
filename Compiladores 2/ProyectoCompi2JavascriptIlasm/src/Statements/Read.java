/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Semantic.Environment;
import Semantic.ErrorLog;
import Expressions.Expression;
import Expressions.Id;

/**
 *
 * @author Noel Recarte
 */
public class Read extends Statement{
    Id i;

    public Read(Id i) {
        this.i = i;
    }

    @Override
    public void semanticValidation() {
        i.semanticValidation();
    }

    @Override
    public String codeGenerationStatement() {
        
        String tmp;
        tmp = "call " + i.getType().toStr() + " [mscorlib]System.Console::Read()\n";
        tmp += "stloc "+Environment.getIntance().getNumber(i.getIdentifier())+"\n";
        
        return tmp;
      }
    
}
