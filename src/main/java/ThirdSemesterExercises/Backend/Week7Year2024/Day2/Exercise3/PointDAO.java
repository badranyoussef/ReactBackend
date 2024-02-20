package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PointDAO {

    // Create a DAO class and transfer all methods that interact with the database to this class.
    // Copied and pasted from the main method.

    /*
    Make each of the methods in the DAO class return a value instead of printing it to the console, or
    it's going to be difficult to test them. You do need to test the persistence of the data though.
    The method that retrieves all Points for example should return a list of all the points from the database.
    */

    private static EntityManagerFactory emf;
    private static PointDAO instance;

    public static PointDAO getInstance(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new PointDAO();
        }
        return instance;
    }

    public int store1000Points() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            for (int i = 0; i < 1000; i++) {
                Point p = new Point(i, i);
                em.persist(p);
            }
            em.getTransaction().commit();
            return 1;
        }
    }


    public int numberOfPointObjects() {
        try (EntityManager em = emf.createEntityManager()) {
            Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
            long count = (long) q1.getSingleResult();
            return (int) count; // Casting to int as the method signature suggests
        }
    }

    public double findTheAverageX(int x) {
        try (EntityManager em = emf.createEntityManager()) {
            Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
            return (double) q2.getSingleResult();
        }
    }

    public List<Point> retrieveAllThePointObjects() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            List<Point> results = query.getResultList();
            return results;
        }
    }
}
