package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;

import java.util.List;

public interface IDriverDAO {
    // Driver
    String saveDriver(String name, String surname, int salary);

    Driver getDriverById(String id);

    Driver updateDriver(Driver driver);

    void deleteDriver(String id);

    List<Driver> findAllDriversEmployedAtTheSameYear(String year);

    List<Driver> fetchAllDriversWithSalaryGreaterThan10000();

    double fetchHighestSalary();

    List<String> fetchFirstNameOfAllDrivers();

    long calculateNumberOfDrivers();

    Driver fetchDriverWithHighestSalary();
}
