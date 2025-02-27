import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T, N;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println(isAnswer() ? "YES" : "NO");
        }
    }

    private static boolean isAnswer() throws IOException {
        N = Integer.parseInt(br.readLine());
        Set<String> numbers = new HashSet<>();
        for (int j = 0; j < N; j++) {
            numbers.add(br.readLine());
        }
//        System.out.println(numbers);
        for (String cur : numbers) {
            for (int k = 0; k < cur.length(); k++) {
                String subStr = cur.substring(0, k);
//                System.out.println(subStr);
                if (numbers.contains(subStr)) return false;
            }
        }
        return true;
    }

}
