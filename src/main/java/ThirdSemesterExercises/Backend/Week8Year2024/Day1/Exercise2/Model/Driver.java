package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;

// The two entities should be called Driver and WasteTruck.
// Both entities should be placed in a package called model.
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "driver")
public class Driver {

    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    @Column(name = "employment_date")
    private LocalDate employmentDate;

    private String name;
    private String surname;
    private double salary;

    @Column(name = "truck_id")
    private int truckId;

    // the driver constructor should only take the name, surname and salary as a parameter
    public Driver(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public int getEmploymentYear() {
        return employmentDate.getYear();
    }

    public String generateId() {
        if (employmentDate == null) {
            throw new IllegalStateException("Employment date must be initialized before generating ID.");
        }

        // Format employment date
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String formattedDate = dateFormat.format(java.sql.Date.valueOf(employmentDate));

        // Extract initials
        String initials = (name.substring(0, 1) + surname.substring(0, 1)).toUpperCase();

        // Generate random number between 100 and 999
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;

        // Get last letter of surname
        String lastLetter = surname.substring(surname.length() - 1).toUpperCase();

        // Concatenate components to form ID
        String id = formattedDate + "-" + initials + "-" + randomNumber + lastLetter;
        setId(id);

        return id;
    }


    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }

    @PrePersist
    protected void onCreate() {
        employmentDate = LocalDate.now();
        generateId();
        //validateDriverId(s);
    }
}
