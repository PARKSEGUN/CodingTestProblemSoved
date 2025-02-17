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

    private static int N, K, R;

    private static Set<String> roads = new HashSet<>();
    private static Set<String> cows = new HashSet<>();

    private static int[] dix = {-1, 0, 1, 0};
    private static int[] diy = {0, 1, 0, -1};

    private static int cowCnt, answer;

    public static void main(String[] args) throws IOException {
        setting();
        for (String cow : cows) {
            st = new StringTokenizer(cow, ",");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            boolean[][] visited = new boolean[N + 1][N + 1];
            cowCnt = 0;
            visited[x][y] = true;
            dfs(x, y, visited);
            answer += cows.size() - 1 - cowCnt;
//            System.out.println(x + " " + y + " " + cowCnt);
        }
        System.out.println(answer / 2);
    }

    private static void dfs(int x, int y, boolean[][] visited) {
//        System.out.println(x + " " + y);
        for (int i = 0; i < 4; i++) {
            int dx = x + dix[i];
            int dy = y + diy[i];
            if (dx <= 0 || dx > N || dy <= 0 || dy > N) continue;
            if (roads.contains(twoPointToString(x, y, dx, dy))) {
//                System.out.println(x + " " + y + " " + dx + " " + dy);
                continue;
            }
            if (visited[dx][dy]) continue;
            visited[dx][dy] = true;
            if (cows.contains(onePointToString(dx, dy))) cowCnt++;
            dfs(dx, dy, visited);
        }
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            roads.add(twoPointToString(x1, y1, x2, y2));
            roads.add(twoPointToString(x2, y2, x1, y1));
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows.add(onePointToString(x, y));
        }
    }

    private static String twoPointToString(int x1, int y1, int x2, int y2) {
        sb.append(x1).append(",").append(y1).append(",").append(x2).append(",").append(y2);
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }

    private static String onePointToString(int x, int y) {
        sb.append(x).append(",").append(y);
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }
}
