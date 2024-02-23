package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private int amount;
    private LocalDate payDate;

    @ManyToOne
    private Person person;

    public Fee(int amount, LocalDate payDate) {
        this.amount = amount;
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", amount=" + amount +
                // Include other relevant fields but avoid referencing related entities directly
                '}';
    }
}
