import java.util.Scanner;

public class Snake {
    private static int foodQuantity = 0;
    private static boolean alive = true;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] snakeTerritory = new char[size][size];
        int[] position = new int[2];
        int[] firstBurrow = new int[2];
        int[] secondBurrow = new int[2];
        for (int r = 0; r < size; r++) {
            String line = scanner.nextLine();
            if (line.contains("S")) {
                position[0] = r;
                position[1] = line.indexOf("S");
            }
            if (line.contains("B") && firstBurrow[0] == 0 && firstBurrow[1] == 0) {
                firstBurrow[0] = r;
                firstBurrow[1] = line.indexOf("B");
            } else if (line.contains("B")) {
                secondBurrow[0] = r;
                secondBurrow[1] = line.indexOf("B");
            }
            snakeTerritory[r] = line.toCharArray();
        }
        int[] nextPosition = new int[2];

        nextPosition[0] = position[0];
        nextPosition[1] = position[1];

        while (foodQuantity != 10 && alive) {
            String directions = scanner.nextLine();
            switch (directions) {
                case "up":
                    nextPosition[0]--;
                    if (isInRange(nextPosition[0], size, position, snakeTerritory)) {
                        move(position, nextPosition, snakeTerritory, firstBurrow, secondBurrow);
                    }
                    break;
                case "down":
                    nextPosition[0]++;
                    if (isInRange(nextPosition[0], size, position, snakeTerritory)) {
                        move(position, nextPosition, snakeTerritory, firstBurrow, secondBurrow);
                    }
                    break;
                case "right":
                    nextPosition[1]++;
                    if (isInRange(nextPosition[1], size, position, snakeTerritory)) {
                        move(position, nextPosition, snakeTerritory, firstBurrow, secondBurrow);
                    }
                    break;
                case "left":
                    nextPosition[1]--;
                    if (isInRange(nextPosition[1], size, position, snakeTerritory)) {
                        move(position, nextPosition, snakeTerritory, firstBurrow, secondBurrow);
                    }
                    break;

            }
        }
        if (foodQuantity >= 10) {
            System.out.println("You won! You fed the snake.");
        }
        System.out.println("Food eaten: " + foodQuantity);

        for (char[] chars : snakeTerritory) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void move(int[] position, int[] nextPosition, char[][] snakeTerritory, int[] firstBurrow, int[] secondBurrow) {
        if (snakeTerritory[nextPosition[0]][nextPosition[1]] == '-') {
            snakeTerritory[position[0]][position[1]] = '.';
            snakeTerritory[nextPosition[0]][nextPosition[1]] = 'S';
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        } else if (snakeTerritory[nextPosition[0]][nextPosition[1]] == '*') {
            foodQuantity++;
            snakeTerritory[position[0]][position[1]] = '.';
            snakeTerritory[nextPosition[0]][nextPosition[1]] = 'S';
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        } else if (snakeTerritory[nextPosition[0]][nextPosition[1]] == 'B') {
            snakeTerritory[position[0]][position[1]] = '.';
            snakeTerritory[nextPosition[0]][nextPosition[1]] = '.';
            if (nextPosition[0] == firstBurrow[0] && nextPosition[1] == firstBurrow[1]) {
                nextPosition[0] = secondBurrow[0];
                nextPosition[1] = secondBurrow[1];
                snakeTerritory[nextPosition[0]][nextPosition[1]] = 'S';
                position[0] = nextPosition[0];
                position[1] = nextPosition[1];
            } else if (nextPosition[0] == secondBurrow[0] && nextPosition[1] == secondBurrow[1]) {
                nextPosition[0] = firstBurrow[0];
                nextPosition[1] = firstBurrow[1];
                snakeTerritory[nextPosition[0]][nextPosition[1]] = 'S';
                position[0] = nextPosition[0];
                position[1] = nextPosition[1];
            }
        }
    }

    private static void markLastPosition(int[] position, char[][] snakeTerritory) {
        snakeTerritory[position[0]][position[1]] = '.';
    }

    private static boolean isInRange(int number, int size, int[] position, char[][] snakeTerritory) {
        if (number >= 0 && number < size) {
            return true;
        } else {
            markLastPosition(position, snakeTerritory);
            System.out.println("Game over!");
            alive = false;
            return false;
        }
    }
}
