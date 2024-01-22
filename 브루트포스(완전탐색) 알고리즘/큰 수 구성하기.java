--- 첫번째 풀이
1부터 주어진 N까지의 탐색하면서 탐색하는 수가 주어진 숫자들로만 이루어져있는지 확인한다
--- 두번째 풀이
하지만 1부터 모든 경우를 확인하는 것이 아닌 주어진 숫자들로 만들 수 있는 모든 경우를 먼저 확인한뒤 
N보다 작은 값이 되었을때를 확인하는 것이 효율적이다.
  -> 시도해보기


package baekjoon18511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
		StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
		int N = toIntValue(tokenizer1);
		int K = toIntValue(tokenizer1);
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			arr.add(toIntValue(tokenizer2));
		}
		for (int i = N; i >= 1; i--) {
			if (isCorrectAnswer(i, arr)) {
				System.out.println(i);
				return;
			}
		}
	}

	private static boolean isCorrectAnswer(int i, List<Integer> arr) {
		while (i > 0) {
			if (!arr.contains(i % 10)) {
				return false;
			}
			i /= 10;
		}
		return true;
	}

	private static int toIntValue(StringTokenizer tokenizer) {
		return Integer.parseInt(tokenizer.nextToken());
	}

}
