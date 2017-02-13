package by.nahorny.Task2.file_reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class FileReader {
    public String readFromFile (){
        String resultString = "";
        try {
            FileInputStream in = new FileInputStream("./resource/input.txt");
            int c;
            while ((c = in.read()) != -1) {
                resultString += (char)c;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
