// 첫번째는 모든 경우의 수를 확인하기 위해서 dfs를 사용
// 트리플로 얻을 수 있는 최고 점수 60점과 불을 맞춰서 얻는 50점, 그리고 20에서 60사이 의 트리플 경우, 20에서 40 사이 더블 경우, 20 이하의 싱글 경우를 나눠서 모든 경우로 진행
// 하지만 문제는 최소한의 다트로 진행하는 것이기에 bfs를 사용해서 0을 가장 빨리 만든 경우를 확인하는 것이 더 효율적이라고 생각
// dfs, bfs로 모든 경우를 확인하는 경우는 적어도 2^2000 의 경우로 시간초과가 발생할 수 밖에 없다. 모든 경우를 확인하고 시간초과를 방지하기 위해서는 dp 를 사용해서 해결
// dp[i][0]= i 점을 쏘기 위한 최소의 횟수, dp[i][1]= i점을 최소한의 횟수로 쐈을때의 불 or 싱글의 횟수
// 점화식을 만들면 dp[i][0]= Math.min(dp[i][0],dp[i-쏠수있는 모든 경우][0] + 1)
// 단, 싱글이나 불의 경우는 dp[i][0]과 dp[i-쏠 수 있는 모든 경우][0]+1 이 같을때 Math.max(dp[i][1],dp[쏠 수 있는 모든 경우][1]+1) 을 추가적으로 비교해야한다.
// 이는 Bottom-Up 방식
// !! 모든 경우를 확인하는 완전탐색의 느낌인데 시간초과가 발생할 수 있다면 dp 를 생각해보자, 또한 점화식이 적용가능하다면 dp를 생각하자.

import java.util.*;
import java.io.*;

class Solution {
    int[][] dp;
    public int[] solution(int target) {
        dp=new int[target+1][2];
        for(int i=0;i<=target;i++){
            dp[i][0]=Integer.MAX_VALUE;
        }
        dp[0][0]=0;
        for(int i=1;i<=target;i++){
//             불
            if(i-50>=0){
                if(dp[i][0]>dp[i-50][0]+1){
                    dp[i][0]=dp[i-50][0]+1;
                    dp[i][1]=dp[i-50][1]+1;
                }else if(dp[i][0]==dp[i-50][0]+1){
                    dp[i][1]=Math.max(dp[i][1],dp[i-50][1]+1);
                }
            }
            for(int j=1;j<=20;j++){
//                 트리플
                if(i-j*3>=0){
                    if(dp[i][0]>dp[i-j*3][0]+1){
                        dp[i][0]=dp[i-j*3][0]+1;
                        dp[i][1]=dp[i-j*3][1];
                    }
                }
                //                 더블
                if(i-j*2>=0){
                    if(dp[i][0]>dp[i-j*2][0]+1){
                        dp[i][0]=dp[i-j*2][0]+1;
                        dp[i][1]=dp[i-j*2][1];
                    }
                }
                //                 싱글
                if(i-j*1>=0){
                    if(dp[i][0]>dp[i-j*1][0]+1){
                        dp[i][0]=dp[i-j*1][0]+1;
                        dp[i][1]=dp[i-j*1][1]+1;
                    }else if(dp[i][0]==dp[i-j*1][0]+1){
                        dp[i][1]=Math.max(dp[i][1],dp[i-j*1][1]+1);
                    }
                }
            }
        }
        return new int[] {dp[target][0],dp[target][1]};
    }
    
}
