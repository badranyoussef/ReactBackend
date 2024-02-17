package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise5;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "students1")
public class Student1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    //  Add a constraint to the email attribute to ensure that the email address is unique
    @Column(name = "e-mail", nullable = false, unique = true)
    private String eMail;

    @Column(name = "age", nullable = false)
    private int age;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Student1(String firstName, String lastName, String eMail, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.age = age;
    }


    /*
    Implement lifecycle methods using @PrePersist and @PreUpdate annotations to automatically update
    timestamp fields for created and updated dates.

    Seen below
     */

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
