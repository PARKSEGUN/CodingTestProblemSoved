다이나믹 프로그래밍 알고리즘을 사용해서 효율적으로 해결했다
현재 탐색하고있는 부분까지의 합은 [i-2],[i-3]중 최댓값을 이용하는 점화식사용

#include <vector>
#include<algorithm>
#include<iostream>

using namespace std;

int dp[111111];

int solution(vector<int> sticker)
{
    int answer = 0;
    if (sticker.size() <= 3) {
        for (int i = 0; i < sticker.size(); i++)
            answer = max(answer, sticker[i]);
        return answer;
    }

    dp[0] = sticker[0];
    dp[1] = sticker[1];
    dp[2] = sticker[0] + sticker[2];

    for (int i = 3; i < sticker.size() - 1; i++) {
        dp[i] = max(sticker[i] + dp[i - 2], sticker[i] + dp[i - 3]);
        answer = max(answer, dp[i]);
    }

    dp[2] = sticker[2];
    dp[3] = sticker[1] + sticker[3];

    for (int i = 4; i < sticker.size(); i++) {
        dp[i] = max(sticker[i] + dp[i - 2], sticker[i] + dp[i - 3]);
        answer = max(answer, dp[i]);
    }
    return answer;
}
