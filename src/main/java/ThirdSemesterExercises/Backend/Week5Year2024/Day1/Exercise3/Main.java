package ThirdSemesterExercises.Backend.Week5Year2024.Day1.Exercise3;

//                  Functional Interfaces

   /*
   Apply the functional interfaces from the java.util.function package:
   Predicate, Consumer, Supplier, Function
   */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        List<Integer> testNumbers = Arrays.asList(14, 15, 35, 30, 49, 56, 70);
        List<String> employeeNames = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");

        // Use Predicate to filter a list of integers, so only those divisible by 7 remain
        Predicate<Integer> divisibleBySeven = number -> number % 7 == 0;
        for (Integer number : testNumbers) {
            if (divisibleBySeven.test(number)) {
                System.out.println("Følgende tal kan divideres med 7: " + number);
            }
        }

        System.out.println(); // En linje til opdeling af de to forskellige udprint

        /*
        Use Supplier to create a list of Employee objects based on a list of names like
        Arrays.asList("John", "Jane", "Jack", "Joe", "Jill")
         */
        Supplier<List<Employee>> randomNameSupplier = () -> {
            List<Employee> employees = new ArrayList<>();
            for (int i = 0; i < employeeNames.size(); i++) {
                int randomIndex = (int) (Math.random() * employeeNames.size());
                String randomName = employeeNames.get(randomIndex);
                int randomAge = (int) (Math.random() * 10) + 90;
                Employee employee = new Employee(randomName, randomAge);
                employees.add(employee);
            }
            return employees;
        };

        // Creating a list of employees
        List<Employee> randomEmployees = randomNameSupplier.get();

        // Use Consumer to print the list of Employee objects
        Consumer<List<Employee>> printEmployeeList = employeeList -> {
            System.out.println("Medarbejdere liste:");
            employeeList.forEach(System.out::println);
        };

        // Printing the list of employee objects
        printEmployeeList.accept(randomEmployees);

        // Use Function to convert a list of Employee objects to a list of names
        Function<List<Employee>, List<String>> convertToNames = employeeList -> {
            List<String> names = new ArrayList<>();
            for (Employee employee : employeeList) {
                names.add(employee.getName());
            }
            return names;
        };

        System.out.println(); // En linje til opdeling af de to forskellige udprint

        List<String> names = convertToNames.apply(randomEmployees);
        System.out.println("List of Names: " + names);

        // Use Predicate to check if a given employee is older than 18
        Predicate<Employee> olderThan18 = employee -> employee.getAge() > 18;
        for (Employee employee : randomEmployees) {
            boolean result = olderThan18.test(employee);
            System.out.println(employee.getName() + " is older than 18: " + result);
        }
    }
}
