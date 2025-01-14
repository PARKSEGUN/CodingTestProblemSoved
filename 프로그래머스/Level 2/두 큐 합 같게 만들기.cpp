처음에 모든 경우의수를 확인해봐야하는지알고 DFS로 해결하다가 시간초과
경우의 수는 앞에 수를 빼거나 뒤에 수를 넣어주는 두가지 
주어진 벡터의 모든 합의/2 보다 값이 작다면 뒤에를 넣어주고 크다면 앞에 수를 빼주면서 합의/2 이 되는 값을 찾는다
이때 주어진 벡터를 큐에 넣어주고 해결하면 pop, push 과정을 효율적으로 해결할 수 있었다

#include <string>
#include <vector>
#include<queue>
#include<algorithm>
#include<iostream>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    long long answer = 0;
    long long allSum=0;
    long long sum=0;
    queue<int> q1;
    queue<int> q2;
    
    for(int i=0;i<queue1.size();i++){
        allSum+=queue1[i];
        sum+=queue1[i];
        q1.push(queue1[i]);
    }
    for(int i=0;i<queue1.size();i++){
        q2.push(queue2[i]);
        allSum+=queue2[i];
    }
    allSum/=2;
    
    while(1){
        if(answer>=queue1.size()*3){return -1;}
        if(sum==allSum){return answer;}
        else if(sum>allSum){
            sum-=q1.front();
            q2.push(q1.front());
            q1.pop();
            answer++;
        }
        else if(sum<allSum){
            sum+=q2.front();
            q1.push(q2.front());
            q2.pop();
            answer++;
        }
    }
}
