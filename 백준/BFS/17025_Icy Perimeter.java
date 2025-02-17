import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int answer1, answer2;

    public static void main(String[] args) throws IOException {
        setting();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '#' && !visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i, j);
                }
            }
        }
        System.out.println(answer1 + " " + answer2);
    }

    private static void bfs(int x, int y) {
        int size = 0, length = 0;
        int[] dix = {-1, 0, 1, 0};
        int[] diy = {0, 1, 0, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            size++;

            for (int i = 0; i < 4; i++) {
                int dx = cx + dix[i];
                int dy = cy + diy[i];

                if (dx < 0 || dx >= N || dy < 0 || dy >= N || map[dx][dy] != '#') {
                    length++; // 격자 밖이거나 '.'이면 둘레 증가
                    continue;
                }

                if (!visited[dx][dy]) {
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx, dy});
                }
            }
        }

        if (answer1 < size) { // 더 큰 얼음 덩어리 발견
            answer1 = size;
            answer2 = length;
        } else if (answer1 == size) { // 같은 크기면 최소 둘레 선택
            answer2 = Math.min(answer2, length);
        }
    }

    private static void setting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String inputString = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = inputString.charAt(j);
            }
        }
    }
}
