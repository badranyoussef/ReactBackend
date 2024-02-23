package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String firstName;
    private String lastName;

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany(mappedBy = "teacherSet")
    Set<Semester> semesterSet = new HashSet<>();

    public void addSemester(Semester semester) {
        semesterSet.add(semester);
        semester.getTeacherSet().add(this);
    }
}