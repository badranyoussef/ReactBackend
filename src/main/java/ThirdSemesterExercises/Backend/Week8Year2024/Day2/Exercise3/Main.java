package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create a Semester
        Semester semester = new Semester("Spring 2024", "Description of Spring 2024");

        // Create a Teacher
        Teacher teacher = new Teacher("John", "Smith");

        // Associate Teacher with Semester
        semester.addTeacher(teacher);

        // Create a Student
        Student student = new Student("Jane", "Doe");

        // Associate Student with Semester
        student.setSemester(semester);

        // Persist Semester, Teacher, and Student
        em.persist(semester);
        em.persist(teacher);
        em.persist(student);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve Student from the database
        Student savedStudent = em.find(Student.class, student.getId());

        // Verify association
        if (savedStudent != null) {
            Semester associatedSemester = savedStudent.getSemester();
            if (associatedSemester != null) {
                System.out.println("Student " + savedStudent.getFirstName() + " " + savedStudent.getLastName() +
                        " is associated with Semester " + associatedSemester.getName());
            } else {
                System.out.println("Student " + savedStudent.getFirstName() + " " + savedStudent.getLastName() +
                        " is not associated with any Semester.");
            }
        }

        // Retrieve Semester from the database
        Semester savedSemester = em.find(Semester.class, semester.getId());

        // Verify association
        if (savedSemester != null) {
            System.out.println("Semester " + savedSemester.getName() + " has " + savedSemester.getTeacherSet().size() +
                    " teacher(s) associated with it.");
        }

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
