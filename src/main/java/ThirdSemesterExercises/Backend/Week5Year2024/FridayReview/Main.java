package ThirdSemesterExercises.Backend.Week5Year2024.FridayReview;

//                  Review af: Understand what functional programming is and how to use it in Java

public class Main {
    public static void main(String[] args) {

        ArithmeticOperation addition = (int a, int b) -> a + b;
        ArithmeticOperation subtraction = (int a, int b) -> a - b;

        ArithmeticOperation additionMethodReference = Integer::sum;

        System.out.println(mathOperation(10,10,addition));
        System.out.println(mathOperation(50,10,subtraction));

        System.out.println(mathOperation(3,3,additionMethodReference));

    }

    private static int mathOperation(int a, int b, ArithmeticOperation op) {
        return op.perform(a, b);
    }

}

@FunctionalInterface
interface ArithmeticOperation {
    int perform(int a, int b);
}
