import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String targetMap = "123456780";
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int answer = Integer.MAX_VALUE;

    private static Set<String> visitedSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
                if (sb.charAt(sb.length() - 1) == '0') {
                    x = i;
                    y = j;
                }
            }
        }
        String map = sb.toString();
        sb.setLength(0);
        System.out.println(bfs(map));
    }


    private static int bfs(String map) {
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<Integer> queue2 = new ArrayDeque<>();
        queue1.add(map);
        queue2.add(0);
        visitedSet.add(map);
        while (!queue1.isEmpty()) {
            String curMap = queue1.poll();
            int cnt = queue2.poll();
            if (curMap.equals(targetMap)) {
                return cnt;
            }
            int[] curPoint = findZero(curMap);
            for (int i = 0; i < 4; i++) {
                int dx = curPoint[0] + dir[i][0];
                int dy = curPoint[1] + dir[i][1];
                if (dx < 0 || dx >= 3 || dy < 0 || dy >= 3) continue;
                //자리변경
                for (int j = 0; j < curMap.length(); j++) {
                    if (j / 3 == curPoint[0] && j % 3 == curPoint[1]) sb.append(curMap.charAt(dx * 3 + dy));
                    else if (j / 3 == dx && j % 3 == dy) sb.append(curMap.charAt(curPoint[0] * 3 + curPoint[1]));
                    else sb.append(curMap.charAt(j));
                }
                String tmp = sb.toString();
                sb.setLength(0);
                if (!visitedSet.contains(tmp)) {
                    visitedSet.add(tmp);
                    queue1.add(tmp);
                    queue2.add(cnt + 1);
                }
            }
        }
        return -1;
    }

    private static int[] findZero(String map) {
        for (int i = 0; i < map.length(); i++) {
            if (map.charAt(i) == '0') return new int[]{i / 3, i % 3};
        }
        return null;
    }

}
