import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;

    private static List<List<Integer>> connInfo = new ArrayList<>();

    private static int[] inCnt, outCnt;

    public static void main(String[] args) throws IOException {
        setting();
        for (int i = 0; i < N + 1; i++) {
            boolean[] visited = new boolean[N + 1];
            dfs(i, i, visited);
        }
        for (int i = 1; i < N + 1; i++) {
            System.out.println(N - 1 - inCnt[i] - outCnt[i]);
        }
    }

    private static void dfs(int cur, int start, boolean[] visited) {
        for (int i = 0; i < connInfo.get(cur).size(); i++) {
            Integer next = connInfo.get(cur).get(i);
            if (visited[next]) continue;
            visited[next] = true;
            inCnt[next]++;
            outCnt[start]++;
            dfs(next, start, visited);
            
        }
    }

    private static void setting() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        inCnt = new int[N + 1];
        outCnt = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            connInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connInfo.get(b).add(a);
        }
    }

}
