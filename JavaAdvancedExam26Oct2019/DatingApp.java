package JavaAdvancedExam;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class DatingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] maleInput = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] femaleInput = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> male = new ArrayDeque<>();
        ArrayDeque<Integer> female = new ArrayDeque<>();
        for (int maleIn : maleInput) {
            if (maleIn > 0) {
                male.push(maleIn);
            }
        }
        for (int femaleIn : femaleInput) {
            if (femaleIn > 0) {
                female.offer(femaleIn);
            }
        }

        int matches = 0;

        while (!male.isEmpty() && !female.isEmpty()) {
            if (male.peek() <= 0) {
                male.pop();
            } else if (male.peek().equals(female.peek()) && male.peek() % 25 != 0 && female.peek() % 25 != 0) {
                matches++;
                male.pop();
                female.pop();
            } else if (male.peek() % 25 == 0) {
                if (male.size() > 1) {
                    male.pop();
                    male.pop();
                } else {
                    male.pop();
                }
            } else if (female.peek() % 25 == 0) {
                if (female.size() > 1) {
                    female.poll();
                    female.poll();
                } else {
                    female.poll();
                }
            } else {
                female.poll();
                male.push(male.pop() - 2);
            }
        }
        System.out.println("Matches: " + matches);
        if (male.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            StringBuilder output = new StringBuilder();
            for (Integer integer : male) {
                output.append(integer).append(", ");
            }
            System.out.println("Males left: " + output.substring(0, output.length() - 2));
        }
        if (female.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            StringBuilder output = new StringBuilder();
            for (Integer integer : female) {
                output.append(integer).append(", ");
            }
            System.out.println("Females left: " + output.substring(0, output.length() - 2));
        }
    }
}
