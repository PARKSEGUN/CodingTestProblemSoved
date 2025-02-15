import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, K;
    private static int[][] connInfo;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        setting();
        System.out.println(bfs());
    }

    private static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[][] visited = new int[N][(int) Math.pow(2, N)];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < Math.pow(2, N); j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        visited[K][1 << K] = 0;
        pq.add(new int[]{K, 0, 1 << K});
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            int num = cur[0];
            int val = cur[1];
            int visitedVal = cur[2];
//            System.out.println(num + " " + val + " " + Integer.toBinaryString(visitedVal));
            if ((visitedVal & (int) (Math.pow(2, N) - 1)) == ((int) Math.pow(2, N) - 1)) return val;
            for (int i = 0; i < N; i++) {
                if (num != i) {
                    int nextVal = connInfo[num][i];
                    int nextVisitedVal = (visitedVal | (1 << i));
                    if (visited[i][nextVisitedVal] <= val + nextVal) continue;
                    visited[i][nextVisitedVal] = val + nextVal;
                    pq.add(new int[]{i, val + nextVal, nextVisitedVal});
                }
            }
        }
        return -1;
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        connInfo = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                connInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
