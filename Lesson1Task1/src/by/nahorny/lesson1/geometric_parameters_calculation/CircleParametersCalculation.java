package by.nahorny.lesson1.geometric_parameters_calculation;

import by.nahorny.lesson1.geometric_shape.Circle;
/**
 * Created by Dmitri_Nahorny on 2/8/2017.
 */
public class CircleParametersCalculation {
    public double calculatePerimeter (Circle userCircle) {
        double circleRadius = userCircle.getRadius();
        double perimeter = 2*Math.PI*circleRadius;
        return perimeter;
    }

    public double calculateArea (Circle userCircle) {
        double circleRadius = userCircle.getRadius();
        double area = Math.PI*Math.pow(circleRadius,2);
        return area;
    }
}
