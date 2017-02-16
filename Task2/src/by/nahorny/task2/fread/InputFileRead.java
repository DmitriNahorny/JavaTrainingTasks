package by.nahorny.task2.fread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class InputFileRead {
    private final String INPUT_FILE_NAME = "./resource/input.txt";
    public List<String> readFromFile (){
        List<String> inputNumbers = new ArrayList<String>();
        try {
            FileInputStream in = new FileInputStream(INPUT_FILE_NAME);
            String resultString = "";
            int c;
            while ((c = in.read()) != -1) {
                if (c != 10 & c != 13){
                    resultString += (char)c;
                }
                if (c == 13) {
                    inputNumbers.add(resultString);
                    resultString = "";
                }
            }
            inputNumbers.add(resultString);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputNumbers;
    }
}
