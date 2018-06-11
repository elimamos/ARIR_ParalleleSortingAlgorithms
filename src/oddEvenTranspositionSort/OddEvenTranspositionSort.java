/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oddEvenTranspositionSort;

import bubleSort.BubbleSortSerial;
import enumSort.EnumerationSort;
import enumSort.SortData;
import enumSort.SortType;

/**
 *
 * @author Zuzia
 */
public class OddEvenTranspositionSort extends SortType {

    public OddEvenTranspositionSort(SortData sd, int myID) {
        super(sd, myID);
    }

    @Override
    public void run() {
        int start = 0;
        int stop = 0;
        Double tmp = 0.0;

        int evenStart;
        int oddStart;

        boolean sorted = false;

        int part = sd.arraySize / sd.threadCount;
        start = myID * part;
        if (myID != sd.threadCount - 1) {
            stop = start + part;
        } else {
            stop = sd.arraySize - 2;
        }

        while (!sorted) {
            sorted = true;

            for (int i = 1; i <= stop; i = i + 2) {
                if (sd.l[i] > sd.l[i + 1]) {
                    tmp = sd.l[i];
                    sd.l[i] = sd.l[i + 1];
                    sd.l[i + 1] = tmp;
                    sd.sorted[i] = sd.l[i];
                    sd.sorted[i + 1] = sd.l[i + 1];
                    sorted = false;
                }
            }

            for (int i = 0; i <= stop; i = i + 2) {
                if (sd.l[i] > sd.l[i + 1]) {
                    tmp = sd.l[i];
                    sd.l[i] = sd.l[i + 1];
                    sd.l[i + 1] = tmp;
                    sd.sorted[i] = sd.l[i];
                    sd.sorted[i + 1] = sd.l[i + 1];
                    sorted = false;
                }
            }
        }

    }
}
