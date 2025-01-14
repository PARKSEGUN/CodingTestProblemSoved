주어진 weight의 무게들을 count 해주고 weight를 탐색하면서 현재 탐색하고있는 weight 값에서 나올 수 있는 모든 경우 *1,*2,/2*3,/3*2,/3*4,/4*3,/2 의 경우를 모두 확인해서 answer에 추가해준다

#include <string>
#include <vector>
#include <iostream>

using namespace std;

long long countMap[2222];

long long solution(vector<int> weights) {
    long long answer = 0;
    for(int i=0;i<weights.size();i++){
        countMap[weights[i]]++;
    }
    
    for(int i=0;i<weights.size();i++){
        int x=weights[i];
        countMap[x]--;
        answer+=countMap[x];
        answer+=countMap[x*2];
        if(x%2==0){
            answer+=countMap[x/2];
            answer+=countMap[x/2*3];
        }
        if(x%3==0){
            answer+=countMap[x/3*4];
            answer+=countMap[x/3*2];
        }
        if(x%4==0){
            answer+=countMap[x/4*3];
        }
    }

    return answer;
}
