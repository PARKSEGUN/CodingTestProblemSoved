DFS를 확용해서 사이클이 이루어지는 방법이 몇가지가 존재하는 지를 확인한다.
처음에 visited 변수 하나를 구현해서 해결하려했지만, visited가 이전에 방문했던 곳을 의미하는지 이미 그룹화가 되어진 곳을 다시 방문한 것인지를
  구별할 수 없었기에 이를 해결하기위해서 grouped 라는 변수를 하나 더 설정해줘서 해결해주었다.

  package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char map[][];
	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };
	static String dirSign = "URDL";
	static boolean visited[][];
	static boolean grouped[][];
	static int answer;
	static boolean isUnion = false;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder answerString = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException, NumberFormatException {
		setting();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grouped[i][j] == false) {
					visited[i][j] = true;
					grouped[i][j] = true;
					findSafeZone(i, j);
					if (isUnion == false) {
						answer++;
					}
					isUnion = false;
					visited[i][j] = false;
				}
			}
		}
		System.out.println(answer);
	}

	private static void findSafeZone(int x, int y) {
		int idx = dirSign.indexOf(map[x][y]);
		int dx = x + dix[idx];
		int dy = y + diy[idx];
		if (dx >= 0 && dx < N && dy >= 0 && dy < M && visited[dx][dy] == false) {
			if (grouped[dx][dy] == true) {
				isUnion = true;
				return;
			}
			visited[dx][dy] = true;
			grouped[dx][dy] = true;
			findSafeZone(dx, dy);
			visited[dx][dy] = false;
		}
	}

	private static void setting() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		grouped = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
	}

}
