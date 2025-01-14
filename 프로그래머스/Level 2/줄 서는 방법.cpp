visited변수를 사용해서 이전에 사용한 값을 사용하지 않도록 하기 위해서 설정하려고했지만 코드가 너무 복잡해줘서
num 이라는 vector를 사용하고  erase 라는 함수를 사용해서 간단하게 해결
sum이 k 보다 큰지 작은지에 따라 조건을 걸어주려 했지만 결국 없어도 같은 값을 반환

#include <string>
#include <vector>
#include<iostream>

using namespace std;

vector<int> solution(int n, long long k) {
    vector<int> answer;
    vector<int> num;

    for (int i = 0; i < n; i++)
        num.push_back(i + 1);
    long long sum = 1;

    for (int i = 1; i <= n; i++)
        sum *= i;
    k--;
    while (n != 0) {
        sum /= n;
        long long x = k / sum;
        answer.push_back(num[x]);
        num.erase(num.begin() + x);
        k = k % sum;
        n--;
    }
    return answer;
}
