/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickSortMaxThread;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Zuzia
 * Quick Sort that is limited to a give level
 */
public class SortThreadMax extends Thread {

    private ArrayList<Double> data;
    private ArrayList<Double> sorted;
    int maxLevel;
    int level;

    public ArrayList<Double> getSorted() {
        return sorted;
    }

    public SortThreadMax(ArrayList<Double> data, int level, int maxLevel) {
        this.data = data;
        this.level = level;
        this.maxLevel = maxLevel;
    }

    private Double getPivot() {
        int i = new Random().nextInt(data.size());
        return data.get(i);

    }

    @Override
    public void run() {
        if (level == maxLevel) {
            sort(data, 0, data.size() - 1);

            sorted = data;
        } else {
            sorted = new ArrayList<>();
            level++;
            Double pivot = getPivot();

            ArrayList<Double> smaller = new ArrayList<>();
            ArrayList<Double> bigger = new ArrayList<>();

            for (Double d : data) {
                if (d < pivot) {
                    smaller.add(d);
                } else {
                    bigger.add(d);
                }
            }

            SortThreadMax sThread = null;
            SortThreadMax bThread = null;

            try {
                if (smaller.size() > 0) {
                    sThread = new SortThreadMax(smaller, level, maxLevel);
                    sThread.start();
                }

                if (bigger.size() > 0) {
                    bThread = new SortThreadMax(bigger, level, maxLevel);
                    bThread.start();
                }

                if (sThread != null) {
                    sThread.join();
                    sorted.addAll(sThread.getSorted());

                }
                if (bThread != null) {
                    bThread.join();
                    sorted.addAll(bThread.getSorted());
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    int partition(ArrayList<Double> arr, int low, int high) {
        double pivot = arr.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j) <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                double temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        double temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public void sort(ArrayList<Double> arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

}
