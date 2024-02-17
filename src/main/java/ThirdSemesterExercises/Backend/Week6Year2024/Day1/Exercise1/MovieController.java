package ThirdSemesterExercises.Backend.Week6Year2024.Day1.Exercise1;

import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieController {
    // Add a new Class MovieController with the following methods

    public static void main(String[] args) {

        String apiUrl = "https://api.themoviedb.org/3/authentication/token/new";
        String authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYTllMjQyNTU4ZTU5MzY1N2U4ZTc1MmFmOTlmMGVmZiIsInN1YiI6IjY1YzExZDk4YmYwOWQxMDE4NGE3ZGIyMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.p1IpNHp8XDPS2hebgUtgs1j5jtwB8Z22Q0t_Vt8Ctt0";

        String response = getConnection(apiUrl, authToken);
        System.out.println(response);

        // Set the desired page number
        int page = 2;

        // Call the getPopularMovies method with the Authorization header and page number
        List<Movie> popularMovies = getPopularMovies(authToken, page); // Example 1: Release date as a LocalDate
        List<Movie1> popularMovies2 = getPopularMovies2(authToken, page); // Example 2: Release date as a String

        for (Movie1 m : popularMovies2) {
            System.out.println(m);
        }

        findMovieById("tt1375666");

        /*
        getByRating that can take an MPAA rating or similar as a parameter and
        return all movies with that rating or lower or if you prefer, use the quality rating
        and find all movies with a specific rating e.g 8.5 and higher
        */

        List<Movie> sorted = getMovieRatingsAscending(popularMovies);
        List<Movie> sortedByMin = getMovieRatingByMin(popularMovies, 5.00);
        List<Movie> sortedByMax = getMovieRatingByMax(popularMovies, 10.00);

        System.out.println();
        System.out.println("All movies sorted by rating: ");
        for (Movie m : sorted) {
            System.out.println("Title: " + m.getTitle() + " Rating: " + m.getRating());
        }

        System.out.println();
        System.out.println("Movies sorted by minimum rating: ");
        for (Movie m : sortedByMin) {
            System.out.println("Title: " + m.getTitle() + " Rating: " + m.getRating());
        }

        System.out.println();
        System.out.println("Movies sorted by maximum rating: ");
        for (Movie m : sortedByMax) {
            System.out.println("Title: " + m.getTitle() + " Rating: " + m.getRating());
        }

        // getSortedByReleaseDate that returns all movies sorted by release date descending.
        List<Movie> sortedByReleaseDate = getSortedByReleaseDate(popularMovies);
        System.out.println();
        System.out.println("Movies sorted by release date (Descending): ");
        for (Movie m : sortedByReleaseDate) {
            System.out.println("Title: " + m.getTitle() + " Release date: " + m.getReleaseDate());
        }
        // Make a generic interface for the class, so it can be used for Books, Games, etc. as well.
        // My generic interface is called Media.
    }

    public static String findMovieById(String imdbId) {
        String BASE_URL = "https://api.themoviedb.org/3";
        String authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYTllMjQyNTU4ZTU5MzY1N2U4ZTc1MmFmOTlmMGVmZiIsInN1YiI6IjY1YzExZDk4YmYwOWQxMDE4NGE3ZGIyMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.p1IpNHp8XDPS2hebgUtgs1j5jtwB8Z22Q0t_Vt8Ctt0";
        String url = BASE_URL + "/find/" + imdbId + "?external_source=imdb_id";
        String jsonResponse = getConnection(url, authToken);
        System.out.println("!!!" + jsonResponse);

        // Parse the JSON response
        JsonElement jsonElement = JsonParser.parseString(jsonResponse);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Extract the movie title from the JSON response
        String movieTitle = jsonObject.getAsJsonArray("movie_results").get(0).getAsJsonObject().get("title").getAsString();

        return movieTitle;
    }

    public static List<Movie> getSortedByReleaseDate(List<Movie> l) {
        return l.stream().sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .collect(Collectors.toList());
    }

    public static List<Movie> getMovieRatingsAscending(List<Movie> l) {
        return l.stream()
                .sorted(Comparator.comparing(Movie::getRating))
                .collect(Collectors.toList());
    }

    public static List<Movie> getMovieRatingByMin(List<Movie> l, double minimum) {
        return l.stream()
                .filter(movie -> movie.getRating() >= minimum)
                .sorted(Comparator.comparing(Movie::getRating))
                .collect(Collectors.toList());
    }

    public static List<Movie> getMovieRatingByMax(List<Movie> l, double maximum) {
        return l.stream()
                .filter(movie -> movie.getRating() <= maximum)
                .sorted(Comparator.comparing(Movie::getRating))
                .collect(Collectors.toList());
    }

    public static List<Movie> getPopularMovies(String authorizationHeader, int page) {
        String response = getConnection("https://api.themoviedb.org/3/movie/popular?language=en-US&page=" + page, authorizationHeader);

        Gson gson = new Gson();
        List<Movie> movieList = new ArrayList<>();

        try {
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray results = jsonResponse.getAsJsonArray("results");
            for (JsonElement element : results) {
                JsonObject result = element.getAsJsonObject();
                int id = result.get("id").getAsInt();
                boolean forAdults = result.get("adult").getAsBoolean();
                String title = result.get("title").getAsString();
                String originalTitle = result.get("original_title").getAsString();
                String language = result.get("original_language").getAsString();
                String description = result.get("overview").getAsString();
                String releaseDate = result.get("release_date").getAsString();
                double rating = result.get("vote_average").getAsDouble();
                String mediaType = "Movie";
                Movie movie = new Movie(forAdults, id, title, language, originalTitle, description, mediaType, releaseDate, rating);
                movieList.add(movie);
            }

        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }

    public static List<Movie1> getPopularMovies2(String authorizationHeader, int page) {
        String response = getConnection("https://api.themoviedb.org/3/movie/popular?language=en-US&page=" + page, authorizationHeader);

        Gson gson = new Gson();
        List<Movie1> movieList = new ArrayList<>();

        try {
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray results = jsonResponse.getAsJsonArray("results");

            for (JsonElement element : results) {
                JsonObject result = element.getAsJsonObject();
                Movie1 movie = gson.fromJson(result, Movie1.class);
                movieList.add(movie);
            }

        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }


    public static String getConnection(String url, String authorizationHeader) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // Set the Authorization header
        httpGet.setHeader("Authorization", authorizationHeader);
        httpGet.setHeader("accept", "application/json");

        try {
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new RuntimeException("Error: " + statusCode + " " + response.getStatusLine().getReasonPhrase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

