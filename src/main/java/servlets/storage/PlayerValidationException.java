package servlets.storage;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class PlayerValidationException extends Exception {
    public PlayerValidationException(String msg) {
        super(msg);
    }
}
