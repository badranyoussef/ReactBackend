package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.HotelDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.HotelDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.RoomDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Hotel;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import io.javalin.http.Handler;

import java.util.List;
import java.util.stream.Collectors;

public class HotelController {
    public static Handler getAll(HotelDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(404).result("No Hotels available");
            } else {
                ctx.json(dao.getAll());
            }
        };
    }

    public static Handler delete(HotelDAO dao) {
        return ctx -> {
            Hotel foundHotel = dao.getById(Integer.parseInt(ctx.pathParam("id")));
            if (foundHotel != null) {
                dao.delete(foundHotel.getId());
                ctx.status(204).result("Hotel successfully deleted");
            } else {
                ctx.status(500).result("Hotel was not found");
            }
        };
    }

    public static Handler getHotel(HotelDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dao.getById(id) != null) {
                Hotel foundHotel = dao.getById(id);
                HotelDTO hotelDTO = HotelDTO.builder()
                        .id(foundHotel.getId())
                        .name(foundHotel.getName())
                        .address(foundHotel.getAddress())
                        .build();
                ctx.json(hotelDTO);
            } else {
                ctx.status(404).result("The id you are looking for does not exist");
            }
        };
    }

    public static Handler getHotelWithRooms(HotelDAO hotelDAO, RoomDAO roomDAO) {
        return ctx -> {
            int hotelId = Integer.parseInt(ctx.pathParam("id"));

            Hotel foundHotel = hotelDAO.getById(hotelId);

            if (foundHotel != null) {
                List<Room> rooms = roomDAO.getAllRoomsByHotelId(hotelId);

                // Map Room entities to RoomDTO objects
                List<RoomDTO> roomDTOs = rooms.stream()
                        .map(room -> RoomDTO.builder()
                                .id(room.getId())
                                .hotelId(room.getHotelId())
                                .number(room.getNumber())
                                .price(room.getPrice())
                                .build())
                        .collect(Collectors.toList());

                ctx.json(roomDTOs);
            } else {
                ctx.status(404).result("The hotel with the provided id does not exist");
            }
        };
    }

    public static Handler create(HotelDAO dao) {
        return ctx -> {
            Hotel hotel = ctx.bodyAsClass(Hotel.class);
            if (hotel != null) {
                dao.create(hotel);
                ctx.json(hotel);
            } else {
                ctx.status(500).result("Received data was incorrect... Something went wrong. Check create method in controller");
            }
        };
    }

    public static Handler update(HotelDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HotelDTO updatedHotelDTO = ctx.bodyAsClass(HotelDTO.class);

            // Fetch the hotel from the database
            Hotel foundHotel = dao.getById(id);

            if (foundHotel != null) {
                foundHotel.setName(updatedHotelDTO.getName());
                foundHotel.setAddress(updatedHotelDTO.getAddress());

                // Save the updated hotel to the database
                dao.update(foundHotel);
                updatedHotelDTO.setId(id);

                // Return the updated hotelDTO object as JSON response
                ctx.json(updatedHotelDTO);
            } else {
                // Hotel with the provided ID not found
                ctx.status(404);
                ctx.result("Hotel not found");
            }
        };
    }

}

