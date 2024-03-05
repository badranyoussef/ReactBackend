package ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2;

import ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2.Controllers.DogController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day1.Exercise2.DTOs.DogDTO;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    // Let the dogs live in a map in memory with the id as key and the dog as value.
    public static Map<Integer, DogDTO> map = new HashMap<>();

    public static void main(String[] args) {
        // Setup project with javalin
        // Create a new javalin server running on port 7007 that can handle the following routes representing a dog ressource
        Javalin app = Javalin.create().start(7007);
        app.get("/", ctx -> ctx.result("Hello World"));

        DogDTO dogDTO1 = new DogDTO("V1", "V1", 1, DogDTO.Gender.MALE);
        DogDTO dogDTO2 = new DogDTO("V2", "V2", 2, DogDTO.Gender.FEMALE);
        map.put(dogDTO1.getId(), dogDTO1);
        map.put(dogDTO2.getId(), dogDTO2);

        // Set up the server so that all dog endpoints are under the path /api/dogs
        app.routes(() -> {
            path("/api/dogs", () -> {
                get("/", DogController.getAllDogs());
                get("/{id}", DogController.getDogById());
                post("/create", DogController.createDog());
                put("/{id}/update", DogController.updateDog());
                delete("/{id}/delete", DogController.deleteDog());
            });
        });
    }
}
