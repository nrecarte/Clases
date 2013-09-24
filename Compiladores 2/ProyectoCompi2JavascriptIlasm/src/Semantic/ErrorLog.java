/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;

import java.util.ArrayList;

/**
 *
 * @author SergioJavier
 */
public class ErrorLog {
    
    public static ErrorLog instance = null;
    ArrayList<String> errorList = new ArrayList<>();

    private ErrorLog() {
    }
    
    public static ErrorLog getInstance()
    {
        if(instance==null)
        {
            instance = new ErrorLog();
        }
        
        return instance;
    }
    
    public void add(String message)
    {
        errorList.add(message);
    }
    
    public void print()
    {
        for(String error:errorList)
        {
            System.out.println(error);
        }
    }
            
}
