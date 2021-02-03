package exams;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class PresentDelivery {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int presentsAvailable = Integer.parseInt(scanner.nextLine());
        int[] presents = new int[2];
        presents[0] = presentsAvailable;
        int fieldSize = Integer.parseInt(scanner.nextLine());

        char[][] field = new char[fieldSize][fieldSize];
        int[] currentPositions = new int[2];


        for (int r = 0; r < fieldSize; r++) {
            String[] line = scanner.nextLine().split(" ");
            for (int c = 0; c < line.length; c++) {
                if (line[c].equals("S")) {
                    currentPositions[0] = r;
                    currentPositions[1] = c;
                }
                field[r][c] = line[c].charAt(0);
            }
        }
        int[] nextPositions = new int[2];
        nextPositions[0] = currentPositions[0];
        nextPositions[1] = currentPositions[1];

        String direction = scanner.nextLine();
        while (!direction.equals("Christmas morning")) {
            switch (direction) {
                case "up":
                    if (isInRange(nextPositions[0]--, field)) {
                        moveSanta(currentPositions, nextPositions, field, presents);
                    }
                    break;
                case "down":
                    if (isInRange(nextPositions[0]++, field)) {
                        moveSanta(currentPositions, nextPositions, field, presents);
                    }
                    break;
                case "right":
                    if (isInRange(nextPositions[1]++, field)) {
                        moveSanta(currentPositions, nextPositions, field, presents);
                    }
                    break;
                case "left":
                    if (isInRange(nextPositions[1]--, field)) {
                        moveSanta(currentPositions, nextPositions, field, presents);
                    }
                    break;

            }
            if (presents[0] <= 0) {
                System.out.println("Santa ran out of presents!");
                break;
            }
            direction = scanner.nextLine();
        }

        int niceKidsLeft = 0;
        for (char[] chars : field) {
            for (char c : chars) {
                if (c == 'V') {
                    niceKidsLeft++;
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
        if (niceKidsLeft == 0) {
            System.out.printf("Good job, Santa! %d happy nice kid/s.", presents[1]);
        } else {
            System.out.printf("No presents for %d nice kid/s.", niceKidsLeft);
        }
    }

    private static void moveSanta(int[] currentPositions, int[] nextPositions, char[][] field, int[] presents) {
        if (field[nextPositions[0]][nextPositions[1]] == '-') {
            field[currentPositions[0]][currentPositions[1]] = '-';
            field[nextPositions[0]][nextPositions[1]] = 'S';
            currentPositions[0] = nextPositions[0];
            currentPositions[1] = nextPositions[1];
        } else if (field[nextPositions[0]][nextPositions[1]] == 'V') {
            presents[0]--;
            presents[1]++;
            field[currentPositions[0]][currentPositions[1]] = '-';
            field[nextPositions[0]][nextPositions[1]] = 'S';
            currentPositions[0] = nextPositions[0];
            currentPositions[1] = nextPositions[1];
        } else if (field[nextPositions[0]][nextPositions[1]] == 'X') {
            field[currentPositions[0]][currentPositions[1]] = '-';
            field[nextPositions[0]][nextPositions[1]] = 'S';
            currentPositions[0] = nextPositions[0];
            currentPositions[1] = nextPositions[1];
        } else if (field[nextPositions[0]][nextPositions[1]] == 'C') {
            if (field[nextPositions[0] - 1][nextPositions[1]] != '-' && presents[0] > 0) {
                field[nextPositions[0] - 1][nextPositions[0]] = '-';
                presents[0]--;
                presents[1]++;
            }
            if (field[nextPositions[0] + 1][nextPositions[1]] == 'V' && presents[0] > 0) {
                field[nextPositions[0] + 1][nextPositions[0]] = '-';
                presents[0]--;
                presents[1]++;
            }
            if (field[nextPositions[0]][nextPositions[1] - 1] != '-' && presents[0] > 0) {
                field[nextPositions[0]][nextPositions[0] - 1] = '-';
                presents[0]--;
                presents[1]++;
            }
            if (field[nextPositions[0]][nextPositions[1] + 1] != '-' && presents[0] > 0) {
                field[nextPositions[0]][nextPositions[0] + 1] = '-';
                presents[0]--;
                presents[1]++;
            }
            field[currentPositions[0]][currentPositions[1]] = '-';
            field[nextPositions[0]][nextPositions[1]] = 'S';

        }

    }

    private static boolean isInRange(int rangeIndex, char[][] field) {
        return rangeIndex >= 0 && rangeIndex < field.length;
    }
}
