슬라이딩 윈도우, 투포인터 개념을 이용해서 해결

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();
	static char[] val = { 'A', 'C', 'G', 'T' };
	static int[] countVal = { 0, 0, 0, 0 };
	static int[] inputCountVal = new int[4];
	static String inputDNA;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		setting();
		countStartPWD();
		for (int i = M; i < N; i++) {
			for (int k = 0; k < 4; k++) {
				if (val[k] == inputDNA.charAt(i)) {
					countVal[k]++;
				}
			}
			for (int k = 0; k < 4; k++) {
				if (val[k] == inputDNA.charAt(i - M)) {
					countVal[k]--;
				}
			}
			if (isCorrect()) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static void countStartPWD() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 4; j++) {
				if (inputDNA.charAt(i) == val[j]) {
					countVal[j]++;
				}
			}
		}
		if (isCorrect()) {
			answer++;
		}
	}

	private static boolean isCorrect() {
		for (int i = 0; i < 4; i++) {
			if (countVal[i] < inputCountVal[i]) {
				return false;
			}
		}
		return true;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		inputDNA = reader.readLine();
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < 4; i++) {
			inputCountVal[i] = Integer.parseInt(tokenizer.nextToken());
		}
	}

}
