package ThirdSemesterExercises.Backend.Week8Year2024.SchoolExercises.Day1.OneToOneExample;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Establishing a bidirectional One-to-One relationship with Person entity
    // JoinColumn specifies the foreign key column in the Passport table referencing the Person table
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person owner;

    public Passport(Person owner) {
        this.owner = owner; // Set owner when creating Passport
    }
}