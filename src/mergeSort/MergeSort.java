/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mergeSort;

import java.util.concurrent.ForkJoinPool;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveAction;


/**
 *
 * @author Zuzia
 */
public class MergeSort extends RecursiveAction{
 
    private static final ForkJoinPool threadPool = new ForkJoinPool();
    private static final int THRESHOLD = 2;
    Double[] array;
    Double[] tmp;
    int sPos;
    int ePos;    
    
    public MergeSort(Double[] array, Double[] tmp, int sPos, int ePos) {
            this.array = array;
            this.sPos = sPos;
            this.ePos = ePos;
            this.tmp = tmp;
        }
     
    public MergeSort(Double[] array) {
        this.array = array;
    }
 
      public Double[] test() throws InterruptedException {
        sort(array, 0, array.length-1);
        return array;
    }
    
    public static void sort(Double[] array, int sPos, int ePos) {
        Double[] tmp = new Double[array.length];
        threadPool.invoke(new MergeSort(array, tmp, sPos, ePos));
    }
    
    @Override
        protected void compute() {
            if (ePos - sPos < THRESHOLD) {
                insertionSort(array, sPos, ePos);
                return;
            }
 
            int middle = Math.floorDiv((sPos + ePos), 2);
            invokeAll(new MergeSort(array, tmp, sPos, middle), new MergeSort(array, tmp, middle+1, ePos));
            merge(array, tmp, sPos, middle, ePos);                                 

        }    
             
    private static void merge(Double[] array, Double[] tmp, int sPos, int middle, int ePos) {
        if (array[middle] <= array[middle+1])
            return;
 
        System.arraycopy(array, sPos, tmp, sPos, middle-sPos+1);
 
        int i = sPos;
        int j = middle+1;
        int k = sPos;
 
        while (k < j && j <= ePos) {
            if (tmp[i] <= array[j]) {
                array[k++] = tmp[i++];
            } else {
                array[k++] = array[j++];
            }
        }
        
        System.arraycopy(tmp, i, array, k, j-k); 
    }
 
    private static void insertionSort(Double[] array, int sPos, int ePos) {
        for (int i = sPos;  i< ePos; i++) {
            Double tmp = array[i];
            if(array[i] > array[i+1]) {
                array[i] = array[i+1];
                array[i+1] = tmp;
            }
        }
    }      
}