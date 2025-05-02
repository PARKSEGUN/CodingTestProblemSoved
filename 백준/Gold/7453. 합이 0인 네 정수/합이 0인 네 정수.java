import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() throws IOException {
        double[] ab = new double[N * N];
        double[] cd = new double[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[i * N + j] = map[i][0] + map[j][1];
                cd[i * N + j] = map[i][2] + map[j][3];
            }
        }

        long result = 0;
//        Arrays.sort(ab);
        Arrays.sort(cd);
//        System.out.println(Arrays.toString(ab));
//        System.out.println(Arrays.toString(cd));
        for (int i = 0; i < ab.length; i++) {
            //-ab[i]에 해당하는 값이 여러개 있는 경우를 생각해줘야한다
//            System.out.println(-ab[i]);
//            System.out.println(Arrays.binarySearch(cd, -ab[i]));
            if (Arrays.binarySearch(cd, (ab[i] == 0.0) ? 0.0 : -(ab[i])) >= 0) {

                int startIdx = -(Arrays.binarySearch(cd, -ab[i] - 0.5)) - 1;
                int endIdx = -(Arrays.binarySearch(cd, -ab[i] + 0.5)) - 1;
                result += endIdx - startIdx;
            }
        }

        answerString.append(result);
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}