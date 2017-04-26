package by.nahorny.task5.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Dmitri_Nahorny on 3/28/2017.
 */
public class TextLoader {
    public String readTextFromFile(String path) throws IOException {
        StringBuilder result = new StringBuilder();

        File file = new File(path);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                result.append(line);
                result.append(System.lineSeparator());
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        return result.toString();
    }
}
