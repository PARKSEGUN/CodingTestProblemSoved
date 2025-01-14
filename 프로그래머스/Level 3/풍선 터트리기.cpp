문제 해결하기위해서는 현재 탐색하고있는 풍선의 왼쪽에있는 모든 풍선보다 작거나 또는 오른쪽에있는 모든 풍선보다 작거나 해야 answer++ 이 성립됩니다.
오른쪽의 최솟값을 확인하기위해서 priorityQueue를 사용했고 오른쪽에 있는 풍선들중에 이미 탐색이 끝난 부분을 확인하기위해서
unordered_map을 사용했습니다

#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

int solution(vector<int> a) {
    priority_queue<int,vector<int>,greater<int>> pq;
    int answer =1;
    int leftMin=1111111111;
    unordered_map<int,bool> um;

    for(int i=0;i<a.size();i++){
        pq.push(a[i]);
    }
    for(int i=0;i<a.size()-1;i++){
        um[a[i]]=true;
        while(um[pq.top()]==true){
            pq.pop();
        }
        if(a[i]<leftMin||a[i]<pq.top()){
            answer++;
        }
        leftMin=min(leftMin,a[i]);
    }
    return answer;
}
