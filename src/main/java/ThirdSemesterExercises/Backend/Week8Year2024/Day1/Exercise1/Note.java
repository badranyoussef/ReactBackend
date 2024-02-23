package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String note;
    private LocalDate payDate;

    @ManyToOne
    private Person person;

    public Note(String note, LocalDate payDate) {
        this.note = note;
        this.payDate = payDate;
    }
}