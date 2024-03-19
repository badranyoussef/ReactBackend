package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Routes;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controllers.RoomController;
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
            get("/rooms", RoomController.getAll(roomDAO), Role.ADMIN);
            get("/rooms/{id}", RoomController.getRoom(roomDAO), Role.ADMIN);
            post("/rooms", RoomController.create(roomDAO), Role.ADMIN);
            put("/rooms/{id}", RoomController.update(roomDAO), Role.ADMIN);
            delete("/rooms/{id}", RoomController.delete(roomDAO), Role.ADMIN);
        };
    }
}
