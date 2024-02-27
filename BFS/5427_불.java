지도 탐색의 알고리즘으로 DFS와 BFS가 있었고 DFS를 통해서 해결하려고했지만 매 초마다 불이 퍼진다고 했기에
매 초마다 즉, 한칸 이동할때마다 비교를 해야되었기때문에 매 초 마다 비교할 수 있는 BFS를 사용했다.
DFS는 현재 탐색중인 시간이 몇초인지만 확인할 수 있었기에 맵을 매 초마다 저장해주고 꺼내쓰는 방식을 사용했지만 메모리 초과가 발생했다.
이를 해결하기위해서 BFS를 사용해 현재의 탐색하고있는 초에 따라서 맵을 변화시켜 주는 방식을 사용했다.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;
	static char map[][];
	static int sx, sy;
	static int[] dix = { 0, 0, 1, -1 };
	static int[] diy = { 1, -1, 0, 0 };
	static boolean visited[][];
	static boolean isFired[][];
	static Queue<int[]> fires;
	static int answer = Integer.MAX_VALUE;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(reader.readLine());
		for (int t = 0; t < T; t++) {
			setting();
			startEscape(sx, sy, 0);
			System.out.println((answer == Integer.MAX_VALUE) ? "IMPOSSIBLE" : answer);
			answer = Integer.MAX_VALUE;
		}
	}

	private static void fireSpead() {
		int curSize = fires.size();
		for (int i = 0; i < curSize; i++) {
			int x = fires.peek()[0];
			int y = fires.peek()[1];
			fires.poll();
			for (int k = 0; k < 4; k++) {
				int dx = dix[k] + x;
				int dy = diy[k] + y;
				if (dx >= 0 && dx < N && dy >= 0 && dy < M && (map[dx][dy] == '.' || map[dx][dy] == '@')) {
					map[dx][dy] = '*';
					fires.add(new int[] { dx, dy });
				}
			}
		}

	}

	private static void startEscape(int x, int y, int cnt) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y, cnt });
		int curCount = 0;
		visited[x][y] = true;
		fireSpead();
		while (!queue.isEmpty()) {
			int cx = queue.peek()[0];
			int cy = queue.peek()[1];
			int count = queue.peek()[2];
			if (cx == 0 || cy == 0 || cx == N - 1 || cy == M - 1) {
				answer = count + 1;
				return;
			}
			if (count != curCount) {
				fireSpead();
				curCount++;
			}
			queue.poll();
			for (int i = 0; i < 4; i++) {
				int dx = dix[i] + cx;
				int dy = diy[i] + cy;
				if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == '.' && visited[dx][dy] == false) {
					visited[dx][dy] = true;
					queue.add(new int[] { dx, dy, count + 1 });
				}
			}
		}
	}

	private static void setting() throws IOException {
		fires = new LinkedList<>();
		tokenizer = new StringTokenizer(reader.readLine());
		M = Integer.parseInt(tokenizer.nextToken());
		N = Integer.parseInt(tokenizer.nextToken());
		visited = new boolean[N][M];
		isFired = new boolean[N][M];
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = reader.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == '@') {
					sx = i;
					sy = j;
				} else if (map[i][j] == '*') {
					fires.add(new int[] { i, j });
				}
			}
		}
	}
}
