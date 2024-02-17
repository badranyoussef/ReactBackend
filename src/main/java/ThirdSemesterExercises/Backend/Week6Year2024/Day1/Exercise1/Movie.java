package ThirdSemesterExercises.Backend.Week6Year2024.Day1.Exercise1;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {
    private boolean forAdults;
    private int id;
    private String title;
    private String originalLanguage;
    private String originalTitle;
    private String description;
    private String mediaType;
    private LocalDate releaseDate;
    private double rating;

    public Movie(boolean forAdults, int id, String title, String originalLanguage, String originalTitle, String description, String mediaType, String releaseDate, double rating) {
        this.forAdults = forAdults;
        this.id = id;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.description = description;
        this.mediaType = mediaType;
        this.releaseDate = LocalDate.parse(releaseDate);
        this.rating = rating;
    }

    public boolean isForAdults() {
        return forAdults;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getMediaType() {
        return mediaType;
    }

    public double getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Media{" +
                "forAdults=" + forAdults +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", description='" + description + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
