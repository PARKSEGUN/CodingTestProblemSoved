import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    private static String[] map;
    private static boolean isPossible;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        while (setting() != 0) {
            visited[1] = true;
            dfs(1, 0);
            System.out.println((isPossible) ? "Yes" : "No");
        }
    }

    private static void dfs(int cur, int money) {
        st = new StringTokenizer(map[cur]);
        String type = st.nextToken();
        int moneyValue = Integer.parseInt(st.nextToken());

        List<Integer> nextPoints = new ArrayList<>();
        while (st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());
            if (next != 0) nextPoints.add(next);
        }
        //들어가기위한 조건
        if (type.equals("L")) {
            if (money <= moneyValue) money = moneyValue;
        } else if (type.equals("T")) {
            if (money < moneyValue) {
                return;
            } else {
                money -= moneyValue;
            }
        }
        //들어간 곳이 N-1이라면 YES
        if (cur == N) {
            isPossible = true;
            return;
        }
        //다음 칸으로 진행
        for (int nextPoint : nextPoints) {
            if (visited[nextPoint]) continue;
            visited[nextPoint] = true;
            dfs(nextPoint, money);
            visited[nextPoint] = false;
        }
    }

    private static int setting() throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N == 0) return N;

        isPossible = false;
        map = new String[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            String tmp = br.readLine();
            map[i] = tmp;
        }
        return N;
    }

}
