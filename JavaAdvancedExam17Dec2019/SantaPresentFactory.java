package exams;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class SantaPresentFactory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] firstSequence = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] secondSequence = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> materials = new ArrayDeque<>();
        ArrayDeque<Integer> magic = new ArrayDeque<>();

        int doll = 0;
        int train = 0;
        int bear = 0;
        int bicycle = 0;

        for (int number : firstSequence) {
            if (number != 0) {
                materials.push(number);
            }
        }
        for (int number : secondSequence) {
            if (number != 0) {
                magic.offer(number);
            }
        }
        while (!materials.isEmpty() && !magic.isEmpty()) {
            if (materials.peek() == 0) {
                materials.pop();
            }
            if (magic.peek() == 0) {
                magic.poll();
            }
            if (!materials.isEmpty() && !magic.isEmpty()) {
                int sum = materials.peek() * magic.peek();
                if (sum < 0) {
                    materials.push(materials.pop() + magic.poll());
                } else if (sum == 150) {
                    doll++;
                    magic.poll();
                    materials.pop();
                } else if (sum == 250) {
                    train++;
                    magic.poll();
                    materials.pop();
                } else if (sum == 300) {
                    bear++;
                    magic.poll();
                    materials.pop();
                } else if (sum == 400) {
                    bicycle++;
                    magic.poll();
                    materials.pop();
                } else {
                    magic.poll();
                    materials.push(materials.pop() + 15);
                }
            } else {
                break;
            }
        }
        if (doll != 0 && train != 0 || bear != 0 && bicycle != 0) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }
        if (!materials.isEmpty()) {
            StringBuilder output = new StringBuilder();
            for (Integer integer : materials) {
                output.append(integer).append(", ");
            }
            System.out.println("Materials left: " + output.substring(0, output.length() - 2));
        } else if (!magic.isEmpty()) {
            StringBuilder output = new StringBuilder();
            for (Integer integer : magic) {
                output.append(integer).append(", ");
            }
            System.out.println("Magic left: " + output.substring(0, output.length() - 2));
        }
        if (bicycle != 0) {
            System.out.println("Bicycle: " + bicycle);
        }
        if (doll != 0) {
            System.out.println("Doll: " + doll);
        }
        if (bear != 0) {
            System.out.println("Teddy bear: " + bear);
        }
        if (train != 0) {
            System.out.println("Wooden train: " + train);
        }

    }

    private static int addPresent(int gift, ArrayDeque<Integer> magic, ArrayDeque<Integer> materials) {
        gift++;
        materials.pop();
        magic.poll();
        return gift;
    }

}
