package by.nahorny.taxipark.test;

import by.nahorny.taxipark.compute.TaxiParkFilter;
import by.nahorny.taxipark.creator.CarsCreator;
import by.nahorny.taxipark.fread.InputFileRead;
import by.nahorny.taxipark.freport.ExportFileReport;
import by.nahorny.taxipark.park.ParkCarsList;
import by.nahorny.taxipark.taxi.CarBrand;
import by.nahorny.taxipark.taxi.CargoTaxi;
import by.nahorny.taxipark.taxi.Taxi;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by Dmitri_Nahorny on 3/6/2017.
 */
@RunWith(Parameterized.class)
public class CargoTaxiCapacityFilterTest {

    private static TreeSet<Taxi> currentTaxiList;

    private CargoTaxi[] cargoTaxiSet;
    private float minCarryingCapacity;
    private float maxCarryingCapacity;

    public CargoTaxiCapacityFilterTest(CargoTaxi[] cargoTaxiSet, float minCarryingCapacity, float maxCarryingCapacity) {
        this.cargoTaxiSet = cargoTaxiSet;
        this.minCarryingCapacity = minCarryingCapacity;
        this.maxCarryingCapacity = maxCarryingCapacity;
    }

    @BeforeClass
    public static void currentTaxiParkInit() {

        ArrayList<String> carsInfo = InputFileRead.readFromFile();
        ParkCarsList currentTaxiParkInst = ParkCarsList.getParkInstance();
        CarsCreator.createCarsFromList(carsInfo,currentTaxiParkInst);
        currentTaxiList = currentTaxiParkInst.getCarsContainer();
    }

    @Parameters
    public static Collection<Object[]> cargoCapacityFilterTestParametersTable() {
        return Arrays.asList(new Object[][] {
                {
                    new CargoTaxi[]{
                        new CargoTaxi(9, CarBrand.GEELY, 13.5f, 10.3f),
                        new CargoTaxi(12, CarBrand.MERCEDES, 13.7f,15.3f),
                        new CargoTaxi(3, CarBrand.MERCEDES, 17.5f,15.3f)
                    },
                    10,
                    20
                },
                {
                    new CargoTaxi[]{
                        new CargoTaxi(11, CarBrand.VOLVO, 12.5f, 25.3f)
                    },
                    20,
                    1000
                },
                {
                    new CargoTaxi[0], 0, 1
                }
            }
        );
    }

    @Test
    public void filterCargoTaxiListTest() {

        CargoTaxi[] filteredCargoTaxiSet = TaxiParkFilter.cargoTaxiCapacityFilter(currentTaxiList,this.minCarryingCapacity,this.maxCarryingCapacity).toArray(new CargoTaxi[0]);

        Assert.assertArrayEquals(filteredCargoTaxiSet,this.cargoTaxiSet);
    }

    @AfterClass
    public static void printFilterResult() {

        Collection<CargoTaxi> filteredCargoTaxiSet = TaxiParkFilter.cargoTaxiCapacityFilter(currentTaxiList,10,20);
        StringBuilder headerReport = new StringBuilder("The next cargo taxi have capacity between 10 and 20:").append(System.lineSeparator());
        ExportFileReport.exportTaxiList(filteredCargoTaxiSet, headerReport.toString());
    }
}
