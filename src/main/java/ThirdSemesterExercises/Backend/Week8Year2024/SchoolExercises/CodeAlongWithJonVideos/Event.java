package ThirdSemesterExercises.Backend.Week8Year2024.SchoolExercises.CodeAlongWithJonVideos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;

    public Event(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<PersonEvent> persons = new HashSet<>();
}
