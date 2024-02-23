package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DolphinDAOTest {

    private static EntityManagerFactory emf;
    private static DolphinDAO dao;

    @BeforeAll
    static void start() {
        emf = HibernateConfig.getEntityManagerFactoryConfig(); // Adjust as needed
        dao = new DolphinDAO(); // Adjust as needed
    }

    @AfterAll
    static void finish() {
        emf.close();
    }

    @Test
    void create() {
        // Implement your test case for create method here
        Person person = new Person("Ahmad");
        assertEquals(person, dao.create(person));
    }

    @Test
    void update() {
        // Implement your test case for update method here
        assertEquals(1, dao.update("Ali", 1));
    }

    @Test
    void delete() {
        // Implement your test case for delete method here
        assertEquals(1, dao.delete(1));
    }

    @Test
    void findPersonById() {
        // Implement your test case for findPersonById method here
        // Arrange: Prepare test data (assuming there's a Person with ID 2)
        int personId = 2;

        // Act: Invoke the method
        Person foundPerson = dao.findPersonById(personId);

        // Assert: Check the result
        assertNotNull(foundPerson); // Check if a person is found
        assertEquals(personId, foundPerson.getId()); // Check if the found person has the correct ID
    }

    @Test
    void createFee() {
        // Implement your test case for createFee method here
        Fee fee = new Fee(200, LocalDate.now());
        assertNotNull(dao.createFee(fee, 2));
    }

    @Test
    void updateFee() {
        // Implement your test case for updateFee method here
        assertEquals(1, dao.updateFee(1, 2000));
    }

    @Test
    void deleteFee() {
        // Implement your test case for deleteFee method here
        assertEquals(1, dao.deleteFee(1));
    }

    @Test
    void findFeeById() {
        // Implement your test case for findFeeById method here
        assertNotNull(dao.findFeeById(2));
    }

    @Test
    void createNote() {
        // Implement your test case for createNote method here
        Note note = new Note("SKAL ...", LocalDate.now());
        assertNotNull(dao.createNote(note, 2));
    }

    @Test
    void updateNote() {
        // Implement your test case for updateNote method here
        assertEquals(1, dao.updateNote(3, "..."));
    }

    @Test
    void deleteNote() {
        // Implement your test case for deleteNote method here
        assertEquals(1, dao.deleteNote(1));
    }

    @Test
    void findNoteById() {
        // Implement your test case for findNoteById method here
        assertNotNull(dao.findNoteById(4));
    }

    @Test
    void createPersonDetail() {
        // Implement your test case for createPersonDetail method here
        PersonDetail personDetail = new PersonDetail("Avej", 280, "Lynge", 30);
        assertNotNull(dao.createPersonDetail(personDetail, 44));
    }

    @Test
    void updatePersonDetail() {
        // Implement your test case for updatePersonDetail method here
        assertEquals(1, dao.updatePersonDetail(44, "Test", 45));
    }

    @Test
    void deletePersonDetail() {
        // Implement your test case for deletePersonDetail method here
        assertEquals(1, dao.deletePersonDetail(2));
    }


    @Test
    void findPersonDetailById() {
        // Implement your test case for findPersonDetailById method here
        assertNotNull(dao.findPersonDetailById(44));
    }

    @Test
    void getTotalAmountPaidForPerson() {
        // Implement your test case for getTotalAmountPaidForPerson method here
        try {
            assertEquals(325, dao.getTotalAmountPaidForPerson(2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllNotesForPerson() throws Exception {
        // Implement your test case for getAllNotesForPerson method here
        assertNotNull(dao.getAllNotesForPerson(2));
    }

    @Test
    void getAll() throws Exception {
        // Implement your test case for getAll method here
        List<PersonDTO> getAll = dao.readAll();
        getAll.forEach(System.out::println);
    }
}