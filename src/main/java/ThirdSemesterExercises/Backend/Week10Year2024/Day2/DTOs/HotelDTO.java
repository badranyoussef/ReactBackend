package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private Integer id;
    private String name;
    private String address;
    private List<Room> rooms;
}