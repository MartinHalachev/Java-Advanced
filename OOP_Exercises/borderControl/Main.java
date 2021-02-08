package borderControl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] readIds = scanner.nextLine().split(" ");

        HashSet<String> ids = new HashSet<>();

        while (!readIds[0].equals("End")) {
            if (readIds.length == 3) {
                Citizen citizen = new Citizen(readIds[0], Integer.parseInt(readIds[1]), readIds[2]);
                ids.add(citizen.getId());
            } else {
                Robot robot = new Robot(readIds[0], readIds[1]);
                ids.add(robot.getId());
            }
            readIds = scanner.nextLine().split(" ");
        }
        String search = scanner.nextLine();
        for (String id : ids) {
            if (id.contains(search)) {
                System.out.println(id);
            }
        }
    }
}
