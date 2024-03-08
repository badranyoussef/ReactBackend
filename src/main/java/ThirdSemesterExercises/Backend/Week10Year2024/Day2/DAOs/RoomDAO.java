package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class RoomDAO extends AbstractDAO<Room> {
    public RoomDAO(EntityManagerFactory emf, Class<Room> entityClass) {
        super(emf, entityClass);
    }

    public List<Room> getAllRoomsByHotelId(int hotelId) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT r FROM Room r WHERE r.hotel.id = :hotelId");
            query.setParameter("hotelId", hotelId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Room> getAll() {
        EntityManager em = emf.createEntityManager();
        try {

            Query query = em.createQuery("SELECT r FROM Room r");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
