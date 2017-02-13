package by.nahorny.Task2.compute;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class SumDigitCompute {
    public int computeSum (int customNumber){
        customNumber = Math.abs(customNumber);
        int sumOfDigits = customNumber % 10 + (int)Math.floor(customNumber/10) % 10 + (int)Math.floor(customNumber/100) % 10 + (int)Math.floor(customNumber/1000);
        return sumOfDigits;
    }
}
