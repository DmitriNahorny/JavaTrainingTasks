package by.nahorny.lesson1.shape;

/**
 * Created by Dmitri_Nahorny on 2/8/2017.
 */
public class Circle {
    private double circleRadius;
    public Circle(double inputRadius) {
        this.circleRadius = inputRadius;
    }
    public void setRadius(double userRadius) {
        this.circleRadius = userRadius;
    }
    public double getRadius () {
        return this.circleRadius;
    }
}
