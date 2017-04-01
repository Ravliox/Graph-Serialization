/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

import java.io.Serializable;

/**
 * Clasa abstracta pentru stocharea nodurilor adiacente
 * @author magni
 */
public abstract class Storage implements Serializable{
    
    public abstract void add(Nod a);
    public abstract void del(Nod a);
    public abstract void start();
    public abstract Nod next();
    public abstract boolean hasNext();
}
