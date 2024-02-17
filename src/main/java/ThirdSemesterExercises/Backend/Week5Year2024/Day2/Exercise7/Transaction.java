package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise7;

//                          Collectors

import java.util.List;

// Create the Transaction Class
// Create a Transaction class with attributes like id, amount, and currency.
public class Transaction {

    private int id;
    private int amount;
    private String currency;

    public Transaction(int id, int amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    // Data Collection
    // Create a collection of Transaction objects to work with. You can either create a sample dataset or
    // read data from a file or database.
    public static List<Transaction> bookData() {
        return List.of(
                new Transaction(1, 100, "DKK"),
                new Transaction(2, 200, "SWE"),
                new Transaction(3, 300, "LDK"),
                new Transaction(4, 400, "DKK"),
                new Transaction(5, 500, "SWE"),
                new Transaction(6, 600, "LDK")
        );
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
