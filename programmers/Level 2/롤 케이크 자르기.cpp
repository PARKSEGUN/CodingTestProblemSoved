#include <string>
#include <vector>

using namespace std;

int leftCnt[11111];
int leftSum;
int rightCnt[11111];
int rightSum;

int solution(vector<int> topping) {
    int answer = 0;
    //모든 토핑종류의 개수, 각각의 토핑의 개수를 파악(오른쪽 구역)
    for (auto x : topping) {
        if (rightCnt[x] == 0)
            rightSum++;
        rightCnt[x]++;
    }
    for (auto x : topping) {
        //x값을 기준으로 x를 포함해서 왼쪽에 있는 값들(왼쪽 구역)
        if (leftCnt[x] == 0)
            leftSum++;
        leftCnt[x]++;
        //오른쪽 구역에서 현재 탐색하고있는 x를 제거
        if (rightCnt[x] == 1)
            rightSum--;
        rightCnt[x]--;
        //제거후 같은지 비교
        if (leftSum == rightSum)
            answer++;
    }
    return answer;
}
