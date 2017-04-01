package tema2;

import java.io.Serializable;

/**
 *Clasa abstracta a nodurilor din graf
 * @author magni
 */
public abstract class Nod implements Serializable {
    
    char type;
    
    int iteration;
    String name;
    Storage adiacenta;
    
    /**
     * 
     * @param iteration versiunea nodului
     * @param name numele nodului
     * @param type tipul de nod: NodA, NodB sau NodC
     */
    Nod (int iteration, String name, char type){
        this.iteration = iteration;
        StorageFactory t = new StorageFactory ();
        this.adiacenta = t.generateStorage(iteration);
        this.name = name;
        this.type= type;
    }
    
}
