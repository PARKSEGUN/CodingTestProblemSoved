누적합과 DP의 개념을 이용해서 해결
넓이를 구할 수 있는 공식 대입

----------코드----------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int[][] arr;
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();

	public static void main(String[] args) throws IOException {
		setting();
		solution();
		System.out.println(resultString);

	}

	private static void solution() throws IOException {
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int y1 = Integer.parseInt(tokenizer.nextToken());
			int x1 = Integer.parseInt(tokenizer.nextToken());
			int y2 = Integer.parseInt(tokenizer.nextToken());
			int x2 = Integer.parseInt(tokenizer.nextToken());
			resultString.append(arr[y2][x2] - arr[y1 - 1][x2] - arr[y2][x1 - 1] + arr[y1 - 1][x1 - 1]).append("\n");
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(tokenizer.nextToken());
				arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + tmp;
			}
		}
	}
}
