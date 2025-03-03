import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;

    private static int[] moveSeq;
    private static char[][] map;

    private static Queue<int[]> opponents = new ArrayDeque<>();
    private static int mx, my;
    private static int[][] dir = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        setting();
        if (!isGamePossible()) System.out.println("kraj " + answer);
        else printMap();
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isGamePossible() {
        for (int i = 0; i < moveSeq.length; i++) {
            answer++;
            if (!isMovePossible(moveSeq[i]) || !isOpponentsPossible()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOpponentsPossible() {
        Map<String, Integer> nextPosition = new HashMap<>();
        int size = opponents.size();
        for (int i = 0; i < size; i++) {
            int[] cur = opponents.poll();
            int ox = cur[0];
            int oy = cur[1];
            int nextDir = -1;
            int sum = Integer.MAX_VALUE;
            for (int j = 0; j < dir.length; j++) {
                int dx = ox + dir[j][0];
                int dy = oy + dir[j][1];
                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
                int distance = Math.abs(mx - dx) + Math.abs(my - dy);
//                System.out.println(mx + " " + my + " | " + dx + " " + dy + " | " + distance + " " + j);
                if (sum > distance) {
                    sum = distance;
                    nextDir = j;
                }
            }
//            System.out.println();
            int dx = ox + dir[nextDir][0];
            int dy = oy + dir[nextDir][1];
//            System.out.println(dx + " " + dy);
            map[ox][oy] = '.';
            if (dx == mx && dy == my) return false;
            sb.append(dx).append(",").append(dy);
            nextPosition.put(sb.toString(), nextPosition.getOrDefault(sb.toString(), 0) + 1);
            sb.setLength(0);
        }
//        System.out.println("size : " + nextPosition.keySet().size());
        for (String cur : nextPosition.keySet()) {
            if (nextPosition.get(cur) == 1) {
                String[] splits = cur.split(",");
                int x = Integer.parseInt(splits[0]);
                int y = Integer.parseInt(splits[1]);
//                System.out.println(x + " " + y);
                opponents.add(new int[]{x, y});
                map[x][y] = 'R';
            }
        }
//        printMap();
        return true;
    }

    private static boolean isMovePossible(int num) {
        int dx = mx + dir[num][0];
        int dy = my + dir[num][1];
        if (map[dx][dy] == 'R') return false;
        map[mx][my] = '.';
        map[dx][dy] = 'I';
        mx = dx;
        my = dy;
//        printMap();
        return true;
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String mapInput = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = mapInput.charAt(j);
                if (map[i][j] == 'I') {
                    my = j;
                    mx = i;
                } else if (map[i][j] == 'R') opponents.add(new int[]{i, j});
            }
        }
        String moveInput = br.readLine();
        moveSeq = new int[moveInput.length()];
        for (int i = 0; i < moveSeq.length; i++) {
            moveSeq[i] = (moveInput.charAt(i) - '0') - 1;
        }
    }

}
