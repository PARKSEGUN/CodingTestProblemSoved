//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class Main {
//    private static StringTokenizer st;
//    private static StringBuilder sb = new StringBuilder();
//    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//    private static int N;
//    private static List<int[]> bricks = new ArrayList<>();
//    private static int maxWeight;
//
//    private static int[] dp;
//    private static int[] dpBack;
//
//    private static int resultIdx;
//
//    public static void main(String[] args) throws IOException {
//        N = Integer.parseInt(br.readLine());
//        bricks.add(new int[]{0, 0, 0});
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int size = Integer.parseInt(st.nextToken());
//            int height = Integer.parseInt(st.nextToken());
//            int weight = Integer.parseInt(st.nextToken());
//            maxWeight = Math.max(maxWeight, weight);
//            bricks.add(new int[]{size, height, weight, i + 1});
//        }
//        dp = new int[N + 1];
//        dpBack = new int[N + 1];
//        Collections.sort(bricks, Comparator.comparingInt(o -> o[0]));
//
//        for (int i = 1; i < N + 1; i++) {
//            dp[i] = bricks.get(i)[1];
//            for (int j = 1; j < i; j++) {
//                if (bricks.get(j)[0] < bricks.get(i)[0] && bricks.get(j)[2] < bricks.get(i)[2]) {
//                    if (dp[i] < dp[j] + bricks.get(i)[1]) {
//                        dp[i] = dp[j] + bricks.get(i)[1];
//                        dpBack[i] = j;
//                        resultIdx = i;
//                    }
//                }
//            }
//        }
////        System.out.println(Arrays.toString(dp));
////        System.out.println();
////        System.out.println(Arrays.toString(dpBack));
////        백트래킹을 이용해서 이전의 벽돌을 참고
//        Stack<Integer> stack = new Stack<>();
//        int answer = 0;
//        while (resultIdx != 0) {
//            answer++;
//            stack.add(bricks.get(resultIdx)[3]);
//            resultIdx = dpBack[resultIdx];
//        }
//        stack.add(answer);
//        while (!stack.isEmpty()) {
//            sb.append(stack.pop()).append("\n");
//        }
//        System.out.println(sb.toString());
//    }
//
//
//}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    // 각 벽돌: {size, height, weight, index}
    private static List<int[]> bricks = new ArrayList<>();

    private static int[] dp;
    private static int[] dpBack;
    private static int resultIdx;
    private static int maxHeight; // 전체 탑의 최대 높이

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        // 인덱스 0은 더미로 사용
        bricks.add(new int[]{0, 0, 0, 0});
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            bricks.add(new int[]{size, height, weight, i + 1});
        }
        dp = new int[N + 1];
        dpBack = new int[N + 1];

        // 문제 조건을 만족하기 위해 무게에 대해 오름차순 정렬
        Collections.sort(bricks, Comparator.comparingInt(o -> o[2]));

        maxHeight = 0;
        resultIdx = 0;
        // DP: i번째 벽돌을 맨 위로 쌓았을 때의 최대 높이
        for (int i = 1; i <= N; i++) {
            dp[i] = bricks.get(i)[1];  // 자신의 높이로 초기화
            for (int j = 1; j < i; j++) {
                // 밑면 넓이와 무게가 모두 증가하는 경우에만 쌓을 수 있다.
                if (bricks.get(j)[0] < bricks.get(i)[0] && bricks.get(j)[2] < bricks.get(i)[2]) {
                    if (dp[i] < dp[j] + bricks.get(i)[1]) {
                        dp[i] = dp[j] + bricks.get(i)[1];
                        dpBack[i] = j;
                    }
                }
            }
            // 최대 높이를 갱신하면 결과 인덱스 업데이트
            if (dp[i] > maxHeight) {
                maxHeight = dp[i];
                resultIdx = i;
            }
        }

        // dpBack 체인을 통해 탑의 벽돌 번호(출력 순서는 탑의 가장 위부터) 구하기
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        while (resultIdx != 0) {
            count++;
            stack.push(bricks.get(resultIdx)[3]); // 벽돌의 원래 번호
            resultIdx = dpBack[resultIdx];
        }
        // 첫 줄에 사용된 벽돌의 개수를 출력한 후, 탑의 가장 위 벽돌부터 출력
        sb.append(count).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
