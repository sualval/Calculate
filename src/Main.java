
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static int arraySum;

    public static void main(String[] args) {
        int arrayLength = 2000;
        int[] numbers = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            Random random = new Random(100);
            numbers[i] = random.nextInt();
        }

        long startArr = System.currentTimeMillis();
        System.out.println(arraySum(numbers) + " sum");
        long endArr = System.currentTimeMillis();
        System.out.println(endArr - startArr + "ms : sum array");

////
        ForkJoinPool pool = new ForkJoinPool();
        MyAction myAction = new MyAction(numbers, 0, numbers.length);

        long startPool = System.currentTimeMillis();
        System.out.println(pool.invoke(myAction) + " pool");
        long endPool = System.currentTimeMillis();
        System.out.println(endPool - startPool + " ms : pool");
    }

    private static long arraySum(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            arraySum += ints[i];
        }
        return arraySum;
    }
}

