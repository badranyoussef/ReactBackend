package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise6;

//                  Stream API
    /*
    In this exercise, you will work with a list of books and use the Stream API to perform
    various operations on the data.
    */

import java.util.List;

// Create the Book Class
// Create a Book class with attributes like title, author, publication year, pages and rating.
public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;
    private double rating;

    public Book(String title, String author, int publicationYear, double rating, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.rating = rating;
    }

    // Data Collection
    /*
    Create a collection of Book objects to work with. You can either create a sample
    dataset or read data from a file or database.
    */

    public static List<Book> bookData() {
        return List.of(
                new Book("The Hero", "Henrik Henriksen", 1992, 4.5, 220),
                new Book("The Anti-Hero", "Susan Bondegaard", 2003, 3.8, 234),
                new Book("Come-on!", "Anders Andersen", 2008, 3.9, 344),
                new Book("The New Man", "Lars Larsen", 2022, 2.1, 151),
                new Book("The New Man2", "Lars Larsen", 2022, 2.1, 151),
                new Book("The New Woman", "Lars Larsen", 2023, 4.2, 148),
                new Book("The New Woman1", "Lars Larsen", 2023, 4.2, 148),
                new Book("The New Woman2", "Lars Larsen", 2023, 4.2, 148),
                new Book("The New Woman3", "Lars Larsen", 2023, 4.2, 148),
                new Book("History of Denmark", "Dan Marksen", 1995, 4.9, 516),
                new Book("Learn Programming", "Johan Johansen", 2015, 4.5, 374),
                new Book("Learn Python", "Johan Johansen", 2016, 4.1, 194),
                new Book("Learn SQL", "Johan Johansen", 2017, 2.0, 174),
                new Book("Programming 101", "Jens Jensen", 2018, 4.0, 299)
        );
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", pages=" + pages +
                ", rating=" + rating +
                '}';
    }
}
