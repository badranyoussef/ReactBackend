package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        // Please consider how to implement US-1 - and do it

        try (EntityManager em = emf.createEntityManager()) {
            Person p1 = new Person("Ahmad");
            PersonDetail personDetail = new PersonDetail("L", 28, "H", 200);

            // Add a few notes to each person
            Note note1 = new Note("MEGET VIGTIG BESKED", LocalDate.of(2024, 2, 19));
            Note note2 = new Note("EKSTRA VIGTIG BESKED", LocalDate.of(2024, 2, 19));
            p1.addPersonDetail(personDetail);
            Fee f1 = new Fee(125, LocalDate.of(2023, 8, 25));
            Fee f2 = new Fee(150, LocalDate.of(2023, 7, 19));
            p1.addFee(f1);
            p1.addFee(f2);
            p1.addNote(note1);
            p1.addNote(note2);

            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
        }
    }
}
