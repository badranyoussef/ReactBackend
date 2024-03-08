package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.RoomDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import io.javalin.http.Handler;

public class RoomController {

    public static Handler create(RoomDAO dao) {
        return ctx -> {
            Room room = ctx.bodyAsClass(Room.class);
            if (room != null) {
                Room createdRoom = dao.create(room);
                ctx.json(createdRoom);
            } else {
                ctx.status(500).result("Received data was incorrect... Something went wrong. Check create method in controller");
            }
        };
    }

    public static Handler delete(RoomDAO dao) {
        return ctx -> {
            Room foundRoom = dao.getById(Integer.parseInt(ctx.pathParam("id")));
            if (foundRoom != null) {
                dao.delete(foundRoom.getId());
                ctx.status(204).result("Room successfully deleted");
            } else {
                ctx.status(500).result("Room was not found");
            }
        };
    }

    public static Handler getRoom(RoomDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dao.getById(id) != null) {
                Room foundRoom = dao.getById(id);
                RoomDTO roomDTO = RoomDTO.builder()
                        .id(foundRoom.getId())
                        .number(foundRoom.getNumber())
                        .price(foundRoom.getPrice())
                        .hotelId(foundRoom.getHotelId())
                        .build();
                ctx.json(roomDTO);
            } else {
                ctx.status(404).result("The id you are looking for does not exist");
            }
        };
    }

    public static Handler getAll(RoomDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(404).result("No rooms available");
            } else {
                ctx.json(dao.getAll());
            }
        };
    }

    public static Handler update(RoomDAO roomDAO) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RoomDTO updatedRoomDTO = ctx.bodyAsClass(RoomDTO.class);

            // Fetch the hotel from the database
            Room foundRoom = roomDAO.getById(id);

            if (foundRoom != null) {
                foundRoom.setNumber(updatedRoomDTO.getNumber());
                foundRoom.setPrice(updatedRoomDTO.getPrice());

                // Save the updated hotel to the database
                roomDAO.update(foundRoom);
                updatedRoomDTO.setId(id);

                // Return the updated hotelDTO object as JSON response
                ctx.json(updatedRoomDTO);
            } else {
                // Hotel with the provided ID not found
                ctx.status(404);
                ctx.result("Hotel not found");
            }
        };
    }
}


