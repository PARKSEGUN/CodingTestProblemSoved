// 특정 노드로 들어오는 방향의 총 노드 개수 + 특정 노드에서 나가는 방향의 총 노드 개수 = n-1 이면 해당 노드의 순위 매김 가능

import java.util.*;
import java.io.*;


class Solution {
    
    int N;
    List<Integer>[] connInfo;
    int[] inCnt;
    int[] outCnt;
    
    public int solution(int n, int[][] results) {
        N=n+1;
        inCnt=new int[N];
        outCnt=new int[N];
//         간선 매핑
        connInfo=new List[N];
        for(int i=0;i<N;i++){
            connInfo[i]=new ArrayList<>();
        }
        for(int[] cur : results){
            connInfo[cur[1]].add(cur[0]);
        }
        //모든 노드에서 BFS를 사용
        for(int i=1;i<N;i++){
            bfs(i);
        }
        int answer = 0;
        for(int i=1;i<N;i++){
            if(inCnt[i]+outCnt[i]==n-1) answer++;
        }
        return answer;
    }
    
    
    private void bfs(int start){
        boolean[] visited=new boolean[N];        
        Queue<Integer> q=new LinkedList<>();
        q.add(start);
        visited[start]=true;
        int outSum=0;
        while(!q.isEmpty()){
            int cur=q.poll();
            for(int next : connInfo[cur]){
                if(visited[next]) continue;
                visited[next]=true;
                inCnt[next]++;
                outSum++;
                q.add(next);
            }
        }
        outCnt[start]+=outSum;
    }
}
