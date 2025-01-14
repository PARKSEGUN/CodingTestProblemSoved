주어진 x 에 n을 더하기, 2곱하기, 3 곱하기 총 3개의 연산이 가능해서
3개의 연산을 각각해주는 BFS를 사용해서 해결하려 했지만 시간초과 발생
최적해를 찾기위해서는 BFS를 사용하는 것이 맞다고 생각해서 시간을 줄일 수 있는 방법을 생각
일반적으로 BFS를 사용해서 3개의 연산을 모두 push 하겠지만 반대로 y를 x 로 만들 수 있는지를 생각한다면
3으로 나눠지는지 2로 나눠지는지를 확인하고 나눠지지 않는다면 push 하지 않기때문에 경우의 수가 확연히 줄어든다

#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int x, int y, int n) {
    if (x == y)
        return 0;
    queue<pair<int, int>> q;
    q.push({ y,0 });
    while (q.size() != 0) {
        int tmp = q.front().first;
        int cnt = q.front().second;
        q.pop();
        if (tmp - n >= x) {
            if (tmp - n == x)
                return cnt + 1;
            q.push({ tmp - n,cnt + 1 });
        }
        if (tmp % 2 == 0 && tmp / 2 >= x) {
            if (tmp / 2 == x)
                return cnt + 1;
            q.push({ tmp / 2,cnt + 1 });
        }
        if (tmp % 3 == 0 && tmp / 3 >= x) {
            if (tmp / 3 == x)
                return cnt + 1;
            q.push({ tmp / 3,cnt + 1 });
        }
    }
    return -1;
}
