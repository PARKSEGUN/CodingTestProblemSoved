#### BOJ 7576 토마토
태그 : 너비 우선 탐색, 그래프 이론, 그래프 탐색
메모리: ```146,504kb```
실행 시간: ```572ms```
아이디어:
- 모든 익은 토마토에서 시작하는 BFS



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
	static int map[][];
	static List<int[]> normal = new ArrayList<int[]>();
	static int[] dix = { 0, 0, 1, -1 };
	static int[] diy = { 1, -1, 0, 0 };
//	static boolean visited[][];
	static int answer;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		startCook();
		System.out.println((allClear()) ? answer : -1);
	}

	private static boolean allClear() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					return false;
				}
			}
		}
		return true;
	}

	private static void startCook() {
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < normal.size(); i++) {
			// visited[normal.get(i)[0]][normal.get(i)[1]] = true;
			map[normal.get(i)[0]][normal.get(i)[1]] = -1;
			queue.add(new int[] { normal.get(i)[0], normal.get(i)[1], 0 });
		}
		while (queue.size() > 0) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int cnt = queue.peek()[2];
			answer = cnt;
			queue.poll();
			for (int i = 0; i < 4; i++) {
				int dx = x + dix[i];
				int dy = y + diy[i];
				if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] != -1) {
					// visited[dx][dy] = true;
					map[dx][dy] = -1;
					queue.add(new int[] { dx, dy, cnt + 1 });
				}
			}
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		M = Integer.parseInt(tokenizer.nextToken());
		N = Integer.parseInt(tokenizer.nextToken());
		// visited = new boolean[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if (map[i][j] == 1) {
					normal.add(new int[] { i, j });
				}
			}

		}
	}
}
