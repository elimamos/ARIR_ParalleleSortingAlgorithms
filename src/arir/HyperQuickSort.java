/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elisa
 */
public class HyperQuickSort extends Thread {

    SortData sd;
    int myID;

    public HyperQuickSort(SortData sd, int myID) {
        this.sd = sd;
        this.myID = myID;

    }

    @Override
    public void run() {
        int start = 0;
        int stop = 0;
        int part = sd.arraySize / sd.threadCount;
        start = myID * part;
        if (myID != sd.threadCount - 1) {
            stop = start + part;
        } else {
            stop = sd.arraySize;
        }
        //  Double[] myPart = new Double[part];
        List<Double> pQueue = new ArrayList<Double>();
        int j = 0;
        for (int i = start; i < stop; i++) {
            pQueue.add(sd.l[i]);
            j++;
        }

        int elementLenght = pQueue.size();
        for (int h = 0; h < sd.arraySize / 2; h++) {

            int pivot = new Random().nextInt(elementLenght);
            Double pivotValue = pQueue.get(pivot);
            List<Double> smaller = new ArrayList<Double>();
            List<Double> larger = new ArrayList<Double>();

            for (int l = 0; l < elementLenght; l++) {

                if (pivotValue >= pQueue.get(l)) {
                    smaller.add(pQueue.get(l));

                } else if (pivotValue < pQueue.get(l)) {
                    larger.add(pQueue.get(l));
                }
            }

       sd.stopped.add(myID);
            synchronized (sd.sorted) {
                if(sd.stopped.size() != sd.threadCount ){
                    try {
                        System.out.println("LOCKED: "+myID+ " out of " + sd.threadCount+" stopped "+sd.stopped );
                        sd.sorted.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HyperQuickSort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 sd.sorted.notifyAll();
                 sd.stopped.clear();
            }
           for(int g=0; g<smaller.size();g++){
               int newStart= start-smaller.size();
               if(newStart<0){
                   newStart=0;
               }
                sd.l[newStart+g]= smaller.get(g);
           } 
              for(int g=0; g<larger.size();g++){
               int newStart= stop-larger.size();
               if(newStart<0){
                   newStart=0;
               }
                sd.l[newStart+g]= larger.get(g);
           }
//        Iterator it = pQueue.iterator();
//        //  Double[] currentMin = pQueue.element();
//        while (it.hasNext()) {
////            if (currentMin[0] > ((Double[]) it.next())[0]) {
////
////            }
//
//            for (Double d : (Double[]) it.next()) {
//                //   sd.sorted[]
//                //System.out.print(d);
//            }
//            System.out.println();
//        }
//    }
        }
        
    }
}
