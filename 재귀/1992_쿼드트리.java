주어진 map을 4분면으로 나눠가면서 나눠진 분면이 모두 1 또는 0으로 이루어져있는지를 확인하면서 진행한다.


-------------------코드--------------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean map[][];

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		setting();
		findAnswer(0, 0, N);
	}

	private static void findAnswer(int sy, int sx, int len) {
		// System.out.println(sy + " " + sx + " " + ey + " " + ex);
		if (isCollectArea(sy, sx, len)) {
			System.out.print((map[sy][sx] == true ? 1 : 0));
			return;
		} else {
			System.out.print("(");
		}
		findAnswer(sy, sx, len / 2);
		findAnswer(sy, sx + len / 2, len / 2);
		findAnswer(sy + len / 2, sx, len / 2);
		findAnswer(sy + len / 2, sx + len / 2, len / 2);
		System.out.print(")");
	}

	private static boolean isCollectArea(int sy, int sx, int len) {
		// System.out.println(sy + " " + sx + " " + ey + " " + ex);
		boolean tmp = map[sy][sx];
		for (int i = sy; i < sy + len; i++) {
			for (int j = sx; j < sx + len; j++) {
				if (map[i][j] != tmp) {
					return false;
				}
			}
		}
		return true;
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = reader.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = (tmp.charAt(j) == '1' ? true : false);
			}
		}
	}
}
