package ThirdSemesterExercises.Backend.Week8Year2024.SchoolExercises.CodeAlongWithJonVideos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try (EntityManager em = emf.createEntityManager()) {

            Person p1 = new Person("Ahmad");
            PersonDetail pd1 = new PersonDetail("Lundtoftegade 3", 8000, "Roskilde", 66);
            p1.addPersonDetail(pd1);
            Fee f1 = new Fee(125, LocalDate.of(2023, 8, 25));
            Fee f2 = new Fee(150, LocalDate.of(2023, 7, 19));
            p1.addFee(f1);
            p1.addFee(f2);

            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
        }
    }
}
