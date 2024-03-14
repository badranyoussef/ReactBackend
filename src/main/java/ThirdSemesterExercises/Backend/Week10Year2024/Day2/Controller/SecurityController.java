package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controller;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.UserDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.TokenDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DTOs.UserDTO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.sql.Date;
import java.util.Set;

public class SecurityController {

    private static UserDAO userDAO;

    public SecurityController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Handler authenticate() {
        // To check the users roles against the allowed roles for the endpoint (managed by javalins accessManager)
        // Checked in 'before filter' -> Check for Authorization header to find token.
        // Find user inside the token, forward the ctx object with userDTO on attribute
        // When ctx hits the endpoint it will have the user on the attribute to check for roles (ApplicationConfig -> accessManager)
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnObject = objectMapper.createObjectNode();
        return (ctx) -> {
            if (ctx.method().toString().equals("OPTIONS")) {
                ctx.status(200);
                return;
            }
            String header = ctx.header("Authorization");
            if (header == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header missing"));
                return;
            }
            String token = header.split(" ")[1];
            if (token == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header malformed"));
                return;
            }
            UserDTO verifiedTokenUser = verifyToken(token);
            if (verifiedTokenUser == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Invalid User or Token"));
            }
            System.out.println("USER IN AUTHENTICATE: " + verifiedTokenUser);
            ctx.attribute("user", verifiedTokenUser);
        };
    }

    private UserDTO verifyToken(String token) {
        return new UserDTO();
    }

    public Handler login() {
        return (ctx) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode(); // for sending json messages back to the client
            try {
                UserDTO user = ctx.bodyAsClass(UserDTO.class);
                System.out.println("USER IN LOGIN: " + user);

                User verifiedUserEntity = userDAO.getVerifiedUser(user.getUsername(), user.getPassword());
                String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(new TokenDTO(token, user.getUsername()));

            } catch (EntityNotFoundException | ValidationException e) {
                ctx.status(401);
                System.out.println(e.getMessage());
                ctx.json(returnObject.put("msg", e.getMessage()));
            }
        };
    }

    public String createToken(UserDTO user) throws JOSEException {
        String ISSUER;
        String TOKEN_EXPIRE_TIME;
        String SECRET_KEY = null;

        if (System.getenv("DEPLOYED") != null) {
            ISSUER = System.getenv("ISSUER");
            TOKEN_EXPIRE_TIME = System.getenv("TOKEN_EXPIRE_TIME");
            SECRET_KEY = System.getenv("SECRET_KEY");
        } else {
            ISSUER = "Thomas Hartmann";
            TOKEN_EXPIRE_TIME = "1800000"; // 30 minutes in milliseconds
            //SECRET_KEY = Utils.getPropertyValue("SECRET_KEY", "config.properties");
        }
        SecurityController tokenUtils = new SecurityController(userDAO);
        return tokenUtils.createToken(user, ISSUER, TOKEN_EXPIRE_TIME, SECRET_KEY);
    }

    public String createToken(UserDTO user, String ISSUER, String TOKEN_EXPIRE_TIME, String SECRET_KEY) throws JOSEException {
        try {
            Set<String> roles = user.getRoles();

            // Convert roles to a comma-separated string
            String rolesString = String.join(",", roles);

            // Build the JWT claims set
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer(ISSUER)
                    .claim("username", user.getUsername())
                    .claim("roles", rolesString)
                    .expirationTime(new Date(System.currentTimeMillis() + Long.parseLong(TOKEN_EXPIRE_TIME)))
                    .build();

            // Create payload
            Payload payload = new Payload(claimsSet.toJSONObject());

            // Create signer
            JWSSigner signer = new MACSigner(SECRET_KEY);

            // Create JWS header
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

            // Create JWS object
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);

            // Sign the JWS object
            jwsObject.sign(signer);

            // Serialize the JWS object to produce the final JWT
            return jwsObject.serialize();

        } catch (JOSEException e) {
            // Handle the JOSEException here
            throw new JOSEException("Could not create token", e);
        }
    }

    public Handler register() {
        return (ctx) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode();
            try {
                UserDTO userInput = ctx.bodyAsClass(UserDTO.class);
                User created = userDAO.createUser(userInput.getUsername(), userInput.getPassword());

                String token = createToken(new UserDTO(created));
                ctx.status(HttpStatus.CREATED).json(new TokenDTO(token, userInput.getUsername()));
            } catch (EntityExistsException e) {
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
                ctx.json(returnObject.put("msg", "User already exists"));
            }
        };
    }
}

