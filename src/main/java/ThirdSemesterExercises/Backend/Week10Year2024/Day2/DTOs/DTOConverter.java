package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Hotel;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;

public class DTOConverter {
    public static HotelDTO convertToHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        return hotelDTO;
    }

    public static RoomDTO convertToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setHotelId(room.getHotelId());
        roomDTO.setNumber(room.getNumber());
        roomDTO.setPrice(room.getPrice());
        return roomDTO;
    }
}
