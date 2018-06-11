package arir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import bubleSort.BubbleSortSerial;
import enumSort.EnumSortTest;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mergeSort.MergeSort;

/**
 *
 * @author Elisa
 */
public class ARIR {

    public static class Result {

        public int num;
        public long time;

        public Result(int num, long time) {
            this.num = num;
            this.time = time;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        String resultFileName = "OddEvenTranspositionSortTest" + System.currentTimeMillis() + ".csv";
        int testCaseCount = 10;
        ArrayList<Result> results = new ArrayList<>();
        int testRepetition = 500;

        for (int i = 0; i < testCaseCount; i++) {

            String fileName = String.valueOf(i) + ".txt";
            Double[] l = readArrayFromFile(fileName);
            /*
                  Bubble sort for refference   
             */

            Double[] bubbleResult= l;
            BubbleSortSerial bu = new BubbleSortSerial(bubbleResult);
            bubbleResult = bu.test();
//        
            long avg = 0;
            for (int j = 0; j < testRepetition; j++) {
                Double[] testArray = new Double[l.length];
                int s = 0;
                for (Double d : l) {
                    testArray[s] = d;
                    s++;

                }
                /*
                    All the possible sorting algorithms 
                 */
               // EnumSortTest t = new EnumSortTest(l, 64);
                //QuickSort t = new QuickSort(l);
                //  BubbleSortSerial t = new BubbleSortSerial(testArray);
                //  QuickSortMaxThread t = new QuickSortMaxThread(l, 32);
                 MergeSort t = new MergeSort(l);
                //  OddEvenTranspositionSortTest t = new OddEvenTranspositionSortTest(testArray, 1);
//              
                Double[] result;
                long startTime = System.currentTimeMillis();
                //quicksort(testArray, 0, testArray.length-1);
                result = t.test();
                long endTime = System.currentTimeMillis();

                /*
                        Check if created data is correct
                 */
                if (j == 0) {

                    Double bubSum = 0.0;
                    for (Double d : bubbleResult) {
                        bubSum += d;
                    }
                    Double testSum = 0.0;
                    for (Double d : result) {
                        testSum += d;
                    }
                    int controlSum = 0;
                    for (int g = 0; g < result.length; g++) {
                        if (result[g].equals(bubbleResult[g])) {

                            controlSum++;
                        }
                    }

                    if (bubSum.equals(bubSum) & controlSum == result.length) {
                        System.out.println("Results are correct!");
                    } else {
                        System.out.println("Results are WRONG!");

                    }
                }
                long totalTime = endTime - startTime;
                avg += totalTime;
            }
            avg = avg / testRepetition;
            System.out.println(avg + "ms");
            results.add(new Result(l.length, avg));
        }

        saveResultToFile(results, resultFileName);

    }

    /*
                    Serial Quick Sort for reference
     */
    private static void quicksort(Double tablica[], int x, int y) {
        int i, j;
        Double v, temp;

        i = x;
        j = y;
        v = tablica[(x + y) / 2];
        do {
            while (tablica[i] < v) {
                i++;
            }
            while (v < tablica[j]) {
                j--;
            }
            if (i <= j) {
                temp = tablica[i];
                tablica[i] = tablica[j];
                tablica[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (x < j) {
            quicksort(tablica, x, j);
        }
        if (i < y) {
            quicksort(tablica, i, y);
        }
    }

    public static void printArray(Double arr[]) {
        int n = arr.length;
        for (int i = 0; i < n / 5; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /*
                 Saving the results to a CSV file    
     */
    static void saveResultToFile(ArrayList<Result> r, String fileName) {
        try {
            // Create file 
            FileWriter fstream = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fstream);

            out.write(String.valueOf(r.get(0).num));
            for (int i = 1; i < r.size(); i++) {
                out.write("," + r.get(i).num);
            }
            out.write("\n");
            out.write(String.valueOf(r.get(0).time));
            for (int i = 1; i < r.size(); i++) {
                out.write("," + r.get(i).time);
            }

            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    /*
                Reading input data from files generated by the GenerateInputData class 
     */
    static Double[] readArrayFromFile(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
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
        return array;
    }
}
