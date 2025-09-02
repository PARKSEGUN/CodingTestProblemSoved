import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    static int N;
    static int[] time;
    static int[] price;
    static int[] dp;
    static int answer;

    public static void main(String[] args) throws IOException {
        inputSetting();


        for (int i = 0; i < N; i++) {
            if (i + time[i] > N) continue;
            dp[i] = price[i];
            for (int j = 0; j < i; j++) {
                if (j + time[j] <= i) {
                    dp[i] = Math.max(dp[j] + price[i], dp[i]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        time = new int[N];
        price = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }
    }
}