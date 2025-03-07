import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M, K;
    private static List<int[]> groupInfo = new ArrayList<>();
    private static List<List<Integer>> connInfo = new ArrayList<>();
    private static int[] candyCnt;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        setting();
        makeGroupInfo();
//        for (int[] ints : groupInfo) {
//            System.out.println(Arrays.toString(ints));
//        }
//        System.out.println();
        System.out.println(getAnswer());
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }

    }

    private static int getAnswer() {
        dp = new int[K][groupInfo.size()];
        for (int i = 1; i < K; i++) {
            for (int j = 1; j < groupInfo.size(); j++) {
                int cnt = groupInfo.get(j)[0];
                int candy = groupInfo.get(j)[1];
                dp[i][j] = dp[i][j - 1];
                if (i - cnt >= 0 && j - 1 >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - cnt][j - 1] + candy);
            }
        }
        return dp[K - 1][groupInfo.size() - 1];
    }

    private static void makeGroupInfo() {
        groupInfo.add(new int[]{0, 0});
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            groupInfo.add(dfs(i, visited));
        }
    }

    private static int[] dfs(int num, boolean[] visited) {
        int cnt = 1;
        int candy = candyCnt[num];
        for (int i = 0; i < connInfo.get(num).size(); i++) {
            int next = connInfo.get(num).get(i);
            if (visited[next]) continue;
            visited[next] = true;
            int[] result = dfs(next, visited);
            cnt += result[0];
            candy += result[1];
        }
        return new int[]{cnt, candy};
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candyCnt = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candyCnt[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= N; i++) {
            connInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connInfo.get(from).add(to);
            connInfo.get(to).add(from);
        }
    }


}
