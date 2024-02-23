package ThirdSemesterExercises.Backend.Week8Year2024.Day3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShipmentDAO {

    private static EntityManagerFactory emf;
    private static ShipmentDAO instance;

    public static ShipmentDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ShipmentDAO();
        }
        return instance;
    }

    public void saveLocation(Location location) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
        }
    }

    public Shipment save(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(shipment);
            em.getTransaction().commit();
            return shipment;
        }
    }

    public Shipment findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Shipment.class, id);
        }
    }

    public int delete(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(shipment);
            em.getTransaction().commit();
            return 1;
        }
    }

    public Shipment updateShipmentWithPackage(Shipment shipment, Package pkg) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            shipment.setShipmentPackage(pkg);
            em.merge(shipment);
            em.getTransaction().commit();
            return shipment;
        }
    }

    public List<Shipment> findShipmentsByPackage(Package pkg) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Shipment> query = em.createQuery("SELECT s FROM Shipment s WHERE s.shipmentPackage.trackingNumber =:pkg", Shipment.class);
            query.setParameter("pkg", pkg.getTrackingNumber());
            return query.getResultList();
        }
    }
}

