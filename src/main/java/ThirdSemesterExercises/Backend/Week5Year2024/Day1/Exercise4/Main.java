package ThirdSemesterExercises.Backend.Week5Year2024.Day1.Exercise4;

//              Time API
    /*
    Add a birthdate to the Employee class and implement the following
    tasks using the Java Time API:
    */

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        // Calculate the age of each employee based on their birthdate (Alderen bliver beregnet i Employee klassen.
        // Metoden hedder calculateAge.
        Supplier<LocalDate> randomDateSupplier = () -> {
            Random random = new Random();
            int randomDay = random.nextInt(31) + 1;
            int randomMonth = random.nextInt(12) + 1;
            int randomYear = random.nextInt(2024) + 1;
            return LocalDate.now().minusDays(randomDay).minusMonths(randomMonth).minusYears(randomYear);
        };

        List<Employee> listOfEmployees = List.of(
                new Employee("Ahmad", randomDateSupplier.get()),
                new Employee("Laura", randomDateSupplier.get()),
                new Employee("Maise", randomDateSupplier.get()),
                new Employee("Laith", randomDateSupplier.get()),
                new Employee("Hanni", randomDateSupplier.get())
        );

        // Print the list of employees
        listOfEmployees.forEach(System.out::println);
        System.out.println(); //Opdelings linje for print

        // Calculate the average age of all employees
        int averageAge = 0;
        for (Employee e : listOfEmployees) {
            averageAge += e.getAge();
        }
        System.out.println("Den gennemsnitlige alder er: " + averageAge / listOfEmployees.size());

        // Filter and display employees who have birthdays in a specific month
        int specificMonth = 1;
        List<Employee> employeesInSpecificMonth = new ArrayList<>();
        for (Employee e : listOfEmployees) {
            if (e.getBirthdayMonth() == specificMonth) {
                employeesInSpecificMonth.add(e);
            }
        }
        System.out.println("Employees with birthdays in month " + specificMonth + ": " + employeesInSpecificMonth.size());
        System.out.println();


        // Group employees by birth month and display the count of employees in each group.
        Map<Integer, Integer> employeesBirthdays = new HashMap<>();
        for (Employee e : listOfEmployees) {
            employeesBirthdays.put(e.getBirthdayMonth(), employeesBirthdays.getOrDefault(e.getBirthdayMonth(), 0) + 1);
        }
        employeesBirthdays.forEach((month, count) ->
                System.out.println("Month " + month + ": " + count + " employees")
        );

        System.out.println();

        // List all employees who have birthday in the current month
        LocalDate localDate = LocalDate.now();
        int currentMonth = localDate.getMonthValue();

        List<Employee> employeesInCurrentMonth = new ArrayList<>();
        for (Employee e : listOfEmployees) {
            if (e.getBirthdayMonth() == currentMonth) {
                employeesInCurrentMonth.add(e);
            }
        }
        System.out.println("Employees with birthdays in the current month: "+employeesInCurrentMonth.size());
        employeesInCurrentMonth.forEach(System.out::println);

    }
}
