스택을 활용한 풀이

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		resultString.append("<");
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		while (queue.size() != 1) {
			for (int i = 0; i < K - 1; i++) {
				queue.add(queue.poll());
			}
			resultString.append(queue.poll()).append(", ");
		}
		resultString.append(queue.poll()).append(">");
		System.out.println(resultString);
	}
}
