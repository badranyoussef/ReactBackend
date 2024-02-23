package ThirdSemesterExercises.Backend.Week8Year2024.FridayReview;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carshop")
public class Carshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String car;


    // Explain and demonstrate the use of Enumerated annotation to map Java enum types to database columns.

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        SOLD,
        PENDING,
        AWAITING
    }

    // Explain and demonstrate the use of Temporal annotation to map Java Date and Calendar types to database columns.
    @Temporal(TemporalType.DATE) //(BEHØVES IKKE. NOGET MAN GJORDE I GAMLE DAGE)
    private LocalDate yearModel;

    // Explain the use of @Transient annotation.
    @Transient
    private int age;
}

class Main {
    public static void main(String[] args) {
        Carshop carshop = new Carshop();
        carshop.setStatus(Carshop.Status.valueOf("PENDING"));
    }
}