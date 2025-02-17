완전탐색으로 모든 경우를 확인


package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map = new int[9][9];
	static int[][] range = { { 0, 0, 3, 3 }, { 0, 3, 3, 6 }, { 0, 6, 3, 9 }, { 3, 0, 6, 3 }, { 3, 3, 6, 6 },
			{ 3, 6, 6, 9 }, { 6, 0, 9, 3 }, { 6, 3, 9, 6 }, { 6, 6, 9, 9 } };
	static boolean isEnd = false;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		findValue(0, 0);
	}

	private static void findValue(int x, int y) {
//		System.out.println(x + " " + y);
		if (isEnd) {
			return;
		}
		if (y == 9) {
			x++;
			y = 0;
		}
		if (x == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			isEnd = true;
			return;
		}
		if (map[x][y] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (isPossibleValue(x, y, i)) {
					map[x][y] = i;
					findValue(x, y + 1);
					map[x][y] = 0;
				}
			}
		} else {
			findValue(x, y + 1);
		}
	}

	private static boolean isPossibleValue(int x, int y, int value) {
		// 가로
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == value) {
				return false;
			}
		}
		// 세로
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == value) {
				return false;
			}
		}
		for (int k = 0; k < 9; k++) {
			if (x >= range[k][0] && y >= range[k][1] && x < range[k][2] && y < range[k][3]) {
				for (int i = range[k][0]; i < range[k][2]; i++) {
					for (int j = range[k][1]; j < range[k][3]; j++) {
						if (map[i][j] == value) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private static void input() throws IOException {
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = (str.charAt(j) - '0');
			}
		}
	}
}
