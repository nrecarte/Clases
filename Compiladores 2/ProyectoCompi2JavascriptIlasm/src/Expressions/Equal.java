/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import proyectocompi2javascriptilasm.AssemblyTemplate;
import Semantic.ErrorLog;
import Types.Bool;

/**
 *
 * @author Noel Recarte
 */
public class Equal extends BinaryOperator{

    public Equal(Expression left, Expression right) {
        super(left, right);
    }
    
    @Override
    public void semantic() {
        left.semanticValidation();
        right.semanticValidation();
        
        if (this.left.getType().getClass() != this.right.getType().getClass()) {
            ErrorLog.getInstance().add("Error: Operador '==' tiene tipos distintos, " + this.left.getType().toStr() + " y " + this.right.getType().toStr());
        }
        
        super.setType(new Bool());
    }

    @Override
    public String codeGeneration() {
        //String etiqueta1 = Assambly.getInstance().getLabel("Equal");
        //String etiqueta2 = Assambly.getInstance().getLabel("EndEqual");
        //return left.codeGeneration()+right.codeGeneration()+"beq "+etiqueta1+"\nldc.i4 0\nbr "+etiqueta2+"\n"+etiqueta1+":\n"+"ldc.i4 1\n"+etiqueta2+":\n";
        
        return left.codeGeneration()+right.codeGeneration()+"ceq\nldc.i4.0\nceq\n";
    }
    
}
