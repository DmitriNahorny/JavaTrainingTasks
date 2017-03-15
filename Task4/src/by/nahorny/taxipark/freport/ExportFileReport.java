package by.nahorny.taxipark.freport;

import by.nahorny.taxipark.taxi.Taxi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Created by Dmitri_Nahorny on 3/13/2017.
 */
public class ExportFileReport {

    static Logger logger = LogManager.getLogger(ExportFileReport.class);
    private final static String OUTPUT_FILE_NAME = "./resource/output.txt";

    public static <T extends Taxi> void exportTaxiList(Collection<T> taxiList) {
        try(PrintWriter out = new PrintWriter(OUTPUT_FILE_NAME)){
            for (Taxi taxiInst: taxiList){
                out.println(taxiInst.toString());
            }
            out.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public static <T extends Taxi> void exportTaxiList(Collection<T> taxiList, String reportHeader) {
        try(PrintWriter out = new PrintWriter(OUTPUT_FILE_NAME)){
            out.println(reportHeader);

            for (Taxi taxiInst: taxiList){
                out.println(taxiInst.toString());
            }
            out.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public static <T extends Taxi> void exportTaxiList(Collection<T> taxiList, String reportHeader, String outputFileURL) {
        try(PrintWriter out = new PrintWriter(outputFileURL)){
            out.println(reportHeader);

            for (Taxi taxiInst: taxiList){
                out.println(taxiInst.toString());
            }

            out.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public static void exportCapacityReport(Number calculatedCapacity) {
        try(PrintWriter out = new PrintWriter(OUTPUT_FILE_NAME)){
            out.println(calculatedCapacity.toString());
            out.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public static void exportCapacityReport(Number calculatedCapacity, String reportHeader) {
        try(PrintWriter out = new PrintWriter(OUTPUT_FILE_NAME)){
            out.println(reportHeader);
            out.println(calculatedCapacity.toString());
            out.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public static void exportCapacityReport(Number calculatedCapacity, String reportHeader, String outputFileURL) {
        try(PrintWriter out = new PrintWriter(outputFileURL)){
            out.println(reportHeader);
            out.println(calculatedCapacity.toString());
            out.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }
}