package servlets.storage;

import servlets.models.Player;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public interface Store {
    void add(Player player) throws PlayerValidationException;
    boolean update(Player player, int id) throws PlayerValidationException;
    boolean delete(int id) throws PlayerValidationException;
    Collection<Player> findAll() throws PlayerValidationException;
    Player findById(int id) throws PlayerValidationException;
}

