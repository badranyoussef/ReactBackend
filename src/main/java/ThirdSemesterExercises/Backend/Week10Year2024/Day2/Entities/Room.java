package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private int number;
    private int price;

    public Room(int number, int price) {
        this.number = number;
        this.price = price;
    }

    public int getHotelId() {
        return hotel.getId();
    }
}
