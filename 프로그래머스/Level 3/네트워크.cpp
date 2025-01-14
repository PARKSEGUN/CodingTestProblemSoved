//정점간에 서로 연결되어있는 구조를 2차원배열에 옮겨주어야 하는 과정은 computers로 이미 주어진다.
//visited 배열을 사용해서 방문 여부를 조사하면서 서로 연결되어있는 네트워크의 개수를 구한다.

#include <string>
#include <vector>

using namespace std;

bool visited[222];

void DFS(int n,int x,vector<vector<int>> computers){
    for(int i=0;i<n;i++){
        if(visited[i]==false&&computers[x][i]==1){  //방문하지 않고 x와 연결되어있다면 
            visited[i]=true;    //방문 체크를 해주고
            DFS(n,i,computers); //DFS에 i정점으로 다시넣어준다
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    for(int i=0;i<n;i++){
        if(visited[i]==false){  //방문하지 않았다면
            answer++;   //네트워크 개수 1추가
            visited[i]=true;    //방문 체크
            DFS(n,i,computers); //i번째와 연결되어있는 정점 확인하는 DFS함수
        }
    }
    return answer;
}
