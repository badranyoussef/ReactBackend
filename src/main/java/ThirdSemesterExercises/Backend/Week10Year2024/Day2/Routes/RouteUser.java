package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Routes;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.SecurityController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.UserDAO;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class RouteUser {

    private static SecurityController securityController;


    public RouteUser(UserDAO userDAO) {
        securityController = new SecurityController(userDAO);
    }

    public EndpointGroup getSecurityRoutes() {
        return () -> {
            path("/auth", () -> {
                post("/login", securityController.login());
                post("/register", securityController.register());
            });
        };
    }

    public enum Role {ANYONE, USER, ADMIN}
}