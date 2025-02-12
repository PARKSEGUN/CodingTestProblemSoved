import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;

    private static int[][][] boomerang = {{{0, -1}, {0, 0}, {1, 0}}, {{0, -1}, {0, 0}, {-1, 0}}, {{-1, 0}, {0, 0}, {0, 1}}, {{0, 1}, {0, 0}, {1, 0}}};

    private static int answer;

    public static void main(String[] args) throws IOException {
        setting();
        dfs(0, 0, 0);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int sum) {
        if (x == N - 1 && y == M) {
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print((visited[i][j]) ? 9 + " " : 0 + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            answer = Math.max(answer, sum);
            return;
        }
        x += y / M;
        y %= M;
//        System.out.println(x + " " + y + " " + sum);

        for (int i = 0; i < 4; i++) {
            int bx1 = x + boomerang[i][0][0];
            int by1 = y + boomerang[i][0][1];
            int bx2 = x + boomerang[i][1][0];
            int by2 = y + boomerang[i][1][1];
            int bx3 = x + boomerang[i][2][0];
            int by3 = y + boomerang[i][2][1];
            if (bx1 < 0 || bx1 >= N || by1 < 0 || by1 >= M || bx2 < 0 || bx2 >= N || by2 < 0 || by2 >= M || bx3 < 0 || bx3 >= N || by3 < 0 || by3 >= M)
                continue;
            if (visited[bx1][by1] || visited[bx2][by2] || visited[bx3][by3]) continue;
            visited[bx1][by1] = visited[bx2][by2] = visited[bx3][by3] = true;
            int value = getValue(bx1, by1, bx2, by2, bx3, by3);
            dfs(x, y + 1, sum + value);
            visited[bx1][by1] = visited[bx2][by2] = visited[bx3][by3] = false;
        }
        dfs(x, y + 1, sum);
    }

    private static int getValue(int bx1, int by1, int bx2, int by2, int bx3, int by3) {
        return map[bx1][by1] + map[bx2][by2] * 2 + map[bx3][by3];
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
