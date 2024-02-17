package ThirdSemesterExercises.Backend.Week6Year2024.SchoolExercises.jokeExercise;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String apiUrl = "https://icanhazdadjoke.com/";
        String jokeJson = getJoke("https://icanhazdadjoke.com/");

        // Parse the JSON response into a Joke object
        Joke jokeObject = parseJsonToJoke(jokeJson);
        Joke jokeObject1 = parseJsonToJoke(jokeJson);

        // Display the parsed Joke object
        System.out.println("Parsed Joke Object:");
        System.out.println("ID: " + jokeObject.getId());
        System.out.println("Joke: " + jokeObject.getJoke());
        System.out.println("Status: " + jokeObject.getStatus());

        System.out.println("Parsed Joke Object:");
        System.out.println("ID: " + jokeObject1.getId());
        System.out.println("Joke: " + jokeObject1.getJoke());
        System.out.println("Status: " + jokeObject1.getStatus());

        for (int i = 0; i < 10; i++){
            Joke j = parseJsonToJoke2();
            System.out.println(j.getJoke());
        }

    }

    private static Joke parseJsonToJoke(String json) {
        // Use Gson library to parse JSON into Joke object
        Gson gson = new Gson();
        return gson.fromJson(json, Joke.class);
    }

    private static Joke parseJsonToJoke2() {
        // Use Gson library to parse JSON into Joke object
        String s = getJoke("https://icanhazdadjoke.com/");
        Gson gson = new Gson();
        return gson.fromJson(s, Joke.class);
    }

    private static String getJoke(String url) {
        // Using OkHttp: Can sometimes cause the program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")  // Ensure the API returns JSON
                .method("GET", null)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}