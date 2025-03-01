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

    private static int N, M;

    private static Set<String> memo = new HashSet<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            memo.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            String[] blogs = br.readLine().split(",");
            for (int j = 0; j < blogs.length; j++) {
                memo.remove(blogs[j]);
            }
            sb.append(memo.size()).append("\n");
        }
        System.out.println(sb.toString());
        
    }
}