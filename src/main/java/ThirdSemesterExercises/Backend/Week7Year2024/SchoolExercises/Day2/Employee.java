package ThirdSemesterExercises.Backend.Week7Year2024.SchoolExercises.Day2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
// Use JPA annotations to map the entity class to a database table named employees
@Table(name = "employees")
public class Employee {
    /*
    Include appropriate annotations such as @Entity, @Table, @Id, @GeneratedValue, and
    @Column to define the primary key and attributes mapping.
    */

    // Create Employee entity with the following attributes: id, firstName, lastName, email, salary and department

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    // Add a constraint to the email attribute to ensure that the email address is unique
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "department", nullable = false)
    private String department;

    // Use the Insert SQL query below to add some data to the employees table

    /*
    INSERT INTO employee (id, firstName, lastName, department, salary, email)
    VALUES (1, 'John', 'Doe', 'HR', 50000, 'john.doe@example.com'),
       (2, 'Jane', 'Smith', 'Finance', 60000, 'jane.smith@example.com'),
       (3, 'Michael', 'Johnson', 'IT', 70000, 'michael.johnson@example.com'),
       (4, 'Emily', 'Williams', 'Sales', 55000, 'emily.williams@example.com'),
       (5, 'Christopher', 'Brown', 'Marketing', 65000, 'christopher.brown@example.com'),
       (6, 'Amanda', 'Jones', 'HR', 48000, 'amanda.jones@example.com'),
       (7, 'David', 'Miller', 'IT', 72000, 'david.miller@example.com'),
       (8, 'Sarah', 'Wilson', 'Finance', 62000, 'sarah.wilson@example.com'),
       (9, 'Matthew', 'Taylor', 'Sales', 58000, 'matthew.taylor@example.com'),
       (10, 'Jennifer', 'Anderson', 'Marketing', 67000, 'jennifer.anderson@example.com');
     */


}
