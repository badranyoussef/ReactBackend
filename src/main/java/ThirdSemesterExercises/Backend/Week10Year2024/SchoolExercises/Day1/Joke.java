package ThirdSemesterExercises.Backend.Week10Year2024.SchoolExercises.Day1;

public class Joke {
    private String id;
    private String joke;
    private int status;

    public Joke(String id, String joke, int status) {
        this.id = id;
        this.joke = joke;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public int getStatus() {
        return status;
    }
}
