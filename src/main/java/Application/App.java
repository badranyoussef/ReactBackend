package Application;

import ReST.ApplicationConfig;
import Routes.Route;

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