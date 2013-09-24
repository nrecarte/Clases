/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import Types.Char;

/**
 *
 * @author Noel Recarte
 */
public class CharLiteral extends Literal {
    
    char value;

    public CharLiteral(char value) {
        this.value = value;
        this.setType(new Char());
    }

    public char getValue() {
        return value;
    }

    public void setlValue(char value) {
        this.value = value;
    }  

    @Override
    public String codeGen() {
        return "ldc.i4.s " +(int)this.value+"\n";
    }
}
