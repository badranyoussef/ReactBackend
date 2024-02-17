package ThirdSemesterExercises.Backend.Week6Year2024.Day1.Exercise1;

public class Movie1 {

    private boolean forAdults;
    private int id;
    private String title;
    private String original_language;
    private String original_title;
    private String overview;
    private String release_date;

    public Movie1(boolean forAdults, int id, String title, String original_language, String original_title, String overview, String release_date) {
        this.forAdults = forAdults;
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie1{" +
                "forAdults=" + forAdults +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
