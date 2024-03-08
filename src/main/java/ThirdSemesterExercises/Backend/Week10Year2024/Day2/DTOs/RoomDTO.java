package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private Integer id;
    private int hotelId;
    private int number;
    private int price;
}
