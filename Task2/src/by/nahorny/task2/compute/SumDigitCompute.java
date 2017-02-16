package by.nahorny.task2.compute;

import java.util.ArrayList;
import java.util.List;
import by.nahorny.task2.checker.NumberChecker;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class SumDigitCompute {
    public int computeSum (int customNumber){
        customNumber = Math.abs(customNumber);
        int sumOfDigits = customNumber % 10 + (int)Math.floor(customNumber/10) % 10 + (int)Math.floor(customNumber/100) % 10 + (int)Math.floor(customNumber/1000);
        return sumOfDigits;
    }

    public List<int[]> computeSum (List<String> customNumbers){
        NumberChecker customChecker = new NumberChecker();
        List<int[]> resultValues = new ArrayList<>();
        int customIntNumber;
        int sumOfDigits;
        for (String customNumber:customNumbers) {
            if (customChecker.fourDigitsChecker(customNumber) != null)
            {
                customIntNumber = Integer.valueOf(customNumber).intValue();
                sumOfDigits = customIntNumber % 10 + (int)Math.floor(customIntNumber/10) % 10 + (int)Math.floor(customIntNumber/100) % 10 + (int)Math.floor(customIntNumber/1000);
                int inputResultPair[] = {customIntNumber,sumOfDigits};
                resultValues.add(inputResultPair);
            }
        }
        return resultValues;
    }
}