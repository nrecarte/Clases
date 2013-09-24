/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressions;

import Semantic.Environment;
import Semantic.ErrorLog;
import Semantic.SymbolTable;
import Types.Array;
import Types.Null;
import Types.Record;
import Types.Type;

/**
 *
 * @author Noel Recarte
 */
public class Id extends Expression {
    
    SymbolTable table;
    String identifier;
    Expression right;
    public String fname;

    public Id(String Identifier) {
        this.identifier = Identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public void setIdentifier(String Identifier) {
        this.identifier = Identifier;
    }

    @Override
    public void semanticValidation() {

        //validacion de identificadores
        Type t = Environment.getIntance().getType(identifier);
        //Type t = this.getEnv().getType(identifier);
        
        if (t == null) {
            
            t = Environment.getIntance().getFunction(identifier);
            if( t == null ){
                ErrorLog.getInstance().add("Error: Variable '" + this.identifier + "' no existe."); 
                super.setType(new Null());
            }         
            super.setType(t);
            
        } else {
            
            super.setType(t);
            Expression e = this.right;
            String _id = this.identifier;
            
            while (e != null) {
                if (e instanceof ArrayExpression) {
                    ArrayExpression ae = (ArrayExpression) e;
                    Array _t = (Array) Environment.getIntance().getType(_id);
                    if (ae.count() != _t.count()) {
                        ErrorLog.getInstance().add("Error: Arreglo '" + this.identifier + "' esperaba " + _t.count() + " parametos.");
                    }

                    //seteo el tipo integer
                    super.setType(_t.getT());
                } else if (e instanceof FieldAccess) {
                    FieldAccess f = (FieldAccess) e;
                    
                    Type _tmp = Environment.getIntance().getType(_id);
                    
                    if (_tmp instanceof Record) {
                        Record r = (Record) Environment.getIntance().getType(_id);

                        Type _t = r.getTable().get(f.getAtribute().getIdentifier());

                        if (_t == null) {
                            ErrorLog.getInstance().add("Error: Atributo '" + f.getAtribute().getIdentifier() + "' no encontrado en registro '" + _id + "'");
                        } else {
                            super.setType(_t);
                        }
                        //actualizo el id para la siguiente validacion
                        _id = f.getAtribute().getIdentifier();
                    } else if (_tmp instanceof Array) {
                        Array a = (Array) Environment.getIntance().getType(_id);
                        Record r = (Record) Environment.getIntance().getType(a.getT().toStr());

                        Type _t = r.getTable().get(f.getAtribute().getIdentifier());

                        if (_t == null) {
                            ErrorLog.getInstance().add("Error: Atributo '" + f.getAtribute().getIdentifier() + "' no encontrado en registro '" + _id + "'");
                        } else {
                            super.setType(_t);
                        }
                    }
                }
                e = e.getNext();
            }
        }
    }

    @Override
    public String codeGeneration() {     
        String tmp = "", r = "";
        if (this.right != null) {
            r = right.codeGeneration();
        }
        
        if (Environment.getIntance().getArgNumber(identifier) != -1) {
            tmp = "ldarg " + Environment.getIntance().getArgNumber(identifier) + "\n" + r;
        } else {
            tmp = "ldloc " + Environment.getIntance().getNumber(identifier) + "\n" + tmp;
        }
        
        return tmp;
    }
}
