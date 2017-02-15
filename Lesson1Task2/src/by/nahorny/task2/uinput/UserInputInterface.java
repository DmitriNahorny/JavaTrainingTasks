package by.nahorny.task2.uinput;

import java.io.IOException;
import java.util.Scanner;
import by.nahorny.task2.checker.NumberChecker;

/**
 * Created by Dmitri_Nahorny on 2/9/2017.
 */
public class UserInputInterface {
    public int userValue() {
        Scanner customScanner = new Scanner(System.in);
        String checkerCode = null;
        do {
            System.out.println("Please, enter the 4-digit number:");
            String userInput = customScanner.nextLine();

            NumberChecker checkerInstance = new NumberChecker();
            checkerCode = checkerInstance.fourDigitsChecker(userInput);
        }
        while (checkerCode == null);

        return Integer.valueOf(checkerCode).intValue();
    }
}