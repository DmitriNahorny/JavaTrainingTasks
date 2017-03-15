package by.nahorny.taxipark.park;

import java.util.Comparator;
import java.util.TreeSet;

import by.nahorny.taxipark.taxi.Taxi;

/**
 * Created by Dmitri_Nahorny on 2/27/2017.
 */
public class ParkCarsList <T extends  Taxi> {
    private TreeSet<T> carsContainer;
    private static ParkCarsList parkInstance;

    private ParkCarsList() {
        Comparator<T> taxiTypeComp = Comparator.comparing(t -> t.getClass().getSimpleName());
        Comparator<T> priceComp = Comparator.comparing(Taxi::getCarPrice);
        Comparator<T> idComp = Comparator.comparing(Taxi::getBoardId);
        carsContainer = new TreeSet<>(taxiTypeComp.thenComparing(priceComp).thenComparing(idComp));
    }

    public static ParkCarsList getParkInstance() {
        if (parkInstance == null) {
            parkInstance = new ParkCarsList();
        }
        return parkInstance;
    }

    public void addCar (T carToAdd)
    {
        carsContainer.add(carToAdd);
    }

    public TreeSet<T> getCarsContainer() {
        return carsContainer;
    }
}
