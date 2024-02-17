package ThirdSemesterExercises.Backend.Week7Year2024.Day1.Exercise1;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "unicorn")

/*
Create a new Java class named Unicorn.
Add attributes: id, name, age, and powerStrength.
Annotate id with @Id and @GeneratedValue.
Annotate the class with @Entity.
 */
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // BONUS CHALLENGE
    // Add validation constraints to the Unicorn entity, such as name should not be null or empty
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    private String name;


    @Column(name = "age", nullable = false)
    private int age;

    // BONUS CHALLENGE
    // PowerStrength should be between 1 and 100.
    @Column(name = "power_strength", nullable = false)
    private Integer powerStrength;

    @PrePersist
    @PreUpdate
    public void validator() {
        if (powerStrength >= 1 && powerStrength <= 100 && !name.isEmpty()) {
        } else {
            throw new RuntimeException();
        }
    }

    public Unicorn(String name, int age, Integer powerStrength) {
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }


}
