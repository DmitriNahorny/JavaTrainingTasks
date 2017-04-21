package by.nahorny.mvc.exception;

/**
 * Created by Dmitri_Nahorny on 4/17/2017.
 */
public class RegisterExistingUserException extends Throwable {
    public RegisterExistingUserException(String msg) {
        super(msg);
    }

    public RegisterExistingUserException(String msg, Exception cause) {
        super(msg, cause);
    }

    public RegisterExistingUserException(Exception cause) {
        super(cause);
    }

    public RegisterExistingUserException() {
        super();
    }
}
