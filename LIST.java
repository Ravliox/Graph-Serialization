package tema2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * clasa ce implementeaza stocarea folosind lista 
 * @author magni
 */
public class LIST extends Storage {
    
    ArrayList<Nod> adiacenta = new ArrayList<> (200);
    Iterator Itr;
    
    /**
     * metoda de adaugare in lista
     * @param a nodul ce urmeaza a fi adaugat
     */
    @Override
    public void add (Nod a){
        this.adiacenta.add(a);
    }
    
    /**
     * metoda de stergere din lista
     * @param a nodul ce urmeaza a fi sters
     */
    @Override
    public void del(Nod a){
        this.adiacenta.remove(a);
    }
    /**
     * metoda de initializare a iteratorului
     * se apeleaza inainte de a itera prin lista
     */
    @Override
    public void start(){
        Itr = adiacenta.iterator();
    }
    
    /**
     * metoda de iterare prin lista
     * @return urmatorul element din lista de adiacente
     */
    @Override
    public Nod next(){
        return (Nod)this.Itr.next();
    }
    /**
     * metoda de verificare a faptului ca am ajuns la finalul listei
     * @return valoarea de adevar a conditiei ca indexul curent este mai mic ca nr total de elemente
     */
    @Override
    public boolean hasNext(){
        return this.Itr.hasNext();
    }
    
    
}
