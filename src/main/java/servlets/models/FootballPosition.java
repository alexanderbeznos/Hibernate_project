package servlets.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

/**
 * @version 1.0.
 * @since 05.11.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
@Entity
@Table(name = "football_position")
public class FootballPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<Player> players;

    public FootballPosition(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FootballPosition(int id) {
        this.id = id;
    }

    public FootballPosition() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
