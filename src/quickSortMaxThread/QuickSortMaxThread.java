/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickSortMaxThread;

import arir.GenericSort;
import java.util.ArrayList;
import java.util.Arrays;
import quickSort.SortThread;

/**
 *
 * @author Bodzio
 */
public class QuickSortMaxThread extends GenericSort {

    int maxLevel;

    public QuickSortMaxThread(Double[] data, int maxLevel) {
        super(data);
        this.maxLevel = maxLevel;
    }

    @Override
    public Double[] test() throws InterruptedException {
        ArrayList<Double> d = new ArrayList<>(Arrays.asList(data));

        SortThreadMax s = new SortThreadMax(d, 0, maxLevel);

        s.run();

        ArrayList<Double> sorted = s.getSorted();
        Double[] returnArray = new Double[sorted.size()];
        sorted.toArray(returnArray);
        return returnArray;
    }

}
