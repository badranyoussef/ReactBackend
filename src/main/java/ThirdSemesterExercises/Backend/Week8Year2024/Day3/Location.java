package ThirdSemesterExercises.Backend.Week8Year2024.Day3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    Extend the existing package tracking system to include a new entity named "Location" using Lombok to manage location information.
    The "Location" entity should have the following attributes:

    ID (auto-generated primary key)
    Latitude (Double)
    Longitude (Double)
    Address (String)
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Double latitude;
    private Double longitude;
    private String address;

    public Location(Double latitude, Double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}