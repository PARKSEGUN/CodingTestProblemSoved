not 연산자 사용

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static boolean arr[];
	static int count;

	public static void main(String args[]) throws Exception {
		N = Integer.parseInt(reader.readLine());
		arr = new boolean[N + 1];
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 1; i <= N; i++) {
			String tmp = tokenizer.nextToken();
			if (tmp.equals("0")) {
				arr[i] = false;

			} else {
				arr[i] = true;
			}
		}
		count = Integer.parseInt(reader.readLine());
		for (int i = 0; i < count; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int type = Integer.parseInt(tokenizer.nextToken());
			int num = Integer.parseInt(tokenizer.nextToken());
			if (type == 1) {
				solutionMan(num);
			} else {
				solutionWoman(num);
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (arr[i] == false) {
				stringBuilder.append(0).append(" ");
			} else {
				stringBuilder.append(1).append(" ");
			}
			if (i % 20 == 0) {
				stringBuilder.append("\n");
			}
		}
		System.out.println(stringBuilder);
	}

	private static void solutionMan(int num) {
		for (int i = num; i <= N; i += num) {
			arr[i] = !arr[i];
		}
	}

	private static void solutionWoman(int num) {
		arr[num] = !arr[num];
		int tmp = 1;
		while (num - tmp > 0 && num + tmp <= N && arr[num - tmp] == arr[num + tmp]) {
			arr[num - tmp] = !arr[num - tmp];
			arr[num + tmp] = !arr[num + tmp];
			tmp++;
		}
	}

}
