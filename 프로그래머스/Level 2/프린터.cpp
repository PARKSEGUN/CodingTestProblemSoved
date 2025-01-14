우선순위와 문서번호의 큐를 각각 한개씩만들어주고 현재보다 뒤에 우선순위가 큰 값이 존재하는지를 판별하는 함수를 하나 만들어줘서 

#include <string>
#include <vector>
#include<queue>

using namespace std;

bool bigger(queue<int> q){
    int x=q.front();
    q.pop();
    while(q.size()!=0){
        if(x<q.front()){return true;}
        q.pop();
    }
    return false;
}
int solution(vector<int> priorities, int location) {
    int answer = 0;
    queue<int> q;
    queue<int> docu;
    for(int i=0;i<priorities.size();i++){
        q.push(priorities[i]);
        docu.push(i);
    }
    int cnt=1;
    while(q.size()!=0){
        if(bigger(q)==false){
            if(docu.front()==location){return cnt;}    
            q.pop();
            docu.pop();
            cnt++;
        }
        else{
            int x=q.front();
            int d=docu.front();
            q.pop();
            q.push(x);
            docu.pop();
            docu.push(d);
        }
    }
    
    return answer;
}
