package ThirdSemesterExercises.Backend.Week7Year2024.SchoolExercises.Day1;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "person")
@NoArgsConstructor
@ToString

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public Person(String firstName, Gender gender, String email) {
        this.firstName = firstName;
        this.gender = gender;
        this.email = email;
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}