package servlets.validation;

import servlets.models.Player;
import servlets.storage.DBStore;
import servlets.storage.PlayerValidationException;
import servlets.storage.Store;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class ValidateService implements Validate {

    /**
     * Ссылка на объект DBStore.
     */
    private final Store objectStore = DBStore.getInstance();
    private static final ValidateService INSTANCE =  new ValidateService();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для добавления игрока в БД.
     * @param player игрок для добавления
     */
    @Override
    public void add(Player player) throws PlayerValidationException {
        this.objectStore.add(player);
    }

    /**
     * Метод для редактирования игрока в БД.
     * @param player игрок, на которого будет заменен игрок в БД.
     * @param id номер игрока для редактирования.
     */
    @Override
    public boolean update(Player player, int id) throws PlayerValidationException {
        boolean result;
        result = this.objectStore.update(player, id);
        return result;
    }

    /**
     * Метод для удаления игрока из БД.
     * @param id номер игрока для удаления.
     */
    @Override
    public boolean delete(int id) throws PlayerValidationException {
        boolean result;
        result = this.objectStore.delete(id);
        return result;
    }

    /**
     * Метод возвращает список всех игроков.
     */
    @Override
    public Collection<Player> findAll() throws PlayerValidationException {
        Collection<Player> result = this.objectStore.findAll();
        return result;
    }

    /**
     * Метод возвращает игрока по номеру.
     * @param id номер искомого игрока.
     */
    @Override
    public Player findById(int id) throws PlayerValidationException {
        return this.objectStore.findById(id);
    }
}
