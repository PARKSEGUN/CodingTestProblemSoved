import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, B, C;
    static int[] A;
    static long answer;

    public static void main(String[] args) throws IOException {
        inputSetting();
        for (int i = 0; i < N; i++) {

            answer++;
            A[i] -= B;
            if (A[i] > 0) {
                answer += (A[i] - 1) / C + 1;
            }
        }
        sb.append(answer);
        System.out.println(answer);
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }
}