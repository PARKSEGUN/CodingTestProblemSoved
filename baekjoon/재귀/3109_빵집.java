백트래킹을 통해서 방문한 곳을 다시 원위치 시켜준다면 같은 상황이 반복되기때문에 방문처리를 원위치 시켜주는 과정을 제거한다.

package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static boolean map[][];
	static int[] dix = { -1, 0, 1 };
	static int[] diy = { 1, 1, 1 };
	static boolean visited[][];
	static int answer;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		for (int i = 0; i < N; i++) {
			if (isGoal(i, 0)) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static boolean isGoal(int x, int y) {
		if (y == M - 1) {
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int dx = x + dix[i];
			int dy = y + diy[i];
			if (dx >= 0 && dx < N && dy >= 0 && dy < M && visited[dx][dy] == false && map[dx][dy] == false) {
				visited[dx][dy] = true;
				if (isGoal(dx, dy)) {
					return true;
				}
			}
		}
		return false;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		map = new boolean[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = reader.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = (tmp.charAt(j) == '.') ? false : true;
			}
		}
	}

}
