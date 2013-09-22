/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolSentencia;

import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author Noel Recarte
 */
public class Valores {
    
    private static Valores instance = null;
    
    private Valores(){}
    
    public static Valores getInstance()
    {
        if(instance==null)
            instance = new Valores();
        return instance;
    }    
    //-------------------------------------------
    Hashtable<String, Integer> valores = new Hashtable<>();
    
    public void setValue(String id,Integer value)
    {
        valores.put(id, value);
    }
    
    public void readValue(String id)
    {
        Scanner entrada = new Scanner(System.in);
        setValue( id,entrada.nextInt());
    }
    
    public Integer getValue(String id)
    {
        
        if(valores.containsKey(id))
            return valores.get(id);
        else
            return 0;
    }
    
}
