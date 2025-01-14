주어진 배열을 최대한 비슷하게 만들어줘야겠다고 생각
주어진 배열의 합을 구하고 n을 빼준뒤 주어진 배열의 size 로 나누게 되면 평균이 나옴
그 평균에 비슷하게 배열을 맞추기위해 내림차순 정렬을 시킨뒤에 큰값의 값을 평균이 될때까지 빼준다
이때 큰값을 빼다가 그다음값보다 작아지게 되는 경우때문에 우선순위 큐 사용해서 해결

#include <string>
#include <vector>
#include<algorithm>
#include<iostream>
#include<queue>

using namespace std;

long long solution(int n, vector<int> works) {
    long long answer = 0;
    long long workSum=0;
    for(auto x:works)
        workSum+=x;

    if(n>=workSum)
        return 0;

   priority_queue<int> pq;
    for(auto x:works)
        pq.push(x);

    for(int i=0;i<n;i++){
        long long tmp= pq.top();
        pq.pop();
        pq.push(tmp-1);    
    }

    while(pq.size()!=0)
    {
        answer+=pq.top()*pq.top();
        pq.pop();
    }


    return answer;
}
