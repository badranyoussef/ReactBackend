package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
            room.setHotel(this);
        }
    }
}
