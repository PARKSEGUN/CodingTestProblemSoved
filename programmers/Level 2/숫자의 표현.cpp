//시작 위치와 끝의 위치를 지정해준다
//현재 점수가 작다면 끝을 하나씩올려주면서 더해주고 현재 점수가 크다면 시작 위치를 빼주고 하나를 올려준다.
//같다면 끝을 answer++ 해주고 끝을 하나 올려주고 더해준다.

#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;    int sum = 0;    int start = 1;    int end = 1;
    while (start <= n) {
        if (sum == n) { answer++; sum += end;end++; }
        else if (sum > n) {
            sum -= start;
            start++;
        }
        else if (sum < n) { sum += end; end++; }
    }
    return answer;
}
