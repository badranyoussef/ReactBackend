package ThirdSemesterExercises.Backend.Week8Year2024.SchoolExercises.Day1.OneToOneExample;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        // Create test data
        Person person1 = new Person("Ahmad1");

        // Obtain EntityManager and begin transaction
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("exercise");
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person1);
            em.getTransaction().commit();
        }
    }
}
