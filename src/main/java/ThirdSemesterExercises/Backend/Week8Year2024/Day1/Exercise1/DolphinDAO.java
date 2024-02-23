package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DolphinDAO {

    // Create a DolphinDAO and implement basic CRUD operations. At least enough to persist some test data

    // CRUD FOR PERSON
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    // Persist a new person
    public Person create(Person p) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
            return p;
        }
    }

    //Update the name of a person
    public int update(String newName, int personId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("update Person p set p.name = :newName where p.id = :personId ");
            query.setParameter("newName", newName);
            int i = query.setParameter("personId", personId).executeUpdate();
            em.getTransaction().commit();
            return i;
        }
    }

    // Remove a person from the system
    public int delete(int personId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Retrieve the person entity to delete
            Person person = em.find(Person.class, personId);

            // Check if the person exists
            if (person != null) {
                // Remove all associated fees
                for (Fee fee : person.getFees()) {
                    em.remove(fee);
                }

                // Remove all associated notes
                for (Note note : person.getNotes()) {
                    em.remove(note);
                }

                // Remove the person entity
                em.remove(person);

                // Commit the transaction
                em.getTransaction().commit();

                return 1; // 1 indicates success
            } else {
                return 0; // 0 indicates failure (person not found)
            }
        }
    }

    // Retrieve a person by person ID
    public Person findPersonById(int person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Person> typedQuery = em.createQuery("select p from Person p where p.id = :personId", Person.class);
            typedQuery.setParameter("personId", person);
            em.getTransaction().commit();
            Person foundPerson = typedQuery.getSingleResult();
            return foundPerson;
        }
    }

    // CRUD FOR FEES FOR A PERSON
    // Create a new fee for a person
    public Fee createFee(Fee fee, int personId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, personId);
            fee.setPerson(person);
            em.persist(fee);
            em.getTransaction().commit();
            return fee;
        }
    }

    // Update a fee's details
    public int updateFee(int feeId, int newAmount) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Fee fee = em.find(Fee.class, feeId);
            fee.setAmount(newAmount);
            em.getTransaction().commit();
            return 1; // 1 indicates success
        }
    }

    // Remove a fee from a person's records
    public int deleteFee(int feeId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Fee fee = em.find(Fee.class, feeId);
            em.remove(fee);
            em.getTransaction().commit();
            return 1; // 1 indicates success
        }
    }

    // Retrieve a fee by its ID
    public Fee findFeeById(int feeId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Fee.class, feeId);
        }
    }

    // CRUD FOR NOTES FOR A PERSON

    // Create a new note for a person
    public Note createNote(Note note, int personId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, personId);
            note.setPerson(person);
            em.persist(note);
            em.getTransaction().commit();
            return note;
        }
    }

    // Update a note's details
    public int updateNote(int noteId, String newNote) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Note note = em.find(Note.class, noteId);
            note.setNote(newNote);
            em.getTransaction().commit();
            return 1; // 1 indicates success
        }
    }

    // Remove a note from a person's records
    public int deleteNote(int noteId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Note note = em.find(Note.class, noteId);
            em.remove(note);
            em.getTransaction().commit();
            return 1; // 1 indicates success
        }
    }

    // Retrieve a note by its ID
    public Note findNoteById(int noteId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Note.class, noteId);
        }
    }

    // CRUD FOR PERSON DETAILS FOR A PERSON
    // Create person detail for a person
    public PersonDetail createPersonDetail(PersonDetail personDetail, int personId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, personId);
            personDetail.setPerson(person);
            em.persist(personDetail);
            em.getTransaction().commit();
            return personDetail;
        }
    }

    // Update person detail
    public int updatePersonDetail(int personDetailId, String newAddress, int newAge) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            PersonDetail personDetail = em.find(PersonDetail.class, personDetailId);
            personDetail.setAddress(newAddress);
            personDetail.setAge(newAge);
            em.getTransaction().commit();
            return 1; // 1 indicates success
        }
    }

    // Remove person detail from a person's records
    public int deletePersonDetail(int personDetailId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            PersonDetail personDetail = em.find(PersonDetail.class, personDetailId);
            if (personDetail != null) {
                // Disassociate the PersonDetail from its associated Person
                personDetail.getPerson().setPersonDetail(null);
                em.remove(personDetail);
            }
            em.getTransaction().commit();
            return 1; // 1 indicates success
        }
    }

    // Retrieve person detail by its ID
    public PersonDetail findPersonDetailById(int personDetailId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(PersonDetail.class, personDetailId);
        }
    }

    // US-2: As an administrator I would like to be able to get the total amount paid for a given person
    // US-3: As an administrator I would like to be able to get a list of all notes for a given person
    // US-4: As an administrator I would like to get a list of all notes with the name and age of the person it belongs to

    //Implement US-2
    // Get the total amount paid for a given person
    public long getTotalAmountPaidForPerson(int personId) throws Exception {
        try (EntityManager em = emf.createEntityManager()) {
            Person person = em.find(Person.class, personId);
            if (person != null) {
                Query query = em.createQuery("select sum(f.amount) from Fee f where f.person = :person");
                query.setParameter("person", person);
                Long totalAmount = (Long) query.getSingleResult(); // Cast to Long instead of Integer
                return totalAmount != null ? totalAmount : 0;
            } else {
                throw new Exception("ID didn't match in the database.");
            }
        }
    }


    //Implement US-3
    public List<Note> getAllNotesForPerson(int personId) throws Exception {
        List<Note> noteList;
        try (EntityManager em = emf.createEntityManager()) {
            Person person = em.find(Person.class, personId);
            if (person != null) {
                // Eagerly fetch the fees collection along with the Person entity
                person.getFees().size(); // This initializes the fees collection

                TypedQuery<Note> query = em.createQuery("select n from Note n where n.person = :person", Note.class);
                query.setParameter("person", person);
                noteList = query.getResultList();
                return noteList;
            } else {
                throw new Exception("ID didn't match in the database.");
            }
        }
    }

    //Implement US-4
    public List<PersonDTO> readAll() {
        try (var em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT NEW ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise1.PersonDTO(p.id, p.name, p.personDetail.age, n.id, n.note) \n" +
                    "FROM Person p \n" +
                    "JOIN p.notes n \n" +
                    "LEFT JOIN p.personDetail pd", PersonDTO.class);
            return query.getResultList();
        }
    }
}