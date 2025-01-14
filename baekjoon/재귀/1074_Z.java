4사분면씩 잘라서 4개의 분면중에 어느 분면에 속하는지를 확인한뒤
  그 분면에서 다시 4분면 을 잘라서 어느 분면에 속하는 지 확인하는 과정을 반복


---------------------코드---------------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, R, C, len;

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		len = (int) Math.pow(2, N);
		findValue(0, len * len);
	}

	private static void findValue(int start, int end) {
		if (end - start == 0) {
			System.out.println(start);
			return;
		}
		int diff = (end - start) / 4;
		if (R < len / 2 && C < len / 2) { // 1분면
			R = R;
			C = C;
			len /= 2;
			findValue(start, start + diff * 1);
		} else if (R < len / 2 && C >= len / 2) { // 2분면
			R = R;
			C = C - len / 2;
			len /= 2;
			findValue(start + diff * 1, start + diff * 2);
		} else if (R >= len / 2 && C < len / 2) { // 3분면
			R = R - len / 2;
			C = C;
			len /= 2;
			findValue(start + diff * 2, start + diff * 3);
		} else if (R >= len / 2 && C >= len / 2) { // 4분면
			R = R - len / 2;
			C = C - len / 2;
			len /= 2;
			findValue(start + diff * 3, start + diff * 4);
		}
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
	}
}
