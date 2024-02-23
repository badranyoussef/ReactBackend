package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.WasteTruck;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class WasteTruckDAOImplTest {

    // Test the methods in the DAO classes using JUnit

    static WasteTruckDAOImpl wasteTruckDAO;
    static DriverDAOImpl driverDAO;

    @BeforeAll
    static void setUp() {
        wasteTruckDAO = new WasteTruckDAOImpl();
        driverDAO = new DriverDAOImpl();
    }

    @AfterAll
    static void tearDown() {
        wasteTruckDAO.closeDown();
        driverDAO.closeDown();
    }

    @Test
    void saveWasteTruck() {
        assertEquals(1, wasteTruckDAO.saveWasteTruck("Mercedes", "ASDNASDN1293", 5));
    }

    @Test
    void getWasteTruckById() {
        assertNotNull(wasteTruckDAO.getWasteTruckById(4));
    }

    @Test
    void setWasteTruckAvailable() {
        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(4);
        wasteTruckDAO.setWasteTruckAvailable(wasteTruck, true);
    }

    @Test
    void deleteWasteTruck() {
        wasteTruckDAO.deleteWasteTruck(4);
    }

    @Test
    void addDriverToWasteTruck() {
        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(3);
        Driver driver = driverDAO.getDriverById("200224-LA-938A");

        wasteTruckDAO.addDriverToWasteTruck(wasteTruck, driver);
    }

    @Test
    void removeDriverFromWasteTruck() {
        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(3);
        wasteTruckDAO.removeDriverFromWasteTruck(wasteTruck, "200224-LA-938A");
    }

    @Test
    void getAllAvailableTrucks() {
        List<WasteTruck> wasteTrucks = wasteTruckDAO.getAllAvailableTrucks();
        assertNotNull(wasteTrucks);
    }
}