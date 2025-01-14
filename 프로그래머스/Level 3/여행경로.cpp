DFS로 모든 경우의수를 탐색하기로 정하고 티켓의 구조를 인접행렬로 만들어주기위해 map구조를 사용 ex) ICN->SFO,ATL, ATL->ICN,SFO
답을 알파벳 순으로 나오게하기위해서 정렬을 인접행렬을 오름차순으로 정렬
ICN이 항상 시작점이기때문에 ICN을 출발점으로 DFS로 모든 경우 탐색
사용한 티켓을 다시 사용하는 반례 발생해서 map 구조에 bool 변수 추가

#include <string>
#include <vector>
#include<unordered_map>
#include<algorithm>
#include<iostream>

using namespace std;
string str[11111];
bool chk;   //답을 찾았는지 확인하는 변수
int N;  //티켓수

void DFS(string cur,int cnt,unordered_map<string,vector<pair<string,bool>>>& um){
    if(chk==true){return;}
    str[cnt]=cur;
    if(cnt==N){chk=true;return;}

    for(int i=0;i<um[cur].size();i++){
        if(um[cur][i].second==false){
            um[cur][i].second=true;
            DFS(um[cur][i].first,cnt+1,um);
            um[cur][i].second=false;
        }
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    vector<string> answer;
    unordered_map<string,vector<pair<string,bool>>> um;  //티켓사용여부를 조사하기위한 bool 변수 추가
    N=tickets.size();
    //티켓 정보를 map 에 저장
    for(int i=0;i<tickets.size();i++){
            um[tickets[i][0]].push_back({tickets[i][1],false});
    }
    //알파벳 순으로 return 하기위한 정렬
    for(auto x:um){
        sort(um[x.first].begin(),um[x.first].end());
    }
    //ICN을 시작점으로 갈수있는 경우 DFS로 탐색
    str[0]="ICN";
    for(int i=0;i<um["ICN"].size();i++){
        um["ICN"][i].second=true;
         DFS(um["ICN"][i].first,1,um);
        um["ICN"][i].second=false;

    }
    //답을 리턴하기위함
    for(int i=0;i<=N;i++){
        answer.push_back(str[i]);
    }
    return answer;
}
