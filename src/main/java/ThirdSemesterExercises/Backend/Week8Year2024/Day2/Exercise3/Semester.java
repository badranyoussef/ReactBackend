package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String description;

    public Semester(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @ManyToMany
    Set<Teacher> teacherSet = new HashSet<>();

    public void addTeacher(Teacher teacher) {
        teacherSet.add(teacher);
        teacher.getSemesterSet().add(this);
    }

    @OneToMany(mappedBy = "semester")
    private Set<Student> students = new HashSet<>();
}