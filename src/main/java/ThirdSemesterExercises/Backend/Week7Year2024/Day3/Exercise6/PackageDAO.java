package ThirdSemesterExercises.Backend.Week7Year2024.Day3.Exercise6;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

// Implement a DAO (Data Access Object) class named "PackageDAO" to perform CRUD operations
// on the "Package" entity using JPA.
public class PackageDAO {

    // Implement methods in the "PackageDAO" to perform the following operations:
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    // Persist a new package
    public Package create(Package p) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
            return p;
        }
    }

    //Update the delivery status of a package
    public int update(String packageStatus, String trackingNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("update Package p set p.deliveryStatus = :packageStatus where p.trackingNumber = :trackNumber ");
            query.setParameter("packageStatus", Package.deliveryStatus.valueOf(packageStatus));
            int i = query.setParameter("trackNumber", trackingNumber).executeUpdate();
            em.getTransaction().commit();
            return i;
        }
    }

    // Remove a package from the system
    public int delete(int packageId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query foundPackage = em.createQuery("delete from Package p where p.id =:packageId ");
            foundPackage.setParameter("packageId",packageId);
            int i = foundPackage.executeUpdate();
            em.getTransaction().commit();
            return i;
        }
    }

    // Retrieve a package by its tracking number
    public Package findByTrackingNumber(String trackingNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Package> typedQuery = em.createQuery("select p from Package p where p.trackingNumber = :first", Package.class);
            typedQuery.setParameter("first", trackingNumber);
            em.getTransaction().commit();
            Package foundPackage = typedQuery.getSingleResult();
            return foundPackage;
        }
    }
}
