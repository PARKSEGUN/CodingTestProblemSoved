모든 경우를 확인해야하는 문제
a에서 b 의 방향과 거리를 구하고 그 같은 방향과 거리에 c 가 있을때
a*100+b*10+c 를 해주고 제곱수인지 확인하는 과정을 반복
  ----------코드----------
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int map[][];
	static StringTokenizer tokenizer;
	static int answer = -1;

	public static void main(String args[]) throws Exception {
		setting();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isSquareNumber(map[i][j])) {
					answer = Math.max(map[i][j], answer);
				}
				solutionStart(i, j);
			}
		}
		System.out.println(answer);
	}

	private static void solutionStart(int sy, int sx) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i != sy || j != sx)
					solution(sy, sx, i, j);
			}
		}
	}

	private static void solution(int sy, int sx, int y, int x) {

		int disY = y - sy;
		int disX = x - sx;
		int tmp = 0;
		while (sy >= 0 && sy < N && sx >= 0 && sx < M) {
			tmp += map[sy][sx];
			if (isSquareNumber(tmp)) {
				answer = Math.max(tmp, answer);
			}
			tmp *= 10;
			sy += disY;
			sx += disX;
		}
	}

	private static boolean isSquareNumber(int num) {
		double result = Math.sqrt(num);
		if (result == (int) Math.sqrt(num)) {
			return true;
		}
		return false;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = reader.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
	}
}
