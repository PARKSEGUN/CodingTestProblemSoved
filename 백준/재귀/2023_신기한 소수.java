완전탐색과 재귀를 이용해서 만들어지는 모든 수를 확인한뒤 소수인지를 판별

-------------------------코드---------------------------------
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();
	static List<Integer> answerList = new ArrayList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(reader.readLine());
		makePrimeNumber(0, 0);
		for (int x : answerList) {
			System.out.println(x);
		}
	}

	private static void makePrimeNumber(int cnt, int num) {
		if (cnt == N) {
			if (isPrimeNumber(num)) {
				answerList.add(num);
			}
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (isPrimeNumber(num * 10 + i)) {
				makePrimeNumber(cnt + 1, num * 10 + i);
			}
		}
	}

	private static boolean isPrimeNumber(int num) {
		if (num == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
