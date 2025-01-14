타입캐스팅에대해 다시 알게되었다 int i 가 있을때 i*i < x 이런식으로 조건식을 넣게되면 i*i는 깨지게 되기때문에 타입 캐스팅 즉 (long long)i*i 이렇게 작성해준다

#include <string>
#include <vector>
#include <iostream>

using namespace std;


long long solution(int k, int d) {
    long long j = (d / k) * k;
    long long answer = 0;
    for (long long i = 0; i <= d; i += k) {
        for (; j >= 0; j -= k) {
            if ((long long)i * i + (long long)j * j <= (long long)d * d) {
                answer += (j / k + 1);
                break;
            }
        }
    }
    return answer;
}
