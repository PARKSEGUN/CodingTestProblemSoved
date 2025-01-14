점화식과 DP를 사용해서 효율적으로 작성하였다.

#include <string>
#include <vector>

using namespace std;

long long dp[2222];

long long solution(int n) {
    dp[0]=0;dp[1]=1;dp[2]=2;
    for(int i=3;i<=n;i++){
        dp[i]=dp[i-2]+dp[i-1];
        dp[i]%=1234567;
    }
    return dp[n];
}
