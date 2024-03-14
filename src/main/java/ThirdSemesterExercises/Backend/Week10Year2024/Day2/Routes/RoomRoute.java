package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Routes;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.RoomController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RoomRoute {
    private static RoomDAO roomDAO;

    public RoomRoute(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public EndpointGroup roomRoutes() {
        return () -> {
            get("/rooms", RoomController.getAll(roomDAO));
            get("/rooms/{id}", RoomController.getRoom(roomDAO));
            post("/rooms", RoomController.create(roomDAO));
            put("/rooms/{id}", RoomController.update(roomDAO));
            delete("/rooms/{id}", RoomController.delete(roomDAO));
        };
    }
}
