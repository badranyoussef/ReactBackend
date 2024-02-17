package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise5;

// Create a JPA-based implementation of the DAO interface, utilizing EntityManager for database operations.


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class JPAStudentDAO implements StudentDAO {

    private EntityManager entityManager;

    public JPAStudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createStudent(Student1 student1) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student1);
        transaction.commit();
    }

    @Override
    public Student1 getStudentById(int id) {
        return entityManager.find(Student1.class, id);
    }

    @Override
    public List<Student1> getAllStudents() {
        Query query = entityManager.createQuery("SELECT s FROM Student1 s");
        return query.getResultList();
    }

    @Override
    public void updateStudent(Student1 student1) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student1);
        transaction.commit();
    }

    @Override
    public void deleteStudent(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student1 student1 = entityManager.find(Student1.class, id);
        if (student1 != null) {
            entityManager.remove(student1);
        }
        transaction.commit();
    }
}

