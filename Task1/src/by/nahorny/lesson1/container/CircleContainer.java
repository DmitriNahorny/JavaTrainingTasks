package by.nahorny.lesson1.container;

import by.nahorny.lesson1.checker.NumberChecker;
import by.nahorny.lesson1.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 2/16/2017.
 */
public class CircleContainer {
    private List<Circle> circleList = new ArrayList<>();

    public CircleContainer(String ... radiusValues){
        if (radiusValues.length > 0){
            for (String circleRadius : radiusValues){
                if (NumberChecker.radiusCheck(circleRadius)){
                    Circle circleInstance = new Circle(Double.valueOf(circleRadius).doubleValue());
                    this.circleList.add(circleInstance);
                }
            }
        }
    }

    public void addCircle(String ... radiusValues){
        if (radiusValues.length > 0){
            for (String circleRadius : radiusValues){
                if (NumberChecker.radiusCheck(circleRadius)){
                    Circle circleInstance = new Circle(Double.valueOf(circleRadius).doubleValue());
                    this.circleList.add(circleInstance);
                }
            }
        }
    }

    public List<Circle> getCircleContainer() {
        return this.circleList;
    }
}
