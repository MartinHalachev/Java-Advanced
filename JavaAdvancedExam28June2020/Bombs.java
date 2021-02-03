import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstInput = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        int[] secondInput = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> effects = new ArrayDeque<>();
        ArrayDeque<Integer> casings = new ArrayDeque<>();

        int daturaBombs = 0;
        int cherryBombs = 0;
        int smokeDecoyBombs = 0;

        for (int number : firstInput) {
            effects.offer(number);
        }

        for (int number : secondInput) {
            casings.push(number);
        }

        while (!effects.isEmpty() && !casings.isEmpty()) {
            int sum = effects.peek() + casings.peek();
            switch (sum) {
                case 40:
                    daturaBombs++;
                    effects.poll();
                    casings.pop();
                    break;
                case 60:
                    cherryBombs++;
                    effects.poll();
                    casings.pop();
                    break;
                case 120:
                    smokeDecoyBombs++;
                    effects.poll();
                    casings.pop();
                    break;
                default:
                    casings.push(casings.pop() - 5);
                    break;
            }
            if (threeOfEachBombs(daturaBombs, cherryBombs, smokeDecoyBombs)) {
                break;
            }
        }


        if (threeOfEachBombs(daturaBombs, cherryBombs, smokeDecoyBombs)) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }
        if (effects.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            StringBuilder output = new StringBuilder();
            for (Integer effect : effects) {
                output.append(effect).append(", ");
            }
            System.out.println("Bomb Effects: " + output.substring(0, output.length() - 2));
        }

        if (casings.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            StringBuilder output = new StringBuilder();
            for (Integer casing : casings) {
                output.append(casing).append(", ");
            }
            System.out.println("Bomb Casings: " + output.substring(0, output.length() - 2));
        }
        System.out.println("Cherry Bombs: " + cherryBombs);
        System.out.println("Datura Bombs: " + daturaBombs);
        System.out.println("Smoke Decoy Bombs: " + smokeDecoyBombs);
    }

    private static boolean threeOfEachBombs(int daturaBombs, int cherryBombs, int smokeDecoyBombs) {
        return daturaBombs >= 3 && cherryBombs >= 3 && smokeDecoyBombs >= 3;
    }
}
