import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, a, b;

    public static void main(String[] args) throws IOException {
        List<List<Integer>> powList = new ArrayList<>();
        powList.add(List.of(10));
        powList.add(List.of(1));
        powList.add(List.of(2, 4, 8, 6));
        powList.add(List.of(3, 9, 7, 1));
        powList.add(List.of(4, 6));
        powList.add(List.of(5));
        powList.add(List.of(6));
        powList.add(List.of(7, 9, 3, 1));
        powList.add(List.of(8, 4, 2, 6));
        powList.add(List.of(9, 1));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            a %= 10;
            int powListIdx = b % powList.get(a).size();
            System.out.println(powList.get(a).get(((powListIdx == 0) ? powList.get(a).size() : powListIdx) - 1));
        }
    }
}