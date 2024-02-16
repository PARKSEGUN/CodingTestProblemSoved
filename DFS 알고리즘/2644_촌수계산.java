#### BOJ 2644 촌수계산
태그 : 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
메모리: ```11,752kb```
실행 시간: ```80ms```
아이디어:
- 연결정보를 저장후 DFS탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int target[];
	static List<Integer> connect[];
	static int answer = -1;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		findTarget(target[0], 0, 1 << target[0]);
		System.out.println(answer);
	}

	private static void findTarget(int cur, int cnt, int visited) {
		if (cur == target[1]) {
			answer = cnt;
			return;
		}
		for (int i = 0; i < connect[cur].size(); i++) {
			int x = connect[cur].get(i);
			if ((visited & (1 << x)) == (1 << x)) {
				continue;
			} else {
				findTarget(x, cnt + 1, visited | 1 << x);
			}

		}
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		connect = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			connect[i] = new ArrayList<Integer>();
		}
		tokenizer = new StringTokenizer(reader.readLine());
		target = new int[] { Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()) };
		M = Integer.parseInt(reader.readLine());
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			connect[a].add(b);
			connect[b].add(a);
		}
	}
}
