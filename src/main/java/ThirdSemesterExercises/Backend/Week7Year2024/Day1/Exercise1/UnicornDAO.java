package ThirdSemesterExercises.Backend.Week7Year2024.Day1.Exercise1;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UnicornDAO {
    /*
    Create a new class named UnicornDAO.
    Implement methods for CRUD operations using standard JPA methods:
    save(Unicorn unicorn) using EntityManager.persist()
    update(Unicorn unicorn) using EntityManager.merge()
    delete(int id) using EntityManager.remove()
    findById(int id) using EntityManager.find()
    */
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Unicorn save(Unicorn unicorn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(unicorn);
        em.getTransaction().commit();
        em.close();
        return unicorn;
    }

    public boolean update(Unicorn unicorn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Unicorn updateDatedUnicorn = em.merge(unicorn);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean delete(int id) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Unicorn foundUnicorn = findById(id);
            em.remove(foundUnicorn);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            System.out.println("Found none with the following ID: " + id);
            return false;
        }
    }

    public Unicorn findById(int id) {
        EntityManager em = emf.createEntityManager();
        Unicorn foundUnicorn = em.find(Unicorn.class, id);
        em.close();
        return foundUnicorn;
    }

    // BONUS CHALLENGE
    // Implement a method findAll() in UnicornDAO that retrieves all Unicorn entities using the EntityManager.createQuery() method
    public List<Unicorn> findAllUnicorns() {
        EntityManager em = emf.createEntityManager();
        var query = em.createQuery("select u FROM Unicorn u", Unicorn.class);
        return query.getResultList();
    }
}
