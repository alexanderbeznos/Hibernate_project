package servlets.models;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class Player {
    private int id;
    private String name;
    private String lastName;
    private int marketValue;
    private String country;
    private String club;

    public Player(int id, String name, String lastname, int markeValue, String country, String club) {
        this.id = id;
        this.name = name;
        this.lastName = lastname;
        this.marketValue = markeValue;
        this.country = country;
        this.club = club;
    }

    public Player(String name, String lastName, int marketValue, String country, String club) {
        this.name = name;
        this.lastName = lastName;
        this.marketValue = marketValue;
        this.country = country;
        this.club = club;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public String getCountry() {
        return country;
    }

    public String getClub() {
        return club;
    }

    @Override
    public String toString() {
        return "Player{"
                + "name='" + name + '\''
                + ", lastName='" + lastName + '\''
                + ", marketValue=" + marketValue
                + ", country='" + country + '\''
                + ", club='" + club + '\''
                + '}';
    }
}
