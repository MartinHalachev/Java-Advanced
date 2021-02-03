import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class LootBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstLootBoxInput = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int[] secondLootBoxInput = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> firstLootBox = new ArrayDeque<>();
        ArrayDeque<Integer> secondLootBox = new ArrayDeque<>();

        for (int number : firstLootBoxInput) {
            firstLootBox.offer(number);
        }
        for (int number : secondLootBoxInput) {
            secondLootBox.push(number);
        }
        int claimed = 0;
        while (!firstLootBox.isEmpty() && !secondLootBox.isEmpty()) {
            int sum = firstLootBox.peek() + secondLootBox.peek();
            if (sum % 2 == 0) {
                claimed += sum;
                firstLootBox.poll();
                secondLootBox.pop();
            } else {
                firstLootBox.offer(secondLootBox.pop());
            }
        }
        if (firstLootBox.isEmpty()) {
            System.out.println("First lootbox is empty");
        }
        if (secondLootBox.isEmpty()) {
            System.out.println("Second lootbox is empty");
        }
        if (claimed >= 100) {
            System.out.printf("Your loot was epic! Value: %d", claimed);
        } else {
            System.out.printf("Your loot was poor... Value: %d", claimed);
        }


    }
}
