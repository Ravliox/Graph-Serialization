
package tema2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * clasa set ce contine un hashset pentru stocarea adiacentelor 
 * @author magni
 */
public class SET extends Storage{
    Set<Nod> adiacenta = new HashSet <> (200);
    int nr_elemente = 0;
    int current;
    Iterator itr;
    /**
     * metoda de adaugarea de elemente in set
     * @param a nodul ce trebuie adaugat
     */
    @Override
    public void add (Nod a){
        adiacenta.add(a);
    }
    
    /**
     * stergere de elemente din set
     * @param a nodul ce trebuie sters
     */
    @Override
    public void del(Nod a){
        adiacenta.remove(a);
    }
    
    /**
     * metoda pentru initializarea iteratorului
     * se apeleaza inainte de parcurgerea elementelor din adiacenta
     */
    @Override
    public void start (){
        this.itr = adiacenta.iterator();
    }
    
    /**
     * metoda pentru parcurgerea iteratorului
     * @return urmatorul nod din set
     */
    @Override
    public Nod next(){
        return (Nod) itr.next();
    }
    
    /**
     * metoda de a verifica daca s-a ajuns la sfarsitul iterarii
     * @return valoarea de adevar a conditiei ca indexul curent este mai mic decat nr total de elemente
     */
    @Override
    public boolean hasNext(){
        return itr.hasNext();
    }
}
