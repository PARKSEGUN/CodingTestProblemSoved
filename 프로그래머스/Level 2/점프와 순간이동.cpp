순간이동이 현재 위치 *2만큼 가는 것이기때문에 2씩 나누어주면서 해결했다.

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
