package ThirdSemesterExercises.Backend.Week10Year2024.Day2;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.ApplicationConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.Route;

public class App {
    public static void main(String[] args) {
        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7007)
                .setExceptionHandlers()
                .setRoute(Route.hotelRoutes())
                .setRoute(Route.roomRoutes());
    }
}
