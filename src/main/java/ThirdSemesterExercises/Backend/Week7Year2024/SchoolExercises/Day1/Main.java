package ThirdSemesterExercises.Backend.Week7Year2024.SchoolExercises.Day1;

import ThirdSemesterExercises.Backend.Week7Year2024.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        var em = emf.createEntityManager();
        //Person ahmad = new Person("Maria", Person.Gender.FEMALE, "maria@email.dk");

        Person test = new Person("KA", Person.Gender.MALE,"mari@dk.dk");
        //create(em, test);

        //var person = em.find(Person.class, 1);
        //System.out.println(person);

        /*
        // C
        Address lundtofte = new Address("Lundtofte", Address.Town.Lyngby, 2800);
        em.getTransaction().begin();
        em.persist(lundtofte);
        em.getTransaction().commit();
        em.close();
         */

        /*
        // U
        em.getTransaction().begin();
        var address = em.find(Address.class,1);
        address.setStreetName("Ingen gade");
        Address merge = em.merge(address);
        em.getTransaction().commit();
        */


        /*
        // F
        var address = em.find(Address.class,1);
        System.out.println(address);
         */

        /*
        // D
        em.getTransaction().begin();
        var address = em.find(Address.class,1);
        em.remove(address);
        em.getTransaction().commit();
         */
    }

    //Virker ikke
    /*private static <T> void create(EntityManager em, T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }*/
}
