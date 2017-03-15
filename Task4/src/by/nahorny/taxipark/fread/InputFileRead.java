package by.nahorny.taxipark.fread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitri_Nahorny on 3/1/2017.
 */
public class InputFileRead {
    private final static String INPUT_FILE_NAME = "./resource/input.txt";

    static Logger logger = LogManager.getLogger(InputFileRead.class);

    public static ArrayList<String> readFromFile (){
        ArrayList<String> inputCarsInfo = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(INPUT_FILE_NAME);
            String resultString = "";
            int c;
            while ((c = in.read()) != -1) {
                if (c != 10 & c != 13){
                    resultString += (char)c;
                }
                if (c == 13) {
                    inputCarsInfo.add(resultString);
                    resultString = "";
                }
            }
            inputCarsInfo.add(resultString);
            in.close();
        } catch (FileNotFoundException e) {
            logger.fatal(e);
        } catch (IOException e) {
            logger.fatal(e);
        }
        return inputCarsInfo;
    }
}
