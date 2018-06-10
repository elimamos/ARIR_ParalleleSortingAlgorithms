/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oddEvenTranspositionSort;

import enumSort.HyperQuickSort;
import enumSort.SortData;

/**
 *
 * @author Elisa
 */
public class OddEvenTranspositionSortTest {
     Double[] data;
    int threadCount;

    public OddEvenTranspositionSortTest(Double[] data, int threadCount) {
        this.data = data;
        this.threadCount = threadCount;

    }
   public Double[] test( ) throws InterruptedException {
       
         SortData sd = new SortData(data, threadCount);
        Thread[] esArray = new Thread[sd.threadCount];
        for (int h = 0; h < sd.threadCount; h++) {
            esArray[h] = new OddEvenTranspositionSort(sd, h);
        }
        for (int h = 0; h < sd.threadCount; h++) {
            esArray[h].start();
        }
        for (int h = 0; h < sd.threadCount; h++) {
            esArray[h].join();
        }

        return sd.sorted;
    }

}
