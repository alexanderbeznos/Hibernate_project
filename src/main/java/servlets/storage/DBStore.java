package servlets.storage;

import org.apache.commons.dbcp2.BasicDataSource;
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

    private static final BasicDataSource SOURSE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    /**
     * При создании объекта DbStore создается пул соединений(при помощи BasicDataSource) с базой данных.
     * Создается таблица в базе данных для храниения информации об игроках.
     */
    private DBStore() {
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

    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод создаёт таблицу для хранения информации об игроках, если она еще не была создана.
     */
    public void createTable() {
        try {
            Connection connection = SOURSE.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists players("
                    + "id serial primary key,"
                    + "name varchar(100),"
                    + "lastname varchar(100),"
                    + "marketValue integer,"
                    + "country varchar(100),"
                    + "club varchar(100));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создаёт и выполняет запрос на добавление нового игрока в БД.
     * Игроку присваивается уникальный номер.
     */
    @Override
    public void add(Player player) throws PlayerValidationException {
        try (Connection connection = SOURSE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into players"
                             + "(name, lastname, marketValue, country, club)"
                             + "values (?, ?, ?, ?, ?);"
             )) {
            ps.setString(1, player.getName());
            ps.setString(2, player.getLastName());
            ps.setInt(3, player.getMarketValue());
            ps.setString(4, player.getCountry());
            ps.setString(5, player.getClub());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new PlayerValidationException("Cannot add Player.");
        }
    }

    /**
     * Метод создает и выполняет запрос на редактирование игрока в БД.
     */
    @Override
    public boolean update(Player player, int id) throws PlayerValidationException {
        boolean result = false;
        try (Connection connection = SOURSE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "update players set name = ?, lastName = ?, marketValue = ?, country = ?, club = ?"
                             + "where id = ?;"
             )) {
            ps.setString(1, player.getName());
            ps.setString(2, player.getLastName());
            ps.setInt(3, player.getMarketValue());
            ps.setString(4, player.getCountry());
            ps.setString(5, player.getClub());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PlayerValidationException("Cannot update Player.");
        }
        return result;
    }

    /**
     * Метод создает и выполняет запрос на удаление игрока в БД.
     */
    @Override
    public boolean delete(int id) throws PlayerValidationException {
        boolean result = false;
        try (Connection connection = SOURSE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "delete from players where id = ?;"
             )) {
            ps.setInt(1, id);
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new PlayerValidationException("Cannot delete Player.");
        }
        return result;
    }

    /**
     * Метод получает всех игроков в БД.
     */
    @Override
    public Collection<Player> findAll() throws PlayerValidationException {
        Collection<Player> list = new ArrayList<>();
        try (Connection connection = SOURSE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "select * from players;"
            );
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                int marketValue = rs.getInt("marketValue");
                String country = rs.getString("country");
                String club = rs.getString("club");
                Player player = new Player(id, name, lastName, marketValue, country, club);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new PlayerValidationException("Cannot find all players.");
        }
        return list;
    }

    /**
     * Метод создает и выполняет запрос по поиску игрока по id в БД.
     */
    @Override
    public Player findById(int id) throws PlayerValidationException {
        Player result = null;
        try (Connection connection = SOURSE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select * from players where id = ?;"
             )) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                int marketValue = rs.getInt("marketValue");
                String country = rs.getString("country");
                String club = rs.getString("club");
                result = new Player(id, name, lastName, marketValue, country, club);
            }
        } catch (SQLException e) {
            throw new PlayerValidationException("Cannot find player.");
        }
        return result;
    }
}

