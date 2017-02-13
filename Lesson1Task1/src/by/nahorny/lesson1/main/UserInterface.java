package by.nahorny.lesson1.main;

import java.util.Scanner;
import by.nahorny.lesson1.geometric_parameters_calculation.CircleParametersCalculation;
import by.nahorny.lesson1.geometric_shape.Circle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Dmitri_Nahorny on 2/8/2017.
 */
public class UserInterface {
    public static void main (String [] args)
    {
        Scanner customScanner = new Scanner(System.in);
        System.out.println("Please, enter the circle radius:");
        String userInput = customScanner.nextLine();

        double circleRadius = Double.valueOf(userInput).floatValue();
        Circle userCircle = new Circle();
        userCircle.setRadius(circleRadius);

        CircleParametersCalculation userCircleCalculation = new CircleParametersCalculation();

        System.out.println("The circle perimeter is: " + userCircleCalculation.calculatePerimeter(userCircle));
        System.out.println("The circle area is: " + userCircleCalculation.calculateArea(userCircle));
    }
}
