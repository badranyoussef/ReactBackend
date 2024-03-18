package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Application;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.ReST.ApplicationConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Routes.Route;

public class App {
    public static void main(String[] args) {
        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7007)
                .setExceptionHandlers()
                .checkSecurityRoles()
                .setRoute(Route.addRoutes());
    }
}