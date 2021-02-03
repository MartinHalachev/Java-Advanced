package JavaAdvancedExam;

import java.util.Scanner;

public class BookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        int matrixSize = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[matrixSize][matrixSize];
        int[] position = new int[2];
        for (int r = 0; r < matrixSize; r++) {
            String line = scanner.nextLine();
            if (line.contains("P")) {
                position[0] = r;
                position[1] = line.indexOf("P");
            }
            matrix[r] = line.toCharArray();
        }
        int[] nextPosition = new int[2];
        nextPosition[0] = position[0];
        nextPosition[1] = position[1];

        String directions = scanner.nextLine();
        while (!directions.equals("end")) {
            switch (directions) {
                case "up":
                    nextPosition[0]--;
                    if (isInRange(nextPosition[0], matrix)) {
                        word = movePlayer(position, nextPosition, matrix, word);
                    } else {
                        nextPosition[0]++;
                        word = deleteLastLetter(word);
                    }
                    break;
                case "down":
                    nextPosition[0]++;
                    if (isInRange(nextPosition[0], matrix)) {
                        word = movePlayer(position, nextPosition, matrix, word);
                    } else {
                        nextPosition[0]--;
                        word = deleteLastLetter(word);
                    }
                    break;
                case "right":
                    nextPosition[1]++;
                    if (isInRange(nextPosition[1], matrix)) {
                        word = movePlayer(position, nextPosition, matrix, word);
                    } else {
                        nextPosition[1]--;
                        word = deleteLastLetter(word);
                    }
                    break;
                case "left":
                    nextPosition[1]--;
                    if (isInRange(nextPosition[1], matrix)) {
                        word = movePlayer(position, nextPosition, matrix, word);
                    } else {
                        nextPosition[1]++;
                        word = deleteLastLetter(word);
                    }
                    break;

            }
            directions = scanner.nextLine();
        }

        System.out.println(word);
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static String movePlayer(int[] position, int[] nextPosition, char[][] matrix, String word) {
        if (matrix[nextPosition[0]][nextPosition[1]] != '-') {
            word += matrix[nextPosition[0]][nextPosition[1]];
        }
        matrix[position[0]][position[1]] = '-';
        position[0] = nextPosition[0];
        position[1] = nextPosition[1];
        matrix[position[0]][position[1]] = 'P';
        return word;
    }

    private static String deleteLastLetter(String word) {
        return word.substring(0, word.length() - 1);
    }

    private static boolean isInRange(int position, char[][] matrix) {
        return position >= 0 && position < matrix.length;
    }
}
