package by.nahorny.task5.exception;

/**
 * Created by Dmitri_Nahorny on 3/28/2017.
 */
public class TextParsingException extends Exception {
    public TextParsingException()
    {

    }

    public TextParsingException(String message)
    {
        super(message);
    }

    public TextParsingException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public TextParsingException(Throwable cause)
    {
        super(cause);
    }
}
