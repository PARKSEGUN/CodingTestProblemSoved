import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M, T;
    private static char[][] bombMap;
    private static int[][] timeMap;

    public static void main(String[] args) throws IOException {
        setting();


    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        bombMap = new char[N][M];
        timeMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                bombMap[i][j] = tmp.charAt(j);
                if (bombMap[i][j] == 'O') timeMap[i][j] = 0;
                else timeMap[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 2; i <= T; i++) {
            if (i % 2 == 0) insertBomb(i);
            else fireBomb(i);
        }
        printMaps();

    }

    private static void printMaps() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(bombMap[i][j]);
            }
            System.out.println();
        }
    }

    private static void fireBomb(int curTime) {
        List<int[]> bombPoints = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (bombMap[i][j] == 'O' && curTime - timeMap[i][j] == 3) bombPoints.add(new int[]{i, j});
            }
        }
        for (int[] bombPoint : bombPoints) {
            removeBombMap(bombPoint[0], bombPoint[1]);
        }
    }

    private static void removeBombMap(int x, int y) {
        bombMap[x][y] = '.';
        int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
            bombMap[dx][dy] = '.';
        }
    }

    private static void insertBomb(int curTime) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (bombMap[i][j] == '.') {
                    bombMap[i][j] = 'O';
                    timeMap[i][j] = curTime;
                }
            }

        }
    }

}