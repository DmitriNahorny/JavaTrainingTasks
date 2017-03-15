package by.nahorny.taxipark.compute;

import by.nahorny.taxipark.taxi.CargoTaxi;
import by.nahorny.taxipark.taxi.PassengerTaxi;
import by.nahorny.taxipark.taxi.Taxi;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by Dmitri_Nahorny on 3/6/2017.
 */
public class TaxiParkFilter {

    public static TreeSet<CargoTaxi> cargoTaxiCapacityFilter (TreeSet currentTaxiPark, float minCapacity, float maxCapacity){

        HashSet<CargoTaxi> cargoTaxiList = TaxiParkFilter.filterTaxiType(currentTaxiPark, CargoTaxi.class);

        List<CargoTaxi> filteredTaxiCargoList = cargoTaxiList.stream().filter(p -> (p.getCarryingCapacity() >= minCapacity) & (p.getCarryingCapacity() <= maxCapacity)).collect(Collectors.toList());

        TreeSet<CargoTaxi> cargoTaxiResultSet = new TreeSet<>(currentTaxiPark.comparator());
        cargoTaxiResultSet.addAll(filteredTaxiCargoList);

        return cargoTaxiResultSet;

    }

    public static TreeSet<PassengerTaxi> passengerTaxiCapacityFilter (TreeSet currentTaxiPark, int minCapacity, int maxCapacity){

        HashSet<PassengerTaxi> passengerTaxiList = TaxiParkFilter.filterTaxiType(currentTaxiPark, PassengerTaxi.class);

        List<PassengerTaxi> filteredTaxiPassengerList = passengerTaxiList.stream().filter(p -> (p.getPassengersCapacity() >= minCapacity) & (p.getPassengersCapacity() <= maxCapacity)).collect(Collectors.toList());

        TreeSet<PassengerTaxi> passengerTaxiResultSet = new TreeSet<>(currentTaxiPark.comparator());
        passengerTaxiResultSet.addAll(filteredTaxiPassengerList);

        return passengerTaxiResultSet;
    }

    static <T extends Taxi> HashSet<T> filterTaxiType(Collection c, Class<T> cl) {

        HashSet<T> resultCollection = new HashSet<>();
        for (Object obj : c){
            if (cl.isInstance(obj)) {
                resultCollection.add((T) obj);
            }
        }
        return resultCollection;
    }
}