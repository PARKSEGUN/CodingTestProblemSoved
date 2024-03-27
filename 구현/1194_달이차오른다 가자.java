비트마스킹으로 방문과 열쇠처리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] map;
	static int sx, sy, ex, ey;
	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };
	static boolean[][][] visited;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		setting();
		System.out.println(startMove(sx, sy));
	}

	private static int startMove(int sx, int sy) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sx, sy, 0, 0 });
		visited[0][sx][sy] = true;
		while (!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int sum = queue.peek()[2];
			int keyBit = queue.peek()[3];
//			System.out.println(x + " " + y + " " + sum);
//			System.out.println(Integer.toBinaryString(keyBit));
			queue.poll();
			if (map[x][y] == '1') {
				return sum;
			}
			for (int i = 0; i < 4; i++) {
				int dx = x + dix[i];
				int dy = y + diy[i];
				if (dx < 0 || dx >= N || dy < 0 || dy >= M) {
					continue;
				}
				if (map[dx][dy] == '#') {
					continue;
				}
				if (map[dx][dy] - 'A' >= 0 && map[dx][dy] - 'A' < 6 && (keyBit & (1 << (map[dx][dy] - 'A'))) == 0) {
					continue;
				}
				if (visited[keyBit][dx][dy] == true) {
					continue;
				}
				if (map[dx][dy] - 'a' >= 0 && map[dx][dy] - 'a' < 6) {
					visited[keyBit | (1 << (map[dx][dy] - 'a'))][dx][dy] = true;
					queue.add(new int[] { dx, dy, sum + 1, keyBit | (1 << (map[dx][dy] - 'a')) });
				} else {
					visited[keyBit][dx][dy] = true;
					queue.add(new int[] { dx, dy, sum + 1, keyBit });
				}

			}
		}
		return -1;
	}

	private static void setting() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[100][N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					sx = i;
					sy = j;
				} else if (map[i][j] == '1') {
					ex = i;
					ey = j;
				}
			}
		}
	}
}
