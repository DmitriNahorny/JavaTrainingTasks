package by.nahorny.lesson1.calculation;

import by.nahorny.lesson1.shape.Circle;
/**
 * Created by Dmitri_Nahorny on 2/8/2017.
 */
public class CircleParametersCalculation {
    public static double calculatePerimeter (Circle userCircle) {
        double circleRadius = userCircle.getRadius();
        double perimeter = 2*Math.PI*circleRadius;
        return perimeter;
    }

    public static double calculateArea (Circle userCircle) {
        double circleRadius = userCircle.getRadius();
        double area = Math.PI*Math.pow(circleRadius,2);
        return area;
    }
}
