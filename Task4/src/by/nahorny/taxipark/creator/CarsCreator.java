package by.nahorny.taxipark.creator;


import by.nahorny.taxipark.custexceptions.IncorrectCarsInfoException;
import by.nahorny.taxipark.park.ParkCarsList;
import by.nahorny.taxipark.taxi.CarBrand;
import by.nahorny.taxipark.taxi.CargoTaxi;
import by.nahorny.taxipark.taxi.PassengerTaxi;
import by.nahorny.taxipark.taxi.Taxi;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by Dmitri_Nahorny on 3/2/2017.
 */
public class CarsCreator {

    static Logger logger = LogManager.getLogger(CargoTaxi.class);

    private static TreeMap<String,Integer> paramMap = new TreeMap<>(String::compareTo);

    static {
        paramMap.put("CARGO", 5);
        paramMap.put("PASSENGER", 5);
    }


    public static void createCarsFromList(ArrayList<String> carsInfo, ParkCarsList taxiPark) {

        String customDelimiter = "\t+";

        for (String carInfo : carsInfo)
        {
            String[] carProperties = carInfo.split(customDelimiter);
            try {
                Taxi taxiInst = CarsCreator.createCarInstance(carProperties);
                taxiPark.addCar(taxiInst);

                logger.debug("The following Taxi instance has been added to the Taxi Park:" + System.lineSeparator() + taxiInst.toString());

            } catch (IncorrectCarsInfoException e) {
                logger.log(Level.WARN, e.getMessage());
            }
        }
    }

    private static Taxi createCarInstance (String[] carParam) throws IncorrectCarsInfoException{
        String taxiType = carParam[0];
        Taxi returnTaxiInst;
        if(paramMap.containsKey(taxiType.toUpperCase())){
            if (carParam.length == paramMap.get(taxiType.toUpperCase())) {
                switch (taxiType.toUpperCase()){
                    case "CARGO":
                        try {
                            int carBoardId = Integer.parseInt(carParam[1]);
                            CarBrand currentCarBrand = CarBrand.valueOf(carParam[2].toUpperCase());
                            float carCost = Float.parseFloat(carParam[3]);
                            float cargoCapacity = Float.parseFloat(carParam[4]);
                            returnTaxiInst = new CargoTaxi(carBoardId, currentCarBrand, carCost, cargoCapacity);
                        }
                        catch (IllegalArgumentException e){
                            throw new IncorrectCarsInfoException(Arrays.toString(carParam) + " can't be converted to Car Object. Not correct arguments' values", e);
                        }
                        break;

                    case "PASSENGER":
                        try {
                            int carBoardId = Integer.parseInt(carParam[1]);
                            CarBrand currentCarBrand = CarBrand.valueOf(carParam[2].toUpperCase());
                            float carCost = Float.parseFloat(carParam[3]);
                            int passengerCapacity = Integer.parseInt(carParam[4]);
                            returnTaxiInst = new PassengerTaxi(carBoardId, currentCarBrand, carCost, passengerCapacity);
                        }
                        catch (IllegalArgumentException e){
                            throw new IncorrectCarsInfoException(Arrays.toString(carParam) + " can't be converted to Car Object. Not correct arguments' values", e);
                        }
                        break;

                    default: throw new IncorrectCarsInfoException(taxiType + " is not specified Taxi type");
                }
            }
            else throw new IncorrectCarsInfoException(Arrays.toString(carParam) + " can't be converted to Car Object. Not correct number of arguments");
        }
        else {
            throw new IncorrectCarsInfoException(taxiType + " is not specified Taxi type");
        }

        return returnTaxiInst;
    }
}