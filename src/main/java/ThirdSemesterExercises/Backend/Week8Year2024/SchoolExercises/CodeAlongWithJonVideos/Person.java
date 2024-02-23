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
@NoArgsConstructor
@ToString
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // Relationer 1:1

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonDetail personDetail;

    public Person(String name) {
        this.name = name;
    }

    // Relationer 1:M

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Fee> fees = new HashSet<>();


    // Relationer 1:M

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<PersonEvent> events = new HashSet<>();

    // Relationer M:M

    /*
    @ManyToMany (cascade = CascadeType.PERSIST)
    private Set<Event> events = new HashSet<>();

    // Uni-directional add

    public void addEvent(Event event) {
        this.events.add(event);
    } */

    // Bi-directional update
    public void addPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
        if (personDetail != null) {
            personDetail.setPerson(this);
        }
    }

    public void addFee(Fee fee) {
        this.fees.add(fee);
        if (fee != null) {
            fee.setPerson(this);
        }
    }

    // Uni-directional add

    public void addEvent(Person person, Event event, LocalDate l, int eventFee) {
        PersonEvent personEvent = new PersonEvent(person, event, l, eventFee);
        this.events.add(personEvent);
    }
}
