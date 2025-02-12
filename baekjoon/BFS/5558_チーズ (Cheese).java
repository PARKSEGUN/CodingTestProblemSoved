import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int H, W, N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int sx, sy; // 시작 좌표
    private static Map<Integer, int[]> cheeseLocation = new HashMap<>(); // 치즈 좌표 저장

    public static void main(String[] args) throws IOException {
        setting();
        System.out.println(solve());
    }

    private static int solve() {
        int totalTime = 0;
        for (int cheese = 1; cheese <= N; cheese++) {
            int[] target = cheeseLocation.get(cheese); // 목표 치즈 좌표 가져오기
            totalTime += bfs(sx, sy, target[0], target[1]);
            sx = target[0]; // 다음 시작점 갱신
            sy = target[1];

            // visited 초기화 (매번 초기화하는 방식이 아니라 queue 내부에서만 사용)
            for (int i = 0; i < H; i++) Arrays.fill(visited[i], false);
        }
        return totalTime;
    }

    private static int bfs(int sx, int sy, int tx, int ty) {
        int[] dix = {-1, 0, 1, 0};
        int[] diy = {0, 1, 0, -1};

        visited[sx][sy] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy, 0});

        System.out.println();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], cnt = cur[2];

            System.out.println(Arrays.toString(cur));
            if (x == tx && y == ty) return cnt; // 목표 치즈에 도착

            for (int i = 0; i < 4; i++) {
                int dx = x + dix[i];
                int dy = y + diy[i];

                if (dx < 0 || dx >= H || dy < 0 || dy >= W || visited[dx][dy] || map[dx][dy] == 'X')
                    continue;

                visited[dx][dy] = true;
                queue.add(new int[]{dx, dy, cnt + 1});
            }
        }
        return -1;
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            String inputString = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = inputString.charAt(j);
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
                if (map[i][j] >= '1' && map[i][j] <= '9') {
                    cheeseLocation.put(map[i][j] - '0', new int[]{i, j});
                }
            }
        }
    }
}
