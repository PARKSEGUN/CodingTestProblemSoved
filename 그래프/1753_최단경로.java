#### BOJ 1753 최단경로
태그 : 데이크스트라, 그래프 이론, 최단 경로
메모리: ```116,284kb```
실행 시간: ```1,816ms```
아이디어:
- 다익스트라 이용



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int V, E, K;
	static List<int[]> connect[];
	static int arr[];
	static boolean visited[];

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		findShortest();
		for (int i = 1; i < V + 1; i++) {
			if (arr[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(arr[i]);
			}
		}
	}

	private static void findShortest() {
		int cur = K;
		arr[cur] = 0;
		visited[cur] = true;
		while (cur != -1) {
			visited[cur] = true;
			for (int i = 0; i < connect[cur].size(); i++) {
				int x = connect[cur].get(i)[0];
				int val = connect[cur].get(i)[1];
				arr[x] = Integer.min(arr[x], arr[cur] + val);
			}
			cur = findLeastIdx();
		}
	}

	private static boolean isNotVisitedAll() {
		for (int i = 1; i < V + 1; i++) {
			if (visited[i] == false) {
				return true;
			}
		}
		return false;
	}

	private static int findLeastIdx() {
		int idx = -1, val = Integer.MAX_VALUE;
		for (int i = 1; i < V + 1; i++) {
			if (val > arr[i] && visited[i] == false) {
				val = arr[i];
				idx = i;
			}
		}
		return idx;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		V = Integer.parseInt(tokenizer.nextToken());
		visited = new boolean[V + 1];
		arr = new int[V + 1];
		Arrays.fill(arr, Integer.MAX_VALUE);
		E = Integer.parseInt(tokenizer.nextToken());
		connect = new List[V + 1];
		for (int i = 0; i < V + 1; i++) {
			connect[i] = new ArrayList<int[]>();
		}
		K = Integer.parseInt(reader.readLine());
		for (int i = 0; i < E; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			int c = Integer.parseInt(tokenizer.nextToken());
			connect[a].add(new int[] { b, c });

		}
	}

}
