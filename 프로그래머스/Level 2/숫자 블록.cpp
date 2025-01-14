브루트 포스를 사용해서 모든 경우를 확인한다면 시간 초과가 발생할 거라고 생각했다.
인덱스로 해당하는 값을 알 수 있다면 해결할 수 있다고 생각했다.
따라서 mod 연산을 통해서 2부터 시작해서 나눠지는 수가 있다면 return 해주는 indexToVal() 이라는 함수를 구현
-> 시간 초과 발생
인덱스와 해당 블록의 수의 관계를 보다가  소수를 생각했다.
2를 거쳤다면 4, 6, 8, 10, 12 .... 를 확인할 필요가 없었다.
탐색하고있는 인덱스보다 작은 소수들로만 나눌 수 있다면 시간을 줄일 수 있다고 생각했지만 수의 범위가 10억이기때문에 배열 인덱스를 사용하는 에라토스테네스 체 알고리즘 사용 불가
에라토스테네스의 체 알고리즘을 따와서 주어진 인덱스의 제곱근 까지만 탐색 -> 시간초과 해결.
블록의 수는 10000000이 넘으면 안된다는 문제 확인을 제대로 못해서 반례 발생
index / x <= 10000000 코드를 추가했지만 반례 발생
index/x 가 모두 10000000 을 넘는다면 결국 1을 리턴하는 것이아닌 이전까지에 x 중에 가장 큰 값을 리턴해한다.
-> x 로 나눠서 해당하는 블록의 수(y)를 찾았다면 y를 기준으로는 x가 블록의 수가 될수 있기때문
x를 vec에 넣어서 가장 큰수 리턴하는 코드 추가 -> 해결




#include <string>
#include <vector>
#include <iostream>
#include <cmath>

using namespace std;

long long indexToVal(long long index) {
    if (index == 1) {
        return 0;
    }
    int x = 2;
    vector<int> vec;
    while (x <= sqrt(index)) {
        if (index % x == 0) {
            vec.push_back(x);
            if (index / x <= 10000000) {
                return index / x;
            }
        }
        x++;
    }
    if (vec.size() != 0) {
        return vec.back();
    }
    return 1;
}

vector<int> solution(long long begin, long long end) {
    vector<int> answer;
    for (long long i = begin; i <= end; i++) {
        answer.push_back(indexToVal(i));
    }
    return answer;
}
