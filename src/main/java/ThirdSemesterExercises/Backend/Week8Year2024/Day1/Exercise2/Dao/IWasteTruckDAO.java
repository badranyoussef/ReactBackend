package ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Dao;

import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.Driver;
import ThirdSemesterExercises.Backend.Week8Year2024.Day1.Exercise2.Model.WasteTruck;

import java.util.List;

public interface IWasteTruckDAO {
    // WasteTruck
    int saveWasteTruck(String brand, String registrationNumber, int capacity);

    WasteTruck getWasteTruckById(int id);

    void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available);

    void deleteWasteTruck(int id);

    void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver);

    void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id);

    List<WasteTruck> getAllAvailableTrucks();
}
