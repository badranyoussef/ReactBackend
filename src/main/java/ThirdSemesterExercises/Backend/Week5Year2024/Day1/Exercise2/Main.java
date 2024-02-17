package ThirdSemesterExercises.Backend.Week5Year2024.Day1.Exercise2;


//                  Functional Programming

public class Main {
    public static void main(String[] args) {

        // Implement the following methods using lambda expressions
        MyTransformingType addThree = (int a) -> a + 3;
        MyValidatingType isTheNumberThree = (int a) -> a == 3;
    }

    /*
    Create the following 2 methods that use the functional interfaces, to perform map and
    filter operations on an array of integers:
     */

    private static int[] filter(int[] a, MyValidatingType op) {

        int[] resultArray = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            boolean resultChecker = op.perform(a[i]);
            if (resultChecker == true) {
                resultArray[i] = a[i];
            }
        }
        return resultArray;
    }

    private static int[] map(int[] a, MyTransformingType op) {
        int[] result = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = op.perform(a[i]);
        }
        return result;
    }

}

    /*
    Create a functional interface MyTransformingType that has a method that can
    take an integer and return another integer (transforming the first one in some way)
    */

@FunctionalInterface
interface MyTransformingType {
    int perform(int a);
}

    /*
    Create another functional interface MyValidatingType that has a method,
    that can take an integer and return a boolean (based on some kind of
    validation of the boolean, e.g. is it divisible by 3 or whatever)
     */

@FunctionalInterface
interface MyValidatingType {
    boolean perform(int a);
}