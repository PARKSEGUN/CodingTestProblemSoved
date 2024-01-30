메모리: 42884 kb
실행시간: 804 ms

재귀문과 방문처리를 이용

  import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static boolean[] visited;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		visited = new boolean[N + 1];
		M = Integer.parseInt(tokenizer.nextToken());
		arr = new int[M];
		makeNumberList(0);
	}

	private static void makeNumberList(int cnt) {
		if (cnt == M) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				builder.append(arr[i]).append(" ");
			}
			System.out.println(builder);
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				arr[cnt] = i;
				makeNumberList(cnt + 1);
				visited[i] = false;
			}
		}

	}

}
