package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise5;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        // Implement a class that demonstrates the use of the DAO to interact with the database,
        // perform CRUD operations, and retrieve data.

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        // Instantiate the DAO with the EntityManager
        StudentDAO studentDAO = new JPAStudentDAO(em);

        // Create and insert a new student
        Student1 newStudent1 = new Student1("John", "Doe", "john@live.dk", 30);
        newStudent1.onCreate();
        newStudent1.onUpdate();
        System.out.println(newStudent1.getCreatedAt());
        System.out.println(newStudent1.getUpdatedAt());

        studentDAO.createStudent(newStudent1);


        // Retrieve a student by ID
        Student1 retrievedStudent1 = studentDAO.getStudentById(newStudent1.getId());
        System.out.println("Retrieved Student: " + retrievedStudent1);

        // Update the student's age
        retrievedStudent1.setAge(26);
        studentDAO.updateStudent(retrievedStudent1);

        // Retrieve all students
        System.out.println("All Students:");
        studentDAO.getAllStudents().forEach(System.out::println);

        // Delete the student
        studentDAO.deleteStudent(retrievedStudent1.getId());

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();

        // Explain the benefits of using a DAO architecture for separating database access logic from business logic.

        /*
        Using a DAO separates database code from the rest of the application, making it:

        Clear:         It's easy to understand what interacts with the database.
        Testable:      Database code can be tested independently, improving reliability.
        Flexible:      You can change databases or frameworks without affecting the rest of the code.
        Encapsulated:  All database-related code is in one place, making updates easier.
        Reusable:      Database access code can be used in different parts of the application, reducing repetition.
         */
    }
}
