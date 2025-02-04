import java.util.*;
import java.io.*;

class Solution {
    List<Integer>[] connInfo;
    int N;
    int[] cntArr;
    public int solution(int n, int[][] edge) {
        N=n+1;
        cntArr=new int[N];
        //간선정보 저장
        connInfo=new List[N];
        for(int i=0;i<N;i++){
            connInfo[i]=new ArrayList<>();
        }
        for(int i=0;i<edge.length;i++){
            connInfo[edge[i][0]].add(edge[i][1]);
            connInfo[edge[i][1]].add(edge[i][0]);
        }
        //BFS로 1부터 나머지 노드의 최단거리, 가장 멀리 떨어진 노드의 거리를 리턴
        int result=bfs(1);
        // System.out.println(Arrays.toString(cntArr));
//      가장 긴 노드의 거리(result)와 같은 노드들의 개수 확인
        int answer = 0;
        for(int i=1;i<N;i++){
            if(cntArr[i]==result) answer++;
        }
        return answer;
    }
    
    private int bfs(int start){
        int result=0;
        boolean[] visited=new boolean[N];
        Queue<int[]> q=new LinkedList<>();
        visited[start]=true;
        q.add(new int[] {start,0});
        while(!q.isEmpty()){
            int[] cur=q.poll();
            for(int i=0;i<connInfo[cur[0]].size();i++){
                int next=connInfo[cur[0]].get(i);
                int cnt=cur[1];
                if(visited[next]) continue;
                visited[next]= true;
                cntArr[next]=cnt+1;
                result=Math.max(result,cnt+1);
                q.add(new int[] {next,cnt+1});
            }
        }
        return result;
    }
}
