import java.util.*;
import java.io.*;

class Solution {
    
    int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
    
        
    public int[] solution(String[][] places) {
        int[] answer=new int[places.length];
        for(int i=0;i<places.length;i++){
            answer[i]=getAnswerByBfs(places[i]);
        }
        return answer;
    }
    
    public int getAnswerByBfs(String[] map){
        
        // System.out.println(Arrays.toString(map));
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length();j++){
                if(map[i].charAt(j)=='P'&&!isAnswerByBfs(map,i,j)) return 0;
            }
        }
        return 1;
    }
    
    public boolean isAnswerByBfs(String[] map,int x,int y){
        Queue<int[]> queue=new ArrayDeque<>();
        queue.add(new int[] {x,y,0,-1}); //x좌표, y좌표, 이동 횟수, 이전 이동 방향
        while(!queue.isEmpty()){
            int[] cur=queue.poll();
            int cx=cur[0];
            int cy=cur[1];
            int cnt=cur[2];
            int beforeDir=cur[3];
            
            // System.out.println(Arrays.toString(cur));
            
            for(int i=0;i<4;i++){
                if((i+2)%4==beforeDir) continue;
                int nx=cx+dir[i][0];
                int ny=cy+dir[i][1];
                if(nx<0||nx>=5||ny<0||ny>=5) {
                    // System.out.println(nx+" "+ny +" : 범위 밖");
                    continue;
                }
                if(map[nx].charAt(ny)=='X') {
                    // System.out.println(nx+" "+ny +" : X표시임");                 
                    continue;
                }
                if(map[nx].charAt(ny)=='P') {
                    // System.out.println(nx+" "+ny +" : P만남");
                 
                    return false;
                }
                
                if(cnt==1) continue;
                queue.add(new int[] {nx,ny,cnt+1,i});
            }
        }
        return true;
    }
}