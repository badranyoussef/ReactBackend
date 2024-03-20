package ThirdSemesterExercises.Backend.Week12Year2024.Day2.Exercise2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class TDDExerciseTest {

    @Test
    @DisplayName("Requirement 1")
    void test1() {
        // Given
        String setName = "Ahmad";
        String expectedString = "Hello, Ahmad.";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 2")
    void test2() {
        // Given
        String setName = null;
        String expectedString = "Hello, my friend.";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 3")
    void test3() {
        // Given
        String setName = "AHMAD";
        String expectedString = "HELLO AHMAD!";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 4")
    void test4() {
        // Given
        String[] setName = {"Ahmad", "Youssef"};
        String expectedString = "Hello, Ahmad and Youssef.";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 5")
    void test5() {
        // Given
        String[] setName = {"Ahmad", "Youssef", "Hanni"};
        String expectedString = "Hello, Ahmad, Youssef, and Hanni.";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 6")
    void test6() {
        // Given
        String[] setName = {"Ahmad", "YOUSSEF", "Hanni"};
        String expectedString = "Hello, Ahmad and Hanni. AND HELLO YOUSSEF!";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 7")
    void test7() {
        // Given
        String[] setName = {"Ahmad", "Youssef, Hanni"};
        String expectedString = "Hello, Ahmad, Youssef, and Hanni.";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("Requirement 8")
    void test8() {
        // Given
        String[] setName = {"Ahmad", "\"Youssef, Hanni\""};
        String expectedString = "Hello, Ahmad and Youssef, Hanni.";

        // When
        String result = TDDExercise.greet(setName);

        //Then
        assertEquals(expectedString, result);
    }
}