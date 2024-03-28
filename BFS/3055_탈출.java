#### BOJ 3055 탈출
태그 : 너비 우선 탐색, 그래프 이론, 그래프 탐색
메모리: ```23,828kb```
실행 시간: ```196ms```
아이디어:
- BFS




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] map;
	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };
	static List<int[]> waterPoints = new ArrayList<>();
	static int sx, sy, ex, ey;
	static boolean[][] visited;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		int answer = function();
		System.out.println(answer == -1 ? "KAKTUS" : answer);
	}

	private static int function() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sx, sy, 0 });
		visited[sx][sy] = true;
		int curCnt = -1;
		while (!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int cnt = queue.peek()[2];
			queue.poll();
			if (map[x][y] == 'D') {
				return cnt;
			}
			if (curCnt != cnt) {
				curCnt = cnt;
				expandWater();
			}
			for (int i = 0; i < 4; i++) {
				int dx = x + dix[i];
				int dy = y + diy[i];
				if (dx < 0 || dx >= N || dy < 0 || dy >= M) {
					continue;
				}
				if (visited[dx][dy] == true || map[dx][dy] == 'X' || map[dx][dy] == '*') {
					continue;
				}
				queue.add(new int[] { dx, dy, cnt + 1 });
				visited[dx][dy] = true;
			}
		}
		return -1;
	}

	// S 시작점 / D 도착점 / * : 물 / X : 돌 / . : 비어있음
	private static void expandWater() {
		List<int[]> points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '*') {
					points.add(new int[] { i, j });
				}
			}
		}
		for (int[] point : points) {
			for (int k = 0; k < 4; k++) {
				int dx = point[0] + dix[k];
				int dy = point[1] + diy[k];
				if (dx < 0 || dx >= N || dy < 0 || dy >= M) {
					continue;
				}
				if (map[dx][dy] == 'X') {
					continue;
				}
				if (map[dx][dy] == 'D') {
					continue;
				}
				map[dx][dy] = '*';
			}
		}
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '*') {
					waterPoints.add(new int[] { i, j });
				} else if (map[i][j] == 'S') {
					sx = i;
					sy = j;
				} else if (map[i][j] == 'D') {
					ex = i;
					ey = j;
				}
			}
		}
	}
}
