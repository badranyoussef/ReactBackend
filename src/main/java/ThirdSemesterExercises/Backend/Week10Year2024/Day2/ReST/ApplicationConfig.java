package ThirdSemesterExercises.Backend.Week10Year2024.Day2.ReST;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller.SecurityController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.UserDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Exceptions.AuthorizationException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.HttpStatus;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationConfig {
    private ObjectMapper om = new ObjectMapper();
    private static ApplicationConfig instance;
    private Javalin app;

    private ApplicationConfig() {
        // Initialize ObjectMapper
        ObjectMapper om = new ObjectMapper();

        // Initialize Javalin app
        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "/api";
        });
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    public ApplicationConfig initiateServer() {
        return instance;
    }

    public ApplicationConfig startServer(int portNumber) {
        app.start(portNumber);
        return instance;
    }

    public ApplicationConfig setRoute(EndpointGroup route) {
        app.routes(route);
        return instance;
    }

    public ApplicationConfig setExceptionHandlers() {

        app.exception(Exception.class, (e, ctx) -> {
            ObjectNode node = om.createObjectNode().put("errorMessage", e.getMessage());
            ctx.status(500).json(node);
        });

        app.error(404, ctx -> {
            ctx.status(404).result("Not Found");
        });

        app.exception(IllegalStateException.class, (e, ctx) -> {
            ctx.status(400).result("Bad Request: " + e.getMessage());
        });

        return instance;
    }
    public ApplicationConfig checkSecurityRoles() {
        ObjectMapper objectMapper = new ObjectMapper(); // Assuming objectMapper is available in the scope

        app.updateConfig(config -> {
            config.accessManager((handler, ctx, permittedRoles) -> {
                Set<String> allowedRoles = new HashSet<>();
                        /*permittedRoles.stream()
                        .map(Enum::name)
                        .map(String::toUpperCase)
                        .collect(Collectors.toSet());*/

                if (allowedRoles.contains("ANYONE") || ctx.method().toString().equals("OPTIONS")) {
                    handler.handle(ctx);
                    return;
                }

                UserDTO user = ctx.attribute("user");
                System.out.println("USER IN CHECK_SEC_ROLES: " + user);

                if (user == null) {
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(objectMapper.createObjectNode()
                                    .put("msg", "Not authorized. No username was added from the token"));
                    return;
                }

                /*if (SecurityController.authorize(user, allowedRoles)) {
                    handler.handle(ctx);
                } else {
                    throw new ApiException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: " + allowedRoles);
                }*/
            });
        });

        return instance; // Assuming instance is declared and initialized somewhere in your code
    }

    public void stopServer() {
        app.stop();
    }
}