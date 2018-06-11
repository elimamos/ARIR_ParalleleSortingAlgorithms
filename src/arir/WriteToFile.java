/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arir;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Elisa
 */
public class WriteToFile {

    /*
    Write input data to txt file 
     */
    public WriteToFile(int testNum, Double args[]) {
        try {
            // Create file 
            FileWriter fstream = new FileWriter(testNum + ".txt");
            BufferedWriter out = new BufferedWriter(fstream);
            for (int i = 0; i < args.length; i++) {
                out.write(args[i] + "\n");
            }

            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

}
