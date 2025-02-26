import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    private static List<int[]> lineInfo = new ArrayList<>();
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        setting();
        Collections.sort(lineInfo, (Comparator.comparingInt(o -> o[0])));
        System.out.println(N - dfs(0, 0));
    }

    private static int dfs(int ah, int bh) {
        if (dp[ah][bh] != -1) return dp[ah][bh];
        dp[ah][bh] = 0;
        for (int i = 0; i < lineInfo.size(); i++) {
            int a = lineInfo.get(i)[0];
            int b = lineInfo.get(i)[1];
            if (ah < a && bh < b) {
                dp[ah][bh] = Math.max(dp[ah][bh], dfs(a, b) + 1);

            }
        }
        return dp[ah][bh];
    }

    private static void setting() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[555][555];
        for (int i = 0; i < 555; i++) {
            for (int j = 0; j < 555; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lineInfo.add(new int[]{a, b});
        }
    }

}