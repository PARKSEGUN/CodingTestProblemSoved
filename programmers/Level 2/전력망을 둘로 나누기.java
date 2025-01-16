트리 모양으로 이어져있는 전력망을 하나의 연결선을 끊어서 두 그룹의 노드의 차이를 최소화하는 문제
처음에 리스트 배열을 사용하여서 연결정보를 저장하려 했지만 끊었을때의 구현의 편리함을 위해 2차원 배열로 연결정보를 저장
이후 DFS를 진행하는 메서드를 구현하여 각 두 그룹의 노드의 차이를 비교


import java.util.*;
import java.io.*;

class Solution {
    boolean[][] conn={};
    int N;
    public int solution(int n, int[][] wires) {
        conn=new boolean[n+1][n+1];
        N=n;
        int answer = Integer.MAX_VALUE;
        //setting
        for(int i=0;i<wires.length;i++){
            conn[wires[i][0]][wires[i][1]]=true;
            conn[wires[i][1]][wires[i][0]]=true;
        }
        //특정 전선 제거
        for(int i=0;i<wires.length;i++){
            conn[wires[i][0]][wires[i][1]]=false;
            conn[wires[i][1]][wires[i][0]]=false;
            answer=Math.min(answer,findDiff());
            conn[wires[i][0]][wires[i][1]]=true;
            conn[wires[i][1]][wires[i][0]]=true;
            
        }
        return answer;
    }
    
    private int findDiff(){
        int firstZone=0, secondZone=0;
        boolean[] visited=new boolean[N+1];
        for(int i=1;i<=N;i++){
            if(visited[i]==false){
                visited[i]=true;
                int result=dfsConn(i,visited);
                if(firstZone==0){
                    firstZone=result;
                }else{
                    secondZone=result;
                    break;
                }
            }
        }
        System.out.println(firstZone+" "+secondZone);
        return Math.abs(firstZone-secondZone);
    }
    private int dfsConn(int start,boolean[] visited){
        // System.out.println(start);
        int returnVal=1;
        for(int i=1;i<=N;i++){
            if(conn[start][i]==true&&visited[i]==false){
                visited[i]=true;
                returnVal+=dfsConn(i,visited);
            }
        }
        return returnVal;
    }
}
