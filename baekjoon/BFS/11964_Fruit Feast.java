import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T, A, B;

    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        visited = new boolean[T + 1][2];
        int answer = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        while (queue.size() > 0) {
            int[] cur = queue.poll();
            int point = cur[0];
            int isDrink = cur[1];
//            System.out.println(Arrays.toString(cur));

            answer = Math.max(answer, point);

            if (answer == T) break;

            if (point + A <= T && !visited[point + A][isDrink]) {
                visited[point + A][isDrink] = true;
                queue.add(new int[]{point + A, isDrink});
            }
            if (point + B <= T && !visited[point + B][isDrink]) {
                visited[point + B][isDrink] = true;
                queue.add(new int[]{point + B, isDrink});
            }
            if (isDrink == 0 && !visited[point][1]) {
                visited[point / 2][1] = true;
                queue.add(new int[]{point / 2, 1});
            }
        }
        System.out.println(answer);
    }

}
