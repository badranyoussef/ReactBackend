package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Config.HibernateConfig;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.WasteTruck;
import jakarta.persistence.*;

import java.util.List;

public class WasteTruckDAOImpl implements IWasteTruckDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("dolphin");

    @Override
    public int saveWasteTruck(String brand, String registrationNumber, int capacity) {
        WasteTruck wasteTruck = new WasteTruck(brand, capacity, registrationNumber);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(wasteTruck);
            em.getTransaction().commit();
            return 1; // Return 1 if the operation was successful
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
            return 0; // Return 0 if an exception occurs
        }
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<WasteTruck> typedQuery = em.createQuery("SELECT w FROM WasteTruck w WHERE w.id = :id", WasteTruck.class);
            typedQuery.setParameter("id", id);
            WasteTruck foundTruck = typedQuery.getSingleResult();
            em.getTransaction().commit();
            return foundTruck;
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("Driver with ID " + id + " not found.");
            return null; // or throw a custom exception
        }
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Update the driver's name
            Query query = em.createQuery("update WasteTruck w set w.isAvailable = :is where w.id = :id ");
            query.setParameter("is", available);
            query.setParameter("id", wasteTruck.getId()).executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteWasteTruck(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query foundDriver = em.createQuery("delete from WasteTruck w where w.id =:waste_id ");
            int i = foundDriver.setParameter("waste_id", id).executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query foundDriver = em.createQuery("update Driver d set d.truckId = :driver_id where d.id = :second");
            foundDriver.setParameter("driver_id", wasteTruck.getId());
            foundDriver.setParameter("second", driver.getId()).executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query removeDriver = em.createQuery("update Driver d set d.truckId = null where d.id = :id");
            removeDriver.setParameter("id", id).executeUpdate();
            em.getTransaction().commit();
        }
    }


    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<WasteTruck> query = em.createQuery(
                    "SELECT w FROM WasteTruck w WHERE w.id NOT IN (SELECT d.truckId FROM Driver d WHERE d.truckId IS NOT NULL)",
                    WasteTruck.class);
            List<WasteTruck> availableTrucks = query.getResultList();
            em.getTransaction().commit();
            return availableTrucks;
        }
    }

    public void closeDown() {
        emf.close();
    }
}
