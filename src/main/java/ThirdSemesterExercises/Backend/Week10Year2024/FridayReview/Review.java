package ThirdSemesterExercises.Backend.Week10Year2024.FridayReview;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class Review {
    /*
    8) Path Parameters: Understand how to use path parameters in Javalin routes to capture dynamic parts of URLs
    and pass them as parameters to route handlers, enabling flexible and dynamic routing.
    */

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);

        app.get("/hello/{name}", ctx -> {
            String name = ctx.pathParam("name");
            String greeting = "Hi";
            String message = greeting + " " + name;

            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("output", message);

            ctx.json(jsonResponse);
        });

        app.get("hello/{name}/age/{age}", ctx -> {
            String name = ctx.pathParam("name");
            int age = Integer.parseInt(ctx.pathParam("age"));
            String greeting = "Hello";
            String message = greeting + " " + name + ". You are " + age + " years old.";

            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("output", message);

            ctx.json(jsonResponse);
        });
    }
}
