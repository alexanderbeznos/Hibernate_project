package servlets.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import servlets.models.Player;
import servlets.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBUsers {
    private SessionFactory factory;
    private static final BasicDataSource SOURSE = new BasicDataSource();
    private static final DBUsers INSTANCE = new DBUsers();

    /**
     * При создании объекта DbStore создается пул соединений(при помощи BasicDataSource) с базой данных.
     * Создается таблица в базе данных для храниения информации об игроках.
     */
    private DBUsers() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static DBUsers getInstance() {
        return INSTANCE;
    }

    private void doVoid(final Consumer<Session> command) throws UserValidationException {
        final Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            command.accept(session);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                throw new UserValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
    }

    private <T> T doFunction(final Function<Session, T> command) throws UserValidationException {
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
                throw new UserValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * Метод создаёт и выполняет запрос на добавление нового пользователя в БД.
     * Пользователю присваивается уникальный номер.
     */
    public void addOrUpdate(User user) throws UserValidationException {
        doVoid(tmp -> tmp.saveOrUpdate(user));
    }

    /**
     * Метод создает и выполняет запрос по поиску игрока по id в БД.
     */
//    public User findByLogin(String login) throws UserValidationException {
//        User result = null;
//        try (Connection connection = SOURSE.getConnection();
//             PreparedStatement ps = connection.prepareStatement(
//                     "select * from users where login = ?;"
//             )) {
//            ps.setString(1, login);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int id = Integer.parseInt(rs.getString("id"));
//                String password = rs.getString("password");
//                result = new User(id, login, password);
//            }
//        } catch (SQLException e) {
//            throw new UserValidationException("Cannot find user.");
//        }
//        return result;
//    }
    public User findByLogin(String login) throws UserValidationException {
        String query = String.format("from User u where u.login = '%s'", login);
        return (User) doFunction(tmp -> tmp.createQuery(query).getSingleResult());
    }
}
