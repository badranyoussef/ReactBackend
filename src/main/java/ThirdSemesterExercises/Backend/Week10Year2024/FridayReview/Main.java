package ThirdSemesterExercises.Backend.Week10Year2024.FridayReview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Bro", "Youssef").stream().collect(Collectors.toList());
        stringList.add("itworks");
    }
}
