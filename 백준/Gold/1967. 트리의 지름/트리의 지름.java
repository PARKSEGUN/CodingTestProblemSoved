import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<List<int[]>> connInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        //1에서 가장 멀리 떨어진 노드 찾기
        int[] tmp = getFarNode(1);
        int[] result = getFarNode(tmp[0]);
        answerString.append(result[1]);

    }

    private static int[] getFarNode(int startNode) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startNode, 0});
        boolean[] visited = new boolean[N + 1];
        visited[startNode] = true;
        int[] result = {0, 0};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int num = cur[0];
            int val = cur[1];

            if (result[1] < val) {
                result[1] = val;
                result[0] = num;
            }
            for (int i = 0; i < connInfo.get(num).size(); i++) {
                int[] next = connInfo.get(num).get(i);
                if (visited[next[0]]) continue;
                visited[next[0]] = true;
                queue.add(new int[]{next[0], val + next[1]});
            }
        }
        return result;
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            connInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.get(parent).add(new int[]{child, val});
            connInfo.get(child).add(new int[]{parent, val});
        }
    }


}