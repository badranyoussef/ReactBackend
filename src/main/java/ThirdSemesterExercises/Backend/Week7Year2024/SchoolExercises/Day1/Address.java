package ThirdSemesterExercises.Backend.Week7Year2024.SchoolExercises.Day1;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address")
@NoArgsConstructor
@ToString
@Setter

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Enumerated(EnumType.STRING)
    @Column(name = "town", nullable = false)
    private Town town;

    @Column(name = "zipcode", nullable = false, unique = true)
    private int zipode;

    public Address(String streetName, Town town, int zipode) {
        this.streetName = streetName;
        this.town = town;
        this.zipode = zipode;
    }

    public enum Town {
        Lyngby,
        Roskilde
    }
}