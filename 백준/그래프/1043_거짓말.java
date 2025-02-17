import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static List<Integer> knowMans = new ArrayList<>();

    private static List<Integer>[] connInfo;


    private static List<Integer>[] parties;


    public static void main(String[] args) throws IOException {
        setting();
//        for (List<Integer> integerList : connInfo) {
//            System.out.println(integerList);
//        }

        System.out.println(getResult());
    }

    private static int getResult() {
        boolean[] visited = new boolean[N + 1];
        for (Integer knowMan : knowMans) {
            if (visited[knowMan]) continue;
            visited[knowMan] = true;
            dfs(knowMan, visited);
        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (isVisited(i, visited)) cnt++;
        }
        return cnt;
    }

    private static boolean isVisited(int i, boolean[] visited) {
        for (Integer x : parties[i]) {
            if (visited[x]) return false;
        }
        return true;
    }

    private static void dfs(Integer knowMan, boolean[] visited) {
        for (int i = 0; i < connInfo[knowMan].size(); i++) {
            Integer conn = connInfo[knowMan].get(i);
            if (visited[conn]) continue;
            visited[conn] = true;
            dfs(conn, visited);
        }
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parties = new List[M];
        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
        }
        connInfo = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            connInfo[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            knowMans.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                parties[i].add(Integer.parseInt(st.nextToken()));
            }

            for (int j = 0; j < parties[i].size(); j++) {
                for (int k = j + 1; k < parties[i].size(); k++) {
                    connInfo[parties[i].get(j)].add(parties[i].get(k));
                    connInfo[parties[i].get(k)].add(parties[i].get(j));
                }
            }
        }
    }

}
