package ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise3;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Optional;

public class AppointmentHandler {

    static List<Appointment> appointmentList = Main.appointmentList;

    public static Handler getAllUpcomingAppointments() {
        return ctx -> {
            if (!appointmentList.isEmpty()) {
                ctx.json(appointmentList);
            } else {
                ctx.status(404).result("No upcoming appointments found");
            }
        };
    }

    public static Handler getAppointmentById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            Optional<Appointment> optionalAppointment = appointmentList.stream()
                    .filter(appointment -> appointment.getId() == id)
                    .findFirst();

            if (optionalAppointment.isPresent()) {
                ctx.json(optionalAppointment.get());
            } else {
                ctx.status(404).result("Appointment not found");
            }
        };
    }

    public static Handler createAnAppointment() {
        return ctx -> {
            try {
                // Deserialize JSON payload into Appointment object
                Appointment newAppointment = ctx.bodyAsClass(Appointment.class);

                // Add the new appointment to the appointmentList
                appointmentList.add(newAppointment);

                // Return success response
                ctx.status(201).result("Appointment created successfully");
            } catch (Exception e) {
                // Log the exception
                e.printStackTrace();

                // Return error response
                ctx.status(500).result("Failed to create appointment: " + e.getMessage());
            }
        };
    }

    public static AppointmentDTO getAppointmentDTOById(int id) {
        Appointment appointment = appointmentList.get(id);

        // Map animal data to DTO
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDescription(appointment.getDescription());
        appointmentDTO.setTestDate(appointment.getTestDate());

        return appointmentDTO;
    }
}
