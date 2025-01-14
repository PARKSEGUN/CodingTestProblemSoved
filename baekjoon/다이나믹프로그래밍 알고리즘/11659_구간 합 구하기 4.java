import java.io.BufferedReader;
import java.io.IOException;
누적합과 DP의 개념을 이용해서 해결

----------코드----------
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int[] arr;
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
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			resultString.append(arr[b] - arr[a - 1]).append("\n");
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		arr = new int[N + 1];
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 1; i <= N; i++) {
			int tmp = Integer.parseInt(tokenizer.nextToken());
			arr[i] = arr[i - 1] + tmp;
		}
	}
}
