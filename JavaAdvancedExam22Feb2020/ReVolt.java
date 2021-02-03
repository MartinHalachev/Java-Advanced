import java.util.Scanner;

public class ReVolt {
    private static boolean win = false;
    private static String direction;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());

        int steps = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[matrixSize][matrixSize];
        int[] position = new int[2];
        for (int r = 0; r < matrixSize; r++) {
            String line = scanner.nextLine();
            if (line.contains("f")) {
                position[0] = r;
                position[1] = line.indexOf("f");
            }
            matrix[r] = line.toCharArray();
        }
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1];
        while (steps-- != 0) {
            if (win) {
                break;
            }
            direction = scanner.nextLine();
            switch (direction) {
                case "up":
                    nextPosition[0]--;
                    if (!isInRange(nextPosition[0], matrixSize)) {
                        nextPosition[0] = matrix.length - 1;
                    }
                    move(position, nextPosition, matrix);
                    break;
                case "down":
                    nextPosition[0]++;
                    if (!isInRange(nextPosition[0], matrixSize)) {
                        nextPosition[0] = 0;
                    }
                    move(position, nextPosition, matrix);
                    break;
                case "left":
                    nextPosition[1]--;
                    if (!isInRange(nextPosition[1], matrixSize)) {
                        nextPosition[1] = matrix.length - 1;

                    }
                    move(position, nextPosition, matrix);
                    break;
                case "right":
                    nextPosition[1]++;
                    if (!isInRange(nextPosition[1], matrixSize)) {
                        nextPosition[1] = 0;
                    }
                    move(position, nextPosition, matrix);
                    break;

            }
        }
        if (!win) {
            System.out.println("Player lost!");
        }
        for (
                char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    private static void move(int[] position, int[] nextPosition, char[][] matrix) {

        if (matrix[nextPosition[0]][nextPosition[1]] == '-') {
            matrix[position[0]][position[1]] = '-';
            matrix[nextPosition[0]][nextPosition[1]] = 'f';
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        } else if (matrix[nextPosition[0]][nextPosition[1]] == 'B') {
            if (direction.equals("up")) {
                nextPosition[0]--;
                if (!isInRange(nextPosition[0], matrix.length)) {
                    nextPosition[0] = matrix.length - 1;
                }
                move(position, nextPosition, matrix);
            } else if (direction.equals("down")) {
                nextPosition[0]++;
                if (!isInRange(nextPosition[0], matrix.length)) {
                    nextPosition[0] = 0;
                }
                move(position, nextPosition, matrix);
            } else if (direction.equals("left")) {
                nextPosition[1]--;
                if (!isInRange(nextPosition[1], matrix.length)) {
                    nextPosition[1] = matrix.length - 1;
                }
                move(position, nextPosition, matrix);
            } else if (direction.equals("right")) {
                nextPosition[1]++;
                if (!isInRange(nextPosition[1], matrix.length)) {
                    nextPosition[1] = 0;
                }
                move(position, nextPosition, matrix);
            }

        } else if (matrix[nextPosition[0]][nextPosition[1]] == 'F') {
            System.out.println("Player won!");
            win = true;
            matrix[position[0]][position[1]] = '-';
            matrix[nextPosition[0]][nextPosition[1]] = 'f';
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
        } else {
            switch (direction) {
                case "up":
                    nextPosition[0]++;
                    break;
                case "down":
                    nextPosition[0]--;
                    break;
                case "right":
                    nextPosition[1]--;
                    break;
                case "left":
                    nextPosition[1]++;
                    break;
            }
        }
    }

    private static boolean isInRange(int number, int range) {
        return number >= 0 && number < range;
    }
}
