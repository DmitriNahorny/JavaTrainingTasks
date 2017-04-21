package by.nahorny.mvc.exception;

/**
 * Created by Dmitri_Nahorny on 4/17/2017.
 */
public class DAOException extends Throwable {
    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Exception cause) {
        super(msg, cause);
    }

    public DAOException(Exception cause) {
        super(cause);
    }

    public DAOException() {
        super();
    }
}
