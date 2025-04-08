import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class LinkedListCycleDetect {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodesCnt = Integer.parseInt(sc.nextLine().trim());
        int headId = 0;
        Set<Integer> visited = new HashSet<>();

        // Init the map
        Map<Integer, Integer> map = new java.util.HashMap<>();
        for (int line = 1; line <= nodesCnt; line++) {
            String[] input = sc.nextLine().trim().split(" ");
            int id = Integer.parseInt(input[0]);
            int next = Integer.parseInt(input[1]);

            if (line == 1) {
                headId = id;
            }

            map.put(id, next);
        }
        sc.close();

        // Traverse the linked list
        int currId = headId;
        boolean hasCycle = false;
        while (map.containsKey(currId)) {
            if (visited.contains(currId)) {
                hasCycle = true;
                break;
            }
            visited.add(currId);
            currId = map.get(currId);
        }

        if (!hasCycle) {
            System.out.println("-1");
            return;
        }

        // After found a loop, find the min id of the loop
        List<Integer> loopIds = new ArrayList<Integer>();
        loopIds.add(currId);
        int loopStartId = currId;
        currId = map.get(currId); // currId = currId.next
        while (currId != loopStartId) {
            loopIds.add(currId);
            currId = map.get(currId);
        }

        int min = loopIds.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);
        System.out.println(min);
    }
}
