//블로그 참고해서 해결(https://mungto.tistory.com/15)

//하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
//라는 제한 사항을 확인하지 못했다.
//걸린 시간의 평균을 가장 줄이는 방법은 시간이라는 변수를 지정해주고 그 변수를 증가시키면서 작업들이 들어올 수 있는지를 확인한다.
//들어온 작업들을 수행되는 시간이 짧은 순서대로 처리되어야하기때문에 우선순위큐를 사용해서 수행되는 시간이 짧은 순서대로 pop() 해준다
//시간과 처리되어야하는 작업을 제대로 알고 있다면 해결할 수 있는 문제였다.


#include <string>
#include <vector>
#include<queue>
#include<iostream>
#include<algorithm>

using namespace std;

struct cmp{
  bool operator()(vector<int> a,vector<int> b){
      return a[1]>b[1];
  }  
};

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    priority_queue<vector<int>,vector<vector<int>>,cmp> pq;
    int time=0; //현재 시간
    int cnt=0;  //처리한 일의 개수
    sort(jobs.begin(),jobs.end());
    while(cnt<jobs.size()||pq.size()!=0){
        if(cnt<jobs.size()&&time>=jobs[cnt][0]){
            pq.push(jobs[cnt]);
            cnt++;
        }
        else if(pq.size()!=0){
            time+=pq.top()[1];
            answer+=(time-pq.top()[0]);
            pq.pop();
        }
        else{
            time=jobs[cnt][0];
        }
    }
    return answer/jobs.size();
}
