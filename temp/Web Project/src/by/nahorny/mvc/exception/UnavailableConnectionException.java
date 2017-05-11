package by.nahorny.mvc.exception;

/**
 * Created by Dmitri_Nahorny on 4/17/2017.
 */
public class UnavailableConnectionException extends Exception {
    public UnavailableConnectionException(String msg) {
        super(msg);
    }

    public UnavailableConnectionException(String msg, Exception cause) {
        super(msg, cause);
    }

    public UnavailableConnectionException(Exception cause) {
        super(cause);
    }

    public UnavailableConnectionException() {
        super();
    }
}
