/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

/**
 *
 * @author Noel Recarte
 */
public class StringLiteral extends Literal {
    
    String value;

    public StringLiteral(String value) {
        this.value = value;
        this.setType(new Types.String());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String codeGen() {
        return "ldstr \"" +this.value+"\"\n";
    }
      
}
