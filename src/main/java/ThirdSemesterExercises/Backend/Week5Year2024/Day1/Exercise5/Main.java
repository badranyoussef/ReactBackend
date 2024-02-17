package ThirdSemesterExercises.Backend.Week5Year2024.Day1.Exercise5;

//                  Method References
public class Main {
    public static void main(String[] args) {

        /*
        The methods defined in Step 2 "Functional Programming" can be implemented
        using method references. Implement them using method references.
        E.g. Create a named method that doubles a value and use this method as a
        method reference: MyTransformingType doubleValue = (x) -> x * 2; in your map function.
         */

        int[] arrayOne = {12, 13, 14, 15, 16, 17};
        int[] arrayTwo = {3, 6, 9, 3, 13, 63, 93};

        MyTransformingType doubleTheValue = Main::doubleValue;
        MyValidatingType addThreeToTheValue = Main::isTheNumberThree;

        int[] test1 = map(arrayOne, doubleTheValue);
        for (int i: test1){
            System.out.println(i);
        }
        System.out.println();
        int[] test2 = filter(arrayTwo, addThreeToTheValue);
        for (int i: test2){
            System.out.println(i);
        }


    }

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

    private static int doubleValue(int x) {
        return x * 2;
    }

    private static boolean isTheNumberThree(int x) {
        if (x == 3) {
            return true;
        } else {
            return false;
        }
    }
}

@FunctionalInterface
interface MyTransformingType {
    int perform(int a);
}

@FunctionalInterface
interface MyValidatingType {
    boolean perform(int a);
}
