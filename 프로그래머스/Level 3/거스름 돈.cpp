동적계획법을 사용해서 해결
5원으로 x 원을 만들수 있는 경우의 수= x-5 원을 만들수있는 경우의수
dp[x]=dp[x-5]
2, 5 원이 있다면
dp[x]+=dp[x-2]
dp[x]+=dp[x-5]
  
#include <string>
#include <vector>
#include <iostream>

using namespace std;

int dp[111111];

int solution(int n, vector<int> money) {
    for (int j = 0; j < money.size(); j++) {
        dp[money[j]] += 1;
        for (int i = 0; i <= n; i++)
            if (i - money[j] >= 0)
                dp = (dp[i] + dp[i - money[j]] % 1000000007) % 1000000007;

    }
    return dp[n];
}
