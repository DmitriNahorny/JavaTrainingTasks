package by.nahorny.lesson1.freport;

import by.nahorny.lesson1.calculation.CircleParametersCalculation;
import by.nahorny.lesson1.shape.Circle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 2/16/2017.
 */

public class FileReport {
    private final String OUTPUT_FILE_NAME = "./resource/output.txt";
    private final String CUSTOM_FORMAT_PATTERN = "##.##";
    private DecimalFormat customFormat = new DecimalFormat(CUSTOM_FORMAT_PATTERN);

    public void printReport (List<Circle> circleList){
        String resultReportString = "";
        if (circleList.size() > 0){
            for (Circle circleInstance:circleList) {
                double resultRadius = circleInstance.getRadius();
                double resultPerimeter = CircleParametersCalculation.calculatePerimeter(circleInstance);
                double resultArea = CircleParametersCalculation.calculateArea(circleInstance);
                resultReportString += "The circle with the radius " + customFormat.format(resultRadius) + " has perimeter " + customFormat.format(resultPerimeter) + " and area " + customFormat.format(resultArea) + "\r\n";
            }
        }
        else {
            resultReportString = "There is no correct radius stored in the input file";
        }

        try(PrintWriter out = new PrintWriter(OUTPUT_FILE_NAME)){
            out.println(resultReportString);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}