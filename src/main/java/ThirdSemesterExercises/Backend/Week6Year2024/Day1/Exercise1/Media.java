package ThirdSemesterExercises.Backend.Week6Year2024.Day1.Exercise1;

import java.time.LocalDate;

public interface Media<T> {
    boolean isForAdults();
    int getId();
    String getTitle();
    String getOriginalLanguage();
    String getOriginalTitle();
    String getDescription();
    String getMediaType();
    LocalDate getReleaseDate();
    double getRating();
}