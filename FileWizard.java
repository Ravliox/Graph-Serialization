package tema2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * clasa de citire a fisierului si prelucrarea comenzilor citite
 * @author magni
 */
public class FileWizard {
    
    BufferedReader in;
    Nod[] graf = new Nod[100];
    int nr_elemente = 0;
    int max = 100;
    int current_id = 0;
    int[] settings = new int[3];
    
    /**
     * constructor
     * @param file_name fisierul ce contine detaliile despre graf
     * @throws FileNotFoundException
     */
    FileWizard (String file_name) throws FileNotFoundException{
        in = new BufferedReader(new FileReader(file_name));
    }
    
    /**
     * functia de adaugare in graf
     * @param tokens linia sparta dupa spatiu
     */
    void add(String[] tokens){
        if (nr_elemente == max){
            Nod[] graf_aux = new Nod[2 * max];
            System.arraycopy(graf, 0, graf_aux, 0, nr_elemente);
            graf = graf_aux;
            max = max * 2;
        }
         
        switch (tokens[1].charAt(3)) {
            case 'A':
                graf[nr_elemente] = new NodA(settings[0], tokens[2], 'A');
                break;
            case 'B':
                graf[nr_elemente] = new NodB(settings[1], tokens[2], 'B');
                break;
            case 'C':
                graf[nr_elemente] = new NodC(settings[2], tokens[2], 'C');
                break;
            default:
                break;
            }
            int i;
            for (i = 3; i < tokens.length; i++){                
                int k;
                            
                for (k = 0; k < nr_elemente; k++){                                
                    if (graf[k].name.equals(tokens[i])){
                        graf[k].adiacenta.add(graf[nr_elemente]);
                        graf[nr_elemente].adiacenta.add(graf[k]);
                        break;
                    }
                }                           
            }    
                        
            nr_elemente++;            
        }
    /**
     * metoda de stergere din graf
     * @param tokens linia sparta dupa spatiu
     */    
    void del (String[] tokens){
        int k = 0;      
        while ( !(graf[k].name.equals(tokens[2]) ) ){
            k++;
        }
        Nod t = graf[k];
        t.adiacenta.start();
        Nod cursor;
        while (t.adiacenta.hasNext()){
            cursor = t.adiacenta.next();
            cursor.adiacenta.del(t);
        }
        for (; k < nr_elemente - 1; k++){
            graf[k] = graf[k + 1];
        }
         nr_elemente--;            
    }
    /**
     * functia de adaugare de muchii
     * @param tokens linia sparta dupa spatiu
     */    
    void addM (String [] tokens){
        String nod1 = tokens[1];
        String nod2 = tokens[2];
        int k = 0;
        int j = 0;
        while ( !(graf[k].name.equals(nod1))){
            k++;
        }           

        while ( !(graf[j].name.equals(nod2))){
            j++;
        }           

        graf[k].adiacenta.add(graf[j]);
        graf[j].adiacenta.add(graf[k]);
    }
    
    /**
     * functia de stergere de muchii
     * @param tokens linia sparta dupa spatiu
     */
    void delM (String [] tokens){
        String nod1 = tokens[1];
        String nod2 = tokens[2];
        int k = 0;
        int j = 0;
        while ( !(graf[k].name.equals(nod1))){
            k++;
        }

        while ( !(graf[j].name.equals(nod2))){
            j++;
        }
                        
        graf[k].adiacenta.del(graf[j]);
        graf[j].adiacenta.del(graf[k]);
    }
    
    /**
     * functia de serializare
     * @param file_name numele fisierului in care se face serializarea
     * @param nume_nod numele nodului de la care incepe serializarea
     * @throws FileNotFoundException 
     */
    void serialize (String file_name, String nume_nod) throws FileNotFoundException{
        PrintWriter ser_out = new PrintWriter(file_name);
        int nivel = 0;
        
        int k = 0;
        while (! (graf[k].name.equals(nume_nod)) ){
            k++;
        }
        Nod[] IdList = new Nod[200];
        
        Nod t = graf[k];
        IdList[0] = t;
        writeObject(ser_out, t, nivel);
        this.current_id++;        
        
        t.adiacenta.start();
        Nod cursor;
        while (t.adiacenta.hasNext()){
            cursor = t.adiacenta.next();
            serialize_aux(ser_out, cursor, IdList, nivel + 1);           
        }
                
        closeTag(ser_out, t, nivel);
        ser_out.println("</Object>");
        
        ser_out.close();
        
    }
    
