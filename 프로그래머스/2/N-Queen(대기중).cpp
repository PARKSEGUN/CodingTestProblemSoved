백트래킹을 사용해서 모든경우를 확인했다.
퀸이 들어오지못하는 조건은 이전에 놓았던 퀸의 위치들과 x좌표 값이 같으면 안되고 대각선에 위치해서도 안된다
대각선에 위치하면안된다는 조건을 해결하기위해서는
-> 현재 탐색하고있는좌표(x2,y2)와 이전에 놓았던 퀸의 좌표(x1,y1)의 x1-x2 의 절대값과 y1-y2의 절대값이 같으면 안된다.

#include <string>
#include <vector>
#include <cmath>

using namespace std;

int answer = 0;
int arr[15] = { -1, };

void backTracking(int floor, int n) {
    if (floor == n) {
        answer++;
        return;
    }
    for (int i = 0; i < n; i++) {
        bool error = false;
        for (int j = 0; j < floor; j++) {
            if (arr[j] == i || abs(j - floor) == abs(arr[j] - i)) {
                error = true;
                break;
            }
        }
        if (error == false) {
            arr[floor] = i;
            backTracking(floor + 1, n);
        }
    }
}

int solution(int n) {
    backTracking(0, n);
    return answer;
}
