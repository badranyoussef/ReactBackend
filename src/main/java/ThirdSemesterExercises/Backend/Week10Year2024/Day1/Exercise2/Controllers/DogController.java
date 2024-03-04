package ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2.Controllers;

import ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2.DTOs.DogDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2.Main;
import io.javalin.http.Handler;

import java.util.Map;

public class DogController {

    private static Map<Integer, DogDTO> dogs = Main.map;

    public static Handler getAllDogs() {
        return ctx -> {
            if (!dogs.isEmpty()) {
                ctx.json(dogs);
            } else {
                ctx.status(404).result("No dogs found");
            }
        };
    }

    public static Handler getDogById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DogDTO dogDTO = dogs.get(id);
            if (dogDTO != null) {
                ctx.json(dogDTO);
            } else {
                ctx.status(404).result("Dog not found");
            }
        };
    }

    public static Handler createDog() {
        return ctx -> {
            // Parse JSON body to get the new dog information
            DogDTO newDog = ctx.bodyAsClass(DogDTO.class);

            // Add the new dog to your data source (e.g., map)
            dogs.put(newDog.getId(), newDog);

            ctx.status(201).result("Dog created successfully");
        };
    }

    public static Handler updateDog() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            // Parse JSON body to get the updated dog information
            DogDTO updatedDog = ctx.bodyAsClass(DogDTO.class);

            boolean success = updateDog(id, updatedDog);

            if (success) {
                ctx.result("Dog updated successfully");
            } else {
                ctx.status(404).result("Dog not found");
            }
        };
    }

    private static boolean updateDog(int id, DogDTO updatedDog) {
        if (dogs.containsKey(id)) {
            dogs.put(id, updatedDog);
            return true;
        } else {
            return false;
        }
    }

    public static Handler deleteDog() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            boolean success = deleteDog(id);

            if (success) {
                ctx.result("Dog deleted successfully");
            } else {
                ctx.status(404).result("Dog not found");
            }
        };
    }

    private static boolean deleteDog(int id) {
        if (dogs.containsKey(id)) {
            dogs.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
