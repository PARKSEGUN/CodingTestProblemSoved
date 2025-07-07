import java.util.*;
import java.io.*;

class Solution {
    
    int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
    
    public int solution(int[][] maps) {
        Queue<int[]> queue=new ArrayDeque<>();
        queue.add(new int[] {0,0,1});
        boolean[][] visited=new boolean[maps.length][maps[0].length];
        visited[0][0]=true;
        
        while(!queue.isEmpty()){
            int[] cur=queue.poll();
            int x=cur[0];
            int y=cur[1];
            int cnt=cur[2];
            
            // System.out.println(Arrays.toString(cur));
            
            if(x==maps.length-1&&y==maps[0].length-1) return cnt;
            
            for(int i=0;i<4;i++){
                int nx=x+dir[i][0];
                int ny=y+dir[i][1];
                
                if(nx<0||nx>=maps.length||ny<0||ny>=maps[0].length) continue;
                if(maps[nx][ny]==0) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny]=true;
                queue.add(new int[] {nx,ny,cnt+1});
            }
        }
        return -1;
    }
}