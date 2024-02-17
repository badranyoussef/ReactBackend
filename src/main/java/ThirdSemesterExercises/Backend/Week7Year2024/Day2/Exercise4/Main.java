package ThirdSemesterExercises.Backend.Week7Year2024.Day2.Exercise4;


// Test the Person class
public class Main {
    // Now create a Main class to test out the Person class:
    public static void main(String[] args) {
        Person person = new Person("John", "Doe", 25);
        System.out.println(person); // This should print something like "Person(firstName=John, lastName=Doe, age=25)"
        person.setAge(26);
        System.out.println(person.getAge()); // This should print "26"

        // Expected Output:

        //Person(firstName=John, lastName=Doe, age=25)
        //26

        //Challenge
        /*
        For those who want to explore further, try adding more Lombok annotations
        to the Person class like @EqualsAndHashCode, and @Builder and observe the functionalities they bring.
        */

        // Builder kan bruges således.
        Person person1 = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .age(26)
                .build();

        // EqualsAndHashCode kan bruges således. Nu har de begge samme hashkode.
        System.out.println(person.equals(person1));
    }
}
