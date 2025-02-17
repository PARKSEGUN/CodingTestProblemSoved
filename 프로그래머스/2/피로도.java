일반적인 DFS
모든 경로를 탐색하기위한 visited 설정

import java.util.*;

class Solution {

    
    int answer = -1;
    
    boolean[] visited={};
    public int solution(int k, int[][] dungeons) {
        visited=new boolean[dungeons.length];
        dfs(k,dungeons,0);
        return answer;
    }
    private void dfs(int k,int[][] dungeons,int cnt){
        answer=Math.max(answer,cnt);
        for(int i=0;i<dungeons.length;i++){
            if(k>=dungeons[i][0]&&visited[i]==false){
                visited[i]=true;
                dfs(k-dungeons[i][1],dungeons,cnt+1);
                visited[i]=false;
            }
        }
    }
}
