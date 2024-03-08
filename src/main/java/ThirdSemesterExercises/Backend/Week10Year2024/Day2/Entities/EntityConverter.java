package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.HotelDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.RoomDTO;

public class EntityConverter {
    public static Hotel convertToHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        return hotel;
    }


    public static Room convertToRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        Hotel hotel = new Hotel();
        //room.setHotel(hotel.setId(roomDTO.getHotelId()));
        room.setNumber(roomDTO.getNumber());
        room.setPrice(roomDTO.getPrice());

        // Retrieve Hotel entity based on hotelId if it's not null
        /*if (roomDTO.getHotelId() != 0) {
            Hotel hotel = new Hotel();
            hotel.setId(roomDTO.getHotelId());
            room.setHotel(hotel);
        }*/
        return room;
    }
}
