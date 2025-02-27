import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String A = br.readLine();
        String B = br.readLine();

        int[][] dp = new int[A.length() + 1][B.length() + 1];
        int cnt = 0;
        if (A.charAt(0) == B.charAt(0)) dp[1][1] = 1;
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    if (cnt < dp[i][j]) {
                        cnt = dp[i][j];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], dp[i - 1][j]));
                }

            }
        }
        System.out.println(cnt);
    }

}