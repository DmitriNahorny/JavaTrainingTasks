package by.nahorny.lesson1.checker;

/**
 * Created by Dmitri_Nahorny on 2/16/2017.
 */
public class NumberChecker {
    public static boolean radiusCheck (String customNumber){
        boolean checkResult = false;
        try {
            double userNumber = Double.valueOf(customNumber).doubleValue();
            if (userNumber > 0){
                checkResult = true;
            }
        }
        catch (NumberFormatException e){}
        return checkResult;
    }
}
