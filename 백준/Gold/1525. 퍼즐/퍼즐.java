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
        System.out.println(bfs(x * 3 + y, map));
    }


    private static int bfs(int startZeroIdx, String map) {
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<int[]> queue2 = new ArrayDeque<>(); //[현재 횟수 cnt, 0이 존재하는 인덱스]
        queue1.add(map);
        queue2.add(new int[]{0, startZeroIdx});
        visitedSet.add(map);
        while (!queue1.isEmpty()) {
            String curMap = queue1.poll();
            int[] curTmp = queue2.poll();
            int cnt = curTmp[0];
            int zeroIDx = curTmp[1];
            if (curMap.equals(targetMap)) {
                return cnt;
            }
            //0을 찾아내는 곳에서 *9 이 추가되기에 오버헤드
            //0의 위치를 큐에 넣어서 관리하는 식으로 변경
            int[] curPoint = {zeroIDx / 3, zeroIDx % 3};
            for (int i = 0; i < 4; i++) {
                int dx = curPoint[0] + dir[i][0];
                int dy = curPoint[1] + dir[i][1];
                if (dx < 0 || dx >= 3 || dy < 0 || dy >= 3) continue;
                //자리 변경하는 코드가 오버헤드가 큼
//                for (int j = 0; j < curMap.length(); j++) {
//                    if (j / 3 == curPoint[0] && j % 3 == curPoint[1]) sb.append(curMap.charAt(dx * 3 + dy));
//                    else if (j / 3 == dx && j % 3 == dy) sb.append(curMap.charAt(curPoint[0] * 3 + curPoint[1]));
//                    else sb.append(curMap.charAt(j));
//                }
                //수정한 변경하는 코드
                char[] charArr = curMap.toCharArray();
                char tmp = charArr[curPoint[0] * 3 + curPoint[1]];
                charArr[curPoint[0] * 3 + curPoint[1]] = charArr[dx * 3 + dy];
                charArr[dx * 3 + dy] = tmp;
                String nextMap = new String(charArr);
                if (!visitedSet.contains(nextMap)) {
                    visitedSet.add(nextMap);
                    queue1.add(nextMap);
                    queue2.add(new int[]{cnt + 1, dx * 3 + dy});
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
