package ThirdSemesterExercises.Backend.Week10Year2024.Day2;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.ApplicationConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.HibernateConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.HotelController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.RoomController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.HotelDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {

    private static HotelDAO hotelDAO;
    private static RoomDAO roomDAO;

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        hotelDAO = HotelDAO.getInstance(emf);
        roomDAO = RoomDAO.getInstance(emf);

        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7007)
                .setExceptionHandlers()
                .setRoute(hotelRoutes())
                .setRoute(roomRoutes());
    }

    public static EndpointGroup hotelRoutes() {
        return () -> {
            get("/hotels", HotelController.getAll(hotelDAO));
            get("/hotels/{id}", HotelController.getHotel(hotelDAO));
            get("/hotels/{id}/rooms", HotelController.getHotelWithRooms(hotelDAO, roomDAO));
            post("/hotels", HotelController.create(hotelDAO));
            put("/hotels/{id}", HotelController.update(hotelDAO));
            delete("/hotels/{id}", HotelController.delete(hotelDAO));
        };
    }

    public static EndpointGroup roomRoutes() {
        return () -> {
            get("/rooms", RoomController.getAll(roomDAO));
            get("/rooms/{id}", RoomController.getRoom(roomDAO));
            post("/rooms", RoomController.create(roomDAO));
            put("/rooms/{id}", RoomController.update(roomDAO));
            delete("/rooms/{id}", RoomController.delete(roomDAO));
        };
    }
}
