package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private Integer id;
    private String name;
    private String address;
}