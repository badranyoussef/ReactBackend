package ThirdSemesterExercises.Backend.Week6Year2024.Day1.Exercise2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;

public class JSON {

    public static void main(String[] args) {

        //Customer array
        Customer[] customers = getCustomers();

        //Converted to customerDTO array
        CustomerDTO[] customerDTOS = arrayCustomerToArrayDTO(customers);

        //Printing out the array of CustomerDTOS
        printCustomerDTOArray(customerDTOS);

    }

    // Create a method that can read the JSON object from the file and return an array of Account objects
    // You can use a JAVA library to convert the JSON object to a JAVA object or vice versa.
    public static Customer[] getCustomers() {
        Customer[] arr = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("C:\\Users\\baban\\OneDrive\\Skrivebord\\Datamatiker\\3 Tredje Semester\\BackEndExercisesByAhmad\\BackEndExercisesByAhmad\\src\\main\\java\\week2\\day1\\exercise2\\account.json")) {
            arr = gson.fromJson(reader, Customer[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return arr;
    }

    // Create a method that can convert a Customer object to a DTO object
    public static CustomerDTO convertCustomerToDTO(Customer c) {
        String fullName = c.getFirstName() + " " + c.getLastName();
        CustomerDTO dto = new CustomerDTO(fullName, c.getAddress().getCity(), c.getAddress().getZipCode(), c.getAccount().getActive());
        return dto;
    }


    // Create a method that can convert an array of Account objects to an array of DTO objects
    public static CustomerDTO[] arrayCustomerToArrayDTO(Customer[] arrayCustomer) {
        CustomerDTO[] arrayOfDTO = new CustomerDTO[arrayCustomer.length];
        String fullname = null;

        for (int i = 0; i < arrayCustomer.length; i++) {
            Customer c = arrayCustomer[i];
            fullname = c.getFirstName() + " " + c.getLastName();
            arrayOfDTO[i] = new CustomerDTO(fullname, c.getAddress().getCity(), c.getAddress().getZipCode(), c.getAccount().getActive());
        }
        return arrayOfDTO;
    }


    // Create a method that can print out the DTO objects in the array in a nice format
    public static void printCustomerDTOArray(CustomerDTO[] customerDTOS) {
        int counter = 1;
        for (CustomerDTO c : customerDTOS) {
            System.out.println(counter + ")");
            System.out.println("Full name: " + c.getFullName());
            System.out.println("City: " + c.getCity());
            System.out.println("Active: " + c.getIsActive());
            System.out.println();
            counter++;
        }
    }
}

// Open a Java project in your IDE and create classes for the JSON object you created in part 2
class Customer {
    private String firstName;
    private String lastName;
    private String birthDate;
    private Address address;
    private Account account;


    public Account getAccount() {
        return account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }
}

class Account {
    private String id;
    private String balance;
    private String isActive;

    public String getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    public String getActive() {
        return isActive;
    }
}

class Address {
    private String street;
    private String city;
    private String zipCode;

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }
}

    /*
    Create a DTO class that has the following properties:
    `fullName` (string)
    `city` (string)
    `zipCode` (string)
    `isActive` (string)
    */

class CustomerDTO {
    private String fullName;
    private String city;
    private String zipCode;
    private String isActive;

    public CustomerDTO(String fullName, String city, String zipCode, String isActive) {
        this.fullName = fullName;
        this.city = city;
        this.zipCode = zipCode;
        this.isActive = isActive;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}