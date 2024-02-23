package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// The two entities should be called Driver and WasteTruck.
// Both entities should be placed in a package called model.
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "wastetruck")
public class WasteTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private int capacity;

    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    @Column(name = "is_available")
    private boolean isAvailable;

    // the truck constructor should only take the brand, capacity and registration number as a parameter
    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }
}
