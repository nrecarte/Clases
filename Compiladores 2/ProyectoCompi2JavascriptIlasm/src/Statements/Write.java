/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Expressions.Expression;

/**
 *
 * @author Noel Recarte
 */
public class Write extends Statement{
    Expression param;

    public Write(Expression param) {
        this.param = param;
    }

    public Expression getParam() {
        return param;
    }

    public void setParam(Expression param) {
        this.param = param;
    } 

    @Override
    public void semanticValidation() {
        param.semanticValidation();
    }

    @Override
    public String codeGenerationStatement() {
        return param.code()+"call void [mscorlib]System.Console::WriteLine("+this.param.getType().toAssembly()+")\n";
    }
}
