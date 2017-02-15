package by.nahorny.task2.main;

        import java.util.List;
        import java.util.Scanner;
        import by.nahorny.task2.uinput.UserInputInterface;
        import by.nahorny.task2.compute.SumDigitCompute;
        import by.nahorny.task2.report.DigitsSumReport;
        import by.nahorny.task2.fread.InputFileRead;
        import by.nahorny.task2.freport.ExportReport;
/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class LaunchApplication {
    public static void main (String[] args)
    {
        //Create objects for the console input-output
        UserInputInterface userInput = new UserInputInterface();
        int inputResult = userInput.userValue();

        SumDigitCompute computeLaunch  = new SumDigitCompute();
        int resultSum = computeLaunch.computeSum(inputResult);

        DigitsSumReport resultReport = new DigitsSumReport();
        resultReport.printReport(resultSum);

        //Create objects for the file input-output
        InputFileRead customReader = new InputFileRead();
        List<String> inputFileValue = customReader.readFromFile();

        SumDigitCompute computeFileValues  = new SumDigitCompute();
        List<int[]> resultDigitSum = computeFileValues.computeSum(inputFileValue);

        ExportReport customExport = new ExportReport();
        customExport.printReport(resultDigitSum);
    }
}