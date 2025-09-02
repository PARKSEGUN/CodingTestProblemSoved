import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    static int N, M;
    static boolean[][] wallMap;
    static boolean[][] visitedMap;
    static int sx, sy, sd;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int answer;

    public static void main(String[] args) throws IOException {
        inputSetting();

        while (true) {
            if (!visitedMap[sx][sy]) answer++;
            visitedMap[sx][sy] = true;
            if (isAroundClean(sx, sy)) {
                if (wallMap[sx + dir[(sd + 2) % 4][0]][sy + dir[(sd + 2) % 4][1]]) {
                    break;
                } else {
                    sx = sx + dir[(sd + 2) % 4][0];
                    sy = sy + dir[(sd + 2) % 4][1];
                }
            } else {
                sd = (sd + 3) % 4;
                int nx = sx + dir[sd][0];
                int ny = sy + dir[sd][1];
                if (!wallMap[nx][ny] && !visitedMap[nx][ny]) {
                    sx = nx;
                    sy = ny;
                }
            }

        }
        System.out.println(answer);
    }

    static private boolean isAroundClean(int sx, int sy) {
        for (int i = 0; i < 4; i++) {
            int nx = sx + dir[i][0];
            int ny = sy + dir[i][1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (wallMap[nx][ny]) continue;

            if (!visitedMap[nx][ny]) return false;
        }
        return true;

    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        wallMap = new boolean[N][M];
        visitedMap = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        sd = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                wallMap[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

    }
}