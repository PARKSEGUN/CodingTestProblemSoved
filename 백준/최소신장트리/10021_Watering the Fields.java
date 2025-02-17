import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, C;
    private static int[][] nodes;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        setting();
        System.out.println(prim());
    }

    private static long prim() {
        long answer = 0;
        int edgeCount = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        visited = new boolean[N];

        // 1. 초기 노드(0)부터 시작
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int num = cur[0];
            int val = cur[1];

            // 2. 이미 방문한 노드라면 스킵
            if (visited[num]) continue;

            // 3. 방문 처리 및 비용 추가
            visited[num] = true;
            answer += val;
            edgeCount++;

            // 4. 현재 노드와 연결된 간선 추가
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    int connValue = getConnValue(nodes[num][0], nodes[num][1], nodes[i][0], nodes[i][1]);
                    if (connValue >= C) {
                        pq.add(new int[]{i, connValue});
                    }
                }
            }
        }

        // 5. 모든 노드가 연결되지 않았다면 -1 반환
        return (edgeCount == N) ? answer : -1;
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        nodes = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i][0] = Integer.parseInt(st.nextToken());
            nodes[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private static int getConnValue(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
