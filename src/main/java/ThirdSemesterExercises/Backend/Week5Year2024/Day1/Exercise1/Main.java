package ThirdSemesterExercises.Backend.Week5Year2024.Day1.Exercise1;


//              Lambda

public class Main {

    public static void main(String[] args) {

    /*
    Implement the following arithmetic operations using lambda expressions:
        Addition
        Subtraction
        Multiplication
        Division
        Modulus
        Power
     */

        ArithmeticOperation addition = (int a, int b) -> a + b;
        ArithmeticOperation subtraction = (int a, int b) -> a - b;
        ArithmeticOperation multiplication = (int a, int b) -> a * b;
        ArithmeticOperation division = (int a, int b) -> a / b;
        ArithmeticOperation modulus = (int a, int b) -> a % b;
        ArithmeticOperation power = (int a, int b) -> (int) Math.pow(a, b);

    }

    /*
       Implement the following methods that take either 2 integers or integer
       arrays and an ArithmeticOperation and return the result of the operation

       int operate(int a, int b, ArithmeticOperation op)
       int[] operate(int[] a, int[] b, ArithmeticOperation op)
    */

    private static int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a, b);
    }

    private static int[] operate(int[] a, int[] b, ArithmeticOperation op) {

        int[] result = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = op.perform(a[i], b[i]);
        }
        return result;
    }
}

// Based on the following functional interface
@FunctionalInterface
interface ArithmeticOperation {
    int perform(int a, int b);
}