    /**
     * functie ajutatoare; parcurgere de arbore in adancime
     * @param out fisierul in care se scrie serializarea
     * @param nod nodul la care suntem in serializare
     * @param IdList vector in care sunt contine nodurile care au fost serializate deja
     * @param nivel nivelul la care suntem in recursivitate
     */
    void serialize_aux(PrintWriter out, Nod nod,  Nod[] IdList, int nivel){
        int k;
        int j = 0;
        String indent = "";
        while (j < nivel){
            indent = indent + "    ";
            j++;
        }
        
        for (k = 0; k < this.current_id; k++){
            if(IdList[k].name.equals(nod.name)){
                out.println(indent + "<Reference class=\"Nod"+IdList[k].type+"\" Version=\"" + IdList[k].iteration + "\" id = \"" + k +"\">");
                return;
            }
        }
        IdList[this.current_id] = nod;
        writeObject(out, nod, nivel);
       
        this.current_id++;
        nod.adiacenta.start();
        Nod cursor;
        while (nod.adiacenta.hasNext()){
            
            cursor = nod.adiacenta.next();
            serialize_aux(out, cursor, IdList, nivel + 1 );

        }        
        
        closeTag(out, nod, nivel);
        out.println(indent + "</Object>");
        
    }
    
    /**
     * functia de scriere a nodului in fisierul de serializare
     * @param out fisierul de serializare
     * @param t nodul serializat
     * @param nivel nivelul la care suntem in recurenta
     */
    void writeObject(PrintWriter out, Nod t, int nivel){
        int j = 0;
        String indent = "";
        while (j < nivel){
            indent = indent + "    ";
            j++;
        }
        
        String line = indent + "<Object class=\"Nod" + t.type + "\" Version=\"" + t.iteration + "\" id = \"" + this.current_id +"\">";
        out.println(line);
        
        indent = indent + "  ";
        line = indent + "<Name>" + t.name + "</Name>";
        out.println(line);
        
        switch (t.iteration){
            case 2:
            {
               out.println(indent + "<VECTOR>" + "<" + nivel + ">");
                break;
            }
            case 1:
            {
                out.println(indent + "<LIST>" + "<" + nivel + ">");
                break;
            }
            case 3:
            {
                out.println(indent + "<SET>" + "<" + nivel + ">");
            }
        }       
    }
    
    /**
     * functie de inchidere a tagurilor dupa ce s-a terminat serializare
     * @param out fisierul de serializare
     * @param t nodul serializat
     * @param nivel nivelul la care suntem in recurenta
     */
    void closeTag(PrintWriter out, Nod t, int nivel){
        int j = 0;
        String indent = "";
        while (j < nivel){
            indent = indent + "    ";
            j++;
        }
        indent = indent + "  ";
        switch (t.iteration){
            case 2:{
                out.println(indent + "</VECTOR>" + "<" + nivel + ">");
                break;
            }
            case 1:{
                out.println(indent + "</LIST>" + "<" + nivel + ">");
                break;
            }
            case 3:{
                out.println(indent + "</SET>" + "<" + nivel + ">");
                break;
            }
        }
    }
    
