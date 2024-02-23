package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao.DriverDAOImpl;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao.WasteTruckDAOImpl;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.WasteTruck;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a main method in the root of the project and test all the methods in the DAO classes

        // Create an instance of your DAO class
        DriverDAOImpl driverDAO = new DriverDAOImpl();

        // Save a new driver
        String savedDriverInfo = driverDAO.saveDriver("John", "Doe", 15000);
        System.out.println("Saved Driver: " + savedDriverInfo);


        // Get the saved driver by ID
        Driver retrievedDriver = driverDAO.getDriverById("200224-JD-947E");
        System.out.println("Retrieved Driver: " + retrievedDriver);


        // Update the retrieved driver's name
        retrievedDriver.setName("Jane");
        Driver updatedDriver = driverDAO.updateDriver(retrievedDriver);
        System.out.println("Updated Driver: " + updatedDriver);


        // Fetch all drivers employed in the same year
        List<Driver> driversSameYear = driverDAO.findAllDriversEmployedAtTheSameYear("2024");
        System.out.println("Drivers Employed in 2024: " + driversSameYear);


        // Fetch all drivers with salary greater than $10,000
        List<Driver> highSalaryDrivers = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        System.out.println("Drivers with Salary > $10,000: " + highSalaryDrivers);

        // Fetch the highest salary among all drivers
        double highestSalary = driverDAO.fetchHighestSalary();
        System.out.println("Highest Salary: $" + highestSalary);


        // Fetch the first names of all drivers
        List<String> firstNames = driverDAO.fetchFirstNameOfAllDrivers();
        System.out.println("First Names of Drivers: " + firstNames);


        // Calculate the number of drivers
        long numberOfDrivers = driverDAO.calculateNumberOfDrivers();
        System.out.println("Number of Drivers: " + numberOfDrivers);


        // Fetch the driver with the highest salary
        Driver driverWithHighestSalary = driverDAO.fetchDriverWithHighestSalary();
        System.out.println("Driver with the Highest Salary: " + driverWithHighestSalary);


        // Delete the saved driver
        // driverDAO.deleteDriver("211104-CE-572S");

        // Instantiate WasteTruckDAOImpl
        WasteTruckDAOImpl wasteTruckDAO = new WasteTruckDAOImpl();

        // Test saveWasteTruck method
        int saved = wasteTruckDAO.saveWasteTruck("Brand", "RegistrationNumber1", 10000);
        System.out.println("Saved: " + saved);


        // Test getWasteTruckById method
        WasteTruck truckById = wasteTruckDAO.getWasteTruckById(2);
        System.out.println("Truck by ID: " + truckById);


        // Test setWasteTruckAvailable method
        wasteTruckDAO.setWasteTruckAvailable(truckById, true);


        // Test deleteWasteTruck method
        wasteTruckDAO.deleteWasteTruck(2);


        // Test addDriverToWasteTruck method
        Driver driver = new Driver("John", "Doe", 10000);
        wasteTruckDAO.addDriverToWasteTruck(truckById, driver);


        // Test removeDriverFromWasteTruck method
        wasteTruckDAO.removeDriverFromWasteTruck(truckById, driver.getId());


        // Test getAllAvailableTrucks method
        List<WasteTruck> availableTrucks = wasteTruckDAO.getAllAvailableTrucks();
        System.out.println("Available Trucks:");
        for (WasteTruck truck : availableTrucks) {
            System.out.println(truck);
        }
    }
}

