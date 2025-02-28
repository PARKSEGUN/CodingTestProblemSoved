import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String A = br.readLine();
        String B = br.readLine();

        Queue<String> answers = new ArrayDeque<>();

        int[][] dp = new int[A.length() + 1][B.length() + 1];
        int[][] dpDir = new int[A.length() + 1][B.length() + 1];
        if (A.charAt(0) == B.charAt(0)) dp[1][1] = 1;
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    dpDir[i][j] = 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    if (dp[i][j - 1] > dp[i - 1][j]) dpDir[i][j] = 2;
                    else dpDir[i][j] = 3;
                }

            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dpDir[i]));
//        }
        System.out.println(dp[A.length()][B.length()]);
        if (dp[A.length()][B.length()] != 0) {
            int x = A.length();
            int y = B.length();
            while (x > 0 && y > 0) {
                if (dpDir[x][y] == 1) {
                    sb.append(A.charAt(x - 1));
                    x -= 1;
                    y -= 1;
                } else if (dpDir[x][y] == 2) {
                    y -= 1;
                } else {
                    x -= 1;
                }
            }
            sb.reverse();
            System.out.println(sb.toString());
        }
    }

}
