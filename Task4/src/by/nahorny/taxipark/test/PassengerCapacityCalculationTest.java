package by.nahorny.taxipark.test;

import by.nahorny.taxipark.compute.CapacityParkCompute;
import by.nahorny.taxipark.creator.CarsCreator;
import by.nahorny.taxipark.fread.InputFileRead;
import by.nahorny.taxipark.freport.ExportFileReport;
import by.nahorny.taxipark.park.ParkCarsList;
import by.nahorny.taxipark.taxi.CarBrand;
import by.nahorny.taxipark.taxi.CargoTaxi;
import by.nahorny.taxipark.taxi.PassengerTaxi;
import by.nahorny.taxipark.taxi.Taxi;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;


/**
 * Created by Dmitri_Nahorny on 3/12/2017.
 */
@RunWith(Parameterized.class)
public class PassengerCapacityCalculationTest {

    private static TreeSet<Taxi> currentTaxiList;
    private static Comparator<Taxi> defaultTaxiComparator = ParkCarsList.getParkInstance().getCarsContainer().comparator();

    private TreeSet taxiSet;
    private int totalPassengersCapacity;

    public PassengerCapacityCalculationTest(TreeSet<Taxi> taxiSet, int totalPassengersCapacity) {
        this.taxiSet = taxiSet;
        this.totalPassengersCapacity = totalPassengersCapacity;
    }

    @BeforeClass
    public static void currentTaxiParkInit() {

        ArrayList<String> carsInfo = InputFileRead.readFromFile();
        ParkCarsList currentTaxiParkInst = ParkCarsList.getParkInstance();
        CarsCreator.createCarsFromList(carsInfo,currentTaxiParkInst);
        currentTaxiList = currentTaxiParkInst.getCarsContainer();
    }

    @Parameters
    public static Collection<Object[]> passengersCapacityCalcTestParametersTable() {

        PassengerTaxi pt1 = new PassengerTaxi(1, CarBrand.GEELY, 23.5f, 4);
        PassengerTaxi pt2 = new PassengerTaxi(3, CarBrand.MERCEDES, 13.7f,6);
        PassengerTaxi pt3 = new PassengerTaxi(5, CarBrand.MERCEDES, 7.5f,4);

        CargoTaxi ct1 = new CargoTaxi(2, CarBrand.GEELY, 9.5f, 10.3f);
        CargoTaxi ct2 = new CargoTaxi(4, CarBrand.MERCEDES, 23.7f,15.3f);
        CargoTaxi ct3 = new CargoTaxi(6, CarBrand.MERCEDES, 8.5f,15.3f);

        TreeSet<Taxi> testTaxiSet1 = new TreeSet<>(defaultTaxiComparator);
        testTaxiSet1.add(pt1);
        testTaxiSet1.add(pt2);
        testTaxiSet1.add(pt3);

        TreeSet<Taxi> testTaxiSet2 = new TreeSet<>(defaultTaxiComparator);
        testTaxiSet2.add(pt1);
        testTaxiSet2.add(pt3);
        testTaxiSet2.add(ct1);
        testTaxiSet2.add(ct2);

        TreeSet<Taxi> testTaxiSet3 = new TreeSet<>(defaultTaxiComparator);
        testTaxiSet3.add(ct1);
        testTaxiSet3.add(ct2);
        testTaxiSet3.add(ct3);

        return Arrays.asList(new Object[][] {
                {testTaxiSet1, 14},
                {testTaxiSet2, 8},
                {testTaxiSet3, 0}
            }
        );
    }

    @Test
    public void passengersCapacityCalcTest() {

        int resultPassengersCapacity = CapacityParkCompute.passengersTaxiParkCapacity(taxiSet);

        Assert.assertEquals(resultPassengersCapacity,totalPassengersCapacity);
    }

    @AfterClass
    public static void exportTaxiParkCapacity() {
        Integer parkPassengerCapacity = CapacityParkCompute.passengersTaxiParkCapacity(currentTaxiList);
        StringBuilder reportHeader = new StringBuilder("The whole taxi park passenger capacity is:");
        ExportFileReport.exportCapacityReport(parkPassengerCapacity, reportHeader.toString());
    }
}
