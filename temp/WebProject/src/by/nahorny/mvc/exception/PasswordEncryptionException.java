package by.nahorny.mvc.exception;

/**
 * Created by Dmitri_Nahorny on 5/5/2017.
 */
public class PasswordEncryptionException extends Exception {
    public PasswordEncryptionException(String msg) {
        super(msg);
    }

    public PasswordEncryptionException(String msg, Exception cause) {
        super(msg, cause);
    }

    public PasswordEncryptionException(Exception cause) {
        super(cause);
    }

    public PasswordEncryptionException() {
        super();
    }
}
