 '1번 섬으로 가는 경로는 유일하며 i번 섬에는 pi번 섬으로 가는 다리가 있습니다'
라는 조건으로 트리모양을 예상할 수 있다.
   트리의 모양의 경우 연결정보를 양쪽으로 저장할 필요가없이 진행방향, 한 방향으로만 연결정보를 저장해주면된다.
   단말노드까지 탐색해서 내려간뒤에 백트래킹을 통해서 양의 수를 합쳐주면서 올라온다.
   
   
-------------코드-------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static long nodeCnt[];
	static List<Integer> connectedInfo[];

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();

	public static void main(String[] args) throws IOException {
		setting();
		saveSheep(1);
		System.out.println(nodeCnt[1]);
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		connectedInfo = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			connectedInfo[i] = new ArrayList<>();
		}
		nodeCnt = new long[N + 1];
		for (int i = 2; i <= N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			boolean isSheep = (tokenizer.nextToken().equals("S")) ? true : false;
			int cnt = Integer.parseInt(tokenizer.nextToken());
			int target = Integer.parseInt(tokenizer.nextToken());
			nodeCnt[i] = (isSheep) ? cnt : -cnt;
			connectedInfo[target].add(i);
		}
	}

	private static long saveSheep(int cur) {
		for (int i = 0; i < connectedInfo[cur].size(); i++) {
			nodeCnt[cur] += saveSheep(connectedInfo[cur].get(i));
		}
		if (nodeCnt[cur] > 0) {
			return nodeCnt[cur];
		} else {
			return 0;
		}

	}

}
