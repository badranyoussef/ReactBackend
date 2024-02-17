package ThirdSemesterExercises.Backend.Week7Year2024.Day1.Exercise2;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    // Add a Main.class including a main method
    public static void main(String[] args) {

        //Object is transient
        Student student = new Student("Ahmad", "Alkaseb", "ahmad1@email.dk", 25);

        //Object is detached
        //createStudent(student);

        //Object is removed
        //deleteStudent(1);

        //Object is managed
        readStudent(1);

        //Objects are managed
        readAllStudents();
    }

    // Create the following methods in the Main class:

    //public static void createStudent(Student student) - This method should create a new student and persist it to the database.
    //public static Student readStudent(int id) - This method should read a student from the database using the student's id.
    //public static Student updateStudent(Student updStd) - This method should update an existing student in the database.
    //public static void deleteStudent(int id) - This method should delete a student from the database using the student's id.
    //public static List<Student> readAllStudents() - This method should retrieve all students from the database and return them as a list. Use a TypedQuery to retrieve all students.

    public static void createStudent(Student student) {
        try (EntityManager em = emf.createEntityManager()) {
            // Object is transient until persisted
            em.getTransaction().begin();
            em.persist(student); // Object becomes managed after persisting
            em.getTransaction().commit(); // Object remains managed after commit
        }
    }

    public static Student readStudent(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            // Object is managed while being retrieved
            Student foundStudent = em.find(Student.class, id); // Object remains managed
            return foundStudent; // Object is detached after method returns
        }
    }

    public static Student updateStudent(Student updateStudent) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Object is detached after being passed as an argument
            Student updateDatedUnicorn = em.merge(updateStudent); // Object becomes managed
            em.getTransaction().commit(); // Object remains managed after commit
            return updateDatedUnicorn; // Object is detached after method returns
        }
    }

    public static void deleteStudent(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Object is managed while being retrieved
            Student foundStudent = readStudent(id); // Object remains managed
            em.remove(foundStudent); // Object becomes removed
            em.getTransaction().commit(); // Object becomes detached after commit
        }
    }

    public static List<Student> readAllStudents() {
        try (EntityManager em = emf.createEntityManager()) {
            // Objects are managed while being retrieved
            var query = em.createQuery("select s FROM Student s", Student.class); // Objects remain managed
            return query.getResultList(); // Objects are detached after method returns
        }
    }


    /*
    In all the methods above, remember to open and close the EntityManager and EntityManagerFactory objects.
    You can use either the try-with-resources or the final block to close the objects.
     */

    /*
    In all the methods above, write small comments that explains when an object is transient, detached, removed or
    managed.
     */
}
