package by.nahorny.taxipark.compute;

import by.nahorny.taxipark.taxi.CargoTaxi;
import by.nahorny.taxipark.taxi.PassengerTaxi;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by Dmitri_Nahorny on 3/12/2017.
 */
public class CapacityParkCompute {
    public static int passengersTaxiParkCapacity (TreeSet currentTaxiPark){

        int taxiParkPassengersCapacity = 0;

        HashSet<PassengerTaxi> passengerTaxiList = TaxiParkFilter.filterTaxiType(currentTaxiPark, PassengerTaxi.class);

        for (PassengerTaxi passengerTaxiCar : passengerTaxiList) {
            taxiParkPassengersCapacity += passengerTaxiCar.getPassengersCapacity();
        }

        return taxiParkPassengersCapacity;
    }

    public float cargoTaxiParkCapacity (TreeSet currentTaxiPark){

        float taxiParkCargoCapacity = 0f;

        HashSet<CargoTaxi> cargoTaxiList = TaxiParkFilter.filterTaxiType(currentTaxiPark, CargoTaxi.class);

        for (CargoTaxi passengerTaxiCar : cargoTaxiList) {
            taxiParkCargoCapacity += passengerTaxiCar.getCarryingCapacity();
        }

        return taxiParkCargoCapacity;
    }
}
