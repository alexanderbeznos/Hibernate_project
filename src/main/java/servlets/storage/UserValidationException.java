package servlets.storage;

/**
 * @version 1.0.
 * @since 29.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class UserValidationException extends Exception {
    public UserValidationException(String msg) {
        super(msg);
    }
}
