package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.DAO;

import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.HibernateConfig;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Semester;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Student;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

class StudentDAOImplTest {

    // Create a test class with a test method for each of the methods in the StudentDAOImpl class.
    // Run the test class and verify that all tests pass.
    static EntityManagerFactory emf;
    static StudentDAOImpl studentDAO;

    @BeforeAll
    static void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig(true);
        studentDAO = StudentDAOImpl.getInstance(emf);

        try (EntityManager em = emf.createEntityManager()) {
            // Begin transaction
            em.getTransaction().begin();

            // Reset the sequence for the Student table
            //em.createNativeQuery("ALTER TABLE student MODIFY COLUMN id INT AUTO_INCREMENT = 1").executeUpdate();

            // Create and persist teachers
            Teacher teacher1 = new Teacher("John", "Smith");
            Teacher teacher2 = new Teacher("Alice", "Johnson");
            Teacher teacher3 = new Teacher("Michael", "Brown");
            Teacher teacher4 = new Teacher("Emily", "Davis");
            em.persist(teacher1);
            em.persist(teacher2);
            em.persist(teacher3);
            em.persist(teacher4);

            // Create and persist semesters
            Semester semester1 = new Semester("Spring 2024", "Description of Spring 2024");
            Semester semester2 = new Semester("Fall 2024", "Description of Fall 2024");
            Semester semester3 = new Semester("Summer 2024", "Description of Summer 2024");
            Semester semester4 = new Semester("Winter 2024", "Description of Winter 2024");
            Semester semester5 = new Semester("Winter 2025", "Description of Winter 2025");
            Semester semester6 = new Semester("Test 00", "Description of Winter 2025");
            em.persist(semester1);
            em.persist(semester2);
            em.persist(semester3);
            em.persist(semester4);
            em.persist(semester5);
            em.persist(semester6);

            // Create and persist students
            Student student1 = new Student("Jane", "Doe");
            Student student2 = new Student("Tom", "Brown");
            Student student3 = new Student("Emma", "Wilson");
            Student student4 = new Student("James", "Taylor");
            Student student5 = new Student("Ahmad", "A");
            Student student6 = new Student("Ahmad", "B");
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(student5);
            em.persist(student6);

            // Associate teachers with semesters
            semester1.addTeacher(teacher1);
            semester1.addTeacher(teacher2);
            semester2.addTeacher(teacher3);
            semester2.addTeacher(teacher4);
            semester3.addTeacher(teacher1);
            semester3.addTeacher(teacher2);
            semester4.addTeacher(teacher3);
            semester4.addTeacher(teacher4);
            semester4.addTeacher(teacher1);

            // Associate students with semesters
            student1.setSemester(semester1);
            student2.setSemester(semester2);
            student3.setSemester(semester3);
            student4.setSemester(semester4);
            student5.setSemester(semester5);
            student6.setSemester(semester5);

            // Commit the transaction
            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }

    @Test
    @DisplayName("Finding all students by first name.")
    void findAllStudentsByFirstName() {
        // Given
        String firstName = "Tom";
        long expectedSize = 1;

        // When
        List<Student> students = studentDAO.findAllStudentsByFirstName(firstName);

        // Then
        assertEquals(expectedSize, students.size());
    }

    @Test
    @DisplayName("Finding all students by last name.")
    void findAllStudentsByLastName() {
        // Given
        String lastName = "Doe";
        long expectedSize = 1;

        // When
        List<Student> students = studentDAO.findAllStudentsByLastName(lastName);

        // Then
        assertEquals(expectedSize, students.size());
    }

    @Test
    @DisplayName("Finding total number of students by semester name.")
    void findTotalNumberOfStudentsBySemester() {
        // Given
        String semesterName = "Test 00";
        long expectedSize = 0;

        // When
        long actualSize = studentDAO.findTotalNumberOfStudentsBySemester(semesterName);

        // Then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Finding total number of students by teacher.")
    void findTotalNumberOfStudentsByTeacher() {
        // Given
        Teacher teacher = new Teacher("John", "");
        long expectedSize = 3;

        // When
        long actualSize = studentDAO.findTotalNumberOfStudentsByTeacher(teacher);

        // Then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Finding the teacher with the most semesters.")
    void findTeacherWithMostSemesters() {
        // Given
        Teacher expectedTeacher = new Teacher("John", "Smith");

        // When
        Teacher actualTeacher = studentDAO.findTeacherWithMostSemesters();

        // Then
        assertEquals(expectedTeacher.getFirstName(), actualTeacher.getFirstName());
    }

    @Test
    @DisplayName("Finding the semester with fewest students.")
    void findSemesterWithFewestStudents() {
        // Given
        String expectedSemesterName = "Test 00";

        // When
        Semester actualSemester = studentDAO.findSemesterWithFewestStudents();

        // Then
        assertEquals(expectedSemesterName, actualSemester.getName());
    }

    @Test
    @DisplayName("Getting all the information about the student by id")
    void getAllStudentInfo() {
        // Given
        StudentInfoDTO expectedStudentInfoDTO = new StudentInfoDTO("Jane Doe", 0, "Spring 2024", "");

        // When
        StudentInfoDTO actualStudent = studentDAO.getAllStudentInfo(1);

        // Then
        assertEquals(expectedStudentInfoDTO.getFullName(), actualStudent.getFullName());
        assertEquals(expectedStudentInfoDTO.getThisSemesterName(), actualStudent.getThisSemesterName());
    }
}