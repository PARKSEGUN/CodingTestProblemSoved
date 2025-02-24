import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int curServer=0;
        Queue<int[]> addServerQueue=new ArrayDeque<>();
        for(int i=0;i<players.length;i++){
            //가동이 끝난 서버 먼저 확인
            while(!addServerQueue.isEmpty()&&i-addServerQueue.peek()[1]>=k){
                curServer-=addServerQueue.peek()[0];
                addServerQueue.poll();    
            } 
            int curPlayer=players[i];
            int needServer=(int)Math.floor((double)curPlayer/m);
            if(curServer<needServer){
                int increaseServer=needServer-curServer;
                addServerQueue.add(new int[] {increaseServer,i});
                answer+=increaseServer;
                curServer+=increaseServer;
            } 
        }
        return answer;
    }
}