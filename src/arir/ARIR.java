/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import arir.EnumSortTest;
import arir.EnumerationSort;
import arir.HyperQuickSort;
import arir.SortData;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elisa
 */
public class ARIR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
         int threadCount = 4;
        Double[] l = readArrayFromFile();
        EnumSortTest t = new EnumSortTest(l, threadCount);
        Double[] result;
        long startTime = System.currentTimeMillis();
        result = t.test();
        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;
        System.out.println(totalTime+"ms");
         startTime = System.currentTimeMillis();
        result = t.testHyper();
         endTime = System.currentTimeMillis();

         totalTime = endTime - startTime;
        System.out.println(totalTime+"ms");
        

    }

    static Double[] readArrayFromFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("array.txt");
        List<Double> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(Double.valueOf(line));
            }
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Unable to read !");
        }
        Double[] array = lines.toArray(new Double[lines.size()]);
        // return lines.toArray(new String[lines.size()]);
        return array;
    }
}
