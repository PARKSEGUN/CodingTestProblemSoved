#### BOJ 17135 캐슬 디펜스
태그 : 너비 우선 탐색, 브루트포스 알고리즘, 그래프 이론, 그래프 탐색, 구현, 시뮬레이션
메모리: ```17,536kb```
실행 시간: ```244ms```
아이디어:
- 조합, 구현


package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, D;
	static boolean map[][];
	static int answer;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					int tmp = shoot(new int[] { i, j, k });
					// System.out.println(tmp);
					answer = Integer.max(answer, tmp);
				}
			}
		}
		System.out.println(answer);
	}

	private static int shoot(int[] archer) {
		boolean[][] tmpMap = new boolean[N][M];
		copyMap(tmpMap);
		int mapUpCnt = 0;
		int killCnt = 0;
		while (mapUpCnt < N) {
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(tmpMap));
//			}
			int[][] shooted = new int[3][3];
			for (int i = 0; i < 3; i++) { // 아처를 탐색
				shooted[i][0] = -1;
				shooted[i][1] = -1;
				shooted[i][2] = Integer.MAX_VALUE;
				for (int k = 0; k < M; k++) {
					for (int j = 0; j < D && j < N; j++) {
						// 적군이 있고 거리가 최솟값이라면 거리 초기화
						if (tmpMap[j][k] == true) {
							int tmpDis = Math.abs(j + 1) + Math.abs(k - archer[i]);
							if (tmpDis <= D && tmpDis < shooted[i][2]) {

								shooted[i][2] = tmpDis;
								shooted[i][0] = j;
								shooted[i][1] = k;
							}
						}
					}
				}
			}
			for (int i = 0; i < 3; i++) {

				if (shooted[i][0] != -1 && tmpMap[shooted[i][0]][shooted[i][1]] == true) {
					// System.out.println(shooted[i][0] + " " + shooted[i][1]);
					tmpMap[shooted[i][0]][shooted[i][1]] = false;
					killCnt++;
				}
			}
			mapUp(tmpMap);
			mapUpCnt++;
		}
		return killCnt;
	}

	private static void mapUp(boolean[][] tmpMap) {
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpMap[i - 1][j] = tmpMap[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			tmpMap[N - 1][i] = false;
		}
	}

	private static void copyMap(boolean[][] tmpMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		map = new boolean[N][M];
		D = Integer.parseInt(tokenizer.nextToken());
		for (int i = N - 1; i >= 0; i--) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = (tokenizer.nextToken().equals("0")) ? false : true;
			}
		}
	}
}
