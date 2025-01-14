//가로 세로 좌표가 어떻게 이루어져 있는지를 제대로 확인해서 실수가 없도록 하자
//내가 작성 했던 방법은 [세로][가로] 이지만 문제에서는 [가로][세로] 였기에 계속 

#include <string>
#include <vector>
#include<iostream>
#define MOD 1000000007

using namespace std;

int solution(int m, int n, vector<vector<int>> puddles) {
    bool map[111][111];
    int dp[111][111];
    
    for(int i=0;i<puddles.size();i++){
        map[puddles[i][1]][puddles[i][0]]=1;
    }
    dp[1][1]=1;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(map[i][j]==0){
                if(i-1>=1){
                    dp[i][j]+=dp[i-1][j];
                    dp[i][j]%=MOD;
                }
                if(j-1>=1){
                    dp[i][j]+=dp[i][j-1];
                    dp[i][j]%=MOD;
                }
            }
        }
    }
    return dp[n][m]%MOD;
}
