package servlets.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import servlets.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUsers {
    private static final BasicDataSource SOURSE = new BasicDataSource();
    private static final DBUsers INSTANCE = new DBUsers();

    /**
     * При создании объекта DbStore создается пул соединений(при помощи BasicDataSource) с базой данных.
     * Создается таблица в базе данных для храниения информации об игроках.
     */
    private DBUsers() {
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURSE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURSE.setUrl(config.getProperty("url"));
            SOURSE.setUsername(config.getProperty("username"));
            SOURSE.setPassword(config.getProperty("password"));
            SOURSE.setMinIdle(5);
            SOURSE.setMaxIdle(10);
            SOURSE.setMaxOpenPreparedStatements(100);
            this.createTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DBUsers getInstance() {
        return INSTANCE;
    }

    /**
     * Метод создаёт таблицу для хранения информации об игроках, если она еще не была создана.
     */
    public void createTable() {
        try {
            Connection connection = SOURSE.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists users("
                    + "id serial primary key,"
                    + "login varchar(100) unique,"
                    + "password varchar(100))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создаёт и выполняет запрос на добавление нового пользователя в БД.
     * Пользователю присваивается уникальный номер.
     */
    public void add(User user) throws UserValidationException {
        try (Connection connection = SOURSE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into users"
                             + "(login, password)"
                             + "values (?, ?);"
             )) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new UserValidationException("Cannot add user.");
        }
    }

    /**
     * Метод создает и выполняет запрос по поиску игрока по id в БД.
     */
    public User findByLogin(String login) throws UserValidationException {
        User result = null;
        try (Connection connection = SOURSE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select * from users where login = ?;"
             )) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String password = rs.getString("password");
                result = new User(id, login, password);
            }
        } catch (SQLException e) {
            throw new UserValidationException("Cannot find user.");
        }
        return result;
    }
}
