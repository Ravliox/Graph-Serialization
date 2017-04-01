/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Factory pattern folosit in constructorul nodului
 * @author magni
 */
public class StorageFactory{
    
    /**
     * metoda ce creeaza stocarea de adiacenta
     * @param type versiunea nodului
     * @return una din cele 3 clase de stocare a adiacentelor
     */
    Storage generateStorage (int type){
        switch (type){
            case 2:
                return new VECTOR(100);
            case 1:
                return new LIST();
            case 3:
                return new SET();
        }
        return null;
    }
    
}
