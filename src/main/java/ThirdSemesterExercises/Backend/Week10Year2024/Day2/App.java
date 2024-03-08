package ThirdSemesterExercises.Backend.Week10Year2024.Day2;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.HibernateConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.HotelController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.RoomController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.HotelDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Hotel;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        HotelDAO hotelDAO = new HotelDAO(emf, Hotel.class);
        RoomDAO roomDAO = new RoomDAO(emf, Room.class);

        // Generating data for my database
        /*Hotel hotel1 = new Hotel("Hotel A", "Lyngby");
        Hotel hotel2 = new Hotel("Hotel B", "ASD");

        Room room1ForHotel1 = new Room(1, 500);
        Room room2ForHotel1 = new Room(2, 600);
        Room room1ForHotel2 = new Room(1, 500);
        Room room2ForHotel2 = new Room(2, 600);

        hotel1.addRoom(room1ForHotel1);
        hotel1.addRoom(room2ForHotel1);

        hotel2.addRoom(room1ForHotel2);
        hotel2.addRoom(room2ForHotel2);

        hotelDAO.create(hotel1);
        hotelDAO.create(hotel2);*/
        // Generating data for my database

        // ???
        /*ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7070)
                .setException500()
                .setException404()
                .setRoute(() -> {
                    path("/hotels", () -> {
                        get("/", ctx -> HotelController.getAll(hotelDAO));
                    });
                });*/

        Javalin app = Javalin.create().start(7007);

        // For hotel
        app.get("/hotels", HotelController.getAll(hotelDAO));
        app.get("/hotels/{id}", HotelController.getHotel(hotelDAO));
        app.get("/hotels/{id}/rooms", HotelController.getHotelWithRooms(hotelDAO, roomDAO));
        app.post("/hotels", HotelController.create(hotelDAO));
        app.put("/hotels/{id}", HotelController.update(hotelDAO));
        app.delete("/hotels/{id}", HotelController.delete(hotelDAO));

        // For Room
        app.get("/rooms", RoomController.getAll(roomDAO));
        app.get("/rooms/{id}", RoomController.getRoom(roomDAO));
        app.post("/rooms", RoomController.create(roomDAO));
        app.put("/rooms/{id}", RoomController.update(roomDAO));
        app.delete("/rooms/{id}", RoomController.delete(roomDAO));
    }
}
