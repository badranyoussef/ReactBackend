package ThirdSemesterExercises.Backend.Week10Year2024.Day2;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.ApplicationConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.HibernateConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.Route;
import jakarta.persistence.EntityManagerFactory;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        Route route = new Route(emf);

        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7007)
                .setExceptionHandlers()
                .setRoute(route.hotelRoutes())
                .setRoute(route.roomRoutes());
    }
}