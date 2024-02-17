package ThirdSemesterExercises.Backend.Week6Year2024.Day2.Exercise7;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Create a program to get data from 10 different web services (APIs) at the same time
public class Main {
    public static void main(String[] args) {

        // Get the returned value from the web service and save it in DTOs

        //Connection 1 - Pokémon API
        String connection1 = getConnection("https://pokeapi.co/api/v2/pokemon/ditto");
        PokemonDTO pokemonDTO = parseDTO(connection1, PokemonDTO.class);

        //Connection 2 - Hazard Joke API
        String connection2 = getConnection("https://icanhazdadjoke.com/");
        HazardJokeDTO hazardJokeDTO = parseDTO(connection2, HazardJokeDTO.class);

        //Connection 3 - Chuck Norris API
        String connection3 = getConnection("https://api.chucknorris.io/jokes/random");
        ChuckNorrisDTO chuckNorrisDTO = parseDTO(connection3, ChuckNorrisDTO.class);

        //Connection 4 - Kanye West API
        String connection4 = getConnection("https://api.kanye.rest");
        KanyeWestDTO kanyeWestDTO = parseDTO(connection4, KanyeWestDTO.class);

        //Connection 5 - What does Trump Think API
        String connection5 = getConnection("https://api.whatdoestrumpthink.com/api/v1/quotes/random");
        WhatDoesTrumpThinkDTO whatDoesTrumpThinkDTO = parseDTO(connection5, WhatDoesTrumpThinkDTO.class);

        //Connection 6 - Predicting the age of a person API
        String connection6 = getConnection("https://api.agify.io?name=ahmad");
        PredictingTheAgeOfAPersonDTO predictingTheAgeOfAPersonDTO = parseDTO(connection6, PredictingTheAgeOfAPersonDTO.class);

        //Connection 7 - Random dog picture API
        String connection7 = getConnection("https://dog.ceo/api/breeds/image/random");
        RandomDogPictureDTO randomDogPictureDTO = parseDTO(connection7, RandomDogPictureDTO.class);

        //Connection 8 - Your IP API
        String connection8 = getConnection("https://api.ipify.org/?format=json");
        YourIPAPIDTO yourIPAPIDTO = parseDTO(connection8, YourIPAPIDTO.class);

        //Connection 9 - Cat facts API
        String connection9 = getConnection("https://catfact.ninja/fact");
        CatFactsDTO catFactsDTO = parseDTO(connection9, CatFactsDTO.class);

        //Connection 10 - Activity to do API
        String connection10 = getConnection("https://www.boredapi.com/api/activity");
        ActivityToDoDTO activityToDoDTO = parseDTO(connection10, ActivityToDoDTO.class);

        // Collect all the DTO data in a mega DTO and print it out in a nice format
        MegaDTO megaDTO = new MegaDTO(pokemonDTO, hazardJokeDTO, chuckNorrisDTO, kanyeWestDTO, whatDoesTrumpThinkDTO, predictingTheAgeOfAPersonDTO,
                randomDogPictureDTO, yourIPAPIDTO, catFactsDTO, activityToDoDTO);
        System.out.println(megaDTO);

        System.out.println();

        System.out.println("Threads ->");

        //Laver threads klar
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<DTO>> futures = new ArrayList<>();

        // Define tasks for each API connection
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://pokeapi.co/api/v2/pokemon/ditto"), PokemonDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://icanhazdadjoke.com/"), HazardJokeDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://api.chucknorris.io/jokes/random"), ChuckNorrisDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://api.kanye.rest"), KanyeWestDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://api.whatdoestrumpthink.com/api/v1/quotes/random"), WhatDoesTrumpThinkDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://api.agify.io?name=ahmad"), PredictingTheAgeOfAPersonDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://dog.ceo/api/breeds/image/random"), RandomDogPictureDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://api.ipify.org/?format=json"), YourIPAPIDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://catfact.ninja/fact"), CatFactsDTO.class)));
        futures.add(executorService.submit(() -> parseDTO(getConnection("https://www.boredapi.com/api/activity"), ActivityToDoDTO.class)));

        List<DTO> dtos = new ArrayList<>();

        for (Future<DTO> future : futures) {
            try {
                dtos.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Shutdown the executor service
        executorService.shutdown();

        // Create MegaDTO object with collected DTOs
        MegaDTO megaDTO1 = new MegaDTO(
                (PokemonDTO) dtos.get(0),
                (HazardJokeDTO) dtos.get(1),
                (ChuckNorrisDTO) dtos.get(2),
                (KanyeWestDTO) dtos.get(3),
                (WhatDoesTrumpThinkDTO) dtos.get(4),
                (PredictingTheAgeOfAPersonDTO) dtos.get(5),
                (RandomDogPictureDTO) dtos.get(6),
                (YourIPAPIDTO) dtos.get(7),
                (CatFactsDTO) dtos.get(8),
                (ActivityToDoDTO) dtos.get(9)
        );

        // Print the MegaDTO
        System.out.println(megaDTO);

    }

    public static <T> T parseDTO(String connection, Class<T> clazz) {
        Gson gson = new Gson();
        try {
            JsonObject jsonResponse = gson.fromJson(connection, JsonObject.class);
            return gson.fromJson(jsonResponse, clazz);
        } catch (JsonSyntaxException | NullPointerException e) {
            throw new RuntimeException("Error parsing JSON response: " + e.getMessage());
        }
    }

    public static String getConnection(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
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

interface DTO {
}

class MegaDTO implements DTO {
    PokemonDTO pokemonDTO;
    HazardJokeDTO hazardJokeDTO;
    ChuckNorrisDTO chuckNorrisDTO;
    KanyeWestDTO kanyeWestDTO;
    WhatDoesTrumpThinkDTO whatDoesTrumpThinkDTO;
    PredictingTheAgeOfAPersonDTO predictingTheAgeOfAPersonDTO;
    RandomDogPictureDTO randomDogPictureDTO;
    YourIPAPIDTO yourIPAPIDTO;
    CatFactsDTO catFactsDTO;
    ActivityToDoDTO activityToDoDTO;

    public MegaDTO(PokemonDTO pokemonDTO, HazardJokeDTO hazardJokeDTO, ChuckNorrisDTO chuckNorrisDTO, KanyeWestDTO kanyeWestDTO, WhatDoesTrumpThinkDTO whatDoesTrumpThinkDTO, PredictingTheAgeOfAPersonDTO predictingTheAgeOfAPersonDTO, RandomDogPictureDTO randomDogPictureDTO, YourIPAPIDTO yourIPAPIDTO, CatFactsDTO catFactsDTO, ActivityToDoDTO activityToDoDTO) {
        this.pokemonDTO = pokemonDTO;
        this.hazardJokeDTO = hazardJokeDTO;
        this.chuckNorrisDTO = chuckNorrisDTO;
        this.kanyeWestDTO = kanyeWestDTO;
        this.whatDoesTrumpThinkDTO = whatDoesTrumpThinkDTO;
        this.predictingTheAgeOfAPersonDTO = predictingTheAgeOfAPersonDTO;
        this.randomDogPictureDTO = randomDogPictureDTO;
        this.yourIPAPIDTO = yourIPAPIDTO;
        this.catFactsDTO = catFactsDTO;
        this.activityToDoDTO = activityToDoDTO;
    }

    @Override
    public String toString() {
        return "MegaDTO{" +
                "pokemonDTO=" + pokemonDTO +
                ", hazardJokeDTO=" + hazardJokeDTO +
                ", chuckNorrisDTO=" + chuckNorrisDTO +
                ", kanyeWestDTO=" + kanyeWestDTO +
                ", whatDoesTrumpThinkDTO=" + whatDoesTrumpThinkDTO +
                ", predictingTheAgeOfAPersonDTO=" + predictingTheAgeOfAPersonDTO +
                ", randomDogPictureDTO=" + randomDogPictureDTO +
                ", yourIPAPIDTO=" + yourIPAPIDTO +
                ", catFactsDTO=" + catFactsDTO +
                ", activityToDoDTO=" + activityToDoDTO +
                '}';
    }
}

class PredictingTheAgeOfAPersonDTO implements DTO {
    int count;

    @Override
    public String toString() {
        return "PredictingTheAgeOfAPersonDTO: The count is " + count;
    }
}

class YourIPAPIDTO implements DTO {
    String ip;

    @Override
    public String toString() {
        return "YourIPAPIDTO: Your ip is " + ip;
    }
}

class ActivityToDoDTO implements DTO {
    String activity;

    @Override
    public String toString() {
        return "ActivityToDoDTO: The count is " + activity;
    }
}

class CatFactsDTO implements DTO {
    String fact;

    @Override
    public String toString() {
        return "CatFactsDTO: The fact is " + fact;
    }
}

class RandomDogPictureDTO implements DTO {
    String message;

    @Override
    public String toString() {
        return "RandomDogPictureDTO: Link to the picture is " + message;
    }
}

class PokemonDTO implements DTO {
    String name;

    @Override
    public String toString() {
        return "PokemonDTO: The name is " + name;
    }
}

class WhatDoesTrumpThinkDTO implements DTO {
    String message;

    @Override
    public String toString() {
        return "WhatDoesTrumpThinkDTO: The message from Trump is " + message;
    }
}

class HazardJokeDTO implements DTO {
    String joke;

    @Override
    public String toString() {
        return "HazardJokeDTO: The joke is " + joke;
    }
}

class ChuckNorrisDTO implements DTO {
    String value;

    @Override
    public String toString() {
        return "ChuckNorrisDTO: The joke is " + value;
    }
}

class KanyeWestDTO implements DTO {
    String quote;

    @Override
    public String toString() {
        return "KanyeWestDTO: The quote is " + quote;
    }
}

