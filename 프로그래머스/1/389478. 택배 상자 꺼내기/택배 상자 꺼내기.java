import java.util.*;
import java.io.*;

class Solution {
    int[][] map;
    public int solution(int n, int w, int num) {
        int answer=0;
        //문제의 규칙에 맞게 map을 세팅
        map=new int[n/w+1][w];
        int cur=1;
        int numIdx=-1;
        for(int i=0;i<map.length;i++){
            if(i%2==0){
               for(int j=0;j<w&&cur<=n;j++){
                   if(cur==num) numIdx=j;
                    map[i][j]=cur;
                    cur++;
                } 
            }else{
                for(int j=w-1;j>=0&&cur<=n;j--){
                   if(cur==num) numIdx=j;
                    map[i][j]=cur;
                    cur++;
                } 
            }
        }
        // for(int i=0;i<map.length;i++){
        //     for(int j=0;j<map[i].length;j++){
        //         System.out.print(map[i][j]+" ");
        //     }System.out.println();
        // }
        //map을 확인해서 해당 num 과 같은 idx가 존재하는지 확인
        for(int i=0;i<map.length;i++){
            if(map[i][numIdx]>num&&map[i][numIdx]!=0){
                answer++;
                System.out.println(map[i][numIdx]);
            } 
        }
        return answer+1;
    }
}