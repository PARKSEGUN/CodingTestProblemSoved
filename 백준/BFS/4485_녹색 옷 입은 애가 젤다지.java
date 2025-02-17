BFS와 DP 알고리즘은 혼합해서 사용
  


package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int map[][];
	static int dp[][];

	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder answerString = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int idx = 1;
		while (true) {
			N = Integer.parseInt(bufferedReader.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			BFS(0, 0);
			answerString.append("Problem ").append(idx).append(": ").append(dp[N - 1][N - 1]).append("\n");

			idx++;
		}
		System.out.println(answerString);
	}

	private static void BFS(int sx, int sy) {
		// TODO Auto-generated method stub
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sx, sy, map[sx][sy] });
		dp[sx][sy] = map[sx][sy];
		while (!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int sum = queue.peek()[2];
			queue.poll();
			for (int i = 0; i < 4; i++) {
				int dx = x + dix[i];
				int dy = y + diy[i];
				if (dx >= 0 && dx < N && dy >= 0 && dy < N) {

					if (sum + map[dx][dy] < dp[dx][dy]) {
						dp[dx][dy] = sum + map[dx][dy];
						queue.add(new int[] { dx, dy, sum + map[dx][dy] });

					}
				}
			}
		}
	}
}
