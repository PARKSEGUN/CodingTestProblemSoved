성적 순으로 입력이 들어온다는 것을 이용해서 입력받은 순서를 저장한다.
입력받은 이름의 길이를 인덱스로 하는 배열을 생성해서 저장해준뒤 투포인터 방식으로 start 부분과 end 부분을 증가하고 감소시켜주면서 확인한다.



-----------------------코드----------------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int nameLength[];
	static String names[];
	static Long answer = 0L;
	static String firstName;

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		K = (K == N) ? N - 1 : K;
		findAnswer(0, K + 1);
		System.out.println(answer);
	}

	private static void findAnswer(int start, int end) {
		for (int i = start; i < end; i++) {
			nameLength[names[i].length()]++;
		}
		answer += nameLength[names[start].length()] - 1;
		while (start < N - 1) {

			nameLength[names[start].length()]--;
			if (end < N) {
				nameLength[names[end++].length()]++;
			}
			start++;

			answer += nameLength[names[start].length()] - 1;
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		nameLength = new int[22];
		names = new String[N];
		K = Integer.parseInt(tokenizer.nextToken());
		for (int i = 0; i < N; i++) {
			names[i] = reader.readLine();
		}
	}
}
