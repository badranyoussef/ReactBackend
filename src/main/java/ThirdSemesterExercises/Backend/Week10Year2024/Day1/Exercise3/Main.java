package ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise3;

import io.javalin.Javalin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static List<Appointment> appointmentList;
    public static List<Animal> animalList;

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);
        app.get("/", ctx -> ctx.result("Hello World"));

        // Appointments
        appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(1, "Operation", LocalDate.of(2024, 10, 3).toString()));
        appointmentList.add(new Appointment(2, "Hair cut", LocalDate.of(2023, 1, 30).toString()));

        Appointment appointment = new Appointment(3, "adasd", "123123");
        appointmentList.add(appointment);
        // Animals
        animalList = new ArrayList<>();
        animalList.add((new Animal(1, "None", "None")));
        animalList.add((new Animal(2, "Other dogs", "Panodil")));

        app.routes(() -> {
            // before() method for logging requests
            app.before(ctx -> {
                System.out.println("Received request: " + ctx.method() + " " + ctx.path());
            });
            // after() method for logging responses
            app.after(ctx -> {
                System.out.println("Sent response: " + ctx.status());
            });
            // Appointment Retrieval
            path("/api/vet/appointments", () -> {
                //Shows only upcoming appointments
                get("/", AppointmentHandler.getAllUpcomingAppointments());
                //Shows any appointment by id
                get("/{id}", AppointmentHandler.getAppointmentById());
                //Create an appointment
                post("/create", AppointmentHandler.createAnAppointment());
            });
            // Patient (Animal) Information
            path("/api/vet/patients", () -> {
                //Shows all animals
                get("/", AnimalHandler.getAllAnimals());
                //Shows any animal by id
                get("/{id}", AnimalHandler.getAnimalById());
                post("/create", AnimalHandler.createAnAnimal());
            });
        });
    }
}
