package ThirdSemesterExercises.Backend.Week7Year2024.Day1.Exercise1;

public class Main {

    /*
    Create a main class.
    Inside main, instantiate UnicornDAO.
    Demonstrate CRUD operations:
    Create a Unicorn and save it using persist.
    Update the saved Unicorn using merge.
    Fetch the updated Unicorn by ID using find and display its attributes.
    Delete the Unicorn by ID using remove.
     */

    public static void main(String[] args) {
        UnicornDAO unicornDAO = new UnicornDAO();

        //CREATE
        Unicorn unicorn = new Unicorn("Jack", 99, 50);
        Unicorn savedUnicorn = unicornDAO.save(unicorn);


        //READ
        Unicorn foundUnicorn = unicornDAO.findById(4);
        System.out.println(foundUnicorn);

        //UPDATE
        foundUnicorn.setName("Ali");
        Boolean updateResult = unicornDAO.update(foundUnicorn);

        //DELETE
        //Boolean deleteResult = unicornDAO.delete(3);

        // Run your application
        // Validate that each CRUD operation is functioning by examining the console output and checking the database
        // - I can confirm that all of my CRUD operations.
    }

}
