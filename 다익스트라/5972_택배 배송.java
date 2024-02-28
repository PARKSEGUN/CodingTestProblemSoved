#### BOJ 5972 택배 배송
태그 : 데이크스트라, 그래프 이론, 최단 경로
메모리: ```46,436kb```
실행 시간: ```588ms```
아이디어:
- 다익스트라 알고리즘 이용
- 출발 정점에서 현재 갈 수 있는 정점의 최단거리를 계속해서 저장
- 우선순위 큐 이용



  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<int[]> connect[];
	static int arr[];
	static boolean visited[];

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		findShortest();
		System.out.println(arr[N]);
	}

	private static void findShortest() {
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((a, b) -> {
			return Integer.compare(a[1], b[1]);
		});
		pQueue.add(new int[] { 1, 0 });
		arr[1] = 0;
		while (!pQueue.isEmpty()) {
			int cur = pQueue.peek()[0];
			int val = pQueue.peek()[1];
			pQueue.poll();
			if (arr[cur] < val) {
				continue;
			}
			for (int i = 0; i < connect[cur].size(); i++) {
				int tmpCur = connect[cur].get(i)[0];
				int tmpVal = connect[cur].get(i)[1];
				if (arr[tmpCur] > arr[cur] + tmpVal) {
					arr[tmpCur] = Integer.min(arr[tmpCur], arr[cur] + tmpVal);
					pQueue.add(new int[] { tmpCur, tmpVal });
				}
			}
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		visited = new boolean[N + 1];
		arr = new int[N + 1];
		Arrays.fill(arr, Integer.MAX_VALUE);
		M = Integer.parseInt(tokenizer.nextToken());
		connect = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			connect[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			int c = Integer.parseInt(tokenizer.nextToken());
			connect[a].add(new int[] { b, c });
			connect[b].add(new int[] { a, c });

		}
	}

}
