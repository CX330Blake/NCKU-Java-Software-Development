import java.util.*;

public class CarFleet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<String>> cases = new ArrayList<>();
        List<String> curr = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                if (!curr.isEmpty()) {
                    cases.add(curr);
                    curr = new ArrayList<>();
                }
            } else {
                curr.add(line);
            }
        }
        if (!curr.isEmpty())
            cases.add(curr);
        sc.close();

        int caseNum = 1;
        for (List<String> test : cases) {
            int target = Integer.parseInt(test.get(0));
            int[] pos = Arrays.stream(test.get(1).split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] speed = Arrays.stream(test.get(2).split("\\s+")).mapToInt(Integer::parseInt).toArray();

            int fleets = countFleets(target, pos, speed);
            System.out.printf("Case %d: %d.%n", caseNum++, fleets);
        }
    }

    private static int countFleets(int target, int[] pos, int[] speed) {
        int n = pos.length;
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(pos[i], (double) (target - pos[i]) / speed[i]);
        }
        Arrays.sort(cars, Comparator.comparingInt(c -> -c.position));

        int fleets = 0;
        double slowestTime = -1;
        for (Car c : cars) {
            if (c.time > slowestTime) {
                fleets++;
                slowestTime = c.time;
            }
        }
        return fleets;
    }

    private static class Car {
        int position;
        double time;

        Car(int p, double t) {
            position = p;
            time = t;
        }
    }
}
