import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    /*
    * 시도 1 : 모든 사람이 갖고있는 블럭을 하나의 배열에 저장(블록의 높이를 인덱스로 하여 저장, Ex arr[1] =2, 블럭 높이가 1인 블록은 2개)
    * 사람을 탐색할때 현재 탐색하고 있는 사람의 블럭의 개수는 배열에서 제거한뒤 현재 사람의 현재 탐색하고 있는 블럭(x) 일때, 다음 사람부터 탐색하여 H-x가 되는 블럭들을 배열에서 추출
    * 하지만 이 방법은 단 두개의 블럭만 사용할때만 용이 3개 이상의 사람들의 블럭을 사용해야하는 경우에는 에러
    * 
    * 시도 2 : 재귀문을 사용해서 모든 경우를 탐색, 첫번째 사람이 선택한 블럭(x)을 H에서 빼주고 그다음 사람에게 H-x 만큼의 블럭을 소유하고 있는지, 아니라면 다시 다음 사람에게 넘기는 식으로 진행
    * 이 방법의 경우 N은 50이하이고 M이 10 이하라고 생각하여, 10^50 이라고 생각, 시간이 부족할 것이라고 생각했지만 dp 개념을 추가해줘서 최적화
    * 특정한 사람을 기준으로 x의 블록의 높이를 만들 수 있는 경우를 한번 탐색했다면 다음 탐색에서 똑같은 사람의 x 높이를 찾는다면 dp 배열에서 찾을 수 있도록 진행
    * 해결.
    * 
    * */

    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, H;

    private static List<Integer>[] blocks;
    private static int[][] dp;
    //    private static int[] dp = new int[10];
    private static int answer;

    public static void main(String[] args) throws IOException {
        setting();
        System.out.println(dfs(0, H));
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private static int dfs(int cnt, int remain) {
        if (cnt >= N) return 0;

        int result = 0;
        if (dp[cnt][remain] != -1) {
            return dp[cnt][remain];
        }
        for (int i = 0; i < blocks[cnt].size(); i++) {
            int cur = blocks[cnt].get(i);
            if (remain == cur) result++;
            else if (remain - cur >= 0) result += dfs(cnt + 1, remain - cur) % 10007;
        }
        return dp[cnt][remain] = result % 10007;
    }

    private static void setting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        blocks = new List[N];
//        아무 블럭도 사용하지 않았을때에 1개를 추가해주기 위함
        dp = new int[N][1111];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 1111; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < N; i++) {
            blocks[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            blocks[i].add(0);
            while (st.hasMoreTokens()) {
                int val = Integer.parseInt(st.nextToken());
                blocks[i].add(val);
            }
        }
    }
}
