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
public class EnumerationSort extends SortType {

//    SortData sd;
//    int myID;

    public EnumerationSort(SortData sd, int myID) {
        super(sd, myID);
    }

//    public EnumerationSort(SortData sd, int myID) {
//        this.sd = sd;
//        this.myID = myID;
//       //  new Thread(this, "sorter").start();
//
//    }

    @Override
    public void run() {
        int start = 0;
        int stop = 0;
        int part = sd.arraySize / sd.threadCount;
        start = myID * part;
        if (myID != sd.threadCount - 1) {
            stop = start + part;
        } else {
            stop = sd.arraySize ;
        }

        for (int i = start; i < stop; i++) {
            Double me = sd.l[i];
            int position =0;
            for (int j = 0; j < sd.arraySize; j++) {
                if(me>sd.l[j]){
                    position++;
                }
            }
            sd.sorted[position]=me;
        }
    }

}
