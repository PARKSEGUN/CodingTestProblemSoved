n개의 수로 s 를 만들 때 n개의 수의 곱이 최대가 되게 하려면 n개의 수가 최대한 비슷해야함
비슷하게 만들기위해서 s/n를 구하고 하나의 값을 구했기때문에 s에서 s/n을 빼주고 n-- 를해주면
이번에는 n-- 개의 수로 s-s/n 이 합이 되는 수를 찾게되면된다 이식을 반복해주면된다.

#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    vector<int> answer;
    if(n>s){answer.push_back(-1);
           return answer;}
    while(s%n!=0){
        answer.push_back(s/n);
        s=s-s/n;
        n--;
    }
    for(int i=0;i<n;i++){
        answer.push_back(s/n);
    }
    return answer;
}
