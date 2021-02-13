import java.util.Scanner;

public class Selling {
    public static int money = 0;
    ;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        int[] position = new int[2];
        int[] pillar = new int[4];
        for (int r = 0; r < size; r++) {
            String line = scanner.nextLine();
            if (line.contains("S")) {
                position[0] = r;
                position[1] = line.indexOf("S");
            }
            if (line.contains("O") && pillar[0] == 0 && pillar[1] == 0) {
                pillar[0] = r;
                pillar[1] = line.indexOf("O");
            }
            if (line.contains("O") && (pillar[0] != 0 || pillar[1] != 0)) {
                pillar[2] = r;
                pillar[3] = line.indexOf("O");
            }
            matrix[r] = line.toCharArray();
        }
        int[] nextPosition = new int[2];

        nextPosition[0] = position[0];
        nextPosition[1] = position[1];

        while (money < 50) {
            String direction = scanner.nextLine();
            if ("up".equals(direction)) {
                nextPosition[0]--;
                if (isInRange(nextPosition[0], size)) {
                    move(position, nextPosition, matrix, pillar);
                } else {
                    matrix[position[0]][position[1]] = '-';
                    break;

                }
            } else if ("down".equals(direction)) {
                nextPosition[0]++;
                if (isInRange(nextPosition[0], size)) {
                    move(position, nextPosition, matrix, pillar);
                } else {
                    matrix[position[0]][position[1]] = '-';
                    break;

                }
            } else if ("right".equals(direction)) {
                nextPosition[1]++;
                if (isInRange(nextPosition[1], size)) {
                    move(position, nextPosition, matrix, pillar);
                } else {
                    matrix[position[0]][position[1]] = '-';
                    break;
                }
            } else if ("left".equals(direction)) {
                nextPosition[1]--;
                if (isInRange(nextPosition[1], size)) {
                    move(position, nextPosition, matrix, pillar);
                } else {
                    matrix[position[0]][position[1]] = '-';
                    break;
                }
            }
        }
        if (money >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        } else {
            System.out.println("Bad news, you are out of the bakery.");
        }
        System.out.println("Money: " + money);
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    private static void move(int[] position, int[] nextPosition, char[][] matrix, int[] pillar) {
        matrix[position[0]][position[1]] = '-';
        if (Character.isDigit(matrix[nextPosition[0]][nextPosition[1]])) {
            int number = Integer.parseInt(String.valueOf(matrix[nextPosition[0]][nextPosition[1]]));
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
            money += number;
        } else if (matrix[nextPosition[0]][nextPosition[1]] == 'O') {
            if (nextPosition[0] == pillar[0] && nextPosition[1] == pillar[1]) {
                matrix[nextPosition[0]][nextPosition[1]] = '-';
                nextPosition[0] = pillar[2];
                nextPosition[1] = pillar[3];
                matrix[nextPosition[0]][nextPosition[1]] = 'S';
            } else {
                matrix[nextPosition[0]][nextPosition[1]] = '-';
                nextPosition[0] = pillar[0];
                nextPosition[1] = pillar[1];
                matrix[nextPosition[0]][nextPosition[1]] = 'S';
            }
        }
        position[0] = nextPosition[0];
        position[1] = nextPosition[1];
        matrix[position[0]][position[1]] = 'S';

    }

    private static boolean isInRange(int number, int size) {
        return number >= 0 && number < size;
    }
}
