package by.nahorny.lesson1.main;

import java.util.List;
import by.nahorny.lesson1.container.CircleContainer;
import by.nahorny.lesson1.fread.InputFileRead;
import by.nahorny.lesson1.freport.FileReport;


/**
 * Created by Dmitri_Nahorny on 2/8/2017.
 */
public class LaunchApplication {
    public static void main (String [] args)
    {
        InputFileRead radiusInputFile = new InputFileRead();
        List<String> inputRadiusList =  radiusInputFile.getRadiusFromFile();

        String[] parametersArray = new String[inputRadiusList.size()];
        parametersArray = inputRadiusList.toArray(parametersArray);

        CircleContainer userCircleContainer = new CircleContainer(parametersArray);

        FileReport exportReportToFile = new FileReport();
        exportReportToFile.printReport(userCircleContainer.getCircleContainer());
    }
}