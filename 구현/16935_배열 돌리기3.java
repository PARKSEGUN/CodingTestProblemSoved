#### BOJ 16935 배열 돌리기 3
태그 : 구현
메모리: ```58,780kb```
실행 시간: ```404ms```
아이디어:
- 이동할 지점의 좌표를 구하는 수식 사용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, RCount, RInfo[], map[][];
	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		rotation();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				resultString.append(map[i][j]).append(" ");
			}
			resultString.append("\n");
		}
		System.out.println(resultString);
	}

	private static void rotation() {
		for (int i = 0; i < RCount; i++) {
			if (RInfo[i] == 1) {
				rotation1();
			} else if (RInfo[i] == 2) {
				rotation2();
			} else if (RInfo[i] == 3) {
				rotation3();
			} else if (RInfo[i] == 4) {
				rotation4();
			} else if (RInfo[i] == 5) {
				rotation5();
			} else if (RInfo[i] == 6) {
				rotation6();
			}
		}

	}

	private static void rotation1() {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[N - i - 1][j];
			}
		}
		map = tmp;
	}

	private static void rotation2() {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[i][M - j - 1];
			}
		}
		map = tmp;
	}

	private static void rotation3() {
		int x = N;
		N = M;
		M = x;
		int[][] tmp = new int[N][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tmp[j][M - i - 1] = map[i][j];
			}
		}
		map = tmp;
	}

	private static void rotation4() {
		int x = N;
		N = M;
		M = x;
		int[][] tmp = new int[N][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tmp[N - j - 1][i] = map[i][j];
			}
		}
		map = tmp;
	}

	private static void rotation5() {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				tmp[i][j] = map[i + N / 2][j];
			}
		}
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				tmp[i][j] = map[i][j - M / 2];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				tmp[i][j] = map[i - N / 2][j];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				tmp[i][j] = map[i][j + M / 2];
			}
		}
		map = tmp;
	}

	private static void rotation6() {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				tmp[i][j] = map[i][j + M / 2];
			}
		}
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				tmp[i][j] = map[i + N / 2][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				tmp[i][j] = map[i][j - M / 2];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				tmp[i][j] = map[i - N / 2][j];
			}
		}
		map = tmp;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		RCount = Integer.parseInt(tokenizer.nextToken());
		RInfo = new int[RCount];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());

			}
		}
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < RCount; i++) {
			RInfo[i] = Integer.parseInt(tokenizer.nextToken());
		}
	}

}
