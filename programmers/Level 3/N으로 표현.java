// N이라는 수와 사칙연산으로 만들 수 있는 모든 수를 dp 배열에 저장한다
// dp[i] -> N을 i개 사용했을때에 나올 수 있는 값들을 담은 리스트
// 그렇다면 dp[2]는 dp[1]과 dp[1]을 사칙연산하여 만들 수 있는 모든 값을 넣어주면된다
// 즉, 반복문(j)를 돌린다면 dp[i]= dp[j] (사칙연산) dp[i-j] 한 모든 값을 dp[i] 에 넣어주면된다
// Set 자료구조를 사용한다면 더 효율적으로 원하는 값을 찾는것이 가능
// 주의할점은 N이라는 수를 이어붙이는 행동도 가능하기에 dp[4] 이면 NNNN 이라는 수도 넣어주어야한다.

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp=new Set[9];
        for(int i=0;i<9;i++){
            dp[i]=new HashSet<Integer>();
        }
        dp[1].add(N);
        for(int i=2;i<9;i++){
            dp[i].add(makeNN(i,N));
            for(int j=1;j<i;j++){
                for(int x : dp[j]){
                    for(int y : dp[i-j]){
                        dp[i].add(x*y);
                        if(x-y>0) dp[i].add(x-y);
                        if(y!=0) dp[i].add(x/y);
                        dp[i].add(x+y);
                    }
                }
            }
        }
        
        
        for(int i=1;i<9;i++){
            // System.out.println(i+" : "+dp[i]);
            for(int x : dp[i]){
                if(x==number){
                    return i;
                }
            }
        }
        return -1;
    }
    private int makeNN(int cnt,int N){
        StringBuilder sb=new StringBuilder();
        for(int k=0;k<cnt;k++){
            sb.append(N);
        }
        return Integer.parseInt(sb.toString());
    }
}
