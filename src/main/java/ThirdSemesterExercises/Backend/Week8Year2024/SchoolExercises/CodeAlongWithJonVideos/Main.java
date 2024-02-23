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
            Event e1 = new Event("DM-Senior", LocalDate.now());
            Event e2 = new Event("SM-Senior", LocalDate.now());
            //p1.addEvent(e1);
            //p1.addEvent(e2);

            p1.addEvent(p1, e1, LocalDate.now(), 300);
            p1.addEvent(p1, e2, LocalDate.now(), 300);

            em.getTransaction().begin();
            em.persist(e1);
            em.persist(e2);
            em.persist(p1);
            em.getTransaction().commit();
        }
    }
}
