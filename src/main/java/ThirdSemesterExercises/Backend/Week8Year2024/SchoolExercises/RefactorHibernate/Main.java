package ThirdSemesterExercises.Backend.Week8Year2024.SchoolExercises.RefactorHibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("exercise");
        EntityManager em = emf.createEntityManager();
    }

}
