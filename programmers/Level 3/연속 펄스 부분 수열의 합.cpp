dp1[i]=max(dp1[i-1]+pulse1[i],(long long)pulse1[i]) 이 점화식을 유추하는것까지는 했지만 왜 시작이 -1인 펄스와 1 인 펄스를 구별해야되는지 해결하지못해서 오래걸렸다
  -1로 시작하는 펄스의 연속하는 부분의 합의 절대값에대한 최댓값이 1로 시작하는 펄스의 연속하는 부분의 합과 같다고 생각했다.
  하지만 dp 배열에 저장해야하는 값에 -1로 시작하는 펄스를 넣어줄지 1로 시작하는 펄스를 곱해서 넣어줄지에 따라 값이 달라지기때문에 달라진다고 생각한다


#include <string>
#include <vector>
#include <iostream>
#include <cmath>
#include <algorithm>
#include <climits>

using namespace std;

long long solution(vector<int> sequence) {
    long long answer = LLONG_MIN;
    vector<int> pulse1;
    vector<int> pulse2;
    vector<long long> dp1(sequence.size());
    vector<long long> dp2(sequence.size());
    for (int i = 0; i < sequence.size(); i++) {
        pulse1.push_back((i % 2 == 0) ? sequence[i]*1 : sequence[i]*-1);
        pulse2.push_back((i % 2 == 0) ? sequence[i]*-1 :sequence[i]*1);
    }
    answer=dp1[0]=pulse1[0];
    answer=dp2[0]=pulse2[0];
    for (int i = 1; i < sequence.size(); i++) {
        dp1[i]=max(dp1[i-1]+pulse1[i],(long long)pulse1[i]);
        dp2[i]=max(dp2[i-1]+pulse2[i],(long long)pulse2[i]);
        answer = max(dp1[i], answer);
        answer = max(dp2[i], answer);
    }
    return answer;
}
