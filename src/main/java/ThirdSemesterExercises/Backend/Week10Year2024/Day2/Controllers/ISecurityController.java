package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controllers;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.UserDTO;
import io.javalin.http.Handler;

import java.util.Set;

public interface ISecurityController {
    Handler register();

    Handler login();

    public String createToken(UserDTO user);

    boolean authorize(UserDTO user, Set<String> allowedRoles);

    Handler authenticate();
}
