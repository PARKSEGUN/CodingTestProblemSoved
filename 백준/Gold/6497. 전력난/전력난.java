import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static PriorityQueue<int[]> pq;
    private static int[] parent;

    private static int allSum;

    public static void main(String[] args) throws IOException {
        /*
         * 모두 연결되어있다는 것을 어떻게 판단할까
         * visited 판단할 수 있을까?
         * 1-2-3-4, 5-6-7-8 그룹이 있을때, 모든 그룹을 visited 했다고 해도 두 그룹이 떨어져있기떄문에 신장트리가 아니게된다.
         * pq의 모든 값을 확인한다? -> 문제에서 만약 항상 도시가 연결 그래프 형태라는 말이 있다면 가능함, 하지만 그렇지 않다면 pq 값을 모두 확인해도 만들 수 없는 형태라면?
         * 만들 수 없는 경우에는 -1 을 출력하라고 하면 어떻게 할지 고민하기
         * 일단 해당 문제는 완전 그래프 형태로 나오니 MST와 Union-Find로 해결하기
         * */
        while (true) {
            if (!isSetting()) break;
            sb.append(allSum - getMstSum()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int getMstSum() {
        int sum = 0;
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int val = cur[2];
            if (find(from) != find(to)) {
                union(from, to);
                sum += val;
            }
        }
        return sum;
    }

    private static int find(int num) {
        if (num == parent[num]) return num;
        return parent[num] = find(parent[num]);
    }

    private static void union(int num1, int num2) {
        int parent1 = find(num1);
        int parent2 = find(num2);
        if (parent1 < parent2) {
            parent[parent2] = parent1;
            find(num2); //깊이 줄이기 위함, 줄여지는지 확인
        } else {
            parent[parent1] = parent2;
            find(num1); //깊이 줄이기 위함, 줄여지는지 확인
        }
    }

    private static boolean isSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N == 0 && M == 0) return false; //베이스 케이스
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        allSum = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            allSum += val;
            pq.add(new int[]{from, to, val});
        }
        return true;
    }

}
