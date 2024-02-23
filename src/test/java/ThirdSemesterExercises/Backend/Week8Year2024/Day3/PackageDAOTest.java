package ThirdSemesterExercises.Backend.Week8Year2024.Day3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PackageDAOTest {

    private static EntityManagerFactory emf;
    private static PackageDAO packageDAO;

    @BeforeAll
    public static void setUpClass() {
        emf = HibernateConfigg.getEntityManagerFactoryConfig(true);
        packageDAO = PackageDAO.getInstance(emf);

        // Persist some sample data before running tests
        persistSampleData();
    }

    private static void persistSampleData() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Persist locations
            Location sourceLocation = new Location();
            sourceLocation.setLatitude(123.456);
            sourceLocation.setLongitude(456.789);
            sourceLocation.setAddress("Source Address");
            em.persist(sourceLocation);

            Location destinationLocation = new Location();
            destinationLocation.setLatitude(987.654);
            destinationLocation.setLongitude(654.321);
            destinationLocation.setAddress("Destination Address");
            em.persist(destinationLocation);

            // Persist packages
            Package package1 = new Package();
            package1.setTrackingNumber("TRACK123");
            package1.setSenderName("Sender");
            package1.setReceiverName("Receiver");
            package1.setDeliveryStatus(Package.deliveryStatus.PENDING);
            em.persist(package1);

            // Persist shipments
            Shipment shipment = new Shipment();
            shipment.setShipmentPackage(package1);
            shipment.setSourceLocation(sourceLocation);
            shipment.setDestinationLocation(destinationLocation);

            // Set shipment date/time as needed
            em.persist(shipment);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @BeforeEach
    public void setUp() {
        packageDAO = PackageDAO.getInstance(emf);
    }

    @Test
    public void testCreatePackage() {
        // Given
        Package newPackage = new Package("ABC123", "Sender", "Receiver", Package.deliveryStatus.PENDING);

        // When
        Package createdPackage = packageDAO.create(newPackage);

        // Then
        assertNotNull(createdPackage.getId());
    }

    @Test
    public void testUpdatePackageStatus() {
        // Given
        Package newPackage = new Package("XYZ789", "Sender", "Receiver", Package.deliveryStatus.PENDING);
        packageDAO.create(newPackage);

        // When
        int updatedCount = packageDAO.update("DELIVERED", "XYZ789");

        // Then
        assertEquals(1, updatedCount);
    }

    @Test
    public void testDeletePackage() {
        // Given
        Package newPackage = new Package("123456", "Sender", "Receiver", Package.deliveryStatus.PENDING);
        packageDAO.create(newPackage);

        // When
        int deletedCount = packageDAO.delete(newPackage.getId());

        // Then
        assertEquals(1, deletedCount);
    }

    @Test
    public void testFindByTrackingNumber() {
        // Given
        Package expectedPackage = new Package("TRACK123", "Sender", "Receiver", Package.deliveryStatus.PENDING);

        // When
        Package foundPackage = packageDAO.findByTrackingNumber("TRACK123");

        // Then
        assertEquals(expectedPackage.getTrackingNumber(), foundPackage.getTrackingNumber());
    }
}
