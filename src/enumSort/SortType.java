/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumSort;

/**
 *
 * @author Elisa
 */
public class SortType extends Thread {

    public SortData sd;
    public int myID;

    public SortType(SortData sd, int myID) {
        this.sd = sd;
        this.myID = myID;
        //  new Thread(this, "sorter").start();

    }

}
