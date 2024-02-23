package ThirdSemesterExercises.Backend.Week8Year2024.Day3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfigg.getEntityManagerFactoryConfig(false);

        try (EntityManager em = emf.createEntityManager()){
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
        }

        // Use Jakarta Persistence (JPA) annotations to map the relationships between entities.
        // Update PackageDAO and ShipmentDAO classes to handle CRUD operations for the new entities and relationships.
    }
}
