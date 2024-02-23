package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Populate {

    // Create a ´Populate` class with a main method. In the main method create a method that can populate the database with data
    public static void main(String[] args) {

        // In the root of the project add a new class called Populate and add the following code to the class.
        // Run the main method in the Populate class to populate the database with the students, teachers, and semesters.
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            populateDatabase(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void populateDatabase(EntityManager em) {
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
    }
}
