package by.nahorny.task5.exception;

/**
 * Created by Dmitri_Nahorny on 4/7/2017.
 */
public class SetHandlerException extends Exception {
    public SetHandlerException()
    {

    }

    public SetHandlerException(String message)
    {
        super(message);
    }

    public SetHandlerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SetHandlerException(Throwable cause)
    {
        super(cause);
    }
}
