package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Exceptions;

public class NotAuthorizedException extends Exception {
    private final int statusCode;

    public NotAuthorizedException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
