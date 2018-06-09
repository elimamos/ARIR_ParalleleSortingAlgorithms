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
import java.util.Arrays;
import java.util.List;
import quickSort.QuickSort;
import quickSortMaxThread.QuickSortMaxThread;
import quickSortMaxThread.SortThreadMax;


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

        String resultFileName = "QuickSort_1_"+System.currentTimeMillis()+".csv";
        int testCaseCount = 10;
        ArrayList<Result> results = new ArrayList<>();
        int testRepetition = 500;

        

        for (int i = 0; i < testCaseCount; i++) {

            String fileName = String.valueOf(i) + ".txt";
            Double[] l = readArrayFromFile(fileName);
/*
            SortThreadMax s = new SortThreadMax(null, null, i, i);
            ArrayList<Double> list= new ArrayList<>(Arrays.asList(l));
            s.sort(list, 0, list.size()-1);
            System.out.println(list);
  */          

            
            long avg = 0;
            for (int j = 0; j < testRepetition; j++) {

                //EnumSortTest t = new EnumSortTest(l, 4);
                //QuickSort t = new QuickSort(l);
                //BubbleSortSerial t = new BubbleSortSerial(l);
                QuickSortMaxThread t = new QuickSortMaxThread(l, 1);
                
                Double[] result;
                long startTime = System.currentTimeMillis();
                result = t.test();
                long endTime = System.currentTimeMillis();
                //bo.printArray(result);

                long totalTime = endTime - startTime;
                avg += totalTime;
            }
            avg = avg / testRepetition;
            System.out.println(avg + "ms");
            results.add(new Result(l.length, avg));
        }
        saveResultToFile(results, resultFileName);

    }

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
        // return lines.toArray(new String[lines.size()]);
        return array;
    }
}
