우선순위큐를 사용해서 쉽게 접근할 수 있는 문제이다.
우선순위큐에 넣는 방법에 vector의 begin()과 end() 를 사용해서 만들 수 있다는 점이 새

#include <string>
#include <vector>
#include<queue>


using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
    priority_queue<int,vector<int>,greater<int>> pq(scoville.begin(),scoville.end());

    while(pq.top()<K){
        if(pq.size()==1){return -1;}
        int x=pq.top();pq.pop();
        int y=pq.top();pq.pop();
        pq.push(x+y*2);
        answer++;
    }
    return answer;
}
