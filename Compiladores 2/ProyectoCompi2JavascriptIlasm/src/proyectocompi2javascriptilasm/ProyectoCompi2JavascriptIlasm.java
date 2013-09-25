/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi2javascriptilasm;

import Semantic.Environment;
import Semantic.ErrorLog;
import Declarations.Declarations;
import Declarations.Program;
import java.io.*;

/**
 *
 * @author Noel Recarte
 */
public class ProyectoCompi2JavascriptIlasm {
    
    /**
     * @param args the command line arguments
     */    
    
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            parser p = new parser(new Lexer(new FileReader("src/proyectocompi2javascriptilasm/test.pas")));
            Declarations result = (Declarations) p.parse().value;

            //validacion semantica
            result.semantic();
            ErrorLog.getInstance().print();

            //generacion de codigo
            String code = result.codeGeneration();
            System.out.println(code);
            //escribir en archivo il
            File file = new File("C:\\Users\\scsi0009\\Documents\\GitHub\\Clases\\Compiladores 2\\ProyectoCompi2JavascriptIlasm\\src\\proyectocompi2javascriptilasm\\"
                    + ((Program) result).getName().getIdentifier() + ".il");

            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
            // if file doesnt exists, then create it
            if (!file.exists()) {

                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(code);
            bw.flush();
            bw.close();

            int value = 1;
            //System.out.println(result);
        } catch (Exception e) {
            /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }
    }
}
