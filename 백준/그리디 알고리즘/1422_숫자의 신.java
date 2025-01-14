큰수를 만들어 주기 위해서 입력으로 주어진 수들 중 어떤 수가 와야 하는지를 확인해야한다.
a와 b 가 주어졌을때 ab 와 ba 처럼 붙여서 만든 수를 비교해서 어떤 수가 더 큰지를 확인하는 기준으로 정렬을 실행
가장 긴 값을 중복해서 사용하는 것이 최적이기에 가장 긴 문자열의 길이를 저장해서 탐색


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static String arr[];
	static int maxLength;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		Arrays.sort(arr, (a, b) -> {
			return (b + a).compareTo(a + b);
		});
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length() == maxLength) {
				for (int j = 0; j < K - N; j++) {
					answerString.append(arr[i]);
				}
				maxLength = 0;
			}
			answerString.append(arr[i]);
		}
		System.out.println(answerString);
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		arr = new String[N];
		K = Integer.parseInt(tokenizer.nextToken());
		for (int i = 0; i < N; i++) {
			arr[i] = reader.readLine();
			maxLength = Integer.max(maxLength, arr[i].length());
		}
	}

}
