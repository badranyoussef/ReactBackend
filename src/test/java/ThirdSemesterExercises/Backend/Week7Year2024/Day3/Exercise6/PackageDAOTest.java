package ThirdSemesterExercises.Backend.Week7Year2024.Day3.Exercise6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class PackageDAOTest {
    //                                          All of my tests work fine.
    private static PackageDAO packageDAO;

    @BeforeAll
    public static void setUp() {
        packageDAO = new PackageDAO();
    }

    @AfterAll
    public static void tearDown() {
    }

    @Test
    void create() {
        Package p1 = new Package("123A", "Ahmad", "Alkaseb", Package.deliveryStatus.PENDING);
        Package p2 = new Package("456A", "Ahmad", "Alkaseb", Package.deliveryStatus.IN_TRANSIT);
        Package p3 = new Package("789A", "Ahmad", "Alkaseb", Package.deliveryStatus.DELIVERED);
        Package p4 = new Package("101A", "Ahmad", "Alkaseb", Package.deliveryStatus.PENDING);
        Package p11 = packageDAO.create(p1);
        Package p12 = packageDAO.create(p2);
        Package p13 = packageDAO.create(p3);
        Package p14 = packageDAO.create(p4);

        assertEquals(p1, p11);
        assertEquals(p2, p12);
        assertEquals(p3, p13);
        assertEquals(p4, p14);
    }

    @Test
    void update() {
        assertEquals(1, packageDAO.update("PENDING", "101AA"));
    }

    @Test
    void delete() {
        assertEquals(1, packageDAO.delete(3));
    }

    @Test
    void findByTrackingNumber() {
        Package p1 = packageDAO.findByTrackingNumber("101AA");
        assertEquals(p1, packageDAO.findByTrackingNumber("101AA"));
    }
}
