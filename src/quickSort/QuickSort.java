package quickSort;

import arir.GenericSort;
import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort extends GenericSort{

    public QuickSort(Double[] data) {
        super(data);
    }

    
    @Override
    public Double[] test() throws InterruptedException {

        ArrayList<Double> d = new ArrayList<>(Arrays.asList(data));

        SortThread s = new SortThread(d);

        s.start();
        s.join();

        ArrayList<Double> sorted = s.getSorted();
        Double[] returnArray = new Double[sorted.size()];
        sorted.toArray(returnArray);
        return returnArray;

//System.out.println(sorted);
    }

}
