/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

/**
 *
 * @author Noel Recarte
 */
public class FloatLiteral extends Literal {
    
    float value;

    public FloatLiteral(float value) {
        this.value = value;
        this.setType(new Types.Float());
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String codeGen() {
        return "ldc.r4 " +this.value+"\n";
    }
}
