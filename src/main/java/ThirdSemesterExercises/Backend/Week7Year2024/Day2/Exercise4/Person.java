package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise4;

// Coding Exercise: Demonstrate Lombok in Java

/*
Lombok is a popular library in Java that helps reduce boilerplate code. One of its most used features
is to generate getter, setter, constructor, and toString() methods without having to manually write them.
Let's use that as a basis for our exercise.
 */

/*
Set up a new Java project with Maven.
Add Lombok as a dependency.
Create a simple Person class with a few fields.
Use Lombok annotations to auto-generate the required methods.
*/

// Create the Person class

import lombok.*;

@Getter
// Opretter getters
@Setter
// Opretter setters
@NoArgsConstructor
// Opretter en default konstruktør
@AllArgsConstructor
// Opretter en konstruktør med alle attributter
@ToString
// Opretter en toString metode
@EqualsAndHashCode
// Sørger for at en Person med samme værdier har samme hashkode
@Builder
// Bruges til at oprette en person
public class Person {
    private String firstName;
    private String lastName;
    private int age;
}
