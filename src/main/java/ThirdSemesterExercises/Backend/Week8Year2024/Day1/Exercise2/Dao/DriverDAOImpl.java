package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Config.HibernateConfig;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;
import jakarta.persistence.*;

import java.util.List;

public class DriverDAOImpl implements IDriverDAO {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("dolphin");

    @Override
    public String saveDriver(String name, String surname, int salary) {
        Driver d = new Driver(name, surname, salary);

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        }
        return d.toString();
    }

    @Override
    public Driver getDriverById(String id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Driver> typedQuery = em.createQuery("SELECT d FROM Driver d WHERE d.id = :id", Driver.class);
            typedQuery.setParameter("id", id);
            Driver foundDriver = typedQuery.getSingleResult();
            em.getTransaction().commit();
            return foundDriver;
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("Driver with ID " + id + " not found.");
            return null; // or throw a custom exception
        }
    }

    @Override
    public Driver updateDriver(Driver driver) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Update the driver's name
            Query query = em.createQuery("update Driver d set d.name = :name where d.id = :id ");
            query.setParameter("name", driver.getName());
            query.setParameter("id", driver.getId()).executeUpdate();

            // Retrieve the updated driver
            Driver updatedDriver = em.find(Driver.class, driver.getId());

            em.getTransaction().commit();
            return updatedDriver;
        }
    }

    @Override
    public void deleteDriver(String id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query foundDriver = em.createQuery("delete from Driver d where d.id =:driver_id ");
            int i = foundDriver.setParameter("driver_id", id).executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Driver d WHERE YEAR(d.employmentDate) = :year");
            query.setParameter("year", Integer.parseInt(year)); // Assuming year is a String
            List<Driver> drivers = query.getResultList();
            em.getTransaction().commit();
            return drivers;
        }
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Driver d WHERE d.salary > 10000");
            List<Driver> drivers = query.getResultList();
            em.getTransaction().commit();
            return drivers;
        }
    }

    @Override
    public double fetchHighestSalary() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT MAX(d.salary) FROM Driver d");
            Double highestSalary = (Double) query.getSingleResult();
            em.getTransaction().commit();
            return highestSalary;
        }
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d.name FROM Driver d");
            List<String> firstNames = query.getResultList();
            em.getTransaction().commit();
            return firstNames;
        }
    }

    @Override
    public long calculateNumberOfDrivers() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT COUNT(d) FROM Driver d");
            long numberOfDrivers = (long) query.getSingleResult();
            em.getTransaction().commit();
            return numberOfDrivers;
        }
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT d FROM Driver d WHERE d.salary = (SELECT MAX(d2.salary) FROM Driver d2)");
            Driver driverWithHighestSalary = (Driver) query.getSingleResult();
            em.getTransaction().commit();
            return driverWithHighestSalary;
        }
    }

    public void closeDown() {
        emf.close();
    }
}
