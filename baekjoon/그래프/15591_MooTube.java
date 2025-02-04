import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    /*
     *  최단거리를 구하는 것이 아닌 단순하게 한 노드를 중심으로 모든 노드까지를 탐색하는 것이기에 BFS보다는 DFS를 선택하는것이 더 효율적이라고 생각
     * BFS를 사용하게 되면 Queue 연산이 추가되기때문에 DFS에 비해 속도가 느림
     * */

    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, Q;

    private static List<int[]>[] connInfo;

    public static void main(String[] args) throws IOException {

        setting();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            sb.append(bfs(num, k)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int bfs(int num, int k) {
        int result = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{num, Integer.MAX_VALUE});
        visited[num] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curNum = cur[0];
            int curMinVal = cur[1];
//            System.out.println(curNum + " " + curMinVal);
            for (int i = 0; i < connInfo[curNum].size(); i++) {
                int next = connInfo[curNum].get(i)[0];
                int nextMinVal = Math.min(curMinVal, connInfo[curNum].get(i)[1]);
                if (visited[next]) continue;
                q.add(new int[]{next, nextMinVal});
                visited[next] = true;
                if (nextMinVal >= k) {
                    result++;
                }
            }
        }
        return result;
    }


    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        connInfo = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            connInfo[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            connInfo[a].add(new int[]{b, v});
            connInfo[b].add(new int[]{a, v});
        }
    }

}
