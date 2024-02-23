package ThirdSemesterExercises.Backend.Week8Year2024.Day3;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

class ShipmentDAOTest {

    private static EntityManagerFactory emf;
    private static ShipmentDAO shipmentDAO;
    private static PackageDAO packageDAO;

    @BeforeAll
    static void setUp() {
        emf = HibernateConfigg.getEntityManagerFactoryConfig(true);
        shipmentDAO = ShipmentDAO.getInstance(emf);
        packageDAO = PackageDAO.getInstance(emf);

        // Create sample packages
        Package pkg1 = new Package("PKG123", "Ahmad", "Lars", Package.deliveryStatus.IN_TRANSIT);
        Package pkg2 = new Package("PKG456", "Lars", "Ahmad", Package.deliveryStatus.IN_TRANSIT);

        Location location1 = new Location(123., 456., "Lyngby");
        Location location2 = new Location(456., 789., "Lyngby");

        // Create sample shipments
        Shipment shipment1 = new Shipment(pkg1, location1, location1, LocalDate.now());
        Shipment shipment2 = new Shipment(pkg2, location2, location2, LocalDate.now());

        // Associate packages with shipments
        shipment1.setShipmentPackage(pkg1);
        shipment2.setShipmentPackage(pkg2);

        // Save shipments to the database
        packageDAO.create(pkg1);
        packageDAO.create(pkg2);
        shipmentDAO.saveLocation(location1);
        shipmentDAO.saveLocation(location2);
        shipmentDAO.save(shipment1);
        shipmentDAO.save(shipment2);
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }

    @Test
    void save() {
        //Given
        Package pkg = new Package("TEST", "Ahmad", "TEST", Package.deliveryStatus.IN_TRANSIT);
        Location location = new Location(1.2, 2.3, "TEST");
        Shipment expectedShipment = new Shipment(pkg, location, location, LocalDate.now());

        //When
        packageDAO.create(pkg);
        shipmentDAO.saveLocation(location);
        Shipment actualShipment = shipmentDAO.save(expectedShipment);

        //Then
        assertEquals(expectedShipment.getShipmentPackage().getSenderName(), actualShipment.getShipmentPackage().getSenderName());
    }

    @Test
    void findById() {
        //Given
        Package pkg = new Package("PKG123", "Ahmad", "TEST", Package.deliveryStatus.IN_TRANSIT);
        Location location = new Location(1.2, 2.3, "TEST");
        Shipment expectedShipment = new Shipment(pkg, location, location, LocalDate.now());

        //When
        Shipment actualShipment = shipmentDAO.findById(1);

        //Then
        assertEquals(expectedShipment.getShipmentPackage().getTrackingNumber(), actualShipment.getShipmentPackage().getTrackingNumber());
    }

    @Test
    void delete() {
        //Given
        Integer expectedResult = 1;

        //When
        Integer actualResult = shipmentDAO.delete(shipmentDAO.findById(1));

        //Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateShipmentWithPackage() {
        //Given
        Package pkg = new Package("123A", "Ahmad", "TEST", Package.deliveryStatus.IN_TRANSIT);
        Location location = new Location(1.2, 2.3, "TEST");
        Shipment expectedShipment = new Shipment(pkg, location, location, LocalDate.now());

        //When
        packageDAO.create(pkg);
        shipmentDAO.saveLocation(location);
        Shipment actualShipment = shipmentDAO.updateShipmentWithPackage(shipmentDAO.findById(1), pkg);

        //Then
        assertEquals(expectedShipment.getShipmentPackage().getTrackingNumber(), actualShipment.getShipmentPackage().getTrackingNumber());
    }

    @Test
    void findShipmentsByPackage() {
        //Given
        Integer expectedResult = 1;

        //When
        List<Shipment> shipments = shipmentDAO.findShipmentsByPackage(packageDAO.findByTrackingNumber("PKG123"));
        Integer actualSize = shipments.size();

        //Then
        assertEquals(expectedResult, actualSize);
    }
}