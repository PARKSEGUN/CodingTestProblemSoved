기차의 가운데 지점을 기준으로 조건을 확인하고 탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algo1_서울_10_김태수 {

	static int N;
	static char[][] map;
	static int sx = 0, sy = 0, sDir = 0;
	static int ex = 0, ey = 0, eDir = 0;;
	static int answer;
	static boolean visited[][][];
	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws IOException {
		setting();
		System.out.println(goToTarget());
	}

	private static int goToTarget() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sx, sy, 0, sDir });
		visited[sx][sy][sDir] = true;
		while (!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int cnt = queue.peek()[2];
			int dir = queue.peek()[3];
//			System.out.println(x + " " + y + " " + cnt + " " + dir);
//			System.out.println(ex + " " +ey+ " " + cnt + " " + eDir);
			queue.poll();
			
			if (x == ex && y == ey && dir == eDir) {
				return cnt;
			}
			for (int i = 0; i < 4; i++) {

				int dx = x + dix[i];
				int dy = y + diy[i];
				if (dx < 0 || dx >= N || dy < 0 || dy >= N) {
					continue;
				}
				if (outOfMap(dx, dy, dir)) {
//					System.out.println("aslkdjf");
					continue;
				}

				if (canNotMove(dx, dy, dir)) {
					continue;
				}
				if (visited[dx][dy][dir] == true) {
					continue;
				}
				queue.add(new int[] { dx, dy, cnt + 1, dir });
				visited[dx][dy][dir] = true;
			}

			if (dir == 1) {
				dir = 0;
			} else {
				dir = 1;
			}
			if (visited[x][y][dir] == false && canTurn(x, y)) {
				queue.add(new int[] { x, y, cnt + 1, dir });
				visited[x][y][dir] = true;
			}
		}
		return 0;
	}

	private static boolean canTurn(int x, int y) {
		if (x - 1 < 0 || x + 1 >= N || y - 1 < 0 || y + 1 >= N) {
			return false;
		}
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (map[i][j] == '1') {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean canNotMove(int dx, int dy, int dir) {
		if (dir == 0) {
			for (int i = dy - 1; i <= dy + 1; i++) {
				if (map[dx][i] == '1') {
					return true;
				}
			}
		} else {
			for (int i = dx - 1; i <= dx + 1; i++) {
				if (map[i][dy] == '1') {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean outOfMap(int x, int y, int dir) {
		if (dir == 0) {
			if (y - 1 < 0 || y + 1 >= N) {
				return true;
			}
		} else {
			if (x - 1 < 0 || x + 1 >= N) {
				return true;
			}
		}
		return false;
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2];
		int sCheck=0;
		int eCheck=0;
		
		for (int i = 0; i < N; i++) {
			String string = reader.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = string.charAt(j);
				if (map[i][j] == 'B') {
					// 시작하는 기차의 방향 확인
					if(sCheck==1) {
						if (sx != i) {
							sDir = 1;
						}
					}
					sCheck++;
					sx += i;
					sy += j;
				} else if (map[i][j] == 'E') {
					if(eCheck==1) {
						if (ex != i) {
							eDir = 1;
						}
					}
					eCheck++;
					ex += i;
					ey += j;
				}
			}
		}
		sx /= 3;
		sy /= 3;
		ex /= 3;
		ey /= 3;
	}
}
