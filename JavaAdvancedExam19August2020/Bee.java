import java.util.Scanner;

public class Bee {
    private static boolean alive = true;
    private static int flowers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] beeField = new char[size][size];
        int[] position = new int[2];
        for (int r = 0; r < size; r++) {
            String line = scanner.nextLine();
            if (line.contains("B")) {
                position[0] = r;
                position[1] = line.indexOf("B");
            }
            beeField[r] = line.toCharArray();
        }
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1];
        while (alive) {
            String direction = scanner.nextLine();
            if (direction.equals("End")) {
                break;
            }
            switch (direction) {
                case "up":
                    nextPosition[0]--;
                    if (isInRange(nextPosition[0], size)) {
                        move(position, nextPosition, beeField, direction);
                    } else {
                        beeField[position[0]][position[1]] = '.';
                        System.out.println("The bee got lost!");
                        break;
                    }
                    break;
                case "down":
                    nextPosition[0]++;
                    if (isInRange(nextPosition[0], size)) {
                        move(position, nextPosition, beeField, direction);
                    } else {
                        beeField[position[0]][position[1]] = '.';
                        System.out.println("The bee got lost!");
                        break;
                    }
                    break;
                case "right":
                    nextPosition[1]++;
                    if (isInRange(nextPosition[1], size)) {
                        move(position, nextPosition, beeField, direction);
                    } else {
                        beeField[position[0]][position[1]] = '.';
                        System.out.println("The bee got lost!");
                        break;
                    }
                    break;
                case "left":
                    nextPosition[1]--;
                    if (isInRange(nextPosition[1], size)) {
                        move(position, nextPosition, beeField, direction);
                    } else {
                        beeField[position[0]][position[1]] = '.';
                        System.out.println("The bee got lost!");
                        break;
                    }
                    break;
            }
        }
        if (flowers >= 5) {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", flowers);
        } else {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", 5 - flowers);
        }
        for (char[] chars : beeField) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static void move(int[] position, int[] nextPosition, char[][] beeField, String direction) {
        if (beeField[nextPosition[0]][nextPosition[1]] == 'f') {
            flowers++;
            beeField[position[0]][position[1]] = '.';
            beeField[nextPosition[0]][nextPosition[1]] = 'B';
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        } else if (beeField[nextPosition[0]][nextPosition[1]] == 'O') {
            beeField[position[0]][position[1]] = '.';
            beeField[nextPosition[0]][nextPosition[1]] = '.';
            switch (direction) {
                case "up":
                    nextPosition[0]--;
                    move(position, nextPosition, beeField, direction);
                    break;
                case "down":
                    nextPosition[0]++;
                    move(position, nextPosition, beeField, direction);
                    break;
                case "right":
                    nextPosition[1]++;
                    move(position, nextPosition, beeField, direction);
                    break;
                case "left":
                    nextPosition[1]--;
                    move(position, nextPosition, beeField, direction);
                    break;
            }
        } else if (beeField[nextPosition[0]][nextPosition[1]] == '.') {
            beeField[position[0]][position[1]] = '.';
            beeField[nextPosition[0]][nextPosition[1]] = 'B';
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        }
    }


    private static boolean isInRange(int position, int size) {
        if (position >= 0 && position < size) {
            return true;
        } else {
            alive = false;
            return false;
        }

    }
}
