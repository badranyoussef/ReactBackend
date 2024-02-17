package ThirdSemesterExercises.Backend.Week7Year2024.SchoolExercises.Day2;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a Main.class including a main method. Create the following JPQL queries:
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        //1 Write a JPQL query to select all employees.
        TypedQuery<Employee> listOfAllEmployees = em.createQuery("select e from Employee e", Employee.class);
        List<Employee> employeeList = listOfAllEmployees.getResultList();

        //2 Write a JPQL query to select employees with a salary greater than a certain value.
        TypedQuery<Employee> listOfEmployeesWithSalaryHigherThan10000 = em.createQuery("select e from Employee e where salary = 60000", Employee.class);
        List<Employee> employeesList60000 = listOfEmployeesWithSalaryHigherThan10000.getResultList();

        //3 Write a JPQL query to select employees from a specific department.
        TypedQuery<Employee> itDepartmentList = em.createQuery("select e from Employee e where department = 'IT'", Employee.class);
        List<Employee> departmentListOfIt = itDepartmentList.getResultList();

        //4 Write a JPQL query to select employees whose first name starts with a certain letter.
        TypedQuery<Employee> namesStartingWithA = em.createQuery("select e from Employee e where e.firstName like 'J%'", Employee.class);
        List<Employee> namesAList = namesStartingWithA.getResultList();

        //5 Write a JPQL query to update the salary of an employee using a named parameter.
        em.getTransaction().begin();
        int i = em.createQuery("update Employee e set e.salary = 1000 where e.firstName = 'Amanda'").executeUpdate();
        em.getTransaction().commit();

        //6 Write a JPQL query to update the department of an employee using positional parameters.
        em.getTransaction().begin();
        Query query = em.createQuery("update Employee e set e.department = :departmentName where e.firstName = 'Amanda'");
        query.setParameter("departmentName", "AhmadsAfdeling").executeUpdate();
        em.getTransaction().commit();

        //7 Write a JPQL query to calculate the average salary of all employees.
        Query averageSalary = em.createQuery("select avg(e.salary) from Employee e");
        double d = (double) averageSalary.getSingleResult();

        //8 Write a JPQL query to calculate the total salary of all employees.
        Query totalSalaryForAllEmployees = em.createQuery("select sum(e.salary) from Employee e");
        double d1 = (double) totalSalaryForAllEmployees.getSingleResult();
    }
}
