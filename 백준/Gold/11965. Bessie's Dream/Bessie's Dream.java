import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        setting();
        System.out.println(getGameResult());
    }

    private static int getGameResult() {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][2][4]; //[x좌표, y좌표, 이동 횟수, 오렌지 냄새 획득 여부(0,1), 해당 좌표를 들어온 방향]
        queue.add(new int[]{0, 0, 0, 0, -1}); //[x좌표, y좌표, 이동 횟수, 오렌지 냄새 획득 여부(0,1), 보라색 탑승 여부겸 방향(0~3)]
        visited[0][0][0][1] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];
            int orange = cur[3];
            int purpleDir = cur[4];
//            System.out.println("cur = " + Arrays.toString(cur));
            if (x == N - 1 && y == M - 1) return cnt;

            /*
             *  보라색 경우에 따른 다음 좌표 구간
             * */
            int start = -1, end = -1;
            if (purpleDir == -1) {
                start = 0;
                end = 4;
            } else {
                start = purpleDir;
                end = purpleDir + 1;
            }

            for (int i = start; i < end; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];
                if (dx < 0 || dx >= N || dy < 0 || dy >= M) {
                    if (purpleDir != -1) queue.add(new int[]{x, y, cnt, orange, -1});
                    continue;
                }
                if (visited[dx][dy][orange][i]) {
                    continue;
                }
                if ((map[dx][dy] == 0) || (map[dx][dy] == 3 && orange == 0)) {
                    if (purpleDir != -1) queue.add(new int[]{x, y, cnt, orange, -1});
                    continue;
                }
                if (map[dx][dy] == 2) {
                    visited[dx][dy][1][i] = true;
                    queue.add(new int[]{dx, dy, cnt + 1, 1, -1});
                } else if (map[dx][dy] == 4) {
                    visited[dx][dy][orange][i] = true;
                    queue.add(new int[]{dx, dy, cnt + 1, 0, i});
                } else {
                    visited[dx][dy][orange][i] = true;
                    queue.add(new int[]{dx, dy, cnt + 1, orange, -1});
                }
            }
        }
        return -1;
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
