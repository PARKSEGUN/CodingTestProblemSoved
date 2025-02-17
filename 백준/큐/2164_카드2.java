큐 성질 이용

-----------코드---------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Queue<Integer> queue;
	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		findOneCard();
	}

	private static void findOneCard() {
		while (queue.size() != 1) {
			queue.poll();
			queue.add(queue.poll());
		}
		System.out.println(queue.peek());
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		queue = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
	}
}
