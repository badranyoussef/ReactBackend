package ThirdSemesterExercises.Backend.Week7Year2024.Day3.Exercise6;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();
    }
}
