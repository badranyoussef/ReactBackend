package ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DogDTO {
    private static int lastId = 0;
    private int id;
    private String name;
    private String breed;
    private Gender gender;

    public enum Gender {
        FEMALE,
        MALE
    }

    private int age;

    public DogDTO(String name, String breed, int age, Gender gender) {
        this.id = ++lastId;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
    }
}