    /**
     * functia de deserializare
     * @param file_name fisierul din care citim
     * @throws FileNotFoundException
     * @throws IOException 
     */    
    void deserialize (String file_name) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file_name));
        String cast_log = "Deserialize_" + file_name +"_CAST.log";
        PrintWriter log_out = new PrintWriter(cast_log);
        
        String linie = reader.readLine();
        
        String[] tokens = linie.split(" ");
        int k;
        
        char type = tokens[1].charAt(10);
        
        String[] iterations = tokens[2].split("\"");
        int iteration = Integer.parseInt(iterations[1]);
        
        String[] ids = tokens[5].split("\"");       
        int id = Integer.parseInt(ids[1]);
        
        linie = reader.readLine();
        String[] linie_aux = linie.split("[<>]+");
        
        String name = linie_aux[2];
       
        nr_elemente = current_id;
        Nod[] new_graf = new Nod[nr_elemente];
        graf = new_graf;
        
        if (iteration < settings[type - 65]){
            log_out.println("OK cast Nod" + type + " " + name + " from Version=\"" + iteration +"\" to Version =\"" + settings[type - 65] +"\"");
        }
        else if(iteration > settings[type - 65]){
            log_out.println("Fail cast Nod" + type + " " + name + " from Version=\"" + iteration +"\" to Version =\"" + settings[type - 65] +"\"");
            iteration = settings[type-65];
        }
        
        switch (type){
            case 'A':
            {
                graf[id] = new NodA(iteration, name, type);
                break;
            }
            case 'B':
            {
                graf[id] = new NodB(iteration, name, type);
                break;
            }
            case 'C':
            {
                graf[id] = new NodC(iteration, name, type);
                break;
            }
        }
        
        linie = reader.readLine();
       
        tokens = linie.split("[<>]+");
        
        String end_mark = tokens[0] + "</" + tokens[1] + "><" + tokens[2] + ">";
        linie = reader.readLine();
        while ( !(linie.equals(end_mark)) ){
            deserialize_aux(reader, new_graf[id], linie, log_out);
            linie = reader.readLine();
        }
        
        reader.close();
        log_out.close();
        
    }
    
    /**
     * functie ajutatoare de deserializare; parcurgere recurenta in adancime
     * @param reader readerul cu care citim fiserul serializat
     * @param nod nodul la care suntem in deserializare
     * @param linie linia la care ne aflam in fisier
     * @param log_out fisierul de log pentru casturi
     * @throws IOException 
     */
    void deserialize_aux(BufferedReader reader, Nod nod, String linie, PrintWriter log_out) throws IOException{
        String[] tokens = linie.split(" +");
        int k;
        
        if (tokens[1].equals("<Reference")){
            String[] ids = tokens[6].split("\"");       
            int id = Integer.parseInt(ids[1]);
            nod.adiacenta.add(graf[id]);
            return;            
        }
        char type = tokens[2].charAt(10);
        
        String[] iterations = tokens[3].split("\"");
        int iteration = Integer.parseInt(iterations[1]);
        
        String[] ids = tokens[6].split("\"");
        
        int id = Integer.parseInt(ids[1]);
        
        linie = reader.readLine();
        String[] linie_aux = linie.split("[<>]+");
        String name = linie_aux[2];
        
        if (iteration < settings[type - 65]){
            log_out.println("OK cast Nod" + type + " " + name + " from Version=\"" + iteration +"\" to Version =\"" + settings[type - 65] +"\"");
        }
        else if(iteration > settings[type - 65]){
            log_out.println("Fail cast Nod" + type + " " + name + " from Version=\"" + iteration +"\" to Version =\"" + settings[type - 65] +"\"");
            iteration = settings[type-65];
        }
        
        switch (type){
            case 'A':
            {
                graf[id] = new NodA(iteration, name, type);
                nod.adiacenta.add(graf[id]);
                break;
            }
            case 'B':
            {
                graf[id] = new NodB(iteration, name, type);
                nod.adiacenta.add(graf[id]);
                break;
            }
            case 'C':
            {
                graf[id] = new NodC(iteration, name, type);
                nod.adiacenta.add(graf[id]);
                break;
            }
        }
        
        linie = reader.readLine();
        tokens = linie.split("[<>]+");
        String end_mark = tokens[0] + "</" + tokens[1] + "><" + tokens[2] + ">";
        linie = reader.readLine();
        
        while ( !(linie.equals(end_mark)) ){
            deserialize_aux(reader, graf[id], linie, log_out);
            linie = reader.readLine();
        }
       reader.readLine();
    }
    
    /**
     * functia de citire al fisierului
     * @throws IOException 
     */    
    void readFile() throws IOException{
        String linie = in.readLine();

        String [] tokens = linie.split(" ");
        while (linie != null){
            tokens = linie.split(" ");

            //verificam primul cuvant
            switch (tokens[0]) {
                case "Add":
                {
                    add(tokens);
                    break;
                }
                case "Del":
                {
                    del(tokens);
                    break;    
                }
                    
                case "AddM":
                {                             
                    addM(tokens);
                    break;
                }
                case "DelM":
                {               
                    delM(tokens);
                    break;                        
                }
                case "Serialize":
                {          
                    serialize(tokens[2], tokens[1]);                        
                    break;                   
                }
                case "Settings":
                {
                    settings[0] = Integer.parseInt(tokens[1]);
                    settings[1] = Integer.parseInt(tokens[2]);
                    settings[2] = Integer.parseInt(tokens[3]);
                    break;
                }                   
                case "Deserialize":
                {
                    deserialize(tokens[1]);
                }
                default:
                    break;                  
            }
            linie = in.readLine();
        }
        in.close();
  
    }    
}
