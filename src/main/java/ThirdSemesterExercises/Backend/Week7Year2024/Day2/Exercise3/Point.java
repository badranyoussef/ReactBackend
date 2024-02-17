package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Use Lombok to generate getters, toString and a NoArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "point")
public class Point {

    /*
    Create a new entity called Point with the following fields:
    id (int)
    x (int)
    y (int)
    */

    //Add the correct annotations to the entity. (@Entity, @Id, @GeneratedValue)
    //Use the identity strategy for the @GeneratedValue annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "x", nullable = false)
    private int x;

    @Column(name = "y", nullable = false)
    private int y;

    //Create a constructor that takes x and y as parameters
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Remember to add the entity to the Hibernate config file
    //Done


}
