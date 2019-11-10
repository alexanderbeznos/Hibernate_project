package servlets.validation;

import servlets.models.Player;
import servlets.storage.PlayerValidationException;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public interface Validate {
    void addOrUpdate(Player player) throws PlayerValidationException;
    void delete(int id) throws PlayerValidationException;
    Collection<Player> findAll() throws PlayerValidationException;
    Player findById(int id) throws PlayerValidationException;
}
