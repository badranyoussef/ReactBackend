package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PersonDTO {

    private Integer id;
    private String name;
    private Integer age;
    private Integer noteId;
    private String noteText;

    public PersonDTO(Integer id, String name, Integer age, Integer noteId, String noteText) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.noteId = noteId;
        this.noteText = noteText;
    }


    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "Person id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", noteId=" + noteId +
                ", noteText='" + noteText + '\'' +
                '}';
    }
}