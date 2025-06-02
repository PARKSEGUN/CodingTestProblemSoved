import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, cost;
        Edge(int _to, int _cost) {
            this.to = _to;
            this.cost = _cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 지점 수
            int M = Integer.parseInt(st.nextToken());  // 도로 개수 (양방향)
            int W = Integer.parseInt(st.nextToken());  // 웜홀 개수 (단방향, 음수 가중치)

            // 1. 인접 리스트 구성
            List<Edge>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            // 1-1. 일반 도로 (양방향)
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[u].add(new Edge(v, cost));
                graph[v].add(new Edge(u, cost));
            }

            // 1-2. 웜홀 (단방향, 음수 가중치)
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[u].add(new Edge(v, -cost));
            }

            // 2. SPFA를 위해 필요한 배열들
            //    dist: 최단 거리 (멀티소스로 처음에 0으로 초기화)
            //    count: 해당 노드가 큐에서 pop(=Relax)된 횟수
            //    inQueue: 큐 내에 존재 중인지 여부
            int[] dist = new int[N + 1];
            int[] count = new int[N + 1];
            boolean[] inQueue = new boolean[N + 1];

            // 2-1. “멀티소스” SPFA: 모든 노드를 0으로 초기화하고 큐에 넣는다.
            Arrays.fill(dist, 0);       // dist[i] = 0
            Arrays.fill(count, 0);      // count[i] = 0  ← 반드시 0으로 시작해야 함!
            Queue<Integer> q = new ArrayDeque<>();

            for (int i = 1; i <= N; i++) {
                q.offer(i);
                inQueue[i] = true;
                // count[i] 를 1로 할 필요가 없다! 0으로 두고 아래에서 ++count[u] 로 관리.
            }

            // 3. 실제 SPFA 반복
            boolean hasNegativeCycle = false;
            while (!q.isEmpty()) {
                int u = q.poll();
                inQueue[u] = false;

                // u에서 뻗어 나가는 모든 간선을 Relax
                for (Edge e : graph[u]) {
                    int v = e.to;
                    int w = e.cost;
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        // “u→v” 관계로 인해 v를 다시 큐에 넣을 때 count[v]++ 후 검사
                        if (++count[v] >= N) {
                            // count[v]가 N 이상이면 음수 사이클이 분명
                            hasNegativeCycle = true;
                            break;
                        }
                        if (!inQueue[v]) {
                            q.offer(v);
                            inQueue[v] = true;
                        }
                    }
                }
                if (hasNegativeCycle) break;
            }

            ans.append(hasNegativeCycle ? "YES\n" : "NO\n");
        }

        System.out.print(ans);
    }
}
