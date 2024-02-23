package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PersonDetail {

    @Id
    private Integer id;
    private String address;
    private int zip;
    private String city;
    private int age;


    // Relationer 1:1

    @OneToOne
    @MapsId
    private Person person;

    public PersonDetail(String address, int zip, String city, int age) {
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDetail{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", person=" + person +
                '}';
    }
}
