package tema2;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * mainul temei
 * @author magni
 */
public class Tema2 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

    //creem file wizardu care ne va rezolva problema
    FileWizard file_reader = new FileWizard (args[0]);
    //incepem citirea fiserului
    file_reader.readFile();
    
    }
}
