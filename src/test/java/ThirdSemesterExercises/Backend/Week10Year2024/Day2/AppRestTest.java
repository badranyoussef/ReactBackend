package ThirdSemesterExercises.Backend.Week10Year2024.Day2;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.ApplicationConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.HibernateConfig;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Config.Route;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Hotel;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Entities.Room;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class AppRestTest {

    private static EntityManagerFactory emf;
    private static ApplicationConfig app;
    private static Integer port = 7007;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:7007/api";

        emf = HibernateConfig.getEntityManagerFactoryConfig(true);
        Route route = new Route(emf);

        app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(port)
                .setExceptionHandlers()
                .setRoute(route.hotelRoutes())
                .setRoute(route.roomRoutes());

        Hotel hotel1 = new Hotel("Hotel A", "Lyngby vej");
        Hotel hotel2 = new Hotel("Hotel B", "Roskilde vej");

        hotel1.addRoom(new Room(1, 500));
        hotel1.addRoom(new Room(2, 800));

        hotel2.addRoom(new Room(1, 500));
        hotel2.addRoom(new Room(2, 800));

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hotel1);
            em.persist(hotel2);
            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDown() {
        emf.close();
        app.stopServer();
    }

    @Test
    @DisplayName("Test server is running.")
    public void test1() {
        RestAssured
                .given()
                .when()
                .get("/hotels")
                .then()
                .statusCode(200)
                .body("hotels", hasSize(2));
    }

    @Test
    @DisplayName("Posting a hotel to the database.")
    public void test2() {

        Hotel postHotel = new Hotel("TestName", "TestAddress");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postHotel)
                .when()
                .post("/hotels")
                .then()
                .statusCode(200)
                .body("name", equalTo("TestName"))
                .body("address", equalTo("TestAddress"));
    }

    @Test
    @DisplayName("Reading hotel with specifik id 1.")
    public void test3() {

        Hotel postHotel = new Hotel("TestName", "TestAddress");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postHotel)
                .when()
                .post("/hotels")
                .then()
                .statusCode(200)
                .body("name", equalTo("TestName"))
                .body("address", equalTo("TestAddress"));
    }
}