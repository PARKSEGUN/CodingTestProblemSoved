import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static int roomCnt, maxRoomSize, removedWallMaxRoomSize;
    private static int cntTmp;

    private static int[][] numberMap;
    private static List<Integer> cntByNumber = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        setting();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                roomCnt++;
                getRoomCntDfs(i, j, roomCnt - 1);
                cntByNumber.add(cntTmp);
                maxRoomSize = Math.max(maxRoomSize, cntTmp);
                cntTmp = 0;
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(numberMap[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println(cntByNumber);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    int dx = i + dir[k][0];
                    int dy = j + dir[k][1];
                    if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
                    if (numberMap[i][j] == numberMap[dx][dy]) continue;
                    if ((map[i][j] & (1 << k)) == (1 << k)) {
                        removedWallMaxRoomSize = Math.max(removedWallMaxRoomSize, cntByNumber.get(numberMap[i][j]) + cntByNumber.get(numberMap[dx][dy]));
                    }
                }
            }
        }
        sb.append(roomCnt).append("\n").append(maxRoomSize).append("\n").append(removedWallMaxRoomSize);
        System.out.println(sb.toString());
    }

    private static void getRoomCntDfs(int x, int y, int number) {
        cntTmp++;
        visited[x][y] = true;
        numberMap[x][y] = number;
        for (int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
            if (visited[dx][dy]) continue;
            if ((map[x][y] & (1 << i)) == (1 << i)) continue;
            getRoomCntDfs(dx, dy, number);
        }
    }

//    private static void getRemovedWallRoomCntDfs(int x, int y) {
//        removedCntTmp++;
//        for (int i = 0; i < 4; i++) {
//            int dx = x + dir[i][0];
//            int dy = y + dir[i][1];
//            if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
//            if (removedVisited[dx][dy]) {
//                continue;
//            }
//            if ((map[x][y] & (1 << i)) == (1 << i)) {
//                continue;
//            }
//            removedVisited[dx][dy] = true;
//            getRemovedWallRoomCntDfs(dx, dy);
//            removedVisited[dx][dy] = false;
//        }
//    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        numberMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
