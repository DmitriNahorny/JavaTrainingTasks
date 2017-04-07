package by.nahorny.task5.exception;

/**
 * Created by Dmitri_Nahorny on 4/7/2017.
 */
public class LeafComponentOperationException extends Exception {
    public LeafComponentOperationException()
    {

    }

    public LeafComponentOperationException(String message)
    {
        super(message);
    }

    public LeafComponentOperationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public LeafComponentOperationException(Throwable cause)
    {
        super(cause);
    }
}