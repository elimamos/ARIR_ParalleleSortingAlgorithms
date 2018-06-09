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
public  class EnumSortTest {

    Double[] data;
    int threadCount;

    public EnumSortTest(Double[] data, int threadCount) {
        this.data = data;
        this.threadCount = threadCount;

    }
   public Double[] testHyper() throws InterruptedException {
     SortData sd = new SortData(data, threadCount);
        Thread[] esArray = new Thread[threadCount];
        for (int h = 0; h < threadCount; h++) {
            esArray[h] = new HyperQuickSort(sd, h);
        }
        for (int h = 0; h < threadCount; h++) {
            esArray[h].start();
        }
        for (int h = 0; h < threadCount; h++) {
            esArray[h].join();
        } 
        return null;
    }
    public Double[] test( ) throws InterruptedException {
        // System.out.println(l);
        SortData sd = new SortData(data, threadCount);
        Thread[] esArray = new Thread[threadCount];
        for (int h = 0; h < threadCount; h++) {
            esArray[h] = new EnumerationSort(sd, h);
        }
        for (int h = 0; h < threadCount; h++) {
            esArray[h].start();
        }
        for (int h = 0; h < threadCount; h++) {
            esArray[h].join();
        }
        double previousNotNull = sd.sorted[0];
        for (int j = 1; j < sd.sorted.length; j++) {
            if (sd.sorted[j] != null) {
                previousNotNull = sd.sorted[j];
            } else {
                sd.sorted[j] = previousNotNull;
            }
            // System.out.println(sd.sorted[j]);
        }

        return sd.sorted;
    }

}
