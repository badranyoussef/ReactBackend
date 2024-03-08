package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HotelDAO extends AbstractDAO<Hotel> {

    private static HotelDAO instance;
    private static EntityManagerFactory emf;

    private HotelDAO(EntityManagerFactory _emf, Class<Hotel> entityClass) {
        super(_emf, entityClass);
    }

    public static HotelDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HotelDAO(emf, Hotel.class);
        }
        return instance;
    }

    public List<Hotel> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h", Hotel.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
