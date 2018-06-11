/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arir;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 *
 * @author Elisa
 */
/*
        Class for generating input data 
 */
public class GenerateInputData {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        for (int i = 0; i < 15; i++) {
            Double[] input = createInputData((int) Math.pow(2, i + 10));
            new WriteToFile(i, input);
        }
    }

    /*
        Generate random double values
     */
    static Double[] createInputData(int inputSize) {
        Double[] inputData = new Double[inputSize];
        for (int i = 0; i < inputSize; i++) {
            inputData[i] = new Random().nextDouble();
        }
        return inputData;
    }
}
