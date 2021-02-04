import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wreaths = 0;
        int store = 0;

        int[] inputLilies = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        int[] inputRoses = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> lilies = new ArrayDeque<>();
        ArrayDeque<Integer> roses = new ArrayDeque<>();

        for (int inputLily : inputLilies) {
            lilies.push(inputLily);
        }

        for (int inputRose : inputRoses) {
            roses.offer(inputRose);
        }

        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int sum = lilies.peek() + roses.peek();
            if (sum == 15) {
                wreaths++;
                lilies.pop();
                roses.poll();
            }
            if (sum < 15) {
                store += lilies.pop() + roses.poll();
            }
            if (sum > 15) {
                lilies.push(lilies.pop() - 2);
            }
        }
        wreaths += store / 15;
        if (wreaths >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!", wreaths);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!", 5 - wreaths);
        }

    }
}
