package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Stream Processing
        // Use the Stream API to perform the following operations on the collection of books

        // Find the average rating of all books
        System.out.println(findTheAverageRatingForAllBooks());

        System.out.println();

        // Filter and display books published after a specific year
        List<Book> listOfBooksForYear2023 = listOfBooksForASpecificYear(2023);
        listOfBooksForYear2023.forEach(System.out::println);
        System.out.println();

        // Sort books by rating in descending order
        List<Book> listOfBooksAscendingRating = findTheAverageRatingForAllBooksDescending();
        listOfBooksAscendingRating.forEach(System.out::println);
        System.out.println();

        // Find and display the title of the book with the highest rating.
        System.out.println("The book with the highest rating is: " + findHighestRatedBookTitle());
        System.out.println();

        // Group books by author and calculate the average rating for each author's books
        Map<String, Double> averageRatingByAuthor = calculateAverageRatingByAuthor();
        averageRatingByAuthor.forEach((author, averageRating) ->
                System.out.println("Author: " + author + ", Average Rating: " + averageRating)
        );
        System.out.println();

        // Calculate the total number of pages for all books (assuming each book has a fixed number of pages)
        System.out.println("The amount of pages in all of the books: " + calculateTotalNumberOfPages());

        // Chaining and Composition
        // Chain multiple Stream operations together to perform complex tasks, such as filtering and sorting

        // I have used filtering in the following method: listOfBooksForASpecificYear
        // I have used sorting in the following method: findTheAverageRatingForAllBooksDescending

        // Collecting Results
        // I have only used collect.


    }

    public static int calculateTotalNumberOfPages() {
        List<Book> books = Book.bookData();
        return books.stream().collect(Collectors.reducing(0, Book::getPages, Integer::sum));
    }

    public static Map<String, Double> calculateAverageRatingByAuthor() {
        List<Book> books = Book.bookData();

        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.averagingDouble(Book::getRating)
                ));
    }

    public static String findHighestRatedBookTitle() {
        List<Book> books = Book.bookData();
        return books.stream()
                .max(Comparator.comparingDouble(Book::getRating))
                .map(Book::getTitle)
                .orElse("No books found"); // or any other default value
    }


    public static double findTheAverageRatingForAllBooks() {
        List<Book> books = Book.bookData();
        return books.stream().mapToDouble(book -> book.getRating()).average().orElse(0);
    }

    public static List<Book> listOfBooksForASpecificYear(int year) {
        return Book.bookData()
                .stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    public static List<Book> findTheAverageRatingForAllBooksDescending() {
        List<Book> books = Book.bookData();

        return books.stream()
                .collect(Collectors.groupingBy(Book::getTitle, Collectors.averagingDouble(Book::getRating)))
                .entrySet().stream()
                .map(entry -> new Book(entry.getKey(), "", 0, entry.getValue(), 0))
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList());
    }

}
