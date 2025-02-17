문제는 (x,y) 좌표를 반대로 제공한다
때문에 puddles의 좌표도 뒤집어서 제공됨
좌표가 뒤집어서 제공되는 것은 알고있었지만 내가 다시 뒤집어서 진행하면 해결될거라 생각
뒤집어 졌을때의 문제점은 없는지 다시 한번 확인하기

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp=new int[n+1][m+1];
        // System.out.println(puddles.length);
        for(int[] x : puddles){
            dp[x[1]][x[0]]=-1;
        }
        dp[1][1]=1;
        int answer = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(dp[i][j]!=-1){
                    // System.out.println(i+" "+j);
                    if(i-1>0&&dp[i-1][j]!=-1){
                        dp[i][j]+=dp[i-1][j];
                        dp[i][j]%=1000000007;
                    }
                    if(j-1>0&&dp[i][j-1]!=-1){
                        dp[i][j]+=dp[i][j-1];
                        dp[i][j]%=1000000007;
                    }
                }
            }
        }

        // for(int k=1;k<dp.length;k++){
        //     System.out.println(Arrays.toString(dp[k]));
        // }
        return dp[n][m];
    }
}
