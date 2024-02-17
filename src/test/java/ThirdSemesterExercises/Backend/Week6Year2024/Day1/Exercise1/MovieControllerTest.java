package ThirdSemesterExercises.Backend.Week6Year2024.Day1.Exercise1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class MovieControllerTest {

    @Test
    void findMovieById() {
        /*
        Write unit tests for the MovieController where you search for the following titles and persist them:
        The Shawshank Redemption
        The Godfather
        The Dark Knight
        The Godfather: Part II
        The Lord of the Rings: The Return of the King
        Pulp Fiction
        12 Angry Men
        The Good, the Bad and the Ugly
        Forrest Gump
        Fight Club
        Inception
         */
        String theShawshankRedemption = MovieController.findMovieById("tt0111161");
        String godFather = MovieController.findMovieById("tt0068646");
        String theDarkKnight = MovieController.findMovieById("tt0468569");
        String godFather2 = MovieController.findMovieById("tt0071562");
        String theLordOfTheRings = MovieController.findMovieById("tt0167260");
        String pulpFiction = MovieController.findMovieById("tt0110912");
        String angryMen = MovieController.findMovieById("tt0050083");
        String theGood = MovieController.findMovieById("tt0060196");
        String forestGump = MovieController.findMovieById("tt0109830");
        String fightClub = MovieController.findMovieById("tt0137523");
        String inception = MovieController.findMovieById("tt1375666");

        // Use assertEquals to verify the expected movie titles
        assertEquals("The Shawshank Redemption", theShawshankRedemption);
        assertEquals("The Godfather", godFather);
        assertEquals("The Dark Knight", theDarkKnight);
        assertEquals("The Godfather Part II", godFather2);
        assertEquals("The Lord of the Rings: The Return of the King", theLordOfTheRings);
        assertEquals("Pulp Fiction", pulpFiction);
        assertEquals("12 Angry Men", angryMen);
        assertEquals("The Good, the Bad and the Ugly", theGood);
        assertEquals("Forrest Gump", forestGump);
        assertEquals("Fight Club", fightClub);
        assertEquals("Inception", inception);

    }

    // Then test the other methods in the MovieController

    @Test
    void getSortedByReleaseDateReversed() {
        List<Movie> movieListToCompareWith = new ArrayList<>();
        movieListToCompareWith.add(new Movie(false, 2, "Movie2", "EN", "Movie2", "No text", "Movie", "2023-03-02", 1.00));
        movieListToCompareWith.add(new Movie(false, 1, "Movie1", "EN", "Movie1", "No text", "Movie", "2023-03-01", 1.00));

        // Create the expected sorted list
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(false, 1, "Movie1", "EN", "Movie1", "No text", "Movie", "2023-03-01", 1.00));
        movies.add(new Movie(false, 2, "Movie2", "EN", "Movie2", "No text", "Movie", "2023-03-02", 1.00));
        List<Movie> sortedMovies = MovieController.getSortedByReleaseDate(movies);
        // Assert that the sorted list matches the expected sorted list
        assertEquals(movieListToCompareWith, sortedMovies);
    }

    @Test
    void getMovieRatingsAscending() {
        List<Movie> movieListToCompareWith = new ArrayList<>();
        movieListToCompareWith.add(new Movie(false, 1, "Movie1", "EN", "Movie1", "No text", "Movie", "2023-03-01", 1.00));
        movieListToCompareWith.add(new Movie(false, 2, "Movie2", "EN", "Movie2", "No text", "Movie", "2023-03-02", 2.00));

        // Create the expected sorted list
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(false, 2, "Movie2", "EN", "Movie2", "No text", "Movie", "2023-03-02", 2.00));
        movies.add(new Movie(false, 1, "Movie1", "EN", "Movie1", "No text", "Movie", "2023-03-01", 1.00));
        List<Movie> sortedMovies = MovieController.getMovieRatingsAscending(movies);
        // Assert that the sorted list matches the expected sorted list
        assertEquals(movieListToCompareWith, sortedMovies);
    }

    @Test
    void getMovieRatingByMin() {
        List<Movie> movieListToCompareWith = new ArrayList<>();
        movieListToCompareWith.add(new Movie(false, 1, "Movie1", "EN", "Movie1", "No text", "Movie", "2023-03-01", 1.00));
        movieListToCompareWith.add(new Movie(false, 2, "Movie2", "EN", "Movie2", "No text", "Movie", "2023-03-02", 5.00));
        List<Movie> movies = MovieController.getMovieRatingByMin(movieListToCompareWith,5);
        assertEquals(movies.size(),1);
    }

    @Test
    void getMovieRatingByMax() {
        List<Movie> movieListToCompareWith = new ArrayList<>();
        movieListToCompareWith.add(new Movie(false, 1, "Movie1", "EN", "Movie1", "No text", "Movie", "2023-03-01", 1.00));
        movieListToCompareWith.add(new Movie(false, 2, "Movie2", "EN", "Movie2", "No text", "Movie", "2023-03-02", 5.00));
        List<Movie> movies = MovieController.getMovieRatingByMax(movieListToCompareWith,10);
        assertEquals(movies.size(),2);
    }

    @Test
    void getPopularMovies() {
        //Returns a list of 20 popular movies
        String authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYTllMjQyNTU4ZTU5MzY1N2U4ZTc1MmFmOTlmMGVmZiIsInN1YiI6IjY1YzExZDk4YmYwOWQxMDE4NGE3ZGIyMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.p1IpNHp8XDPS2hebgUtgs1j5jtwB8Z22Q0t_Vt8Ctt0";
        int pageNumber = 1;
        List<Movie> movies = MovieController.getPopularMovies(authToken,pageNumber);
        assertEquals(movies.size(),20);
    }

    @Test
    void getConnection() {
        String apiUrl = "https://api.themoviedb.org/3/authentication/token/new";
        String authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYTllMjQyNTU4ZTU5MzY1N2U4ZTc1MmFmOTlmMGVmZiIsInN1YiI6IjY1YzExZDk4YmYwOWQxMDE4NGE3ZGIyMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.p1IpNHp8XDPS2hebgUtgs1j5jtwB8Z22Q0t_Vt8Ctt0";

        String response = MovieController.getConnection(apiUrl, authToken);

        assertEquals(false, response.isEmpty());
    }
}