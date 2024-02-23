package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.DAO;

import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Semester;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Student;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    // At the same location add a new class called StudentDAOImpl and implement the interface to the class with all the methods.
    // All the methods in the StudentDAOImpl class should now be implemented using JPQL.

    private static EntityManagerFactory emf;
    private static StudentDAOImpl instance;

    public static StudentDAOImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> studentTypedQuery = em.createQuery("SELECT s FROM Student s WHERE s.firstName = :first_name", Student.class);
            studentTypedQuery.setParameter("first_name", firstName);
            return studentTypedQuery.getResultList();
        }
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> studentTypedQuery = em.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class);
            studentTypedQuery.setParameter("lastName", lastName);
            return studentTypedQuery.getResultList();
        }
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(s) FROM Student s JOIN s.semester sem WHERE sem.name = :semesterName");
            query.setParameter("semesterName", semesterName);
            return (long) query.getSingleResult();
        }
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(s) FROM Student s JOIN s.semester sem JOIN sem.teacherSet t WHERE t.firstName = :teacher_first_name");
            query.setParameter("teacher_first_name", teacher.getFirstName());
            return (long) query.getSingleResult();
        }
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT t FROM Teacher t JOIN t.semesterSet sem GROUP BY t ORDER BY COUNT(sem) DESC");
            query.setMaxResults(1); // We only need the teacher with the most semesters
            return (Teacher) query.getSingleResult();
        }
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT s FROM Semester s LEFT JOIN s.students st GROUP BY s ORDER BY COUNT(st) ASC");
            query.setMaxResults(1);
            return (Semester) query.getSingleResult();
        }
    }

    @Override
    public StudentInfoDTO getAllStudentInfo(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<StudentInfoDTO> query = em.createQuery(
                    "SELECT NEW ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.DAO.StudentInfoDTO(CONCAT(st.firstName, ' ', st.lastName), st.id, sem.name, sem.description) " +
                            "FROM Student st JOIN st.semester sem " +
                            "WHERE st.id = :id", StudentInfoDTO.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}

