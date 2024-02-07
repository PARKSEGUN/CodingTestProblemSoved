강의의 시간을 인덱스로 저장한뒤 하나씩 증가하면서 그때의 강의가 몇개 진행되고있는지를 확인함
결과 : 메모리초과, 시간초과
시간의 범위가 10억이었기에 벗어나게된다.

따라서 모든 시간을 탐색하는 것보다 주어진 시간때만 탐색할 수 있도록 우선순위큐 두개를 만들어준뒤 시작시간과 끝나는 시간을 각각넣어줌
front 원소를 각각 뽑은뒤에 비교해서 시작이간이 끝나는 시간의 front 보다 작다면 시작시간 큐 제거 아니라면 끝나는 시간 큐 제거
결과 : 성공

----------------------코드-----------------------
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static PriorityQueue<Integer> startTime = new PriorityQueue<>();
	static PriorityQueue<Integer> endTime = new PriorityQueue<>();
	static int answer;

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		findAnswer();
		System.out.println(answer);
	}

	private static void findAnswer() {
		int cnt = 0;

		while (startTime.size() > 0) {

			int startTmp = startTime.peek();
			int endTmp = endTime.peek();
			if (startTmp < endTmp) {
				startTime.poll();
				cnt++;
			} else {
				endTime.poll();
				cnt--;
			}
			// System.out.println(startTmp + " " + endTmp + " " + cnt);
			answer = Integer.max(answer, cnt);
		}
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int num = Integer.parseInt(tokenizer.nextToken());
			int start = Integer.parseInt(tokenizer.nextToken());
			int end = Integer.parseInt(tokenizer.nextToken());
			startTime.add(start);
			endTime.add(end);
		}
	}
}
