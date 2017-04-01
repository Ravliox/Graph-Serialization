/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;


/**
 * clasa ce contine vectorul de stochare al adiacentelor
 * @author magni
 */
public class VECTOR extends Storage{
    
    Nod [] adiacenta;
    int nr_elemente = 0;
    int current;
    
    /**
     * constructor
     * @param size numarul de elemente al vectorului 
     */
    VECTOR (int size){
        this.adiacenta = new Nod[size];
    }
    
    /**
     * metoda de adaugare in vector
     * @param a nodul ce urmeaza a fi adaugat
     */
    @Override
    public void add (Nod a){
        this.adiacenta[nr_elemente] = a;
        this.nr_elemente++;
    }
    
    /**
     * metoda de stergere din vector
     * @param a nodul ce urmeaza a fi sters
     */
    @Override    
    public void del(Nod a){
        int k;
        for (k = 0; k < nr_elemente; k++){
            if (adiacenta[k].name.equals(a.name)){
                int j;
                for (j = k; j <= nr_elemente - 1; j++){
                    adiacenta[j] = adiacenta[j + 1];
                }
                nr_elemente--;
                break;
            }
        }
    }
    
    /**
     * metoda de incepere a iterarii; nenecesara in mod normal
     * creeata pentru abstractizarea Storage
     */
    @Override 
    public void start(){
        this.current = -1;
    }
    
    /**
     * metoda ce itereaza prin vector
     * @return elementul urmator
     */
    @Override
    public Nod next(){
        this.current++;
        return adiacenta[current];
    }
    
    /**
     * verifica daca mai avem elemente de returnat
     * @return valoarea de adevar a conditiei 
     */
    @Override
    public boolean hasNext(){
        return ( this.current < nr_elemente - 1 );
    }
    
}
