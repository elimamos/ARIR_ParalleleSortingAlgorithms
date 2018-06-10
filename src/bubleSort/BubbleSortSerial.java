/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubleSort;

/**
 *
 * @author Elisa
 */
public class BubbleSortSerial {
    private Double arr[];
    
    public BubbleSortSerial(Double [] data){
        this.arr=data;
    }
    
    public Double[] test()
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    Double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }
 
    /* Prints the array */
   public void printArray(Double arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
 

}
