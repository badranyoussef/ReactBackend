package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise3;


import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointDAOTest {

    /*
    Add tests for each method in the DAO class. Use the @BeforeAll and @AfterAll annotations to set up
    and close the EntityManagerFactory and EntityManager objects.
     */

    static PointDAO pointDAO;

    @BeforeAll
    static void setUp() {
        //All of my tests have been run without errors.
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(true);
        pointDAO = PointDAO.getInstance(emf);
    }

    @AfterAll
    static void tearDown() {
        // I have used try-with-resources. There the below classes gets closed after each use.
        // 1) EntityManagerFactory
        // 2) EntityManager
    }

    @Test
    void store1000Points() {
        assertEquals(1, pointDAO.store1000Points());
        //Test done successfully
    }

    /*

    @Test
    void numberOfPointObjects() {
        assertEquals(7000, pointD.numberOfPointObjects());
    }

    @Test
    void findTheAverageX() {
        assertEquals(499.5, PointDAO.findTheAverageX(1));
    }

    @Test
    void retrieveAllThePointObjects() {
        List<Point> l = PointDAO.retrieveAllThePointObjects();
        assertEquals(7000, l.size());
    }*/
}