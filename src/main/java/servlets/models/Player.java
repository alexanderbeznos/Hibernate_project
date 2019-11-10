package servlets.models;

import javax.persistence.*;

/**
 * @version 1.0.
 * @since 25.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "marketvalue")
    private int marketValue;
    @Column(name = "country")
    private String country;
    @Column(name = "club")
    private String club;
    @ManyToOne
    @JoinColumn(name = "football_position_id")
    private FootballPosition position;

    public Player(int id, String name, String lastName, int marketValue, String country, String club, FootballPosition position) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.marketValue = marketValue;
        this.country = country;
        this.club = club;
        this.position = position;
    }

    public Player(String name, String lastName, int marketValue, String country, String club, FootballPosition position) {
        this.name = name;
        this.lastName = lastName;
        this.marketValue = marketValue;
        this.country = country;
        this.club = club;
        this.position = position;
    }

    public Player(int id) {
        this.id = id;
    }

    public Player() {
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

    public FootballPosition getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void setPosition(FootballPosition position) {
        this.position = position;
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
