import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][3][2];
        dp[0][0][0] = dp[0][0][1] = map[0][0];
        dp[0][1][0] = dp[0][1][1] = map[0][1];
        dp[0][2][0] = dp[0][2][1] = map[0][2];
        int[] answer = {0, Integer.MAX_VALUE};
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j][0] = 0;
                dp[i][j][1] = Integer.MAX_VALUE;
                if (j == 0) {
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][0][0] + map[i][j]);
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][1][0] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][0][1] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][1][1] + map[i][j]);
                } else if (j == 1) {
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][0][0] + map[i][j]);
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][1][0] + map[i][j]);
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][2][0] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][0][1] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][1][1] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][2][1] + map[i][j]);
                } else {
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][1][0] + map[i][j]);
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][2][0] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][1][1] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][2][1] + map[i][j]);
                }

            }
        }
        for (int i = 0; i < 3; i++) {
            answer[0] = Math.max(answer[0], dp[N - 1][i][0]);
            answer[1] = Math.min(answer[1], dp[N - 1][i][1]);
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

}
