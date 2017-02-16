package by.nahorny.task2.freport;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class ExportReport {
    private final String OUTPUT_FILE_NAME = "./resource/output.txt";

    public void printReport (List<int[]> customDigitSums){
        String resultReportString = "";
        if (customDigitSums.size() > 0){
            for (int[] customDigitSum:customDigitSums) {
                resultReportString += "The sum of digits for " + customDigitSum[0] + " is " + customDigitSum[1] + "\r\n";
            }
        }
        else {
            resultReportString = "There is no 4-digit values stored in the input file";
        }

        try(PrintWriter out = new PrintWriter(OUTPUT_FILE_NAME)){
            out.println(resultReportString);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
