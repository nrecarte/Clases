/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

/**
 *
 * @author Noel Recarte
 */
public class IntegerLiteral extends Literal {
    
    int value;

    public IntegerLiteral(int value) {
        this.value = value;
        this.setType(new Types.Integer());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String codeGen() {
        return "ldc.i4 " +this.value+"\n";
    }
    
    
}
