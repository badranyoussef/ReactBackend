package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise7;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Aggregation with Collectors
        // Use the Collectors class to perform the following operations on the list of transactions

        // Calculate the total sum of all transaction amounts
        List<Transaction> transactions = Transaction.bookData();
        int totalSum = transactions.stream()
                .collect(Collectors.summingInt(Transaction::getAmount));
        System.out.println("Total amount of the transactions: " + totalSum);
        System.out.println();

        // Group transactions by currency and calculate the sum of amounts for each currency
        Map<String, Integer> totalSumByCurrency = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency,
                        Collectors.summingInt(Transaction::getAmount)));
        totalSumByCurrency.forEach((currency, sum) ->
                System.out.println("Currency: " + currency + ", Total sum: " + sum));
        System.out.println();

        // Find the highest transaction amount
        Optional<Transaction> highestTransaction = transactions.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Transaction::getAmount)));
        highestTransaction.ifPresent(transaction ->
                System.out.println("Highest Transaction Amount: " + transaction.getAmount()));
        System.out.println();

        // Find the average transaction amount
        double averageAmount = transactions.stream()
                .collect(Collectors.averagingDouble(Transaction::getAmount));
        System.out.println("Average Transaction Amount: " + averageAmount);
    }

}



