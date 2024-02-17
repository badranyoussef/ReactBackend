package ThirdSemesterExercises.Backend.Week7Year2024.Day1.Exercise2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//  Use JPA annotations to map the entity class to a database table named students
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    /*
     Define a simple entity class called "Student" with attributes like id,
     firstName, lastName, email and age. Remember to include a no-arg constructor.
     */

    /*
     Include appropriate annotations such as @Entity, @Table, @Id, @GeneratedValue,
     and @Column to define the primary key and attributes mapping.
     */

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

    public Student(String firstName, String lastName, String eMail, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.age = age;
    }

    /*
    Add a @PrePersist method to the Student class that verifies that the email address is valid.
    If the email address is not valid, throw an exception.
     */
    @PrePersist
    // Use the same logic as above but this time in the @PreUpdate method
    @PreUpdate
    public void validator() {
        if (!isValidEmail(eMail)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    // Method to validate the email
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
