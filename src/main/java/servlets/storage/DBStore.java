package servlets.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import servlets.models.FootballPosition;
import servlets.models.Player;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class DBStore implements Store {
    private static final DBStore INSTANCE = new DBStore();
    private SessionFactory factory;

    /**
     * При создании объекта DbStore создается пул соединений(при помощи BasicDataSource) с базой данных.
     * Создается таблица в базе данных для храниения информации об игроках.
     */
    private DBStore() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private <T> T doFunction(final Function<Session, T> command) throws PlayerValidationException {
        final Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            T rsl = command.apply(session);
            tr.commit();
            return rsl;
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
                throw new PlayerValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
        return null;
    }

    private void doVoid(final Consumer<Session> command) throws PlayerValidationException {
        final Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            command.accept(session);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                throw new PlayerValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
    }

    /**
     * Метод создаёт и выполняет запрос на добавление нового игрока в БД.
     * Игроку присваивается уникальный номер.
     */
    @Override
    public void addOrUpdate(Player player) throws PlayerValidationException {
        doVoid(tmp -> tmp.saveOrUpdate(player));
    }

    /**
     * Метод создает и выполняет запрос на удаление игрока в БД.
     */
    @Override
    public void delete(int id) throws PlayerValidationException {
        Player player = new Player(id);
        doVoid(tmp -> tmp.delete(player));
    }

    /**
     * Метод получает всех игроков в БД.
     */
    @Override
    public Collection<Player> findAll() throws PlayerValidationException {
        return doFunction(tmp -> tmp.createQuery("from Player").list());
    }

    /**
     * Метод создает и выполняет запрос по поиску игрока по id в БД.
     */
    @Override
    public Player findById(int id) throws PlayerValidationException {
        String query = String.format("from Player where id = %d", id);
        return (Player) doFunction(tmp -> tmp.createQuery(query).getSingleResult());
    }

    @Override
    public Collection<FootballPosition> findAllPositions() throws PlayerValidationException {
        return doFunction(tmp -> tmp.createQuery("from FootballPosition").list());
    }
}

