package by.nahorny.Task2.main;

        import java.util.Scanner;
        import by.nahorny.Task2.user_input.UserInputInterface;
        import by.nahorny.Task2.compute.SumDigitCompute;
        import by.nahorny.Task2.report.DigitsSumReport;
        import by.nahorny.Task2.file_reader.FileReader;
        import by.nahorny.Task2.report_to_file.ExportReport;
/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class LaunchApplication {
    public static void main (String[] args)
    {
        UserInputInterface userInput = new UserInputInterface();
        int inputResult = userInput.userValue();

        SumDigitCompute computeLaunch  = new SumDigitCompute();
        int resultSum = computeLaunch.computeSum(inputResult);

        DigitsSumReport resultReport = new DigitsSumReport();
        resultReport.printReport(resultSum);

        FileReader customReader = new FileReader();
        String inputFileValue = customReader.readFromFile();

        ExportReport customExport = new ExportReport();
        customExport.printReport(inputFileValue);
    }
}