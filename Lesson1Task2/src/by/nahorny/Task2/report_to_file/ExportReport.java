package by.nahorny.Task2.report_to_file;

import by.nahorny.Task2.checker.NumberChecker;
import by.nahorny.Task2.compute.SumDigitCompute;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class ExportReport {
    public void printReport (String customValue){
        String customReportString = "";
        NumberChecker checkerInstance = new NumberChecker();
        String checkerCode = checkerInstance.fourDigitsChecker(customValue);

        if (checkerCode != null){
            SumDigitCompute computeLaunch  = new SumDigitCompute();
            int resultSum = computeLaunch.computeSum(Integer.valueOf(checkerCode).intValue());
            customReportString = "The sum of digits from file is: " + resultSum;
        }
        else {
            customReportString = "Not 4-digit number is stored in the input file.";
        }
        try(PrintWriter out = new PrintWriter("./resource/output.txt")){
            out.println(customReportString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
