/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import Types.Bool;

/**
 *
 * @author Noel Recarte
 */
public class BooleanLiteral extends Literal {
    
    Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
        this.setType(new Bool());
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String codeGen() {
        
        int flag = 0;

        if (value) {
            flag = 1;
        }

        return "ldc.i4." + flag + "\n";
    }
   
}
