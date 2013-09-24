/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Semantic.Environment;
import Semantic.ErrorLog;
import Expressions.Expression;
import Expressions.Id;
import Types.Array;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class Assign extends Statement{

    Id i;
    Expression right;

    public Assign(Id i, Expression right) {
        this.i = i;
        this.right = right;
    }

    public Id getI() {
        return i;
    }

    public void setI(Id i) {
        this.i = i;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
    
   
    @Override
    public void semanticValidation() {
        
        right.semantic();
        this.i.semantic();
        

        if (this.i.getType().getClass() != this.right.getType().getClass()) {
            ErrorLog.getInstance().add("Error: Asignacion con tipos incompatibles, " + this.i.getType().toStr() + " y " + this.right.getType().toStr());
        }
    }

    @Override
    public String codeGenerationStatement() {

        StringBuilder builder = new StringBuilder();
        String code = "";

        Type _t = this.i.getType();
        
        if (_t instanceof Array) {
            _t = ((Array) this.i.getType());

            builder.append("ldloc ").append(Environment.getIntance().getNumber(this.i.getIdentifier())).append("\n");

           // Expression e = _t.getExprs();

//            while (e != null) {
//                e = e.getNext();
//            }
        } else if (Environment.getIntance().getFunction(i.getIdentifier()) != null) {
            code = this.right.code();
        } else {
            code = this.right.code() + "stloc." + Environment.getIntance().getNumber(i.getIdentifier()) + "\n";

        }

        return code;
    }

}
