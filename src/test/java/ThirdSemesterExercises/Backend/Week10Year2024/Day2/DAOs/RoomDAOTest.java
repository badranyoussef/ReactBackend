package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.HibernateConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Hotel;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.codehaus.groovy.vmplugin.v8.IndyGuardsFiltersAndSignatures.isNull;
import static org.junit.Assert.assertEquals;

class RoomDAOTest {

    private static EntityManagerFactory emf;
    private static RoomDAO roomDAO;

    @BeforeAll
    static void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig(true);
        roomDAO = RoomDAO.getInstance(emf);
        HotelDAO hotelDAO = HotelDAO.getInstance(emf);
        hotelDAO.create(new Hotel("Test Hotel Name", "Test Hotel Address"));
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }

    @Test
    @DisplayName("Create a new room")
    void test1() {
        // Given
        Room expectedRoom = new Room(1, 500);

        // When
        Room createdRoom = roomDAO.create(new Room(1, 500));

        // Then
        assertEquals(expectedRoom.getNumber(), createdRoom.getNumber());
        assertEquals(expectedRoom.getPrice(), createdRoom.getPrice());
    }

    @Test
    @DisplayName("Get all rooms")
    void test2() {
        // Given
        int expectedRooms = 2;

        // When
        try (EntityManager em = emf.createEntityManager()) {
            Room room1 = new Room(1, 500);
            Room room2 = new Room(2, 600);
            em.getTransaction().begin();
            em.persist(room1);
            em.persist(room2);
            em.getTransaction().commit();
        }
        List<Room> rooms = roomDAO.getAll();

        // Then
        assertEquals(expectedRooms, rooms.size());
    }

    @Test
    @DisplayName("Get all rooms by hotel id")
    void test3() {
        // Given
        int hotelId = 1;
        int expectedRooms = 0;

        // When
        List<Room> rooms = roomDAO.getAllRoomsByHotelId(hotelId);

        // Then
        assertEquals(expectedRooms, rooms.size());
    }

    @Test
    @DisplayName("Update existing room")
    void test4() {
        // Given
        int expectedResult = 1;
        Room room = new Room(1, 500);
        int newPrice = 600;
        int newNumber = 2;

        // When
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        }

        room.setNumber(newNumber);
        room.setPrice(newPrice);
        int result = roomDAO.update(room);
        try (EntityManager em = emf.createEntityManager()) {
            room = em.find(Room.class, 1);
        }

        // Then
        assertEquals(expectedResult, result);
        assertEquals(newPrice, room.getPrice());
        assertEquals(newNumber, room.getNumber());
    }

    @Test
    @DisplayName("Delete existing room by id")
    void test5() {
        // Given
        int roomId = 1;
        int expectedResult = 1;
        Room room = new Room(1, 500);

        // When
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        }
        int result = roomDAO.delete(roomId);
        try (EntityManager em = emf.createEntityManager()) {
            room = em.find(Room.class, roomId);
        }

        // Then
        assertEquals(expectedResult, result);
        isNull(room);
    }
}