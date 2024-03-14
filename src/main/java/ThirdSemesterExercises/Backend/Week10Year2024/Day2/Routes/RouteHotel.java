package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Routes;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.HotelController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.HotelDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RouteHotel {

    private static HotelDAO hotelDAO;
    private static RoomDAO roomDAO;

    public RouteHotel(HotelDAO hotelDAO, RoomDAO roomDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
    }

    public EndpointGroup hotelRoutes() {
        return () -> {
            get("/hotels", HotelController.getAll(hotelDAO));
            get("/hotels/{id}", HotelController.getHotelById(hotelDAO));
            get("/hotels/{id}/rooms", HotelController.getHotelWithRooms(hotelDAO, roomDAO));
            post("/hotels", HotelController.create(hotelDAO));
            put("/hotels/{id}", HotelController.update(hotelDAO));
            delete("/hotels/{id}", HotelController.delete(hotelDAO));
        };
    }
}
