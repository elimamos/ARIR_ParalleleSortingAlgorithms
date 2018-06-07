/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

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
        Double[] myPart = new Double[part];
        int j = 0;
        for (int i = start; i < stop; i++) {
            myPart[j] = sd.l[i];
            j++;
        }
        PriorityQueue<Double[]> pQueue = new PriorityQueue<Double[]>();
        pQueue.add(myPart);
        int elementLenght = pQueue.element().length;
//        for (Double d : pQueue.element()) {
//            System.out.print(d);
//        }
//        System.out.println();
        while (elementLenght != 2) {

            int pivot = new Random().nextInt(elementLenght);
            Double pivotValue = pQueue.element()[pivot];
            List<Double> smaller = new ArrayList<Double>();
            List<Double> larger = new ArrayList<Double>();

            for (int l = 0; l < elementLenght; l++) {
                if (pivot > sd.l[l]) {
                    larger.add(myPart[l]);
                } else {
                    smaller.add(myPart[l]);
                }
            }
            pQueue.remove();
            if (larger.size() != 0) {
                Double[] tempList = new Double[larger.size()];
                larger.toArray(tempList);
                pQueue.add(tempList);
            }
            if (smaller.size() != 0) {
                Double[] tempSmalList = new Double[smaller.size()];
                larger.toArray(tempSmalList);
                pQueue.add(tempSmalList);
            }
            elementLenght = pQueue.element().length;
            //   sd.sorted[position] = me;

        }
        Iterator it = pQueue.iterator();
        while(it.hasNext()){
        for (Double d : (Double[])it.next()) {
            System.out.print(d);
        }
        System.out.println();
        }
    }

}

