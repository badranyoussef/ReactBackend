package ThirdSemesterExercises.Backend.Week7Year2024.FridayReview;


//                      JPA entities and annotations

//1) Be able to use and understand the basic annotations of JPA in classes, properties and methods
//2) Apply JPA annotations to map Java classes to database tables
//3) Be able to explain and demonstrate the use of the annotations @Entity, @Table, @Id, @GeneratedValue, @Column


import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "car_style")//(IN THE () ADD CONSTRAINTS HERE USING ,)
    private String carStyle;


    public Car(String carStyle) {
        this.carStyle = carStyle;
    }

    public Car() {

    }

    @PrePersist
    public void onCreate(){

    }

    @PreUpdate
    public void onUpdate(){

    }

}
