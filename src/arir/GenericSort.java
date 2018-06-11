/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arir;

/**
 *
 * @author Elisa
 */
/*
               Parent class to create clear structure of a class     
 */
public abstract class GenericSort {

    protected Double[] data;

    public GenericSort(Double[] data) {
        this.data = data;
    }

    public abstract Double[] test() throws InterruptedException;
}
