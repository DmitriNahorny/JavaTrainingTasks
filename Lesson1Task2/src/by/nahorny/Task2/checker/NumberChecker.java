package by.nahorny.task2.checker;

/**
 * Created by Dmitri_Nahorny on 2/10/2017.
 */
public class NumberChecker {
    public String fourDigitsChecker (String customNumber){
        String checkResult = null;
        try {
            int userNumber = Math.abs(Integer.valueOf(customNumber).intValue());
            if (userNumber/1000 >= 1 & userNumber/10000 < 1){
                checkResult = customNumber;
            }
        }
        catch (NumberFormatException e){}
        return checkResult;
    }
}