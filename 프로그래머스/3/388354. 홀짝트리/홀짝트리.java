import java.util.*;

class Solution {

    Map<Integer, List<Integer>> trees = new HashMap<>();
    boolean[] visited = new boolean[1000001];

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];

        for (int node : nodes) {
            trees.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            trees.get(edge[0]).add(edge[1]);
            trees.get(edge[1]).add(edge[0]);
        }


        for (int node : nodes) { // 홀짝 트리인지 검사
            if (visited[node]) {
                continue;
            }
            if (checkHolZzak(node, -1)) {
                answer[0] ++;
            }
        }
        visited = new boolean[1000001];
        for (int node : nodes) { // 역홀짝 트리인지 검사
            if (visited[node]) {
                continue;
            }
            if (checkReverseHolZzak(node, -1)) {
                answer[1] ++;
            }
        }

        return answer;
    }

    private boolean checkHolZzak(int current, int parent) {
        List<Integer> children = trees.get(current);
        int childrenSize = children.size() - 1; //연결 정보엔 부모 또한 포함되어있다.
        if (parent == -1) {
            childrenSize ++;
        }

        if (current % 2 == childrenSize % 2) {
            visited[current] = true;
            for (int child : children) {
                if (child == parent) continue;
                if (!checkHolZzak(child, current)) {
                    visited[current] = false;
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean checkReverseHolZzak(int current, int parent) {
        List<Integer> children = trees.get(current);
        int childrenSize = children.size() - 1; //연결 정보엔 부모 또한 포함되어있다.
        if (parent == -1) {
            childrenSize ++;
        }

        if (current % 2 != childrenSize % 2) {
            visited[current] = true;
            for (int child : children) {
                if (child == parent) continue;
                if (!checkReverseHolZzak(child, current)) {
                    visited[current] = false;
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}