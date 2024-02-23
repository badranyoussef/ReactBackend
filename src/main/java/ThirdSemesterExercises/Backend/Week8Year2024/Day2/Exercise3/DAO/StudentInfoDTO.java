package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.DAO;

import lombok.Getter;

@Getter
public class StudentInfoDTO {

    // Create a StudentInfoDTO class with the following properties
    private String fullName;
    private Integer studentId;
    private String thisSemesterName;
    private String thisSemesterDescription;

    public StudentInfoDTO(String fullName, Integer studentId, String thisSemesterName, String thisSemesterDescription) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.thisSemesterName = thisSemesterName;
        this.thisSemesterDescription = thisSemesterDescription;
    }
}
