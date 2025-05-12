import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        int result1 = findPaperCnt(0, 0, N, N, 0);
        int result2 = findPaperCnt(0, 0, N, N, 1);
        answerString.append(result1).append("\n").append(result2);
    }

    private static int findPaperCnt(int sx, int sy, int ex, int ey, int target) {
//        System.out.println(sx + " " + sy + " " + ex + " " + ey);
        int cnt = 0;
        if (isAllTarget(sx, sy, ex, ey, target)) {
            cnt++;
        } else {
            if (ex - sx == 1 && ey - sy == 1) return 0;
            cnt += findPaperCnt(sx, sy, (sx + ex) / 2, (sy + ey) / 2, target);
            cnt += findPaperCnt(sx, (sy + ey) / 2, (sx + ex) / 2, ey, target);
            cnt += findPaperCnt((sx + ex) / 2, sy, ex, (sy + ey) / 2, target);
            cnt += findPaperCnt((sx + ex) / 2, (sy + ey) / 2, ex, ey, target);
        }
        return cnt;
    }

    private static boolean isAllTarget(int sx, int sy, int ex, int ey, int target) {
        for (int i = sx; i < ex; i++) {
            for (int j = sy; j < ey; j++) {
                if (map[i][j] != target) return false;
            }
        }
        return true;
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}