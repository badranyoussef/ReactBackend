package ThirdSemesterExercises.Backend.Week10Year2024.SchoolExercises.Day2;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class ContextDemo {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);

        // Exercise 1
        // Part 1
        app.get("hello/{name}", ctx -> {
            String name = ctx.pathParam("name");
            String greeting = "Hi";
            String message = greeting + " " + name;
            ctx.json(message);
        });

        // Part 2
        app.get("/headers", ctx -> ctx.json(ctx.headerMap()));

        // Part 3
        app.get("/queryParam", ctx -> {
            String name = ctx.queryParam("name");
            ctx.json(name);
            // http://localhost:7007/queryParam?name= //Skriv navnet her
        });
    }
}
