package by.nahorny.taxipark.custexceptions;

/**
 * Created by Dmitri_Nahorny on 3/2/2017.
 */
public class IncorrectCarsInfoException extends Exception{
    public IncorrectCarsInfoException() {

    }

    public IncorrectCarsInfoException(String message) {
        super(message);
    }

    public IncorrectCarsInfoException(String message, Exception cause) {
        super(message,cause);
    }

    public IncorrectCarsInfoException(Exception cause) {
        super(cause);
    }
}
