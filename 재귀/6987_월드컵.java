모든 대결 결과를 미리 구해서 입력으로 주어진 결과와 비교하면서 같은 값이 있는지 비교
입력값의 최대값을 넘지 않는다는 조건으로 최적화 구현


package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int gameResult[][][];
	static int tmpResult[][];
	static int[][] minValGameResult = new int[6][3];
	static int[][] match = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 },
			{ 2, 3 }, { 2, 4 }, { 2, 5 }, { 3, 4 }, { 3, 5 }, { 4, 5 } };
	static int[] answer = { 0, 0, 0, 0 };

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		makeGameResult(0);
		for (int i = 0; i < answer.length; i++) {
			answerString.append(answer[i]).append(" ");
		}
		System.out.println(answerString);
	}

	private static void makeGameResult(int cur) {
		if (cur == 15) {
			searchRound();
			return;
		}
		int x = match[cur][0];
		int y = match[cur][1];
		if (minValGameResult[x][0] >= tmpResult[x][0] + 1 && minValGameResult[y][2] >= tmpResult[y][2] + 1) {
			tmpResult[x][0]++;
			tmpResult[y][2]++;
			makeGameResult(cur + 1);
			tmpResult[x][0]--;
			tmpResult[y][2]--;
		}
		if (minValGameResult[x][2] >= tmpResult[x][2] + 1 && minValGameResult[y][0] >= tmpResult[y][0] + 1) {
			tmpResult[x][2]++;
			tmpResult[y][0]++;
			makeGameResult(cur + 1);
			tmpResult[x][2]--;
			tmpResult[y][0]--;
		}

		if (minValGameResult[x][1] >= tmpResult[x][1] + 1 && minValGameResult[y][1] >= tmpResult[y][1] + 1) {
			tmpResult[x][1]++;
			tmpResult[y][1]++;
			makeGameResult(cur + 1);
			tmpResult[x][1]--;
			tmpResult[y][1]--;
		}

	}

	private static void searchRound() {
		for (int i = 0; i < 4; i++) {
			if (isFindAnswer(i) == true) {
				answer[i] = 1;
			}
		}
	}

	private static boolean isFindAnswer(int round) {
		for (int j = 0; j < 6; j++) {
			for (int k = 0; k < 3; k++) {
				if (gameResult[round][j][k] != tmpResult[j][k]) {
					return false;
				}
			}
		}
		return true;
	}

	private static void setting() throws IOException {
		gameResult = new int[4][6][3];
		tmpResult = new int[6][3];
		for (int i = 0; i < 4; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					gameResult[i][j][k] = Integer.parseInt(tokenizer.nextToken());
					minValGameResult[j][k] = Math.max(minValGameResult[j][k], gameResult[i][j][k]);
				}
			}
		}
	}
}
