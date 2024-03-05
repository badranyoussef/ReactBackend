package ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise3;


import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Optional;

public class AnimalHandler {
    static List<Animal> animalList = Main.animalList;

    public static Handler getAllAnimals() {
        return ctx -> {
            if (!animalList.isEmpty()) {
                ctx.json(animalList);
            } else {
                ctx.status(404).result("No animals found");
            }
        };
    }

    public static Handler getAnimalById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            Optional<Animal> optionalAppointment = animalList.stream()
                    .filter(animal -> animal.getId() == id)
                    .findFirst();

            if (optionalAppointment.isPresent()) {
                ctx.json(optionalAppointment.get());
            } else {
                ctx.status(404).result("Animal not found");
            }
        };
    }

    public static Handler createAnAnimal() {
        return ctx -> {
            Animal newAnimal = ctx.bodyAsClass(Animal.class);

            animalList.add(newAnimal);

            ctx.status(201).result("Animal created successfully");
        };
    }


    public static AnimalDTO getAnimalDTOById(int id) {
        Animal animal = animalList.get(id);

        // Map animal data to DTO
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setAllergies(animal.getAllergies());
        animalDTO.setMedicine(animal.getMedicine());

        return animalDTO;
    }
}
