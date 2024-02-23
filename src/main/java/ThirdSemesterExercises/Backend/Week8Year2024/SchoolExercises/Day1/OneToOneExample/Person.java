package ThirdSemesterExercises.Backend.Week8Year2024.SchoolExercises.Day1.OneToOneExample;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Establishing a One-to-One relationship with Passport entity
    // mappedBy indicates that Passport entity is responsible for managing the association
    // Cascade type ALL ensures that operations (e.g., persist, remove) on Person will be cascaded to Passport
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Passport passport;

    public Person(String name) {
        this.name = name;
        this.passport = new Passport(this); // Initialize passport with this person
    }
}
