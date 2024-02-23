BOJ 15961 회전 초밥
태그 : 슬라이딩 윈도우, 두 포인터
메모리: 168,664kb
실행 시간: 520ms
아이디어:슬라이딩 윈도우



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, D, K, C;
	static int belt[];
	static int visited[];
	static int arr[];
	static int answer;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		// 슬라이딩 윈도우를 위한 초기화
		visited[C]++;
		answer++;
		for (int i = 0; i < K; i++) {
			arr[i] = belt[i];
			if (visited[belt[i]] == 0) {
				answer++;
			}
			visited[belt[i]]++;
		}

		findAnswer(0, K);
		System.out.println(answer);
	}

	private static void findAnswer(int start, int end) {
		int sum = answer;
		while (end < belt.length) {
			// System.out.println(start + " " + end + " " + answer);
			visited[belt[start]]--;
			if (visited[belt[start]] == 0) {
				sum--;
			}
			visited[belt[end]]++;
			if (visited[belt[end]] == 1) {
				sum++;
				answer = Math.max(answer, sum);
			}
			start++;
			end++;
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		D = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		visited = new int[D + 1];
		belt = new int[N + K];
		arr = new int[K];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(reader.readLine());
		}
		for (int i = 0; i < K; i++) {
			belt[N + i] = belt[i];
		}
	}

}
