package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class DriverDAOImplTest {

    // Test the methods in the DAO classes using JUnit
    static DriverDAOImpl driverDAO;

    @BeforeAll
    static void setUp() {
        driverDAO = new DriverDAOImpl();
    }

    @AfterAll
    static void tearDown() {
        driverDAO.closeDown();
    }

    @Test
    void saveDriver() {
        assertNotNull(driverDAO.saveDriver("Laith", "A", 90));
    }

    @Test
    void getDriverById() {
        assertNotNull(driverDAO.getDriverById("200224-JD-926E"));
    }

    @Test
    void updateDriver() {
        Driver d = driverDAO.getDriverById("200224-JD-926E");
        d.setName("Test");
        assertNotNull(driverDAO.updateDriver(d));
    }

    @Test
    void deleteDriver() {
        driverDAO.deleteDriver("200224-JD-926E");
    }

    @Test
    void findAllDriversEmployedAtTheSameYear() {
        assertNotNull(driverDAO.findAllDriversEmployedAtTheSameYear("2023"));
    }

    @Test
    void fetchAllDriversWithSalaryGreaterThan10000() {
        assertNotNull(driverDAO.fetchAllDriversWithSalaryGreaterThan10000());
    }

    @Test
    void fetchHighestSalary() {
        assertNotNull(driverDAO.fetchDriverWithHighestSalary());
    }

    @Test
    void fetchFirstNameOfAllDrivers() {
        assertNotNull(driverDAO.fetchFirstNameOfAllDrivers());
    }

    @Test
    void calculateNumberOfDrivers() {
        assertEquals(37, driverDAO.calculateNumberOfDrivers());
    }

    @Test
    void fetchDriverWithHighestSalary() {
        assertNotNull(driverDAO.fetchDriverWithHighestSalary());
    }
}