import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] prefixMulti;
    private static int answer;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        prefixMulti = new int[N + 1];
        prefixMulti[0] = 1;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixMulti[i] = prefixMulti[i - 1] * (Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i <= N - 3; i++) {
            for (int j = i + 1; j <= N - 2; j++) {
                for (int k = j + 1; k <= N - 1; k++) {
                    for (int l = k + 1; l <= N; l++) {
                        int sum = 0;
                        sum += prefixMulti[i] / prefixMulti[0];
                        sum += prefixMulti[j] / prefixMulti[i];
                        sum += prefixMulti[k] / prefixMulti[j];
                        sum += prefixMulti[l] / prefixMulti[k];
                        answer = Math.max(answer, sum);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
