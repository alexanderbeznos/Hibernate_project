package servlets.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import servlets.models.Player;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

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

    /**
     * Метод создаёт таблицу для хранения информации об игроках, если она еще не была создана.
     */
//    public void createTable() {
//        try {
//            Connection connection = SOURSE.getConnection();
//            Statement statement = connection.createStatement();
//            statement.execute("create table if not exists players("
//                    + "id serial primary key,"
//                    + "name varchar(100),"
//                    + "lastname varchar(100),"
//                    + "marketValue integer,"
//                    + "country varchar(100),"
//                    + "club varchar(100));");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



    /**
     * Метод создаёт и выполняет запрос на добавление нового игрока в БД.
     * Игроку присваивается уникальный номер.
     */
    @Override
    public void addOrUpdate(Player player) throws PlayerValidationException {
        Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.saveOrUpdate(player);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
                throw new PlayerValidationException("Problem has occurred");
            }
        } finally {

            session.close();
        }
    }

    /**
     * Метод создает и выполняет запрос на удаление игрока в БД.
     */
    @Override
    public void delete(int id) throws PlayerValidationException {
        Player player = new Player(id);
        Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.delete(player);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
                throw new PlayerValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
    }

    /**
     * Метод получает всех игроков в БД.
     */
    @Override
    public Collection<Player> findAll() throws PlayerValidationException {
        Collection<Player> list = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            list = session.createQuery("from Player").list();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
                throw new PlayerValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
        return list;
    }

    /**
     * Метод создает и выполняет запрос по поиску игрока по id в БД.
     */
    @Override
    public Player findById(int id) throws PlayerValidationException {
        Player result = null;
        Session session = factory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            String query = String.format("from Player where id = %d", id);
            result = (Player)session.createQuery(query).getSingleResult();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
                throw new PlayerValidationException("Problem has occurred");
            }
        } finally {
            session.close();
        }
        return result;
    }
}

