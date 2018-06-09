/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumSort;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elisa
 */
public class SortData {

    Double[] l;
    int arraySize;
    int threadCount;
    List<Integer> stopped;
   public Double[] sorted;

    public SortData(Double[] l, int threadCount) {
        this.l = l;
        arraySize = l.length;
        this.threadCount = threadCount;
        sorted = new Double[arraySize];
        stopped = new ArrayList<>();
    }

}
