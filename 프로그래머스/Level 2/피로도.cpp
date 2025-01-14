DFS로 남은 피로도를 넘겨주면서 순환구조를 사용 쉽게 해결함

#include <string>
#include <vector>
#include<algorithm>

using namespace std;

int result;
bool visited[11];

void DFS(int cnt,int hp,vector<vector<int>> dungeons){
    result=max(result,cnt);
    for(int i=0;i<dungeons.size();i++){
        if(dungeons[i][0]<=hp&&visited[i]==false){
            visited[i]=true;
            DFS(cnt+1,hp-dungeons[i][1],dungeons);
            visited[i]=false;
        }
    }
    return;
}

int solution(int k, vector<vector<int>> dungeons) {
    DFS(0,k,dungeons);
    return result;
}
